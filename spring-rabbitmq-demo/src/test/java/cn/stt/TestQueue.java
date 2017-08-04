package cn.stt;

import cn.stt.service.MQProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/1.
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-mq.xml"})
public class TestQueue {

    @Autowired
    MQProducer mqProducer;

    final String queue_key = "queue";

    @Test
    public void send() {
        Map<String, Object> msg = new HashMap<>();
        msg.put("data", "hello,rabbmitmq!");
        mqProducer.sendDataToQueue(queue_key, msg);
    }

}
