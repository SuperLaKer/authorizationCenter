package slktop.auth.doamin;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
public class LoginRequest {

    /**
     * 用户名、密码、验证码是必须的
     */
    String username;
    String password;
    String verifyCode;

    /**
     * 不同的客户端可对应不同的接口或实体
     * 默认值可以配置到yml中
     */
    String clientId;
    String clientPassword;
}
