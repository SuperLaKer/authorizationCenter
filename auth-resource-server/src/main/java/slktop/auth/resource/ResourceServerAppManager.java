package slktop.auth.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class ResourceServerAppManager extends ResourceServerConfigurerAdapter {

	@Value("${spring.application.name}")
	private String resourceId;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(resourceId);
	}

	/**
	 * Http安全配置，对每个到达系统的http请求链接进行校验
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		// 所有请求必须认证通过
		http.authorizeRequests()
				//下边的路径放行
				.antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui",
						"/swagger-resources", "/swagger-resources/configuration/security",
						"/swagger-ui.html", "/webjars/**", "/course/coursepic/list/**", "/course/courseview/**")
				.permitAll()
				.anyRequest().authenticated();
	}


}
