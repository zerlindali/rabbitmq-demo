package org.lic.mq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author ZerlindaLi create at 2020/10/27 14:06
 * @version 1.0.0
 * @description FourthConsumer
 */
@Component
@PropertySource("classpath:rabbitmq.properties")
@RabbitListener(queues = "${org.lic.fourthqueue}", containerFactory = "rabbitListenerContainerFactory")
public class FourthConsumer {
    @RabbitHandler
    public void process(String msg){
        System.out.println("First Queue received msg:" + msg);
    }
}
