package slktop.auth.gateway.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import slktop.auth.gateway.AppUser;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

public class JwtUtil {
    public static final Long JWT_TTL = 3600000L;
    public static final String JWT_KEY = "SALT";

    public static String jwtCreator(String id, String subject, Long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        SecretKey secretKey = generalKey();

        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(subject)
                .setIssuer("admin")
                .setIssuedAt(now)
                .claim("userInfo", new AppUser())
                .signWith(signatureAlgorithm, secretKey) // 使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);// 设置过期时间
        return builder.compact();
    }

    /**
     * 解析
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    private static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }
}
