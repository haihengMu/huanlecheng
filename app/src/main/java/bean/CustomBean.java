package bean;

import java.util.List;

/**
 * 自定义彩种的bean
 * Created by Administrator on 2016/10/14.
 */

public class CustomBean {


    /**
     * h_g_n_cid : 1
     * h_g_n_id : 1
     * h_g_n_is_hot : 1
     * h_g_n_is_new : 0
     * h_g_n_off : 1
     * h_g_n_show : 1
     * h_g_n_title : 重庆时时彩
     * h_g_n_win_rules : 1
     */

    private List<HXGameNameBean> data;

    public List<HXGameNameBean> getData() {
        return data;
    }

    public void setData(List<HXGameNameBean> data) {
        this.data = data;
    }
}
