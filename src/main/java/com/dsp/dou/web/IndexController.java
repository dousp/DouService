package com.dsp.dou.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api
@PropertySource("classpath:application.properties")
@RestController
@RequestMapping("/")
public class IndexController {

    @Value("${info.msg}")
    private String appMsg;

    @Value("${info.key}")
    private String appKey;

    @ApiOperation(value="欢迎页面", notes="欢迎页面")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "Hello Index!\n"+ "Msg:" + appMsg + "\n" + "Key:" + appKey;
    }


}
