package aa.auth2.authServer.configBeans;

import com.alibaba.fastjson.JSON;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author slk
 */
@SuppressWarnings("all")
public class ValidateSecretKey {

    public static String KeyStore = "slkSecretKey.store.old";
    public static String KeyStorePassword = "slkroot";
    public static String KeyStoreAlias = "slkSecretKeyStore";
    public static String SecretKeyPassword = "slkroot";

    public static void main(String[] args) {
        String token = privateEncoder();
        String claims = tokenVerify(token);
        System.out.println(claims);
    }

    public static String privateEncoder() {
        Resource keyStoreResource = new ClassPathResource("slkSecretkey.store");
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(keyStoreResource, KeyStorePassword.toCharArray());
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(KeyStoreAlias, SecretKeyPassword.toCharArray());

        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        //自定义载荷payload信息
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("name", "zzj");
        tokenMap.put("roles", "admin,User");
        String tokenString = JSON.toJSONString(tokenMap);
        // 私钥加密
        Jwt jwt = JwtHelper.encode(tokenString, new RsaSigner(privateKey));
        String token = jwt.getEncoded();
        System.out.println(token);
        return token;
    }

    private static String getPubKey() {
        ClassPathResource resource = new ClassPathResource(KeyStore);
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream());
            BufferedReader br = new BufferedReader(inputStreamReader);
            return br.lines().collect(Collectors.joining("\n"));
        } catch (IOException ioe) {
            return null;
        }
    }

    public static String tokenVerify(String token){
        Resource keyStoreResource = new ClassPathResource("slkSecretkey.store");
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(keyStoreResource, KeyStorePassword.toCharArray());
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(KeyStoreAlias, SecretKeyPassword.toCharArray());
        RSAPublicKey public_key = (RSAPublicKey) keyPair.getPublic();
        //校验令牌
        Jwt jwt = JwtHelper.decodeAndVerify(token,new RsaVerifier(public_key));
        String claims = jwt.getClaims(); //获取令牌中的载荷信息
        System.out.println(claims);
        return claims;
    }
}
