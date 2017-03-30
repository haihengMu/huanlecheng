package bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/22.
 */

public class DQIssuseChildBean implements Serializable{
    private String introduction;
    private int code_len;
    @SerializedName("switch")
    private int switchX;
    private int maxissue;
    private int status;
    private int over_time;
    private int is_buy;
    private String curr_issue;
    private String last_issue;
    private int issue;
    private String error="";

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getCode_len() {
        return code_len;
    }

    public void setCode_len(int code_len) {
        this.code_len = code_len;
    }

    public int getSwitchX() {
        return switchX;
    }

    public void setSwitchX(int switchX) {
        this.switchX = switchX;
    }

    public int getMaxissue() {
        return maxissue;
    }

    public void setMaxissue(int maxissue) {
        this.maxissue = maxissue;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getOver_time() {
        return over_time;
    }

    public void setOver_time(int over_time) {
        this.over_time = over_time;
    }

    public int getIs_buy() {
        return is_buy;
    }

    public void setIs_buy(int is_buy) {
        this.is_buy = is_buy;
    }

    public String getCurr_issue() {
        return curr_issue;
    }

    public void setCurr_issue(String curr_issue) {
        this.curr_issue = curr_issue;
    }

    public String getLast_issue() {
        return last_issue;
    }

    public void setLast_issue(String last_issue) {
        this.last_issue = last_issue;
    }

    public int getIssue() {
        return issue;
    }

    public void setIssue(int issue) {
        this.issue = issue;
    }
}
