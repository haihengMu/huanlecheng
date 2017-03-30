package bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mhc on 2017/3/16.
 */

public class GetPayListBean {

    /**
     * data : {"18":{"effective_time":30,"min_moeny":"50.0000","max_moeny":"50000.0000","list":[{"id":"1","code":"ICBC"},{"id":"2","code":"ABC"},{"id":"4","code":"CCB"},{"id":"3","code":"CMB"},{"id":"5","code":"PSBC"},{"id":"6","code":"COMM"},{"id":"7","code":"HXB"},{"id":"8","code":"CEB"},{"id":"9","code":"BOC"},{"id":"13","code":"CMBC"},{"id":"14","code":"CNCB"},{"id":"15","code":"CIB"},{"id":"18","code":"PAB"},{"id":"20","code":"SPDB"},{"id":"21","code":"GDB"},{"id":"27","code":"WEIXIN"}]},"13":{"effective_time":30,"min_moeny":"50.0000","max_moeny":"50000.0000","list":[{"id":"1","code":"ICBC"},{"id":"2","code":"ABC"},{"id":"3","code":"CMBC"},{"id":"4","code":"CCB"},{"id":"5","code":"PSBC"},{"id":"6","code":"BOCOM"},{"id":"7","code":"HXB"},{"id":"8","code":"CEBBANK"},{"id":"9","code":"BOC"},{"id":"13","code":"CMBCS"},{"id":"14","code":"ECITIC"},{"id":"15","code":"CIB"},{"id":"16","code":"BCCB"},{"id":"17","code":"BRCB"},{"id":"18","code":"PINGAN"},{"id":"19","code":"BOS"},{"id":"20","code":"SPDB"},{"id":"21","code":"CGB"}]},"1":{"effective_time":30,"min_moeny":"50.0000","max_moeny":"10000.0000","list":[{"id":"25","code":"ALIPAY","payname":"支付宝姓名","tips":"请在支付宝中选择[转到招商银行]进行转账.","payee":"收款人","payeename":"收款人姓名"}]}}
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
         * 18 : {"effective_time":30,"min_moeny":"50.0000","max_moeny":"50000.0000","list":[{"id":"1","code":"ICBC"},{"id":"2","code":"ABC"},{"id":"4","code":"CCB"},{"id":"3","code":"CMB"},{"id":"5","code":"PSBC"},{"id":"6","code":"COMM"},{"id":"7","code":"HXB"},{"id":"8","code":"CEB"},{"id":"9","code":"BOC"},{"id":"13","code":"CMBC"},{"id":"14","code":"CNCB"},{"id":"15","code":"CIB"},{"id":"18","code":"PAB"},{"id":"20","code":"SPDB"},{"id":"21","code":"GDB"},{"id":"27","code":"WEIXIN"}]}
         * 13 : {"effective_time":30,"min_moeny":"50.0000","max_moeny":"50000.0000","list":[{"id":"1","code":"ICBC"},{"id":"2","code":"ABC"},{"id":"3","code":"CMBC"},{"id":"4","code":"CCB"},{"id":"5","code":"PSBC"},{"id":"6","code":"BOCOM"},{"id":"7","code":"HXB"},{"id":"8","code":"CEBBANK"},{"id":"9","code":"BOC"},{"id":"13","code":"CMBCS"},{"id":"14","code":"ECITIC"},{"id":"15","code":"CIB"},{"id":"16","code":"BCCB"},{"id":"17","code":"BRCB"},{"id":"18","code":"PINGAN"},{"id":"19","code":"BOS"},{"id":"20","code":"SPDB"},{"id":"21","code":"CGB"}]}
         * 1 : {"effective_time":30,"min_moeny":"50.0000","max_moeny":"10000.0000","list":[{"id":"25","code":"ALIPAY","payname":"支付宝姓名","tips":"请在支付宝中选择[转到招商银行]进行转账.","payee":"收款人","payeename":"收款人姓名"}]}
         */

        @SerializedName("18")
        private _$18Bean _$18;
        @SerializedName("13")
        private _$13Bean _$13;
        @SerializedName("1")
        private _$1Bean _$1;

        public _$18Bean get_$18() {
            return _$18;
        }

        public void set_$18(_$18Bean _$18) {
            this._$18 = _$18;
        }

        public _$13Bean get_$13() {
            return _$13;
        }

        public void set_$13(_$13Bean _$13) {
            this._$13 = _$13;
        }

        public _$1Bean get_$1() {
            return _$1;
        }

        public void set_$1(_$1Bean _$1) {
            this._$1 = _$1;
        }

