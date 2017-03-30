package bean;

import java.io.Serializable;
import java.util.List;

/**
 * 在线会员bean
 * Created by Administrator on 2016/12/13.
 */

public class MemberNowBean implements Serializable {

    /**
     * data : {"total_pages":1,"current_page":1,"count":"1","list":[{"h_u_id":"11932","h_u_proxy_list":"aijinnew01","h_u_gid":"10","h_u_name":"ccccc01","h_u_max_rebate":"10.00","h_u_balance":"0.0000","h_u_level":"0","h_u_nickname":"ccccc01","h_u_chat_cid":"57377","h_u_the_login_time":"2017-03-24 09:45:39"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * total_pages : 1
         * current_page : 1
         * count : 1
         * list : [{"h_u_id":"11932","h_u_proxy_list":"aijinnew01","h_u_gid":"10","h_u_name":"ccccc01","h_u_max_rebate":"10.00","h_u_balance":"0.0000","h_u_level":"0","h_u_nickname":"ccccc01","h_u_chat_cid":"57377","h_u_the_login_time":"2017-03-24 09:45:39"}]
         */

        private int total_pages;
        private int current_page;
        private String count;
        private List<ListBean> list;

        public int getTotal_pages() {
            return total_pages;
        }

        public void setTotal_pages(int total_pages) {
            this.total_pages = total_pages;
        }

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * h_u_id : 11932
             * h_u_proxy_list : aijinnew01
             * h_u_gid : 10
             * h_u_name : ccccc01
             * h_u_max_rebate : 10.00
             * h_u_balance : 0.0000
             * h_u_level : 0
             * h_u_nickname : ccccc01
             * h_u_chat_cid : 57377
             * h_u_the_login_time : 2017-03-24 09:45:39
             */

            private String h_u_id;
            private String h_u_proxy_list;
            private String h_u_gid;
            private String h_u_name;
            private String h_u_max_rebate;
            private String h_u_balance;
            private String h_u_level;
            private String h_u_nickname;
            private String h_u_chat_cid;
            private String h_u_the_login_time;

            public String getH_u_id() {
                return h_u_id;
            }

            public void setH_u_id(String h_u_id) {
                this.h_u_id = h_u_id;
            }

            public String getH_u_proxy_list() {
                return h_u_proxy_list;
            }

            public void setH_u_proxy_list(String h_u_proxy_list) {
                this.h_u_proxy_list = h_u_proxy_list;
            }

            public String getH_u_gid() {
                return h_u_gid;
            }

            public void setH_u_gid(String h_u_gid) {
                this.h_u_gid = h_u_gid;
            }

            public String getH_u_name() {
                return h_u_name;
            }

            public void setH_u_name(String h_u_name) {
                this.h_u_name = h_u_name;
            }

            public String getH_u_max_rebate() {
                return h_u_max_rebate;
            }

            public void setH_u_max_rebate(String h_u_max_rebate) {
                this.h_u_max_rebate = h_u_max_rebate;
            }

            public String getH_u_balance() {
                return h_u_balance;
            }

            public void setH_u_balance(String h_u_balance) {
                this.h_u_balance = h_u_balance;
            }

            public String getH_u_level() {
                return h_u_level;
            }

            public void setH_u_level(String h_u_level) {
                this.h_u_level = h_u_level;
            }

            public String getH_u_nickname() {
                return h_u_nickname;
            }

            public void setH_u_nickname(String h_u_nickname) {
                this.h_u_nickname = h_u_nickname;
            }

            public String getH_u_chat_cid() {
                return h_u_chat_cid;
            }

            public void setH_u_chat_cid(String h_u_chat_cid) {
                this.h_u_chat_cid = h_u_chat_cid;
            }

            public String getH_u_the_login_time() {
                return h_u_the_login_time;
            }

            public void setH_u_the_login_time(String h_u_the_login_time) {
                this.h_u_the_login_time = h_u_the_login_time;
            }
        }
    }
}
