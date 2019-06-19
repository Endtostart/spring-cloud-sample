package rediskafka;

public interface MessageOperator {
    <T> void send(String topic,T message);

    <T> Long delete(String topic, T message);
}
