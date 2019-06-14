package cloud.eureka.servicehi;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MsgChannal {
    String PAY_MESSAGE_INPUT = "pay-message";

    /**
     * 消费消息
     * @return
     */
    @Input(value = PAY_MESSAGE_INPUT)
    SubscribableChannel consumePayMessage();

}
