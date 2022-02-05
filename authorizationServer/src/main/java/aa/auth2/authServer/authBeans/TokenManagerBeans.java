package aa.auth2.authServer.authBeans;

import aa.auth2.authServer.TokenPayload;
import aa.auth2.authServer.configBeans.FreeAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.security.KeyPair;

/**
 * @author slk
 */
@Configuration
public class TokenManagerBeans {


	@Autowired
	private KeyPair keyPair;
	@Autowired
	private FreeAttributes freeAttributes;
	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;


	@Bean
	public TokenStore tokenStore() {
		return (freeAttributes.getJwtEnable()) ? new JwtTokenStore(accessTokenConverter) : new InMemoryTokenStore();
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setKeyPair(keyPair);
		DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
		accessTokenConverter.setUserTokenConverter(new TokenPayload());
		converter.setAccessTokenConverter(accessTokenConverter);
		return converter;
	}
}
