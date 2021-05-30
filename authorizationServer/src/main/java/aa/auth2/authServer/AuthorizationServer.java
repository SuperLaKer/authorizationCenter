package aa.auth2.authServer;

import aa.auth2.authServer.authBeans.PasswordEncoders;
import aa.auth2.authServer.configBeans.OtherBeans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.KeyPair;
import java.security.PublicKey;


@SpringBootApplication
@EnableDiscoveryClient
@Import(OtherBeans.class)
public class AuthorizationServer {

    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(AuthorizationServer.class, args);
        FreeAttributes freeAttributes = ac.getBean(FreeAttributes.class);
        System.out.println(freeAttributes.getJwtEnable());

        KeyPair keyPair = ac.getBean(KeyPair.class);
        PublicKey aPublic = keyPair.getPublic();

        BCryptPasswordEncoder passwordEncoders = ac.getBean(BCryptPasswordEncoder.class);
        System.out.println(passwordEncoders.encode("123456"));
    }
}
