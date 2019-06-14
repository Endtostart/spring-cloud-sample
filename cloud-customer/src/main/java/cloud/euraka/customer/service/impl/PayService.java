package cloud.euraka.customer.service.impl;

import cloud.euraka.customer.stream.MessageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayService {

    @Autowired
    private MessageProcessor messageProcessor;

    public void pay() {
        // do something ...
        // 发送支付结果到kafka
        messageProcessor.sendPayMessage("支付成功");
    }
}
