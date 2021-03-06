package aa.auth2.authServer.authBeans;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * Legacy Authorization Server (spring-security-oauth2) does not support any
 * <a href target="_blank" href="https://tools.ietf.org/html/rfc7517#section-5">JWK Set</a> endpoint.
 * <p>
 * This class adds ad-hoc support in order to better support the other samples in the repo.
 */
@FrameworkEndpoint
public class FrameworkEndpoints {

	final KeyPair keyPair;
	final TokenStore tokenStore;

	public FrameworkEndpoints(KeyPair keyPair, TokenStore tokenStore) {
		this.keyPair = keyPair;
		this.tokenStore = tokenStore;
	}

	@GetMapping("/.well-known/jwks.json")
	@ResponseBody
	public Map<String, Object> getPublicKey() {
		RSAPublicKey publicKey = (RSAPublicKey) this.keyPair.getPublic();
		RSAKey key = new RSAKey.Builder(publicKey).build();
		return new JWKSet(key).toJSONObject();
	}


	/**
	 * Legacy Authorization Server (spring-security-oauth2) does not support any
	 * Token Introspection endpoint.
	 * <p>
	 * This class adds ad-hoc support in order to better support the other samples in the repo.
	 */
	@PostMapping("/introspect")
	@ResponseBody
	public Map<String, Object> introspect(@RequestParam("token") String token) {
		OAuth2AccessToken accessToken = this.tokenStore.readAccessToken(token);
		Map<String, Object> attributes = new HashMap<>();
		if (accessToken == null || accessToken.isExpired()) {
			attributes.put("active", false);
			return attributes;
		}
		OAuth2Authentication authentication = this.tokenStore.readAuthentication(token);
		attributes.put("active", true);
		attributes.put("exp", accessToken.getExpiration().getTime());
		attributes.put("scope", String.join(" ", accessToken.getScope()));
		attributes.put("sub", authentication.getName());
		return attributes;
	}
}