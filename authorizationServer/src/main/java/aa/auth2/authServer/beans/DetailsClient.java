package aa.auth2.authServer.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.stereotype.Component;

/**
 * @author slk
 * implements ClientDetails
 */
@Component
public class DetailsClient extends AuthorizationServerConfigurerAdapter {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {
        // @formatter:off
        clients.inMemory()
                .withClient("reader")
                .authorizedGrantTypes("password", "refresh_token")
                .secret(passwordEncoder.encode("secret"))
                .scopes("message:read")
                .accessTokenValiditySeconds(600_000_000)
                .refreshTokenValiditySeconds(600)
                .and()
                .withClient("writer")
                .authorizedGrantTypes("password")
                .secret(passwordEncoder.encode("secret"))
                .scopes("message:write")
                .accessTokenValiditySeconds(600_000_000)
                .and()
                .withClient("noscopes")
                .authorizedGrantTypes("password")
                .secret(passwordEncoder.encode("secret"))
                .scopes("none")
                .accessTokenValiditySeconds(600_000_000);
        // @formatter:on
    }
}
