package cloud.eureka.servicehi.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import rediskafka.MessageOperator;
import rediskafka.RedisKafkaConstants;

@Component
public class SimpleInputMessageOperator implements MessageOperator {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public <T> void send(String topic, T message) {
        throw new RuntimeException("unSupport Exception");
    }

    @Override
    public <T> Long delete(String topic, T message) {
        return redisTemplate.opsForList().remove(RedisKafkaConstants.MESSAGE_DUMPLICATE_PRE+":"+topic, -1, message);
    }
}
