package org.lic.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/**
 * 我们一般将交换机和队列的声明绑定放在消费者端。
 * 我这里参照官网写的例子，在生产者端声明了交换机和对立，所以不需要再在消费者端声明绑定了。
 * @author ZerlindaLi create at 2020/10/26 14:03
 * @version 1.0.0
 * @description Recv
 */
public class Recv {
    // 队列名称
    private final static String QUEUE_NAME = "my first queue";
    private final static String TOPIC_QUEUE_NAME = "INTRODUCE";
    public static void main(String[] args) throws Exception{
        Channel Channel = getChannel();

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        DeliverCallback deliverCallback = (consumerTag, deliver) -> {
            String message = new String(deliver.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message +"'");
        };

        Channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});

        Channel.basicConsume(TOPIC_QUEUE_NAME, true, deliverCallback, consumerTag -> {});
    }

    private static Channel getChannel() throws Exception{
        // 连接到mq服务器
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setVirtualHost("/myvirtual");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        return channel;
    }
}
