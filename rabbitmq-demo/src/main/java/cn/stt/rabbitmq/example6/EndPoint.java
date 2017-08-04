package cn.stt.rabbitmq.example6;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Represents a connection with a queue
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/7/27.
 */
public abstract class EndPoint {
    protected Channel channel;
    protected Connection connection;
    protected String endPointName;

    public EndPoint(String endpointName) throws IOException, TimeoutException {
        this.endPointName = endpointName;

        //Create a connection factory
        ConnectionFactory factory = new ConnectionFactory();

        //hostname of your rabbitmq server
        factory.setHost("192.168.1.182");
        factory.setUsername("stt");
        factory.setPassword("123456");
//        factory.setHost("localhost");

        //getting a connection
        connection = factory.newConnection();

        //creating a channel
        channel = connection.createChannel();

        Map<String,Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", "amq.direct");
        arguments.put("x-dead-letter-routing-key", "message_ttl_routingKey");
//        arguments.put("x-message-ttl", 10000);

        //declaring a queue for this channel. If queue does not exist,
        //it will be created on the server.
        channel.queueDeclare("delay_queue", true, false, false, arguments);
        // 声明队列
        channel.queueDeclare(endPointName, true, false, false, null);
        // 绑定路由
        channel.queueBind(endPointName, "amq.direct", "message_ttl_routingKey");
    }


    /**
     * 关闭channel和connection。并非必须，因为隐含是自动调用的。
     *
     * @throws IOException
     */
    public void close() throws IOException, TimeoutException {
        this.channel.close();
        this.connection.close();
    }
}
