package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/2/27.
 */

public class HxNewPlay implements Serializable {
    private List<HxNewChildPlay> data;

    public List<HxNewChildPlay> getData() {
        return data;
    }

    public void setData(List<HxNewChildPlay> data) {
        this.data = data;
    }
    private String error="";

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
