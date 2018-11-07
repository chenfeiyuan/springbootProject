package org.consumer.feign.service;

import org.springframework.stereotype.Component;

@Component
public class HServiceHystrixClientFallback implements HService{

	@Override
	public String home(String name) {
		// TODO Auto-generated method stub
		return "sorry,"+name;
	}

}
