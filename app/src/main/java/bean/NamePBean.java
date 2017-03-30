package bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/29.
 */

public class NamePBean implements Serializable {

    /**
     * error : 0
     * msg : 对不起,您的安全资料已锁定,不能进行该操作.
     */

    private String error="";
    private String msg;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
