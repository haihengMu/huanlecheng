package bean;

import java.io.Serializable;
import java.util.List;

public class HxGame implements Serializable {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * h_g_n_id : 1
         * h_g_n_cid : 1
         * h_g_n_title : 重庆时时彩
         * h_g_n_is_hot : 1
         * h_g_n_off : 1
         * h_g_n_show : 1
         * h_g_n_is_new : 0
         * h_g_n_win_rules : 1
         */

        private String h_g_n_id;
        private String h_g_n_cid;
        private String h_g_n_title;
        private String h_g_n_is_hot;
        private String h_g_n_off;
        private String h_g_n_show;
        private String h_g_n_is_new;
        private String h_g_n_win_rules;

        public String getH_g_n_id() {
            return h_g_n_id;
        }

        public void setH_g_n_id(String h_g_n_id) {
            this.h_g_n_id = h_g_n_id;
        }

        public String getH_g_n_cid() {
            return h_g_n_cid;
        }

        public void setH_g_n_cid(String h_g_n_cid) {
            this.h_g_n_cid = h_g_n_cid;
        }

        public String getH_g_n_title() {
            return h_g_n_title;
        }

        public void setH_g_n_title(String h_g_n_title) {
            this.h_g_n_title = h_g_n_title;
        }

        public String getH_g_n_is_hot() {
            return h_g_n_is_hot;
        }

        public void setH_g_n_is_hot(String h_g_n_is_hot) {
            this.h_g_n_is_hot = h_g_n_is_hot;
        }

        public String getH_g_n_off() {
            return h_g_n_off;
        }

        public void setH_g_n_off(String h_g_n_off) {
            this.h_g_n_off = h_g_n_off;
        }

        public String getH_g_n_show() {
            return h_g_n_show;
        }

        public void setH_g_n_show(String h_g_n_show) {
            this.h_g_n_show = h_g_n_show;
        }

        public String getH_g_n_is_new() {
            return h_g_n_is_new;
        }

        public void setH_g_n_is_new(String h_g_n_is_new) {
            this.h_g_n_is_new = h_g_n_is_new;
        }

        public String getH_g_n_win_rules() {
            return h_g_n_win_rules;
        }

        public void setH_g_n_win_rules(String h_g_n_win_rules) {
            this.h_g_n_win_rules = h_g_n_win_rules;
        }
    }
}
