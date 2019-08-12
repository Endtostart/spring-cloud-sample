package cloud.gateway.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.factory.StripPrefixGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class Application {

//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        StripPrefixGatewayFilterFactory.Config config = new StripPrefixGatewayFilterFactory.Config();
//        config.setParts(1);
//        return builder.routes()
//                .route(r -> r.path("/gateway/test/**")
//                        .filters(f ->
//                                f.addResponseHeader("X-AnotherHeader", "baz"))
//                        .uri("http://localhost:8762/name")
//                ).route(r -> r.path("/get").uri("http://www.baidu.com"))
//                .build();
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
