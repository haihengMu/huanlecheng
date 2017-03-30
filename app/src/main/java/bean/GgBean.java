package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/21.
 */

public class GgBean implements Serializable {

    /**
     * title : 标题.
     * text : 1223213内容呢?这里是内容吧?内容呢?这里是内容吧?内容呢?这里是内容吧?内容呢?这里是内容吧?
     * count : 9
     * time : 2016-12-13 02:37:25
     */

    private List<GgChildBean> data;

    public List<GgChildBean> getData() {
        return data;
    }

    public void setData(List<GgChildBean> data) {
        this.data = data;
    }

}
