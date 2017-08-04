package cn.stt;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/1.
 */
@Component
public class QueueListenter implements MessageListener {

    public void onMessage(Message msg) {
        try {
            System.out.print("onMessage::" + msg.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
