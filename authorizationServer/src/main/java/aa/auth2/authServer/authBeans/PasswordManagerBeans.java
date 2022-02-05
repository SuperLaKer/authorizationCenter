package aa.auth2.authServer.authBeans;

import aa.auth2.authServer.configBeans.FreeAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;

@Configuration
public class PasswordManagerBeans {

    final FreeAttributes freeAttributes;
    public PasswordManagerBeans(FreeAttributes freeAttributes) {
        this.freeAttributes = freeAttributes;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

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
}
