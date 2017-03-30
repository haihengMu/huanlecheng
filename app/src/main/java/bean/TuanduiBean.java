package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */

public class TuanduiBean implements Serializable {

    private int count;

    private List<MsgTuanBean> msg;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<MsgTuanBean> getMsg() {
        return msg;
    }

    public void setMsg(List<MsgTuanBean> msg) {
        this.msg = msg;
    }
}
