package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/2/27.
 */

public class PlayGameNewType implements Serializable {
    /**
     * h_g_t_cid : 1
     * h_g_t_id : 1
     * h_g_t_nid : 1
     * h_g_t_off : 1
     * h_g_t_play_type : 1
     * h_g_t_show : 1
     * h_g_t_title : 五星
     */

    private List<PlayGameNewChildBean> data;
    /**
     * G_T_Id : 1001001
     * G_T_NameId : 1
     * G_T_Title : 多星
     */
    public List<PlayGameNewChildBean> getData() {
        return data;
    }

    public void setData(List<PlayGameNewChildBean> data) {
        this.data = data;
    }
}
