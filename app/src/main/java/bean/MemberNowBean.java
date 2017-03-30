package bean;

import java.io.Serializable;
import java.util.List;

/**
 * 在线会员bean
 * Created by Administrator on 2016/12/13.
 */

public class MemberNowBean implements Serializable {


    private int count;

    private List<MsgNowBean> msg;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<MsgNowBean> getMsg() {
        return msg;
    }

    public void setMsg(List<MsgNowBean> msg) {
        this.msg = msg;
    }
}
