package com.rental.rental.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;
import org.springframework.boot.SpringBootVersion;

import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id = "springVersion")
public class CustomActuatorEndpoints {

    @ReadOperation
    public Map<String, String> customEndpoint() {
        Map<String, String> response = new HashMap<>();
        response.put("version", SpringBootVersion.getVersion());
        return response;
    }
}
