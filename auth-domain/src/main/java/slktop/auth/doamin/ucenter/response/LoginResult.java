package slktop.auth.doamin.ucenter.response;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import slktop.auth.common.model.response.ResponseResult;
import slktop.auth.common.model.response.ResultCode;


@Data
@ToString
@NoArgsConstructor
public class LoginResult extends ResponseResult {
    public LoginResult(ResultCode resultCode, String token) {
        super(resultCode);
        this.token = token;
    }
    private String token;
}
