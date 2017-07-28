package cn.stt.rabbitmq.example1;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 读取队列的程序端，实现了Runnable接口。
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/7/27.
 */
public class QueueConsumer extends EndPoint implements Runnable, Consumer {

    public QueueConsumer(String endPointName) throws IOException, TimeoutException {
        super(endPointName);
    }

    /**
     * Called when consumer is registered.
     *
     * @param consumerTag
     */
    @Override
    public void handleConsumeOk(String consumerTag) {
        System.out.println("handleConsumeOk::Consumer=" + consumerTag + " registered");
    }

    @Override
    public void handleCancelOk(String consumerTag) {
        System.out.println("handleCancelOk::consumerTag=" + consumerTag);
    }

    @Override
    public void handleCancel(String consumerTag) throws IOException {
        System.out.println("handleCancel::consumerTag=" + consumerTag);
    }

    @Override
    public void handleShutdownSignal(String consumerTag, ShutdownSignalException e) {
        System.out.println("handleShutdownSignal::consumerTag=" + consumerTag);
        System.out.println("handleShutdownSignal::ShutdownSignalException=" + e);
    }

    @Override
    public void handleRecoverOk(String consumerTag) {
        System.out.println("handleRecoverOk::consumerTag=" + consumerTag);
    }

    /**
     * Called when new message is available.
     *
     * @param consumerTag
     * @param envelope
     * @param basicProperties
     * @param bytes
     * @throws IOException
     */
    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
        Map map = (HashMap) SerializationUtils.deserialize(bytes);
        System.out.println("Message Number " + map.get("message number") + " received.");
    }

    @Override
    public void run() {
        try {
            System.out.println("run...");
            //start consuming messages. Auto acknowledge messages.
            channel.basicConsume(endPointName, true, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
