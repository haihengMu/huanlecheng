package bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/7.
 */

public class BankCZQrenBean implements Serializable {

    /**
     * data : {"manual":{"msg":"订单创建成功,请点击确定,前住充值页面.<br>注意:当前充值请填写:<font style=color:red>111.00<\/font> 元.<br>请您尽量在30分钟内完成充值,超过30分钟请勿继续充值.请再重新创建订单.","AccountNumber":"6214854511382797","AccountName":"宋艳军"}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * manual : {"msg":"订单创建成功,请点击确定,前住充值页面.<br>注意:当前充值请填写:<font style=color:red>111.00<\/font> 元.<br>请您尽量在30分钟内完成充值,超过30分钟请勿继续充值.请再重新创建订单.","AccountNumber":"6214854511382797","AccountName":"宋艳军"}
         */

        private ManualBean manual;

        public ManualBean getManual() {
            return manual;
        }

        public void setManual(ManualBean manual) {
            this.manual = manual;
        }

        public static class ManualBean implements Serializable{
            /**
             * msg : 订单创建成功,请点击确定,前住充值页面.<br>注意:当前充值请填写:<font style=color:red>111.00</font> 元.<br>请您尽量在30分钟内完成充值,超过30分钟请勿继续充值.请再重新创建订单.
             * AccountNumber : 6214854511382797
             * AccountName : 宋艳军
             */

            private String msg;
            private String AccountNumber;
            private String AccountName;

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }

            public String getAccountNumber() {
                return AccountNumber;
            }

            public void setAccountNumber(String AccountNumber) {
                this.AccountNumber = AccountNumber;
            }

            public String getAccountName() {
                return AccountName;
            }

            public void setAccountName(String AccountName) {
                this.AccountName = AccountName;
            }
        }
    }
}
