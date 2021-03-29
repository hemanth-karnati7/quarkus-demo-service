package com.delta.rm.service;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class ExternalService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalService.class);
    private AtomicLong counter = new AtomicLong(0);

    @Retry(maxRetries = 4)
    @CircuitBreaker(delay = 5000, requestVolumeThreshold = 4, failureRatio = 0.5)
    public String invokeExternalApiRetries() {
        final Long invocationNumber = counter.getAndIncrement();
        mayBeFail(invocationNumber);
        LOGGER.info("invokeExternalApiRetries invocation {} returning successfully", invocationNumber);
        return "Message from external system#invokeExternalApiRetries";
    }

    @Timeout(250)
    @Fallback(fallbackMethod = "fallbackMethod")
    public String invokeExternalApiTimeOut() {
        long started = System.currentTimeMillis();
        final Long invocationNumber = counter.getAndIncrement();
        try {
            randomDelay();
            LOGGER.info("invokeExternalApiTimeOut invocation {} returning successfully", invocationNumber);
            return "Message from external system#invokeExternalApiTimeOut";
        } catch (InterruptedException e) {
            LOGGER.error("invokeExternalApiTimeOut invocation {} failed and timed out after {}", invocationNumber, System.currentTimeMillis() - started);
            return null;
        }
    }

    public String fallbackMethod() {
        LOGGER.info("Falling back to invokeExternalApiTimeOut()");
        return "Fallback invoked";
    }

    private void mayBeFail(Long invocationNumber) {
        if (new Random().nextBoolean()) {
            LOGGER.error("invokeExternalApiRetries invocation {} failed", invocationNumber);
            throw new RuntimeException("Resource failure.");
        }
    }

    private void randomDelay() throws InterruptedException {
        Thread.sleep(new Random().nextInt(500));
    }
}
