package cloud.euraka.customer;

import cloud.euraka.customer.service.impl.PayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaStreamTest {
    @Autowired
    private PayService payService;

    @Test
    public void testSendMore() throws InterruptedException {
        Runnable runnable = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("===> 发送消息：" + Thread.currentThread().getName() + "=> count:" + i);
                payService.pay(Thread.currentThread().getName() + "=> count:" + i);
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("============= test 开始  ===========");
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("============= test 结束  ===========");
   }
}
