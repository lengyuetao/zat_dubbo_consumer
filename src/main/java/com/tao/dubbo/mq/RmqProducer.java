package com.tao.dubbo.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RmqProducer {
	@Resource
	private RabbitTemplate rabbitTemplate;

	/**
	 * 发送消息
	 * @param message
	 * @param mqQueue
	 */
	public void send(String message,String mqQueue){
		rabbitTemplate.convertAndSend(mqQueue,message);
	}
}
