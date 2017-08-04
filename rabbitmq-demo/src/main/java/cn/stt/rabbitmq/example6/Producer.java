package cn.stt.rabbitmq.example6;

import com.rabbitmq.client.AMQP;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

/**
 * The producer endpoint that writes to the queue.
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/7/27.
 */
public class Producer extends EndPoint {

    public Producer(String endpointName) throws IOException, TimeoutException {
        super(endpointName);
    }

    public void sendMessage(Serializable object) throws IOException {
        AMQP.BasicProperties basicProperties = new AMQP.BasicProperties.Builder()
                .expiration("10000")
                .build();
        // routingKey =delay_queue 进行转发
        channel.basicPublish("","delay_queue", basicProperties, SerializationUtils.serialize(object));
    }
}
