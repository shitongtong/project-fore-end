package cn.stt.product;

import cn.stt.constants.MQConstant;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/1.
 */
@Component
public class Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;
//    @Autowired
//    private AmqpTemplate rabbitTemplate;

    public void send(String queueName, String msg) {
        rabbitTemplate.convertAndSend(MQConstant.DEFAULT_EXCHANGE,queueName, msg);
    }

    public void send(String queueName, String msg, long times) {
//        DLXMessage dlxMessage = new DLXMessage(queueName,msg,times);
        MessagePostProcessor processor = new MessagePostProcessor(){
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                MessageProperties messageProperties = message.getMessageProperties();
                messageProperties.setConsumerQueue(queueName);
                message.getMessageProperties().setExpiration(times + "");
                return message;
            }
        };
//        dlxMessage.setExchange(MQConstant.DEFAULT_EXCHANGE);
//        rabbitTemplate.convertAndSend(MQConstant.DEFAULT_EXCHANGE,MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME, JSON.toJSON(dlxMessage), processor);
        rabbitTemplate.convertAndSend(MQConstant.DEFAULT_EXCHANGE,MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME, msg, processor);
    }

    /*public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend(MQConstant.HELLO_QUEUE_NAME, context);
    }*/
}
