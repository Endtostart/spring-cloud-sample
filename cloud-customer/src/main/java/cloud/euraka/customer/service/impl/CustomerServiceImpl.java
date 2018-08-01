package cloud.euraka.customer.service.impl;

import cloud.euraka.customer.service.CustomerFeignClient;
import cloud.euraka.customer.service.CustomserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerServiceImpl implements CustomserService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private CustomerFeignClient customerFeignClient;

    @Override
    public String getInfo(String id) {
        String serviceId = "server-hi";
        /*List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceId);
        if (instances.isEmpty()) {
            return null;
        }
        ServiceInstance service = instances.get(0);
        String url = service.getHost() + ":" + service.getPort();
        return restTemplate.getForObject("http://"+url+"/info?id="+id,String.class);*/
        return restTemplate.getForObject("http://"+serviceId+"/info/"+id,String.class);
    }

    @Override
    public String getCustomerInfo(String name) {
        return this.customerFeignClient.getCustomerInfo(name);
    }
}
