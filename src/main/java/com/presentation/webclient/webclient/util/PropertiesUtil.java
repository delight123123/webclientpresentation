package com.presentation.webclient.webclient.util;

import com.presentation.webclient.webclient.config.ApiServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PropertiesUtil {

	private ApiServerProperties apiServerProperties;
	public PropertiesUtil(ApiServerProperties apiServerProperties) {
		this.apiServerProperties = apiServerProperties;
	}

	private static Map<String,String> propertiesMap = new HashMap<>();

	@Bean
	private void putProperties() {
		propertiesMap.putAll(apiServerProperties.getServer());
	}

	public static String getProperty(String name) {
		return propertiesMap.get(name);
	}
}
