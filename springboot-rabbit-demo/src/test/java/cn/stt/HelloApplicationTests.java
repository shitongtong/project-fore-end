package cn.stt;

import cn.stt.constants.MQConstant;
import cn.stt.product.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * 创建单元测试类，用来调用消息生产：
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class HelloApplicationTests {

    @Autowired
    private Sender sender;

    @Test
    public void hello() throws Exception {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        sender.send(MQConstant.HELLO_QUEUE_NAME, context);
    }

    @Test
    public void testDelayedMsg(){
        String context = "延迟消息 " + new Date();
        System.out.println("Sender : " + context);
        sender.send(MQConstant.HELLO_QUEUE_NAME, context,10000);
    }
}
