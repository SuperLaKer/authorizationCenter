//package aa.auth2.authServer.authBeans;
//
//
//import aa.auth2.authServer.feignAPI.UserClient;
//import aa.auth2.authServer.web.UserJwt;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.provider.ClientDetails;
//import org.springframework.security.oauth2.provider.ClientDetailsService;
//import org.springframework.stereotype.Service;
//import slktop.auth.doamin.ucenter.XcMenu;
//import slktop.auth.doamin.ucenter.ext.XcUserExt;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * 拉取用户中心服务，校验用户密码
// */
////@Service
//public class DetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    @SuppressWarnings("all")
//    UserClient userClient;
//
//    @Autowired
//    ClientDetailsService clientDetailsService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        //取出身份，如果身份为空说明没有认证
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
//        if (authentication == null) {
//            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
//            if (clientDetails != null) {
//                //密码
//                String clientSecret = clientDetails.getClientSecret();
//                return new User(username, clientSecret, AuthorityUtils.commaSeparatedStringToAuthorityList(""));
//            }
//        }
//        if (StringUtils.isEmpty(username)) {
//            return null;
//        }
//        // 远程调用用户中心根据账号查询用户信息
////        XcUserExt userext = userClient.getUserext(username);
////        if(userext == null){
////            //返回空给spring security表示用户不存在
////            return null;
////        }
//        XcUserExt userext = new XcUserExt();
//        userext.setUsername("admin");
//        userext.setPassword(new BCryptPasswordEncoder().encode("123456"));
//        ArrayList<XcMenu> xcMenus = new ArrayList<>();
//
//        XcMenu xcMenu = new XcMenu();
//        xcMenu.setCode("11111111");
//        xcMenus.add(xcMenu);
//        userext.setPermissions(xcMenus);
//
//        String password = userext.getPassword();
//        List<XcMenu> permissions = userext.getPermissions();
//
//        List<GrantedAuthority> roles = new ArrayList<>();
//        roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        roles.add(new SimpleGrantedAuthority("ROLE_NORMAL"));
//        roles.add(new SimpleGrantedAuthority("ROLE_SUPER"));
//        UserJwt userDetails = new UserJwt(username, password, roles);
//
//        userDetails.setId(userext.getId());
//        userDetails.setUtype(userext.getUtype());//用户类型
//        userDetails.setCompanyId(userext.getCompanyId());//所属企业
//        userDetails.setName(userext.getName());//用户名称
//        userDetails.setUserpic(userext.getUserpic());//用户头像
//
//        return userDetails;
//    }
//}
