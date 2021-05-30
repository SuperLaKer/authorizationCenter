package aa.auth2.authServer.configBeans;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class OtherBeans {
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
