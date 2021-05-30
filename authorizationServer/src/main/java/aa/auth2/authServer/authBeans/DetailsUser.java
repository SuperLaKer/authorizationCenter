package aa.auth2.authServer.authBeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;

/**
 * @author slk
 */
@Configuration
public class DetailsUser {

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * 基于数据库：实现UserDetailsService配置到这里
     * 自定义令牌：ConsumerUserAuthenticationConverter
     * 刷新令牌：客户端配置，ConsumerUserAuthenticationConverter中配置
     *
     */
    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        ArrayList<UserDetails> userDetailsArrayList = new ArrayList<>();
        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        String password = passwordEncoder.encode("123456");
        // normal
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_NORMAL"));
        userDetailsArrayList.add(new User("normal", password, grantedAuthorities));
        // admin
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        userDetailsArrayList.add(new User("admin", password, grantedAuthorities));
        // superAdmin
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_SUPER"));
        userDetailsArrayList.add(new User("superAdmin", password, grantedAuthorities));
        return new InMemoryUserDetailsManager(userDetailsArrayList);
    }

}

