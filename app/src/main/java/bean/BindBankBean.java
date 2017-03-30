package bean;

import java.io.Serializable;

/**
 * Created by Mhc on 2017/3/21.
 */

public class BindBankBean implements Serializable {

    /**
     * data :
     * error :
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
