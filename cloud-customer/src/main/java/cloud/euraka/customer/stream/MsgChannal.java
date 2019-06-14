package cloud.euraka.customer.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MsgChannal {
    String PAY_MESSAGE_INPUT = "pay-message";

    /**
     * 发出消息
     * @return
     */
    @Output(value = PAY_MESSAGE_INPUT)
    MessageChannel sendPayMessage();
}
