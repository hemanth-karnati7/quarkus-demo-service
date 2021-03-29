package com.delta.rm.resource;

import com.delta.rm.service.ExternalService;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Path("/external")
public class ExternalResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalResource.class);

    @Inject
    ExternalService service;

    private AtomicLong counter = new AtomicLong(0);

    @GET
    @Retry(maxRetries = 4)
    @CircuitBreaker(delay = 5000, requestVolumeThreshold = 4, failureRatio = 0.5)
    public String getMessageFromExternalApiRetries() {
        final Long invocationNumber = counter.getAndIncrement();
        mayBeFail(invocationNumber);
        LOGGER.info("getMessageFromExternalApiRetries invocation {} returning successfully", invocationNumber);
        return service.invokeExternalApi();
    }

    @GET
    @Path("/timeout")
    @Timeout(250)
    @Fallback(fallbackMethod = "fallbackMethod")
    public String getMessageFromExternalApiTimeOut() {
        long started = System.currentTimeMillis();
        final Long invocationNumber = counter.getAndIncrement();
        try {
            randomDelay();
            LOGGER.info("getMessageFromExternalApiTimeOut invocation {} returning successfully", invocationNumber);
            return service.invokeExternalApi();
        } catch (InterruptedException e) {
            LOGGER.error("getMessageFromExternalApiTimeOut invocation {} failed", invocationNumber,
                    invocationNumber, System.currentTimeMillis() - started);
            return null;
        }
    }

    public String fallbackMethod() {
        LOGGER.info("Falling back to getMessageFromExternalApiTimeOut()");
        return "Fallback invoked";
    }

    private void mayBeFail(Long invocationNumber) {
        if (new Random().nextBoolean()) {
            LOGGER.error("getMessageFromExternalApiRetries invocation {} failed", invocationNumber);
            throw new RuntimeException("Resource failure.");
        }
    }

    private void randomDelay() throws InterruptedException {
        Thread.sleep(new Random().nextInt(500));
    }

}
