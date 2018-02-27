package com.dou.demo.web;

import com.dou.demo.dao.TimeSheetRepository;
import com.dou.demo.entity.TimeSheet;
import com.netflix.appinfo.EurekaInstanceConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// @Api("测试使用")
@Api
@PropertySource("classpath:application.properties")
@RestController
@RequestMapping("/hello")
public class HelloController {

    protected Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private TimeSheetRepository timeSheetRepository;


    @Autowired
    private EurekaInstanceConfig eurekaInstanceConfig;
    @Value("${server.port}")
    private int serverPort = 0;


    @ApiOperation(value="Hello页面", notes="Hello页面")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
        this.logger.info("/hello, instanceId:{}, host:{}", eurekaInstanceConfig.getInstanceId(), eurekaInstanceConfig.getHostName(false));
        return "Hello, Spring Cloud! My port is " + String.valueOf(serverPort);
    }

    /**
     * 保存数据.
     * @return
     */
    @ApiOperation(value="保存数据", notes="保存数据到时间表")
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String save(){
        // 内存数据库操作
       timeSheetRepository.save(new TimeSheet("title1", "content1"));
       timeSheetRepository.save(new TimeSheet("title2", "content2"));
       timeSheetRepository.save(new TimeSheet("title3", "content3"));
       timeSheetRepository.save(new TimeSheet("title4", "content4"));
       timeSheetRepository.save(new TimeSheet("title5", "content5"));
       return "save ok"+String.valueOf(serverPort);
    }

    /**
     * 获取所有数据.
     * @return
     */
    @ApiOperation(value="查询数据", notes="查询时间表所有数据")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Iterable<TimeSheet> findAll(){
        // 内存数据库操作
       return timeSheetRepository.findAll();
    }
}
