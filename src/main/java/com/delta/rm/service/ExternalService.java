package com.delta.rm.service;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class ExternalService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalService.class);

    @ConfigProperty(name = "retail.offer.api")
    String retailOfferApi;

    private AtomicLong counter = new AtomicLong(0);

    private Client client = ClientBuilder.newClient();

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

    public String invokeRetailOfferApi(String offerId) {
        return client
                .target(retailOfferApi+offerId)
                .request(MediaType.APPLICATION_JSON)
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .header("transactionId", UUID.randomUUID().toString())
                .get(String.class);
    }
}
