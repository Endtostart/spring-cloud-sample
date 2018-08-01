package cloud.euraka.customer.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cloud.euraka.customer.service.CustomserService;

@Controller
@RequestMapping
public class CustomerController {
    @Autowired
    CustomserService customserService;

    @RequestMapping("/customer")
    @ResponseBody
    public String getCustomer(String id) {
        return customserService.getInfo(id);
    }

    @RequestMapping("/getCustomerInfo/{name}")
    @ResponseBody
    public String getCustomerInfo(@PathVariable("name") String name) {
        return customserService.getCustomerInfo(name);
    }


}
