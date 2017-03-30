package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */

public class TuanduiBean implements Serializable {

    /**
     * data : {"total_pages":1,"current_page":1,"count":"8","list":[{"h_w_d_oid":"W_20170324175236183742","h_w_d_bid":"14","h_w_d_uname":"zyj8888","h_w_d_money":"49200.0000","h_w_d_service_fee":"0.0000","h_w_d_state":"1","h_w_d_success_time":"2017-03-24 18:11:29","h_w_d_addtime":"2017-03-24 17:52:36"},{"h_w_d_oid":"W_20170324154853518645","h_w_d_bid":"14","h_w_d_uname":"zyj8888","h_w_d_money":"20000.0000","h_w_d_service_fee":"0.0000","h_w_d_state":"3","h_w_d_success_time":"2017-03-24 16:13:04","h_w_d_addtime":"2017-03-24 15:48:53"},{"h_w_d_oid":"W_20170324114832718962","h_w_d_bid":"14","h_w_d_uname":"zyj8888","h_w_d_money":"20000.0000","h_w_d_service_fee":"0.0000","h_w_d_state":"1","h_w_d_success_time":"2017-03-24 12:04:38","h_w_d_addtime":"2017-03-24 11:48:32"},{"h_w_d_oid":"W_20170324105438905932","h_w_d_bid":"14","h_w_d_uname":"zyj8888","h_w_d_money":"10000.0000","h_w_d_service_fee":"0.0000","h_w_d_state":"1","h_w_d_success_time":"2017-03-24 11:09:33","h_w_d_addtime":"2017-03-24 10:54:38"},{"h_w_d_oid":"W_20170323233321590599","h_w_d_bid":"14","h_w_d_uname":"zyj8888","h_w_d_money":"10000.0000","h_w_d_service_fee":"0.0000","h_w_d_state":"1","h_w_d_success_time":"2017-03-23 23:46:42","h_w_d_addtime":"2017-03-23 23:33:21"},{"h_w_d_oid":"W_20170323103329660647","h_w_d_bid":"14","h_w_d_uname":"zyj8888","h_w_d_money":"10000.0000","h_w_d_service_fee":"0.0000","h_w_d_state":"1","h_w_d_success_time":"2017-03-23 11:06:28","h_w_d_addtime":"2017-03-23 10:33:29"},{"h_w_d_oid":"W_20170315213916942118","h_w_d_bid":"14","h_w_d_uname":"zyj8888","h_w_d_money":"5000.0000","h_w_d_service_fee":"0.0000","h_w_d_state":"1","h_w_d_success_time":"2017-03-15 21:42:24","h_w_d_addtime":"2017-03-15 21:39:16"},{"h_w_d_oid":"W_20170315204405199175","h_w_d_bid":"14","h_w_d_uname":"zyj8888","h_w_d_money":"6000.0000","h_w_d_service_fee":"0.0000","h_w_d_state":"1","h_w_d_success_time":"2017-03-15 20:54:33","h_w_d_addtime":"2017-03-15 20:44:05"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * total_pages : 1
         * current_page : 1
         * count : 8
         * list : [{"h_w_d_oid":"W_20170324175236183742","h_w_d_bid":"14","h_w_d_uname":"zyj8888","h_w_d_money":"49200.0000","h_w_d_service_fee":"0.0000","h_w_d_state":"1","h_w_d_success_time":"2017-03-24 18:11:29","h_w_d_addtime":"2017-03-24 17:52:36"},{"h_w_d_oid":"W_20170324154853518645","h_w_d_bid":"14","h_w_d_uname":"zyj8888","h_w_d_money":"20000.0000","h_w_d_service_fee":"0.0000","h_w_d_state":"3","h_w_d_success_time":"2017-03-24 16:13:04","h_w_d_addtime":"2017-03-24 15:48:53"},{"h_w_d_oid":"W_20170324114832718962","h_w_d_bid":"14","h_w_d_uname":"zyj8888","h_w_d_money":"20000.0000","h_w_d_service_fee":"0.0000","h_w_d_state":"1","h_w_d_success_time":"2017-03-24 12:04:38","h_w_d_addtime":"2017-03-24 11:48:32"},{"h_w_d_oid":"W_20170324105438905932","h_w_d_bid":"14","h_w_d_uname":"zyj8888","h_w_d_money":"10000.0000","h_w_d_service_fee":"0.0000","h_w_d_state":"1","h_w_d_success_time":"2017-03-24 11:09:33","h_w_d_addtime":"2017-03-24 10:54:38"},{"h_w_d_oid":"W_20170323233321590599","h_w_d_bid":"14","h_w_d_uname":"zyj8888","h_w_d_money":"10000.0000","h_w_d_service_fee":"0.0000","h_w_d_state":"1","h_w_d_success_time":"2017-03-23 23:46:42","h_w_d_addtime":"2017-03-23 23:33:21"},{"h_w_d_oid":"W_20170323103329660647","h_w_d_bid":"14","h_w_d_uname":"zyj8888","h_w_d_money":"10000.0000","h_w_d_service_fee":"0.0000","h_w_d_state":"1","h_w_d_success_time":"2017-03-23 11:06:28","h_w_d_addtime":"2017-03-23 10:33:29"},{"h_w_d_oid":"W_20170315213916942118","h_w_d_bid":"14","h_w_d_uname":"zyj8888","h_w_d_money":"5000.0000","h_w_d_service_fee":"0.0000","h_w_d_state":"1","h_w_d_success_time":"2017-03-15 21:42:24","h_w_d_addtime":"2017-03-15 21:39:16"},{"h_w_d_oid":"W_20170315204405199175","h_w_d_bid":"14","h_w_d_uname":"zyj8888","h_w_d_money":"6000.0000","h_w_d_service_fee":"0.0000","h_w_d_state":"1","h_w_d_success_time":"2017-03-15 20:54:33","h_w_d_addtime":"2017-03-15 20:44:05"}]
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
             * h_w_d_oid : W_20170324175236183742
             * h_w_d_bid : 14
             * h_w_d_uname : zyj8888
             * h_w_d_money : 49200.0000
             * h_w_d_service_fee : 0.0000
             * h_w_d_state : 1
             * h_w_d_success_time : 2017-03-24 18:11:29
             * h_w_d_addtime : 2017-03-24 17:52:36
             */

