package bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/30.
 */

public class AndroidBean implements Serializable {
    private String note;
    private String title;
    private String url;
    private String version;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
