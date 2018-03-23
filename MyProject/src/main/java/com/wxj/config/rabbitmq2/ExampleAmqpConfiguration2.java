package com.wxj.config.rabbitmq2;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 杨莫 on 2018/2/5.
 */
@Configuration
@AutoConfigureAfter(RabbitConfig2.class)
public class ExampleAmqpConfiguration2 {

    @Value("${spring.rabbitmq.publisher.queue1}")
    private String spring_rabbitmq_publisher_queue;

    @Bean("testQueueContainer1")
    public MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(spring_rabbitmq_publisher_queue);
        container.setMessageListener(exampleListener());
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setTxSize(1);
        return container;
    }

    @Bean("testQueueListener1")
    public ChannelAwareMessageListener exampleListener() {
        return new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
               System.out.println("asdass "+new String(message.getBody(), "UTF-8"));
                Thread.sleep(1*1000);
               channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            }
        };
    }

}
