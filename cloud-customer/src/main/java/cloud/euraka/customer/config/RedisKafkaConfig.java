package cloud.euraka.customer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rediskafka.MessageOperator;
import rediskafka.One2OneRedisKafka;
import rediskafka.RedisKafka;

@Configuration
public class RedisKafkaConfig {

    @Bean
    @Autowired
    public RedisKafka redisKafka(MessageOperator messageOperator) {
        return new One2OneRedisKafka(messageOperator);
    }


}
