package cn.stt.service;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/1.
 */
public interface MQProducer {
    /**
     * 发送消息到指定队列
     *
     * @param queueKey
     * @param object
     */
    void sendDataToQueue(String queueKey, Object object);
}
