package bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/22.
 */

public class KjBigChildBean implements Serializable {
    private String h_g_c_id;
    private String h_g_c_name;
    private String h_g_c_off;
    private String h_g_c_show;

    public String getH_g_c_id() {
        return h_g_c_id;
    }

    public void setH_g_c_id(String h_g_c_id) {
        this.h_g_c_id = h_g_c_id;
    }

    public String getH_g_c_name() {
        return h_g_c_name;
    }

    public void setH_g_c_name(String h_g_c_name) {
        this.h_g_c_name = h_g_c_name;
    }

    public String getH_g_c_off() {
        return h_g_c_off;
    }

    public void setH_g_c_off(String h_g_c_off) {
        this.h_g_c_off = h_g_c_off;
    }

    public String getH_g_c_show() {
        return h_g_c_show;
    }

    public void setH_g_c_show(String h_g_c_show) {
        this.h_g_c_show = h_g_c_show;
    }
}
