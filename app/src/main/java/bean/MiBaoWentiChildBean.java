package bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/25.
 */

public class MiBaoWentiChildBean implements Serializable {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
