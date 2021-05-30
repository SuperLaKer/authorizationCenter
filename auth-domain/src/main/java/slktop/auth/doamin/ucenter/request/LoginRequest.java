package slktop.auth.doamin.ucenter.request;


import lombok.Data;
import lombok.ToString;
import slktop.auth.common.model.request.RequestData;

/**
 * Created by admin on 2018/3/5.
 */
@Data
@ToString
public class LoginRequest extends RequestData {

    String username;
    String password;
    String verifycode;

}
