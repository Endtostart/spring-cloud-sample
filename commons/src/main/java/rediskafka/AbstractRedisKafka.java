package rediskafka;

import java.util.List;

public class AbstractRedisKafka implements RedisKafka{

    private MessageOperator delegation;

    public AbstractRedisKafka(MessageOperator delegation) {
        this.delegation = delegation;
    }

    @Override
    public <T> void send(String topic, T message) {
        delegation.send(topic, message);
    }

    @Override
    public <T> void complySend(String topic, T message) {
        delegation.send(topic, message);
    }

    @Override
    public <T> void complySend(String topic, List<T> messages) {
        for (T message : messages) {
            delegation.send(topic, message);
        }
    }

    @Override
    public <T> Long delete(String topic, T message) {
        return delegation.delete(topic, message);
    }
}
