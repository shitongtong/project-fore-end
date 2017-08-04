package cn.stt.rabbitmq.example3;

import java.util.concurrent.DelayQueue;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/1.
 */
public class DelayQueueTest {
    public static void main(String[] args) {
        DelayQueue<Message> queue = new DelayQueue<Message>();
        new Thread(new Producer(queue)).start();
        new Thread(new Consumer(queue)).start();
    }
}
