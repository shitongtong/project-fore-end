package cn.stt.rabbitmq.example3;

import java.util.concurrent.DelayQueue;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/1.
 */
public class Consumer implements Runnable {
    private DelayQueue<Message> queue;

    public Consumer(DelayQueue<Message> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message take = queue.take();
                System.out.println("消息需求者获取消息：" + take.getId() + ":" + take.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
