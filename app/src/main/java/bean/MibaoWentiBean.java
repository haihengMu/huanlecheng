package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/2/25.
 */

public class MibaoWentiBean implements Serializable {

    /**
     * id : 1
     * name : 您祖母叫什么名字？
     */

    private List<MiBaoWentiChildBean> data;

    public List<MiBaoWentiChildBean> getData() {
        return data;
    }

    public void setData(List<MiBaoWentiChildBean> data) {
        this.data = data;
    }

}
