//package cn.stt.comsume;
//
//import cn.stt.constants.MQConstant;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// * @Author shitongtong
// * <p>
// * Created by shitongtong on 2017/8/1.
// */
//@Component
//@RabbitListener(queues = MQConstant.HELLO_QUEUE_NAME)
//public class Receiver {
//
//    @RabbitHandler
//    public void process(String content) {
//        System.out.println("Receiver : " + content);
//    }
//
//}
