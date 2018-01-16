package com.tao.dubbo.mq;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

public class RmqConsumer{

//	public void message(String message) {
//        System.out.println("消费者: " + message);
//	}
	public void rmqConsumeMessage(Object obj) {
		System.out.println("rmq 消费者任务:"+ JSON.toJSONString(obj));
		// TODO 具体的消费策略
	}
}
