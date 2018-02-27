package com.dou.consumer.web;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;

import java.net.URI;

/**
 * 负载均衡测试
 */
@RestController
public class HelloController {

    protected Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    @Qualifier(value = "restTemplate")
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier(value = "lbcRestTemplate")
    private RestTemplate lbcRestTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return restTemplate.getForEntity("http://DEMO-SERVICE/demo-service/hello/index", String.class).getBody();
    }

    /**
     * 在helloEx方法中不能够使用之前自动织入的restTemplate，否则会报以下错误:
     * java.lang.IllegalStateException: No instances available for xxxxx
     * 使用lbcRestTemplate或new RestTemplate()
     * @return
     */
    @RequestMapping(value = "/helloEx", method = RequestMethod.GET)
    public String helloEx() {
        ServiceInstance instance = this.loadBalancerClient.choose("DEMO-SERVICE");
        URI helloUri = URI.create(String.format("http://%s:%s/demo-service/hello/index", instance.getHost(), instance.getPort()));
        logger.info("Target service uri = {}. ", helloUri.toString());
        // return restTemplate.getForEntity(helloUri, String.class).getBody();
        // return new RestTemplate().getForEntity(helloUri, String.class).getBody();
        return this.lbcRestTemplate.getForEntity(helloUri, String.class).getBody();
    }

}