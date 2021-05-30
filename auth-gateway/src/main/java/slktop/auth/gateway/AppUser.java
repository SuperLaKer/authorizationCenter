package slktop.auth.gateway;

import lombok.Data;

import java.io.Serializable;

@Data
public class AppUser implements Serializable {
    Integer id = 12138;
    String username = "用户1";
    String password = "密码";
    String role = "角色或权限，由security框架管理";
}
