package com.delta.rm.resource;

import com.delta.rm.service.ExternalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/external")
public class ExternalResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalResource.class);

    @Inject
    ExternalService service;

    @GET
    public String getMessageFromExternalApiRetries() {
        return service.invokeExternalApiRetries();
    }

    @GET
    @Path("/timeout")
    public String getMessageFromExternalApiTimeOut() {
        return service.invokeExternalApiTimeOut();
    }

}
