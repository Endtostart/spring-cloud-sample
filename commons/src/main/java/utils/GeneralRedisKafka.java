package utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class GeneralRedisKafka {

    // 消息副本队列
    private static final String MESSAGE_DUMPLICATE_PRE = "message_duplicate";
    // 消息补偿队列
    private static final String MESSAGE_COMPLY_PRE = "message_comply";
    // 消息补偿发送阈值
    private static int threshold = 1000;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 发送kafka消息前,将消息副本发送至redis
     * @param message
     * @param <T>
     * @return
     */
    public <T> Long sendMessageDuplicate(T message) {
        return redisTemplate.opsForList().leftPush(MESSAGE_DUMPLICATE_PRE, message);
    }

    /**
     * stream 消费后，删除消息副本
     * @param message
     * @param <T>
     * @return
     */
    public <T> int removeMessageDuplicate(T message) {
        Long index = redisTemplate.opsForList().remove(MESSAGE_DUMPLICATE_PRE, -1, message);
        if (Objects.isNull(index)) {
            return 0;
        }
        Long size = redisTemplate.opsForList().size(MESSAGE_DUMPLICATE_PRE);
        if (size - index > threshold) {
            // 触发补偿发送机制
            return 1;
        }
        return 0;
    }



}
