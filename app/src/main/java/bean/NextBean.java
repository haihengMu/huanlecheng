package bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/21.
 */

public class NextBean implements Serializable {
    private String h_n_addtime;
    private String h_n_admin;
    private String h_n_id;
    private String h_n_is_show;
    private String h_n_read_count;
    private String h_n_text;
    private String h_n_title;

    public String getH_n_addtime() {
        return h_n_addtime;
    }

    public void setH_n_addtime(String h_n_addtime) {
        this.h_n_addtime = h_n_addtime;
    }

    public String getH_n_admin() {
        return h_n_admin;
    }

    public void setH_n_admin(String h_n_admin) {
        this.h_n_admin = h_n_admin;
    }

    public String getH_n_id() {
        return h_n_id;
    }

    public void setH_n_id(String h_n_id) {
        this.h_n_id = h_n_id;
    }

    public String getH_n_is_show() {
        return h_n_is_show;
    }

    public void setH_n_is_show(String h_n_is_show) {
        this.h_n_is_show = h_n_is_show;
    }

    public String getH_n_read_count() {
        return h_n_read_count;
    }

    public void setH_n_read_count(String h_n_read_count) {
        this.h_n_read_count = h_n_read_count;
    }

    public String getH_n_text() {
        return h_n_text;
    }

    public void setH_n_text(String h_n_text) {
        this.h_n_text = h_n_text;
    }

    public String getH_n_title() {
        return h_n_title;
    }

    public void setH_n_title(String h_n_title) {
        this.h_n_title = h_n_title;
    }
}
