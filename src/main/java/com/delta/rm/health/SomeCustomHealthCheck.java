package com.delta.rm.health;

import java.util.Random;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

@Readiness
@ApplicationScoped
public class SomeCustomHealthCheck implements HealthCheck {

	@Override
	public HealthCheckResponse call() {
		HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("Downstream system health check");
		
		try {
            downstreamSystemHealthCheck();
            responseBuilder.up();
        } catch (IllegalStateException e) {
            // cannot access the downstream system
            responseBuilder.down();
        }

        return responseBuilder.build();
    }

    private void downstreamSystemHealthCheck() {
        if (new Random().nextBoolean()) {
            throw new IllegalStateException("Cannot contact to downstream");
        }
    }
		
}
