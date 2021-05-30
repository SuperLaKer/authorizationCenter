package aa.auth2.authServer.feignAPI;


import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import slktop.auth.common.client.XcServiceList;
import slktop.auth.doamin.ucenter.XcUser;
import slktop.auth.doamin.ucenter.ext.XcUserExt;

/**
 * UserDetailsService根据用户名查询，会用到这个接口
 */
@FeignClient(value = XcServiceList.XC_SERVICE_UCENTER,
        fallback = FeignAPIFallback.class,
        configuration = FeignAPIConfiguration.class)
public interface UserClient {
    //根据账号查询用户信息
    @GetMapping("/ucenter/getuserext")
    public XcUserExt getUserext(@RequestParam("username") String username);
}

class FeignAPIFallback implements UserClient {
    @Override
    public XcUserExt getUserext(String username) {
        XcUserExt xcUserExt = new XcUserExt();
        xcUserExt.setCompanyId("降级id: 66666666666");
        return null;
    }
}

/**
 * 仅仅是对providerInstance服务的配置，其他服务可以指定其他的负载均衡策略
 */
@Configuration
class FeignAPIConfiguration {
    @Bean
    public UserClient userClient() {
        return new FeignAPIFallback();
    }

    // 可以配置多个
    @Bean
    public IRule roundRobinRule() {
        return new RoundRobinRule();
    }
}
