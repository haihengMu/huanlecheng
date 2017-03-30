package bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/22.
 */

public class DQIssuseBean implements Serializable {

    /**
     * introduction : 当日10点至次日02点
     * code_len : 5
     * switch : 1
     * maxissue : 120
     * status : 1
     * over_time : 204
     * is_buy : 1
     * curr_issue : 20170322-009
     * last_issue : 20170322-008
     * issue : 9
     */

    private DQIssuseChildBean data;


    public DQIssuseChildBean getData() {
        return data;
    }

    public void setData(DQIssuseChildBean data) {
        this.data = data;
    }
}
