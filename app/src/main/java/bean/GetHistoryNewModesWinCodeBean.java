package bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/22.
 */

public class GetHistoryNewModesWinCodeBean implements Serializable {
    private String code;
    private String issue;
    private String native_code;
    private String time;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getNative_code() {
        return native_code;
    }

    public void setNative_code(String native_code) {
        this.native_code = native_code;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
