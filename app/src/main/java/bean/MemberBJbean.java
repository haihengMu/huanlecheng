package bean;

import java.io.Serializable;

/**
 * 会员编辑返点bean
 * Created by Administrator on 2016/12/13.
 */

public class MemberBJbean implements Serializable {

    /**
     * msg : 修改成功!.
     */

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
