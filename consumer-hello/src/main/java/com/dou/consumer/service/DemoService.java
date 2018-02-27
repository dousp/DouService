package com.dou.consumer.service;

import com.dou.demo.entity.TimeSheet;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name ="DEMO-SERVICE", path = "demo-service")
public interface DemoService {

    @RequestMapping(value = "/hello/save", method = RequestMethod.GET)
    String save();

    @RequestMapping(value = "/hello/findAll", method = RequestMethod.GET)
    Iterable<TimeSheet> findAll();
}
