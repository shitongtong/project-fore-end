package cn.stt.rabbitmq.example3;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟执行的消息
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/1.
 */
public class Message implements Delayed {
    private String id;
    private String name;
    private long activeTime;//执行时间

    public Message() {

    }

    public Message(String id, String name, long activeTime) {
        super();
        this.id = id;
        this.name = name;
        this.activeTime = TimeUnit.NANOSECONDS.convert(activeTime, TimeUnit.MILLISECONDS) + System.nanoTime();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.activeTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        Message msg = (Message) o;
        return Integer.valueOf(this.id) > Integer.valueOf(msg.id) ? 1 : (Integer.valueOf(this.id) < Integer.valueOf(msg.id) ? -1 : 0);
    }
}
