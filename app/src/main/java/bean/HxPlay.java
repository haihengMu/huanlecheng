package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mhc on 2017/3/27.
 */

public class HxPlay implements Serializable {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * h_g_p_id : 1
         * h_g_p_name : 五星复式
         * h_g_p_cid : 1
         * h_g_p_nid : 1
         * h_g_p_tid : 1
         * h_g_p_gid : 1
         * h_g_p_rid : 1
         */

        private String h_g_p_id;
        private String h_g_p_name;
        private String h_g_p_cid;
        private String h_g_p_nid;
        private String h_g_p_tid;
        private String h_g_p_gid;
        private String h_g_p_rid;

        public String getH_g_p_id() {
            return h_g_p_id;
        }

        public void setH_g_p_id(String h_g_p_id) {
            this.h_g_p_id = h_g_p_id;
        }

        public String getH_g_p_name() {
            return h_g_p_name;
        }

        public void setH_g_p_name(String h_g_p_name) {
            this.h_g_p_name = h_g_p_name;
        }

        public String getH_g_p_cid() {
            return h_g_p_cid;
        }

        public void setH_g_p_cid(String h_g_p_cid) {
            this.h_g_p_cid = h_g_p_cid;
        }

        public String getH_g_p_nid() {
            return h_g_p_nid;
        }

        public void setH_g_p_nid(String h_g_p_nid) {
            this.h_g_p_nid = h_g_p_nid;
        }

        public String getH_g_p_tid() {
            return h_g_p_tid;
        }

        public void setH_g_p_tid(String h_g_p_tid) {
            this.h_g_p_tid = h_g_p_tid;
        }

        public String getH_g_p_gid() {
            return h_g_p_gid;
        }

        public void setH_g_p_gid(String h_g_p_gid) {
            this.h_g_p_gid = h_g_p_gid;
        }

        public String getH_g_p_rid() {
            return h_g_p_rid;
        }

        public void setH_g_p_rid(String h_g_p_rid) {
            this.h_g_p_rid = h_g_p_rid;
        }
    }
}
