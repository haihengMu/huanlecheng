package bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/25.
 */

public class GaiMiBean implements Serializable {

    /**
     * data :
     * error : 新密码不正确,长度6-16位.
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
