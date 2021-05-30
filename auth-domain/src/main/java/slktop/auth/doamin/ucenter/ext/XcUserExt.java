package slktop.auth.doamin.ucenter.ext;


import lombok.Data;
import lombok.ToString;
import slktop.auth.doamin.ucenter.XcMenu;
import slktop.auth.doamin.ucenter.XcUser;

import java.util.List;

/**
 * Created by admin on 2018/3/20.
 */
@Data
@ToString
public class XcUserExt extends XcUser {

    //权限信息
    private List<XcMenu> permissions;

    //企业信息
    private String companyId;
}
