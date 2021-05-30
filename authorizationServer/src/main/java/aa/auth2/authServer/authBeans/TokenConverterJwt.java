package aa.auth2.authServer.authBeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.security.KeyPair;

/**
 * @author slk
 */
@Configuration
public class TokenConverterJwt {
    @Autowired
    KeyPair keyPair;

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyPair);
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        accessTokenConverter.setUserTokenConverter(new ConsumerUserAuthenticationConverter());
        converter.setAccessTokenConverter(accessTokenConverter);
        return converter;
    }
}
