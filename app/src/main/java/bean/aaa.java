package bean;

import java.util.List;

/**
 * hang
 */

public class aaa {
    /**
     * codes : [{"amount":"1","bet_num":"1","code":"5","multiple":"1","p1d":"104"},{"amount":"1","bet_num":"1","code":"5","multiple":"1","p1d":"104"},{"amount":"1","bet_num":"1","code":"5","multiple":"1","p1d":"104"}]
     * gid : 2
     * issue : 20170321-032
     */

    private String gid;
    private String issue;
    private List<CodesBean> codes;

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public List<CodesBean> getCodes() {
        return codes;
    }

    public void setCodes(List<CodesBean> codes) {
        this.codes = codes;
    }

    public static class CodesBean {
        /**
         * amount : 1
         * bet_num : 1
         * code : 5
         * multiple : 1
         * p1d : 104
         */

        private String amount;
        private String bet_num;
        private String code;
        private String multiple;
        private String p1d;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getBet_num() {
            return bet_num;
        }

        public void setBet_num(String bet_num) {
            this.bet_num = bet_num;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMultiple() {
            return multiple;
        }

        public void setMultiple(String multiple) {
            this.multiple = multiple;
        }

        public String getP1d() {
            return p1d;
        }

        public void setP1d(String p1d) {
            this.p1d = p1d;
        }
    }
}
