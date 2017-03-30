package bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/7.
 */

public class HTPTBankCodeBean implements Serializable {
    private String code;
    private String id;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
