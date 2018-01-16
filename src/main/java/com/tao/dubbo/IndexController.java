package com.tao.dubbo;

import com.tao.dubbo.mq.RmqConsumer;
import com.tao.dubbo.mq.RmqProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by DELL on 2017/9/25.
 */
@RestController
@RequestMapping("/msg")
public class IndexController {
    @Resource
    private RmqProducer rmqProducer;

    @RequestMapping(value="/send",method = RequestMethod.GET)
    public String send(){
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:dd:ss:SSS");
        String message="消息中间件："+sf.format(new Date());
        rmqProducer.send(message,"test");
        System.out.println("****");

       return "success";
    }
    @RequestMapping(value="/get",method = RequestMethod.GET)
    public String get(){
        String str="{\n" +
                "  \"success\": true,\n" +
                "  \"data\": {\n" +
                "    \"lines\": [\n" +
                "      [\n" +
                "        1.50790476E12,\n" +
                "        99.30597249871,\n" +
                "        99.30597249871,\n" +
                "        99.30597249871,\n" +
                "        99.30597249871,\n" +
                "        66.9905449283\n" +
                "      ]\n" +
                "    ],\n" +
                "    \"trades\": [\n" +
                "      {\n" +
                "        \"amount\": 0.02,\n" +
                "        \"price\": 5798.79,\n" +
                "        \"tid\": 373015085,\n" +
                "        \"time\": 1508136949000,\n" +
                "        \"type\": \"buy\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"depths\": {\n" +
                "      \"asks\": [\n" +
                "        [\n" +
                "          500654.27,\n" +
                "          0.5\n" +
                "        ]\n" +
                "      ],\n" +
                "      \"bids\": [\n" +
                "        [\n" +
                "          5798.79,\n" +
                "          0.013\n" +
                "        ]\n" +
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}";
        return str;
    }
}
