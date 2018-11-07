package org.service.master.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()  
public class ServiceController {
	
	@Value("${server.port}")
	String port;
	
	@GetMapping(value="/find/{id}")  
    public String find(@PathVariable("id") String id,HttpServletRequest request){  
        //实际项目中，这里可以使用JSONObject，返回json字符串  
        //为了便于测试消费者app的负载均衡，返回服务端端口  
        //String s = "张三"+"     服务端端口："+request.getLocalPort();
		String s = "\n I am function executor from service service-cfy.PathParam:"+id+"; Service port:"+port;
          
        return s;  
    }  
	
	@GetMapping(value="/calc/add")  
    public String test(@RequestParam("a") int a,@RequestParam("b") int b,HttpServletRequest request){  
        //实际项目中，这里可以使用JSONObject，返回json字符串  
        //为了便于测试消费者app的负载均衡，返回服务端端口  
		int c = a+b;
        String s = "\n I am function executor from service service-cfy.a+b=" + c + "; Service port:"+port;  
          
        return s;  
    } 
	
	@RequestMapping("/hi")
	public String home(@RequestParam("name") String name) {
		return "\n hi "+name+",I am function executor from service service-cfy.i am from port:" +port;
	}
}
