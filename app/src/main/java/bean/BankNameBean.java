package bean;

/**
 * Created by Mhc on 2017/3/20.
 */

public class BankNameBean {


    /**
     * data :
     * error : 银行开户姓名错误,只允许汉字长度2-8个字符.
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
