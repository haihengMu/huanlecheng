package bean;

import java.io.Serializable;

/**
 * Created by Mhc on 2017/3/29.
 */

public class LotteryDetailBean implements Serializable {

    /**
     * data : {"bet_info":"1,3,4,5,6,7,8,9","win_info":{"code":"9,3,6,4,4","time":"2017-03-28 15:50:51"}}
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
         * bet_info : 1,3,4,5,6,7,8,9
         * win_info : {"code":"9,3,6,4,4","time":"2017-03-28 15:50:51"}
         */

        private String bet_info;
        private WinInfoBean win_info;

        public String getBet_info() {
            return bet_info;
        }

        public void setBet_info(String bet_info) {
            this.bet_info = bet_info;
        }

        public WinInfoBean getWin_info() {
            return win_info;
        }

        public void setWin_info(WinInfoBean win_info) {
            this.win_info = win_info;
        }

        public static class WinInfoBean {
            /**
             * code : 9,3,6,4,4
             * time : 2017-03-28 15:50:51
             */

            private String code;
            private String time;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }
}
