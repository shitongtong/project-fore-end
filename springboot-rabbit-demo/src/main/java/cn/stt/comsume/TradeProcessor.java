package cn.stt.comsume;

import cn.stt.constants.MQConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 死信接收处理消费者
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/2.
 */
@Component
@RabbitListener(queues = MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME)
public class TradeProcessor {

    @RabbitHandler
    public void process(String content) {
        System.out.println("process::" + content);
//        DLXMessage dlxMessage = JSON.parseObject(content, DLXMessage.class);
//        System.out.println(dlxMessage);
    }
}
