package com.wxj.config.rabbitmq1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * rabbitmq发送消息工具类
 *
 * @author chenhf
 * @create 2017-10-26 上午11:10
 **/

@Component
public class RabbitMqSender1 implements RabbitTemplate.ConfirmCallback{
    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(RabbitMqSender1.class);

    @Value("${spring.rabbitmq.publisher.exchange}")
    private String spring_rabbitmq_publisher_exchange;

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMqSender1(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setConfirmCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        logger.info("confirm1: " + correlationData.getId());
    }

    public void sendRabbitmqFanout() {
        String msg="message1";
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        logger.info("send1: " + correlationData.getId());
        this.rabbitTemplate.convertAndSend(spring_rabbitmq_publisher_exchange, "sluan" , msg, correlationData);
    }
}
