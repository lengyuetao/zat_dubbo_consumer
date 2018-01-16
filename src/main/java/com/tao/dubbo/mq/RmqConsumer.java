package com.tao.dubbo.mq;

import org.springframework.stereotype.Component;

public class RmqConsumer{

	public void message(String message) {
        System.out.println("消费者: " + message);
	}
}
