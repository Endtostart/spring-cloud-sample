package cloud.euraka.customer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerApplicationTests {
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Test
    public void contextLoads() {
       /* RestTemplate restTemplate = new RestTemplate(new OkHttp3ClientHttpRequestFactory());
        String url = "http://localhost:8082/customer?id=15";
        String res = restTemplate.getForObject(url, String.class);
        System.out.println(res);*/

        String serviceId = "server-hi";
        for (int i = 0; i < 100; i++) {
            ServiceInstance serviceInstance = this.loadBalancerClient.choose(serviceId);
            System.out.println("第 " + i + " 次 : " + serviceInstance.getHost() + ": " + serviceInstance.getPort());
        }
    }

}
