package bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/7.
 */

public class BankCZQrenBean implements Serializable {


    private String img;
    private String msg;
    private int type;
    /**
     * error : 1
     * status : ERROR
     */

    private int error;
    private String status;


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
