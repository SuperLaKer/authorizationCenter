package slktop.auth.doamin.ucenter.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import slktop.auth.common.model.response.ResponseResult;
import slktop.auth.common.model.response.ResultCode;

/**
 * Created by mrt on 2018/5/21.
 */
@Data
@ToString
@NoArgsConstructor
public class JwtResult extends ResponseResult {
    public JwtResult(ResultCode resultCode, String jwt) {
        super(resultCode);
        this.jwt = jwt;
    }
    private String jwt;
}
