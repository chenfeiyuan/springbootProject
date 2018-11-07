package org.consumer.feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="service-cfy",fallback = HServiceHystrixClientFallback.class)
public interface HService {
	
	@RequestMapping("/hi")
	String home(@RequestParam(value = "name") String name);
	
}
