package slktop.auth.resource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;


/**
 * An Authorization Server will more typically have a key rotation strategy, and the keys will not
 * be hard-coded into the application code.
 *
 * For simplicity, though, this sample doesn't demonstrate key rotation.
 */
@Configuration
public class KeyPairs {

    @Autowired
    FreeAttributes freeAttributes;

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
