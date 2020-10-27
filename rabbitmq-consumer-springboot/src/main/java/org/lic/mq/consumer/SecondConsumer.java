package org.lic.mq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author ZerlindaLi create at 2020/10/27 14:06
 * @version 1.0.0
 * @description SecondConsumer
 */
@Component
@PropertySource("classpath:rabbitmq.properties")
@RabbitListener(queues = "${org.lic.secondqueue}", containerFactory = "rabbitListenerContainerFactory")
public class SecondConsumer {
    @RabbitHandler
    public void process(@Payload String msg){
        System.out.println("First Queue received msg:" + msg);
    }
}
