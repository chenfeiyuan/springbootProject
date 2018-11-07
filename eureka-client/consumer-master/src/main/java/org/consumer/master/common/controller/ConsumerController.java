package org.consumer.master.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/consumer/resttemplate")
public class ConsumerController {
	
	@Autowired  
    private RestTemplate restTemplate;  
	
	@GetMapping("/calc/add")
    public String add(@RequestParam("a") int a,@RequestParam("b") int b) {  
        //url中对应api提供者的名称，全大写  
//        String s1 = restTemplate.getForEntity("http://servicecfy/service/test?a=2&b=6", String.class).getBody();
        String s = restTemplate.getForObject("http://service-cfy/calc/add?a="+a+"&b="+b,String.class);
        System.out.println(s);
        return "I am request client 'client-cfy'.Function call info:"+s;  
    }  
	@GetMapping(value = "/find/{id}")  
    public String find(@PathVariable("id") String id) {  
        //url中对应api提供者的名称，全大写  
        String s = restTemplate.getForEntity("http://service-cfy/find/"+id, String.class).getBody();  
        return "I am request client 'client-cfy'.Function call info:"+s;  
    }  
      
	@GetMapping(value = "/hi") 
	@HystrixCommand(fallbackMethod = "error")
    public String home(@RequestParam String name) {  
		String s = restTemplate.getForEntity("http://service-cfy/hi?name="+name, String.class).getBody();    
        return "I am request client 'client-cfy'.Function call info:"+s;  
    } 
    /** 
     * 测试下外网，也就是如果域名是外网的，不在eureka注册服务中的，会怎样 
     * @return 
     */  
    @GetMapping(value = "/testbaidu")  
    public String test() {  
        //url中对应api提供者的名称，全大写  
        return restTemplate.getForEntity("http://www.baidu.com/", String.class).getBody();  
    }
    
    
    public String error(String name){
    	return "sorry "+name+",there is someting wrong with the remote server!";
    }
}
