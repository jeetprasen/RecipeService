package com.recipes.demo.dto;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "custom-health")
public class CustomHealthStatus {

	@ReadOperation
	public Map<String, Object> health() {
		Map<String, Object> details = new LinkedHashMap<>();
		details.put("CustomHealthStatus", "Everything looks good");
//		CustomHealth health = new CustomHealth();
//		health.setHealthDetails(details);
		return details;
	}
}
