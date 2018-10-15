package com.cerner.service;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTController {

    private String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping(value="/template", method=RequestMethod.PUT)
    public String setTemplate(@RequestBody ServiceTemplate srvTemplate) {
    	template = srvTemplate.getStr();
        return "Done";
    }
    
    @RequestMapping(value="/template", method=RequestMethod.GET)
    public String setTemplateWithParam(@RequestParam(value="template") String srvTemplate) {
    	template = srvTemplate;
        return "Done";
    }
    
}
