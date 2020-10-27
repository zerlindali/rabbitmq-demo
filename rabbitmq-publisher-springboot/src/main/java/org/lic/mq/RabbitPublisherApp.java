package org.lic.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitPublisherApp {

    public static void main(String[] args) {
        SpringApplication.run(RabbitPublisherApp.class, args);
    }

}
