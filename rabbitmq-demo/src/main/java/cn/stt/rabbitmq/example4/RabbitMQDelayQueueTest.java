package cn.stt.rabbitmq.example4;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 测试 demo
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/1.
 */
public class RabbitMQDelayQueueTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQDelayQueueTest.class);

    public static void main(String[] args) throws IOException, TimeoutException {
        delayQueue();
    }

    public static void delayQueue() throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.1.182");
        factory.setUsername("stt");
        factory.setPassword("123456");
//        factory.setHost("localhost");

        //getting a connection
        Connection connection = factory.newConnection();

        RabbitMQDelayQueue delayQueue = new RabbitMQDelayQueue.Builder().setConnection(connection).setPerDelayQueueMessageTTL(15, TimeUnit.SECONDS)
                .setDelayExchangeName("delay_exchange_roc").setDelayQueueName("delay_queue_roc").setDelayRoutingKeyName("delay_routing_key_roc")
                .setConsumerRegister(new RabbitMQDelayQueue.ConsumerRegister() {
                    @Override
                    public Consumer register(Channel channel) throws IOException {
                        return new DefaultConsumer(channel) {
                            @Override
                            public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
                                long deliveryTag = envelope.getDeliveryTag();
                                String exchange = envelope.getExchange();
                                String routingKey = envelope.getRoutingKey();
                                // TODO do something
                                String content = new String(body, Charset.forName("utf-8"));
                                LOGGER.info("receive message --- > " + content);

                                Map<String, Object> headers = properties.getHeaders();
                                if (headers != null) {
                                    List<Map<String, Object>> xDeath = (List<Map<String, Object>>) headers.get("x-death");
                                    LOGGER.info("xDeath--- > " + xDeath);
                                    if (xDeath != null && !xDeath.isEmpty()) {
                                        Map<String, Object> entrys = xDeath.get(0);
                                    }
                                }
                                // 消息拒收
                                // if(do something) 消息重新入队
                                getChannel().basicReject(deliveryTag, false);
                                // else 消息应答
                                // getChannel().basicAck(deliveryTag, false);
                            }
                        };
                    }
                }).build();

        delayQueue.put("{\"name\" : \"i am roc!!\"}\"".getBytes("UTF-8"), 3, TimeUnit.SECONDS);
        LOGGER.info("{\"name\" : \"i am roc!!\"}\"");
    }
}
