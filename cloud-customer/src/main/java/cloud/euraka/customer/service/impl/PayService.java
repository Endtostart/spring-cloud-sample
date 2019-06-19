package cloud.euraka.customer.service.impl;

import cloud.euraka.customer.stream.MessageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PayService {

    @Autowired
    private MessageProcessor messageProcessor;
    private int i = 0;

    @Transactional
    public void pay(String id) {
        // do something ...
        // 发送支付结果到kafka
        //messageProcessor.sendPayMessage("支付成功 version = "+id);
        messageProcessor.sendPayMessage(id);
    }
}
