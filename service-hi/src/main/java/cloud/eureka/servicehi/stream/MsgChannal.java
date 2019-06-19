package cloud.eureka.servicehi.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MsgChannal {
    String PAY_MESSAGE_TOPIC = "pay_message";
    String PAY_MESSAGE_INPUT = "pay_message_input";

    /**
     * 消费消息
     * @return
     */
    @Input(value = PAY_MESSAGE_INPUT)
    SubscribableChannel consumePayMessage();

}
