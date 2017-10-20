package com.dsp.demoms.web;

import com.dsp.demoms.dao.TimeSheetRepository;
import com.dsp.demoms.entity.TimeSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PropertySource("classpath:application.properties")
@RestController
public class HelloController {

    @Autowired
    private TimeSheetRepository timeSheetRepository;

    @Value("${info.msg}")
    private String appMsg;

    @Value("${info.key}")
    private String appKey;

    @RequestMapping("/hello")
    public String index(){
        return "Hello Dou!\n"+ "Msg:" + appMsg + "\n" + "Key:" + appKey;
    }

    /**
     * 保存数据.
     * @return
     */
    @RequestMapping("/save")
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
    @RequestMapping("/findAll")
    public Iterable<TimeSheet> findAll(){
        // 内存数据库操作
       return timeSheetRepository.findAll();
    }
}
