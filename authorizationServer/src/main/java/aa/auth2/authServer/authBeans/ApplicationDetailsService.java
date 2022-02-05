package aa.auth2.authServer.authBeans;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

/**
 * 两个DetailsService
 */
@Configuration
public class ApplicationDetailsService implements UserDetailsService, ClientDetailsService {

	final PasswordEncoder passwordEncoder;

	public ApplicationDetailsService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * 基于数据库：实现UserDetailsService配置到这里
	 * 自定义令牌：ConsumerUserAuthenticationConverter
	 * 刷新令牌：客户端配置，ConsumerUserAuthenticationConverter中配置
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if ("admin".equalsIgnoreCase(username)) {
			return User.builder().username("admin").password(passwordEncoder.encode("123456"))
					.roles("ADMIN", "NORMAL").build();
		}
		return User.builder().username("normal").password(passwordEncoder.encode("123456"))
				.roles("NORMAL").build();
	}

	@SneakyThrows
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

		BaseClientDetails clientDetails = new BaseClientDetails("postman",
				"authentication-center,resourceServerDemo",
				"scopes", "password, authentication_code",
				"authorities", "https://www.baidu.com");
		clientDetails.setClientSecret(passwordEncoder.encode("postman"));
		return clientDetails;
	}
}
