package org.lic.mq.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lic.mq.entity.Merchant;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author ZerlindaLi create at 2020/10/27 13:51
 * @version 1.0.0
 * @description RabbitSender
 */
@Configuration
@PropertySource("classpath:rabbitmq.properties")
public class RabbitSender {
    // 自定义的模板，所有的消息都会转换成JSON发送
    @Autowired
    AmqpTemplate zerlTemplate;

    @Value("${org.lic.directexchange}")
    String directExchange;

    @Value("${org.lic.topicexchange}")
    String topicExchange;

    @Value("${org.lic.fanoutexchange}")
    String fanoutExchange;

    @Value("${org.lic.directroutingkey}")
    String directRoutingKey;

    @Value("${org.lic.topicroutingkey1}")
    String topicRoutingKey1;

    @Value("${org.lic.topicroutingkey2}")
    String topicRoutingKey2;

    public void send() throws JsonProcessingException {
        Merchant merchant = new Merchant(1001, "a direct msg: 中原镖局","汉中省解放路266号");
        zerlTemplate.convertAndSend(directExchange, directRoutingKey, merchant);

        zerlTemplate.convertAndSend(topicExchange, topicRoutingKey1, "a topic msg:wuhan.developer");
        zerlTemplate.convertAndSend(topicExchange, topicRoutingKey2, "a topic msg:guangzhou.developer");

        // 发送JSON字符出纳
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(merchant);
        System.out.println(json);

        zerlTemplate.convertAndSend(fanoutExchange, "", json);

    }
}
