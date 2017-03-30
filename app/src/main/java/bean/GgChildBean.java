package bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/25.
 */

public class GgChildBean implements Serializable {
    private String title;
    private String text;
    private String count;
    private String time;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
