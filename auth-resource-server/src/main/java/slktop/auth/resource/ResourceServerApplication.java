package slktop.auth.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
public class ResourceServerApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(ResourceServerApplication.class, args);
        System.out.println(ac.getBeanDefinitionNames()[0]);
    }
}
