package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 */

public class GetHistoryNewWinCodeBean implements Serializable {

    /**
     * code : 3,8,5,2,8
     * issue : 1779493
     * native_code : 01,19,20,23,24,30,31,33,35,39,40,41,53,56,61,62,65,68,77,78
     * time : 22:17:02
     */

    private List<GetHistoryNewModesWinCodeBean> data;

    public List<GetHistoryNewModesWinCodeBean> getData() {
        return data;
    }

    public void setData(List<GetHistoryNewModesWinCodeBean> data) {
        this.data = data;
    }

}
