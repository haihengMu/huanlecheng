package bean;

import java.io.Serializable;
import java.util.List;

/**
 * 开奖bean
 * Created by Administrator on 2017/2/22.
 */

public class KjBean implements Serializable{


    /**
     * h_g_c_id : 1
     * h_g_c_name : 时时彩
     * h_g_c_off : 1
     * h_g_c_show : 1
     */

    private List<KjBigChildBean> data;

    public List<KjBigChildBean> getData() {
        return data;
    }

    public void setData(List<KjBigChildBean> data) {
        this.data = data;
    }


}
