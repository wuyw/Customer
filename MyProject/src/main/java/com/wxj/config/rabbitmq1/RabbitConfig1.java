package com.wxj.config.rabbitmq1;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Scope;

/**
 * Created by 杨莫 on 2018/2/5.
 */

@Configuration
public class RabbitConfig1 {

    @Value("${spring.rabbitmq.host}")
    private String spring_rabbitmq_host;

    @Value("${spring.rabbitmq.port}")
    private String spring_rabbitmq_port;

    @Value("${spring.rabbitmq.username}")
    private String spring_rabbitmq_username;

    @Value("${spring.rabbitmq.password}")
    private String spring_rabbitmq_password;

    @Value("${spring.rabbitmq.publisher.exchange}")
    private String spring_rabbitmq_publisher_exchange;

    @Value("${spring.rabbitmq.publisher.vhost}")
    private String spring_rabbitmq_publisher_vhost;

    @Value("${spring.rabbitmq.publisher.queue}")
    private String spring_rabbitmq_publisher_queue;

    @Value("${spring.rabbitmq.publisher.qos}")
    private String spring_rabbitmq_publisher_qos;

    @Value("${spring.rabbitmq.publisher-confirms}")
    private boolean  spring_rabbitmq_publisher_confirms;

    //声明队列
    @Bean
    public Queue queue1() {
        return new Queue(spring_rabbitmq_publisher_queue, true); // true表示持久化该队列
    }

    //声明交互器
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(spring_rabbitmq_publisher_exchange);
    }

    //绑定
    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(queue1()).to(fanoutExchange());
    }

    @Bean
    public ConnectionFactory connectionFactory() {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(spring_rabbitmq_host+":"+spring_rabbitmq_port);
        connectionFactory.setUsername(spring_rabbitmq_username);
        connectionFactory.setPassword(spring_rabbitmq_password);
        connectionFactory.setVirtualHost(spring_rabbitmq_publisher_vhost);

        /** 如果要进行消息回调，则这里必须要设置为true */
        connectionFactory.setPublisherConfirms(spring_rabbitmq_publisher_confirms);
        return connectionFactory;
    }

    @Bean
    /** 因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置 */
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplatenew() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }
}
