package org.lic.mq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author ZerlindaLi create at 2020/10/26 11:24
 * @version 1.0.0
 * @description Sender
 */
public class Sender {

    // 队列名称
    private final static String QUEUE_NAME = "my first queue";
    // 直连交换机
    private final static String DIRECT_EXCHANGE = "my first direct";
    // 广播交换机
    private final static String FANOUT_EXCHANGE = "my first fanout";
    // 主题交换机
    private final static String TOPIC_EXCHANGE = "my first topic";
    // 主题队列名称
    private final static String TOPIC_QUEUE_NAME = "INTRODUCE";
    // 主题队列绑定键
    private final static String BINDING_KEY = "*.introduce";
    // 广播队列名称
    private final static String FANOUT_QUEUE_NAME = "my fanout queue";
    public static void main(String[] args) {


        System.out.println("I am a Rabbit MQ Sender");
        // 创建MQ连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try(Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()){

            // 创建直连交换机
            channel.exchangeDeclare(DIRECT_EXCHANGE, BuiltinExchangeType.DIRECT);
            // 声明队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // 绑定 一个队列与直连类型的交换机绑定时，需要一个明确的绑定建（binding key）
            channel.queueBind(QUEUE_NAME, DIRECT_EXCHANGE, QUEUE_NAME);
            // 发送消息到消费者
            String message = "Hello, Zerlinda~";
            // 发送消息到指定交换机，并指明routing key, 这里的routing key会匹配队列与交换机绑定的key
            channel.basicPublish(DIRECT_EXCHANGE, QUEUE_NAME, null, message.getBytes());
            System.out.println(DIRECT_EXCHANGE + " send '" + message +"'");

            // 主题交换机
            channel.exchangeDeclare(TOPIC_EXCHANGE, BuiltinExchangeType.TOPIC);
            channel.queueDeclare(TOPIC_QUEUE_NAME, false, false, false, null);
            // 将队列和交换机绑定
            channel.queueBind(TOPIC_QUEUE_NAME, TOPIC_EXCHANGE, BINDING_KEY);
            // 发送消息到消费者
            String message2 = "主题交换机";
            channel.basicPublish(TOPIC_EXCHANGE, "newer.introduce", null, message2.getBytes());
            System.out.println(TOPIC_EXCHANGE + " send '" + message2 +"'");

            // 广播交换机
            channel.exchangeDeclare(FANOUT_EXCHANGE, BuiltinExchangeType.FANOUT);
            channel.queueDeclare(FANOUT_QUEUE_NAME, false, false, false, null);
            channel.queueBind(FANOUT_QUEUE_NAME, FANOUT_EXCHANGE, "");
            String message3 = "我是广播交换机";
            channel.basicPublish(FANOUT_EXCHANGE, "", null, message3.getBytes());
            System.out.println(FANOUT_EXCHANGE + " send '" + message3 +"'");

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
