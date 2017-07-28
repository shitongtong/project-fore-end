package cn.stt.rabbitmq.example1;

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
        channel.basicPublish("",endPointName, null, SerializationUtils.serialize(object));
    }
}
