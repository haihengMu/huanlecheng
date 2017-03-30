package bean;

/**
 * Created by Administrator on 2016/11/18.
 */

public class TixianBean  {

    /**
     * error : 1
     * msg : 对不起,取款密码错误,请重试.
     */

    private int error;
    private String msg;
    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
