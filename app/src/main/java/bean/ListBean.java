package bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/25.
 */

public class ListBean implements Serializable {
    private String h_a_c_id;
    private String h_a_c_info;
    private String h_a_c_picture;
    private String h_a_c_time;
    private String h_a_c_title;

    public String getH_a_c_id() {
        return h_a_c_id;
    }

    public void setH_a_c_id(String h_a_c_id) {
        this.h_a_c_id = h_a_c_id;
    }

    public String getH_a_c_info() {
        return h_a_c_info;
    }

    public void setH_a_c_info(String h_a_c_info) {
        this.h_a_c_info = h_a_c_info;
    }

    public String getH_a_c_picture() {
        return h_a_c_picture;
    }

    public void setH_a_c_picture(String h_a_c_picture) {
        this.h_a_c_picture = h_a_c_picture;
    }

    public String getH_a_c_time() {
        return h_a_c_time;
    }

    public void setH_a_c_time(String h_a_c_time) {
        this.h_a_c_time = h_a_c_time;
    }

    public String getH_a_c_title() {
        return h_a_c_title;
    }

    public void setH_a_c_title(String h_a_c_title) {
        this.h_a_c_title = h_a_c_title;
    }
}
