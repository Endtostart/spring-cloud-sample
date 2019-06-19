package cloud.eureka.servicehi;

import cloud.eureka.servicehi.stream.MsgChannal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@RestController
@EnableBinding(MsgChannal.class)
public class ServiceHiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceHiApplication.class, args);
    }

    @Value("${url.name}")
    String url;

    @Value("${name}")
    String name;
    @RequestMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        return "hi " + name + ", port: " + url;
    }

    @RequestMapping("/name")
    public String getName() {
        return name;
    }

    @RequestMapping("/info/{id}")
    public String info(@PathVariable("id") String id) {
        return "this messeage from service-hi, value is :" + id;
    }

    @RequestMapping("/customer/{name}")
    public String getCustomer(@PathVariable("name") String name) {
        return "this messeage from service-hi, use feign client send request. customer name is: " + name + ",url:" + url;
    }
}
