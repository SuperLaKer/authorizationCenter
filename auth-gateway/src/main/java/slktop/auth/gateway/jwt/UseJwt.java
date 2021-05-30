package slktop.auth.gateway.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import slktop.auth.gateway.AppUser;

import java.util.Date;

public class UseJwt {

    public static String SALT = "this_is_salt";

    public static void main(String[] args) {

        String s = JwtUtil.jwtCreator("gateway_id", "gateway_subject", 3000000L);
        System.out.println(s);
        //String jwtToken = jwtCreator();

        //Claims claims = jwtDecoder(jwtToken);

    }

    private static Claims jwtDecoder(String jwtToken) {
        Claims claims = Jwts.parser().setSigningKey(SALT).parseClaimsJws(jwtToken).getBody();
        System.out.println(claims);
        return claims;
    }


    private static String jwtCreator() {

        JwtBuilder builder = Jwts.builder()
                // 设置唯一编号, 作为jti
                .setId("12138")
                // 设置主题 可以是JSON数据
                .setSubject("from_gateway")
                // 设置签发日期, 每次生成的额token都不一样
                .setIssuedAt(new Date())
                // 过期时间，过期报错
                .setExpiration(new Date(System.currentTimeMillis()+3000000000L))
                // 自定义负载，value是object
                .claim("payload",new AppUser())
                // 使用HS256算法，加盐
                .signWith(SignatureAlgorithm.HS256, SALT);
        System.out.println(builder.compact());
        return builder.compact();
    }
}
