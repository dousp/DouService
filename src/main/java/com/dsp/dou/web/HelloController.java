package com.dsp.dou.web;

import com.dsp.dou.dao.TimeSheetRepository;
import com.dsp.dou.entity.TimeSheet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TimeSheetRepository timeSheetRepository;

    @ApiOperation(value="Hello页面", notes="Hello页面")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
        return "Hello Index!";
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
       return "save ok";
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
