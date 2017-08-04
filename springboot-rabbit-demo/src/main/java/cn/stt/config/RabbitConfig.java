package cn.stt.config;

import cn.stt.constants.MQConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建RabbitMQ的配置类RabbitConfig，用来配置队列、交换器、路由等高级信息。这里我们以入门为主，先以最小化的配置来定义，以完成一个基本的生产和消费过程。
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/1.
 */
@Configuration
public class RabbitConfig {

    //信道配置
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(MQConstant.DEFAULT_EXCHANGE, true, false);
    }

    @Bean
    public Queue repeatTradeQueue() {
        Queue queue = new Queue(MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME, true, false, false);
        return queue;
    }

    @Bean
    public Binding drepeatTradeBinding() {
        return BindingBuilder.bind(repeatTradeQueue()).to(defaultExchange()).with(MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME);
    }

    @Bean
    public Queue deadLetterQueue() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", MQConstant.DEFAULT_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME);
        Queue queue = new Queue(MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME, true, false, false, arguments);
        System.out.println("arguments :" + queue.getArguments());
        return queue;
    }

    @Bean
    public Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(defaultExchange()).with(MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME);
    }


    /*********************
     * hello 队列  测试
     *****************/
    @Bean
    public Queue queue() {
        Queue queue = new Queue(MQConstant.HELLO_QUEUE_NAME, true);
        return queue;
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(defaultExchange()).with(MQConstant.HELLO_QUEUE_NAME);
    }
}
