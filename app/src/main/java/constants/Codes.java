package constants;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/21.
 */

public class Codes implements Serializable {
    private String pid;
    private String code;
    private String multiple;
    private String bt_num;
    private String amount_mode;


    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    public String getBt_num() {
        return bt_num;
    }

    public void setBt_num(String bt_num) {
        this.bt_num = bt_num;
    }

    public String getAmount_mode() {
        return amount_mode;
    }

    public void setAmount_mode(String amount_mode) {
        this.amount_mode = amount_mode;
    }
}
