package rediskafka;

import java.util.List;

public interface RedisKafka extends MessageOperator{

    <T> void complySend(String topic, T message);

    <T> void complySend(String topic, List<T> messages);
}
