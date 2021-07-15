package com.presentation.webclient.webclient.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "properties")
public class ApiServerProperties {

    @Getter
    private final Map<String, String> server = new HashMap<>();
}
