package bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/25.
 */

public class AllcheBean implements Serializable {

    /**
     * data :
     * error : 没有找到可以撤单的订单.
     */

    private String data;
    private String error;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
