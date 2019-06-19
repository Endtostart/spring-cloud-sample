package cloud.euraka.customer.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MsgChannal {
    String PAY_MESSAGE_TOPIC = "pay_message";
    String END = "_output";
    String PAY_MESSAGE_OUT = PAY_MESSAGE_TOPIC + END;

    /**
     * 发出消息
     * @return
     */
    @Output(value = PAY_MESSAGE_OUT)
    MessageChannel sendPayMessage();
}
