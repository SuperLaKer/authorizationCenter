package slktop.auth.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
public class ResourceApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(ResourceApplication.class, args);
        System.out.println(ac.getBeanDefinitionNames()[0]);
    }
}
