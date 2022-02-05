package slktop.auth.resource.authConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;


/**
 * 自定义密钥轮换策略
 */
@Configuration
public class AuthBeans {

	@Autowired
	private FreeAttributes freeAttributes;
	@Autowired
	private KeyPair keyPair;

	@Bean
	@Primary
	KeyPair keyPair() {
		Resource keyStoreResource = new ClassPathResource(freeAttributes.getKeyStore());
		KeyStoreKeyFactory storeKeyFactory =
				new KeyStoreKeyFactory(keyStoreResource, freeAttributes.getKeyStorePassword().toCharArray());
		KeyPair keyPair = storeKeyFactory.
				getKeyPair(freeAttributes.getKeyStoreAlias(), freeAttributes.getSecretKeyPassword().toCharArray());
		assert keyPair != null;
		return keyPair;
	}

	@Bean
	public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
		return new JwtTokenStore(jwtAccessTokenConverter);
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setKeyPair(keyPair());
		return converter;
	}

	@Bean
	JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withPublicKey((RSAPublicKey) keyPair.getPublic()).build();
	}
}
