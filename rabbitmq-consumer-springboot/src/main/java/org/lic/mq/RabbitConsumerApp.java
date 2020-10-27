package org.lic.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZerlindaLi create at 2020/10/27 15:46
 * @version 1.0.0
 * @description RabbitConsumerApp
 */
@SpringBootApplication
public class RabbitConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(RabbitConsumerApp.class, args);
    }
}
