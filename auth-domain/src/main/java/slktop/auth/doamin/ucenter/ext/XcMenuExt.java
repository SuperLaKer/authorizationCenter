package slktop.auth.doamin.ucenter.ext;


import lombok.Data;
import lombok.ToString;
import slktop.auth.doamin.ext.CategoryNode;
import slktop.auth.doamin.ucenter.XcMenu;

import java.util.List;

/**
 * Created by admin on 2018/3/20.
 */
@Data
@ToString
public class XcMenuExt extends XcMenu {

    List<CategoryNode> children;
}
