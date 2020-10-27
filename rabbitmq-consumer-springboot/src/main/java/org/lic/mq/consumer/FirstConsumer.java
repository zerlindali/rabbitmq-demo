package org.lic.mq.consumer;

import org.lic.mq.entity.Merchant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author ZerlindaLi create at 2020/10/27 11:46
 * @version 1.0.0
 * @description Consumer
 */
@Component
@PropertySource("classpath:rabbitmq.properties")
@RabbitListener(queues = "${org.lic.firstqueue}", containerFactory = "rabbitListenerContainerFactory")
public class FirstConsumer {
    @RabbitHandler
    public void process(@Payload Merchant merchant){
        System.out.println("First Queue received msg:" + merchant.getName());
    }
}
