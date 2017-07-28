package cn.stt.rabbitmq.example1;

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

    public static void main(String[] args) throws IOException, TimeoutException {
        QueueConsumer consumer = new QueueConsumer("queue3");
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

    }

    @Test
    public void test() throws IOException, TimeoutException {
        Producer producer = new Producer("queue3");
        HashMap message = new HashMap();
        message.put("message number","第3条信息");
        producer.sendMessage(message);

        /*for (int i = 0; i < 100; i++) {
            HashMap message = new HashMap();
            message.put("message number", i);
            producer.sendMessage(message);
            System.out.println("Message Number " + i + " sent.");
        }*/
    }
}
