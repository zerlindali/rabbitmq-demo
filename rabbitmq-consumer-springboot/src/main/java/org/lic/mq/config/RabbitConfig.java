package org.lic.mq.config;

import lombok.Data;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author ZerlindaLi create at 2020/10/27 10:47
 * @version 1.0.0
 * @description RabbitConfig
 * 创建3个交换机，4个队列
 */
@Data
@Configuration
@PropertySource("classpath:rabbitmq.properties")
public class RabbitConfig {

    @Value("${org.lic.directexchange}")
    private String directExchange;
    @Value("${org.lic.topicexchange}")
    private String topicExchange;
    @Value("${org.lic.fanoutexchange}")
    private String fanoutExchange;
    @Value("${org.lic.firstqueue}")
    private String firstQueue;
    @Value("${org.lic.secondqueue}")
    private String secondQueue;
    @Value("${org.lic.thirdqueue}")
    private String thirdQueue;
    @Value("${org.lic.fourthqueue}")
    private String fourthQueue;

    // 定义交换机
    @Bean("directExchange")
    public DirectExchange getDirectExchange(){
        return new DirectExchange(directExchange);
    }

    @Bean("topicExchange")
    public TopicExchange getTopicExchange(){
        return new TopicExchange(topicExchange);
    }

    @Bean("fanoutExchange")
    public FanoutExchange getFanoutExchange(){
        return new FanoutExchange(fanoutExchange);
    }

    // 定义队列
    @Bean("firstQueue")
    public Queue getFirstQueue(){
        return new Queue(firstQueue);
    }

    @Bean("secondQueue")
    public Queue getSecondQueue(){
        return new Queue(secondQueue);
    }

    @Bean("thirdQueue")
    public Queue getThirdQueue(){
        return new Queue(thirdQueue);
    }

    @Bean("fourthQueue")
    public Queue getFourthQueue(){
        return new Queue(fourthQueue);
    }

    // 定义绑定
    @Bean
    public Binding getBindFirst(Queue firstQueue, DirectExchange directExchange){
        return BindingBuilder.bind(firstQueue).to(directExchange).with("zerlinda.best");
    }

    @Bean
    public Binding getBindSecond(Queue secondQueue, TopicExchange topicExchange){
        return BindingBuilder.bind(secondQueue).to(topicExchange).with("*.zerlinda.*");
    }

    @Bean
    public Binding getBindThird(Queue thirdQueue, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(thirdQueue).to(fanoutExchange);
    }

    @Bean
    public Binding getBindFourth(Queue fourthQueue, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fourthQueue).to(fanoutExchange);
    }

    /**
     * 在消费端转换JSON消息
     * 监听类都要加上containerFactory属性
     * @param connectionFactory
     * @return
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setAutoStartup(true);
        return factory;
    }
}
