package cloud.euraka.customer.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MessageProcessor {
    @Resource(name = MsgChannal.PAY_MESSAGE_INPUT)
    MessageChannel payMessageChannel;

    /**
     * 发送支付消息
     * @param message
     * @param <T>
     * @return
     */
    public <T> boolean sendPayMessage(T message) {
       return payMessageChannel.send(MessageBuilder.withPayload(message).build());
    }
}
