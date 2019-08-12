package cloud.euraka.customer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "server-hi")
public interface  CustomerFeignClient {
    @RequestMapping(value = "/hi/customer/{name}", method = RequestMethod.GET)
    public String getCustomerInfo(@PathVariable("name") String name);
}
