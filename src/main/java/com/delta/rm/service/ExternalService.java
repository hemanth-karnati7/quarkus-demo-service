package com.delta.rm.service;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExternalService {
    public String invokeExternalApi() {
        return "Message from external system";
    }
}
