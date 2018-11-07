package org.consumer.feign.controller;

import org.consumer.feign.service.HService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer/feign")
public class HController {
	
	@Autowired
	HService hservice;

	@RequestMapping("/hi")
	public String home(@RequestParam("name") String name){
		String s = hservice.home(name);
		return "I am request client 'client-feign'.Function call info:" + s;
	}

}
