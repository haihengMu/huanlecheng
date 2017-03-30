package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/2/25.
 */

public class BenefitnewBean implements Serializable{

    /**
     * count : 1
     * current_page : 1
     * list : [{"h_a_c_id":"1","h_a_c_info":"活动简介","h_a_c_picture":"","h_a_c_time":"活动时间","h_a_c_title":"分红"}]
     * total_pages : 1
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String count;
        private String current_page;
        private int total_pages;
        /**
         * h_a_c_id : 1
         * h_a_c_info : 活动简介
         * h_a_c_picture :
         * h_a_c_time : 活动时间
         * h_a_c_title : 分红
         */

        private List<ListBean> list;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(String current_page) {
            this.current_page = current_page;
        }

        public int getTotal_pages() {
            return total_pages;
        }

        public void setTotal_pages(int total_pages) {
            this.total_pages = total_pages;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

    }
}
