package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */

public class MemberGuanliBean implements Serializable {

    private int count;
    private List<MsgBean> msg;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<MsgBean> getMsg() {
        return msg;
    }

    public void setMsg(List<MsgBean> msg) {
        this.msg = msg;
    }

}
