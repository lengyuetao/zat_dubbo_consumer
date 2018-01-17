package com.tao.dubbo.mq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Configuration
public class RabbitConfig {
	//队列
	private String mqQueue="test";

	private String userName="guest";
	private String password="guest";
	private String host="123.207.220.205";
	//虚拟主机，默认/
	private String vhost="/";
	//交换机
	private String exchange="direct";

	private boolean durable=true;
	private boolean exclusive=false;
	private boolean autoDelete=false;

	//创建链接
	@Bean
    public ConnectionFactory connectionFactory() {
	  CachingConnectionFactory connectionFactory = new CachingConnectionFactory(this.host);
      connectionFactory.setUsername(this.userName);
      connectionFactory.setPassword(this.password);
      connectionFactory.setPort(AMQP.PROTOCOL.PORT);
      connectionFactory.setVirtualHost(this.vhost);
//      connectionFactory.setChannelCacheSize(25);
      return connectionFactory;
    }


    //创建代理类
	@Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    public MessageConverter integrationEventMessageConverter() {
        Jackson2JsonMessageConverter messageConverter = new Jackson2JsonMessageConverter();

        return messageConverter;
    }


    //创建消息模板
	@Bean
    public RabbitTemplate getRabbitTemplate(){
    	RabbitTemplate template=new RabbitTemplate(connectionFactory());
    	//template.setExchange(this.exchange);
    	template.setRoutingKey(this.mqQueue);
    	template.setQueue(this.mqQueue);
    	template.setMessageConverter(integrationEventMessageConverter());
    	return template;
    }


    @Bean
	public SimpleMessageListenerContainer listenerContainer(){
		SimpleMessageListenerContainer container=new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
        container.setQueueNames(this.mqQueue);
        container.setMessageConverter(integrationEventMessageConverter());
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(new MessageListenerAdapter() {

            public void onMessage(Message message, Channel channel) {
                String msg= null;
                try {
                    msg = new String(message.getBody(),"utf-8");
                    System.out.println("队列名称："+message.getMessageProperties().getConsumerQueue()+msg);
                    System.out.println("--------消息体："+message);
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return container;
	}


	@Bean
    public Queue bindQueue() {
        return new Queue(this.mqQueue);
    }
}
