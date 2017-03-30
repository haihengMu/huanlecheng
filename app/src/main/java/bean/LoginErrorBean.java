package bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/21.
 */

public class LoginErrorBean implements Serializable {

    /**
     * data :
     * error : 您输入的账号或密码错误!.
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
