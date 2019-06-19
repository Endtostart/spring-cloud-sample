package cloud.euraka.customer.stream;

import cloud.euraka.customer.utils.BeanContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import rediskafka.MessageOperator;
import rediskafka.RedisKafkaConstants;

@Component
public class SimpleOutputMessageOperator implements MessageOperator {

    @Autowired
    private RedisTemplate redisTemplate;

    Logger logger = LoggerFactory.getLogger(SimpleOutputMessageOperator.class);

    @Override
    public <T> void send(String topic, T message) {
        MessageChannel payMessageChannel = BeanContextUtils.getBeanByNameWithType(topic + MsgChannal.END, MessageChannel.class);
        logger.info("发送消息前,先将消息副本发送至redis");
        redisTemplate.opsForList().leftPush(RedisKafkaConstants.MESSAGE_DUMPLICATE_PRE+":"+topic, message);
        logger.info("发送消息：" + message);
        payMessageChannel.send(MessageBuilder.withPayload(message).build());
    }



    @Override
    public <T> Long delete(String topic, T message) {
        return null;
    }
}