        public static class _$18Bean {
            /**
             * effective_time : 30
             * min_moeny : 50.0000
             * max_moeny : 50000.0000
             * list : [{"id":"1","code":"ICBC"},{"id":"2","code":"ABC"},{"id":"4","code":"CCB"},{"id":"3","code":"CMB"},{"id":"5","code":"PSBC"},{"id":"6","code":"COMM"},{"id":"7","code":"HXB"},{"id":"8","code":"CEB"},{"id":"9","code":"BOC"},{"id":"13","code":"CMBC"},{"id":"14","code":"CNCB"},{"id":"15","code":"CIB"},{"id":"18","code":"PAB"},{"id":"20","code":"SPDB"},{"id":"21","code":"GDB"},{"id":"27","code":"WEIXIN"}]
             */

            private int effective_time;
            private String min_moeny;
            private String max_moeny;
            private List<ListBean> list;

            public int getEffective_time() {
                return effective_time;
            }

            public void setEffective_time(int effective_time) {
                this.effective_time = effective_time;
            }

            public String getMin_moeny() {
                return min_moeny;
            }

            public void setMin_moeny(String min_moeny) {
                this.min_moeny = min_moeny;
            }

            public String getMax_moeny() {
                return max_moeny;
            }

            public void setMax_moeny(String max_moeny) {
                this.max_moeny = max_moeny;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * id : 1
                 * code : ICBC
                 */

                private String id;
                private String code;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }
            }
        }

        public static class _$13Bean {
            /**
             * effective_time : 30
             * min_moeny : 50.0000
             * max_moeny : 50000.0000
             * list : [{"id":"1","code":"ICBC"},{"id":"2","code":"ABC"},{"id":"3","code":"CMBC"},{"id":"4","code":"CCB"},{"id":"5","code":"PSBC"},{"id":"6","code":"BOCOM"},{"id":"7","code":"HXB"},{"id":"8","code":"CEBBANK"},{"id":"9","code":"BOC"},{"id":"13","code":"CMBCS"},{"id":"14","code":"ECITIC"},{"id":"15","code":"CIB"},{"id":"16","code":"BCCB"},{"id":"17","code":"BRCB"},{"id":"18","code":"PINGAN"},{"id":"19","code":"BOS"},{"id":"20","code":"SPDB"},{"id":"21","code":"CGB"}]
             */

            private int effective_time;
            private String min_moeny;
            private String max_moeny;
            private List<ListBeanX> list;

            public int getEffective_time() {
                return effective_time;
            }

            public void setEffective_time(int effective_time) {
                this.effective_time = effective_time;
            }

            public String getMin_moeny() {
                return min_moeny;
            }

            public void setMin_moeny(String min_moeny) {
                this.min_moeny = min_moeny;
            }

            public String getMax_moeny() {
                return max_moeny;
            }

            public void setMax_moeny(String max_moeny) {
                this.max_moeny = max_moeny;
            }

            public List<ListBeanX> getList() {
                return list;
            }

            public void setList(List<ListBeanX> list) {
                this.list = list;
            }

            public static class ListBeanX {
                /**
                 * id : 1
                 * code : ICBC
                 */

                private String id;
                private String code;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }
            }
        }

        public static class _$1Bean {
            /**
             * effective_time : 30
             * min_moeny : 50.0000
             * max_moeny : 10000.0000
             * list : [{"id":"25","code":"ALIPAY","payname":"支付宝姓名","tips":"请在支付宝中选择[转到招商银行]进行转账.","payee":"收款人","payeename":"收款人姓名"}]
             */

            private int effective_time;
            private String min_moeny;
            private String max_moeny;
            private List<ListBeanXX> list;

            public int getEffective_time() {
                return effective_time;
            }

            public void setEffective_time(int effective_time) {
                this.effective_time = effective_time;
            }

            public String getMin_moeny() {
                return min_moeny;
            }

            public void setMin_moeny(String min_moeny) {
                this.min_moeny = min_moeny;
            }

            public String getMax_moeny() {
                return max_moeny;
            }

            public void setMax_moeny(String max_moeny) {
                this.max_moeny = max_moeny;
            }

            public List<ListBeanXX> getList() {
                return list;
            }

            public void setList(List<ListBeanXX> list) {
                this.list = list;
            }

            public static class ListBeanXX {
                /**
                 * id : 25
                 * code : ALIPAY
                 * payname : 支付宝姓名
                 * tips : 请在支付宝中选择[转到招商银行]进行转账.
                 * payee : 收款人
                 * payeename : 收款人姓名
                 */

                private String id;
                private String code;
                private String payname;
                private String tips;
                private String payee;
                private String payeename;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getPayname() {
                    return payname;
                }

                public void setPayname(String payname) {
                    this.payname = payname;
                }

                public String getTips() {
                    return tips;
                }

                public void setTips(String tips) {
                    this.tips = tips;
                }

                public String getPayee() {
                    return payee;
                }

                public void setPayee(String payee) {
                    this.payee = payee;
                }

                public String getPayeename() {
                    return payeename;
                }

                public void setPayeename(String payeename) {
                    this.payeename = payeename;
                }
            }
        }
    }
}
