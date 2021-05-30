package slktop.auth.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableFeignClients
public class GatewayApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(GatewayApp.class, args);
        System.out.println(ac.getBeanDefinitionNames()[0]);
    }
}
