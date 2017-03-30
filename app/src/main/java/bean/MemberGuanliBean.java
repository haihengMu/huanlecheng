package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */

public class MemberGuanliBean implements Serializable {
    /**
     * data : {"total_pages":1,"current_page":"0","count":"2","list":[{"h_u_id":"25515","h_u_gid":"10","h_u_name":"fffffgffg","h_u_proxy_id":"24926","h_u_max_rebate":"10.00","h_u_balance":"0.0000","h_u_level":"0","h_u_nickname":"fffffgffg","h_u_chat_cid":"","h_u_the_login_time":"2017-03-21 20:20:38","h_u_last_login_time":"2017-03-21 20:20:38","h_u_regtime":"2017-03-21 20:20:38","count":"0"},{"h_u_id":"25518","h_u_gid":"10","h_u_name":"ccggggg","h_u_proxy_id":"24926","h_u_max_rebate":"10.00","h_u_balance":"0.0000","h_u_level":"0","h_u_nickname":"ccggggg","h_u_chat_cid":"","h_u_the_login_time":"2017-03-21 20:22:51","h_u_last_login_time":"2017-03-21 20:22:51","h_u_regtime":"2017-03-21 20:22:51","count":"0"}]}
     * page_execute_time : 0.14788604 s
     */

    private DataBean data;
    private String page_execute_time;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getPage_execute_time() {
        return page_execute_time;
    }

    public void setPage_execute_time(String page_execute_time) {
        this.page_execute_time = page_execute_time;
    }

    public static class DataBean implements Serializable{
        /**
         * total_pages : 1
         * current_page : 0
         * count : 2
         * list : [{"h_u_id":"25515","h_u_gid":"10","h_u_name":"fffffgffg","h_u_proxy_id":"24926","h_u_max_rebate":"10.00","h_u_balance":"0.0000","h_u_level":"0","h_u_nickname":"fffffgffg","h_u_chat_cid":"","h_u_the_login_time":"2017-03-21 20:20:38","h_u_last_login_time":"2017-03-21 20:20:38","h_u_regtime":"2017-03-21 20:20:38","count":"0"},{"h_u_id":"25518","h_u_gid":"10","h_u_name":"ccggggg","h_u_proxy_id":"24926","h_u_max_rebate":"10.00","h_u_balance":"0.0000","h_u_level":"0","h_u_nickname":"ccggggg","h_u_chat_cid":"","h_u_the_login_time":"2017-03-21 20:22:51","h_u_last_login_time":"2017-03-21 20:22:51","h_u_regtime":"2017-03-21 20:22:51","count":"0"}]
         */

        private int total_pages;
        private String current_page;
        private String count;
        private List<ListBean> list;

        public int getTotal_pages() {
            return total_pages;
        }

        public void setTotal_pages(int total_pages) {
            this.total_pages = total_pages;
        }

        public String getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(String current_page) {
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

        public static class ListBean implements Serializable{
            /**
             * h_u_id : 25515
             * h_u_gid : 10
             * h_u_name : fffffgffg
             * h_u_proxy_id : 24926
             * h_u_max_rebate : 10.00
             * h_u_balance : 0.0000
             * h_u_level : 0
             * h_u_nickname : fffffgffg
             * h_u_chat_cid :
             * h_u_the_login_time : 2017-03-21 20:20:38
             * h_u_last_login_time : 2017-03-21 20:20:38
             * h_u_regtime : 2017-03-21 20:20:38
             * count : 0
             */

            private String h_u_id;
            private String h_u_gid;
            private String h_u_name;
            private String h_u_proxy_id;
            private String h_u_max_rebate;
            private String h_u_balance;
            private String h_u_level;
            private String h_u_nickname;
            private String h_u_chat_cid;
            private String h_u_the_login_time;
            private String h_u_last_login_time;
            private String h_u_regtime;
            private String count;

            public String getH_u_id() {
                return h_u_id;
            }

            public void setH_u_id(String h_u_id) {
                this.h_u_id = h_u_id;
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

            public String getH_u_proxy_id() {
                return h_u_proxy_id;
            }

            public void setH_u_proxy_id(String h_u_proxy_id) {
                this.h_u_proxy_id = h_u_proxy_id;
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

            public String getH_u_last_login_time() {
                return h_u_last_login_time;
            }

            public void setH_u_last_login_time(String h_u_last_login_time) {
                this.h_u_last_login_time = h_u_last_login_time;
            }

            public String getH_u_regtime() {
                return h_u_regtime;
            }

            public void setH_u_regtime(String h_u_regtime) {
                this.h_u_regtime = h_u_regtime;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }
        }
    }
}
