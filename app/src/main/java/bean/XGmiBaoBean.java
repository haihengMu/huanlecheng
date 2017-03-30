package bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/20.
 */

public class XGmiBaoBean implements Serializable {

    /**
     * error : 0
     * msg : 验证原密保资料失败,请确认你的原密保资料是否正确!
     */
    private String error;
    private String data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getdata() {
        return data;
    }

    public void setdata(String data) {
        this.data = data;
    }
}
