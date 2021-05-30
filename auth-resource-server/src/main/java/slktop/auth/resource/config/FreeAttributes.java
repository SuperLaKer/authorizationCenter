package slktop.auth.resource.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author slk
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "free.attributes")
public class FreeAttributes {

    Boolean jwtEnable = true;

    String KeyStore = "slkSecretKey.store";
    String KeyStoreAlias = "slkSecretKeyStore";
    String KeyStorePassword = "slkroot";
    String SecretKeyPassword = "slkroot";
}