            private String h_w_d_oid;
            private String h_w_d_bid;
            private String h_w_d_uname;
            private String h_w_d_money;
            private String h_w_d_service_fee;
            private String h_w_d_state;
            private String h_w_d_success_time;
            private String h_w_d_addtime;

            public String getH_w_d_oid() {
                return h_w_d_oid;
            }

            public void setH_w_d_oid(String h_w_d_oid) {
                this.h_w_d_oid = h_w_d_oid;
            }

            public String getH_w_d_bid() {
                return h_w_d_bid;
            }

            public void setH_w_d_bid(String h_w_d_bid) {
                this.h_w_d_bid = h_w_d_bid;
            }

            public String getH_w_d_uname() {
                return h_w_d_uname;
            }

            public void setH_w_d_uname(String h_w_d_uname) {
                this.h_w_d_uname = h_w_d_uname;
            }

            public String getH_w_d_money() {
                return h_w_d_money;
            }

            public void setH_w_d_money(String h_w_d_money) {
                this.h_w_d_money = h_w_d_money;
            }

            public String getH_w_d_service_fee() {
                return h_w_d_service_fee;
            }

            public void setH_w_d_service_fee(String h_w_d_service_fee) {
                this.h_w_d_service_fee = h_w_d_service_fee;
            }

            public String getH_w_d_state() {
                return h_w_d_state;
            }

            public void setH_w_d_state(String h_w_d_state) {
                this.h_w_d_state = h_w_d_state;
            }

            public String getH_w_d_success_time() {
                return h_w_d_success_time;
            }

            public void setH_w_d_success_time(String h_w_d_success_time) {
                this.h_w_d_success_time = h_w_d_success_time;
            }

            public String getH_w_d_addtime() {
                return h_w_d_addtime;
            }

            public void setH_w_d_addtime(String h_w_d_addtime) {
                this.h_w_d_addtime = h_w_d_addtime;
            }
        }
    }
}
