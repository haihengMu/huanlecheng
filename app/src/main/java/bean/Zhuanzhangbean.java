package bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/12.
 */

public class Zhuanzhangbean implements Serializable {

    /**
     * msg : 转账成功,您当前还有:￥322.0111元.
     */

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
