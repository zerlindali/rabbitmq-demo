package org.lic.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.lic.mq.producer.RabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitPublisherAppTests {

    @Autowired
    RabbitSender rabbitSender;
    @Test
    void contextLoads() throws JsonProcessingException {
        rabbitSender.send();
    }

}
