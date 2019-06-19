package cloud.euraka.customer.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rediskafka.RedisKafka;

@Component
public class MessageProcessor {

    Logger logger = LoggerFactory.getLogger(MessageProcessor.class);

    @Autowired
    RedisKafka redisKafka;

    /**
     * 发送支付消息
     * @param message
     * @param <T>
     * @return
     */
    public <T> void sendPayMessage(T message) {
        logger.info("发送支付消息");
        redisKafka.send(MsgChannal.PAY_MESSAGE_TOPIC, message);
    }
}
