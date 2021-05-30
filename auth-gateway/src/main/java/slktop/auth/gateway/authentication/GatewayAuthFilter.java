package slktop.auth.gateway.authentication;


import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import slktop.auth.gateway.jwt.JwtUtil;

/**
 * 网关认证
 * 过滤器：用户是否携带token?(携带token可以访问微服务):登录?(发放token):登录
 * 实际：浏览器携带jti访问gateway，gateway请求认证中心，认证中心把access_token放到redis中，认证中心发放jti短令牌给gateway
 */
@Component
public class GatewayAuthFilter implements GlobalFilter, Ordered {
    private static final String AUTHORIZE_TOKEN = "token";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 登录请求放行
        if (request.getURI().getPath().contains("/admin/login")) {
            return chain.filter(exchange);
        }
        // 获取token.(携带jti访问认证中心)
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst(AUTHORIZE_TOKEN);
        if (StringUtils.isEmpty(token)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        // token不为空
        try {
            JwtUtil.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        // 默认会传递token
        // request.mutate().header("token", token);
        // 正确解析token.(网关携带token访问其他服务)
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}