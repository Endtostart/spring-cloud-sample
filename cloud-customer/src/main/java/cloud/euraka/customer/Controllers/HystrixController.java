package cloud.euraka.customer.Controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("remotesvc")
public class HystrixController {
    @RequestMapping("test")
    @HystrixCommand(fallbackMethod = "testFallBack")
    public String test() {
        throw new RuntimeException();
    }

    private String testFallBack() {
        return "fallBack...";
    }
}
