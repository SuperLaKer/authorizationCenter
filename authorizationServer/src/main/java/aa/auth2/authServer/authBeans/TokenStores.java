package aa.auth2.authServer.authBeans;

import aa.auth2.authServer.FreeAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author slk
 */
@Configuration
public class TokenStores {

    @Autowired
    FreeAttributes freeAttributes;

    @Autowired
    JwtAccessTokenConverter accessTokenConverter;

    @Bean
    public TokenStore tokenStore() {
        return (freeAttributes.getJwtEnable()) ? new JwtTokenStore(accessTokenConverter) : new InMemoryTokenStore();
    }
}
