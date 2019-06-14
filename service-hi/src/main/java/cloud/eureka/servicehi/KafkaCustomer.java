package cloud.eureka.servicehi;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaCustomer {
    /**
     * 消费支付消息
     */
    @StreamListener(TopicConstant.PAY_MESSAGE_INPUT)
    public void payMessage(String msg) {
        System.out.println("商品支付：" + msg);
    }
}
