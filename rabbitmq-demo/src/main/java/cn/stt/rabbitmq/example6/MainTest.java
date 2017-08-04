package cn.stt.rabbitmq.example6;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/7/27.
 */
public class MainTest {

    private static final String queneName = "tt1";

    public static void main(String[] args) throws IOException, TimeoutException {
        QueueConsumer consumer = new QueueConsumer(queneName);
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

    }

    @Test
    public void test() throws IOException, TimeoutException {
        Producer producer = new Producer(queneName);
        HashMap message = new HashMap();
        message.put("message number", "第" + queneName + "条信息");
        producer.sendMessage(message);

        /*for (int i = 0; i < 100; i++) {
            HashMap message = new HashMap();
            message.put("message number", i);
            producer.sendMessage(message);
            System.out.println("Message Number " + i + " sent.");
        }*/
    }
}
