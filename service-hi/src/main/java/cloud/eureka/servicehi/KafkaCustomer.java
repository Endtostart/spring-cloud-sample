package cloud.eureka.servicehi;

import cloud.eureka.servicehi.stream.MsgChannal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import rediskafka.MessageOperator;

@Component
public class KafkaCustomer {

    @Autowired
    private MessageOperator messageOperator;

    Logger logger = LoggerFactory.getLogger(KafkaCustomer.class);

    private String charger = "1";
    /**
     * 消费支付消息
     */
    @StreamListener(MsgChannal.PAY_MESSAGE_INPUT)
    public void payMessage(String msg) {
        if (charger.equals(msg)) {
            charger = "2";
            logger.info("msg = 1 消费失败");
            throw new RuntimeException("msg 1 消费失败");
        }
        logger.info("商品支付：" + msg);
        charger = "1";
        logger.info(" ===== 删除redis中消息副本");
        messageOperator.delete(MsgChannal.PAY_MESSAGE_TOPIC,msg);
    }

}
