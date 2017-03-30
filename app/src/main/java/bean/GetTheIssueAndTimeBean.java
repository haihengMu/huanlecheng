package bean;

import java.io.Serializable;

public class GetTheIssueAndTimeBean implements Serializable {

	private String Then_Issue;
	private String Last_Issue;
	private String isbuy;
	private String overtime;
	private String status;
	private String len;
	private String error="";
	private String msg;
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getThen_Issue() {
		return Then_Issue;
	}
	public void setThen_Issue(String then_Issue) {
		Then_Issue = then_Issue;
	}
	public String getLast_Issue() {
		return Last_Issue;
	}
	public void setLast_Issue(String last_Issue) {
		Last_Issue = last_Issue;
	}
	public String getIsbuy() {
		return isbuy;
	}
	public void setIsbuy(String isbuy) {
		this.isbuy = isbuy;
	}
	public String getOvertime() {
		return overtime;
	}
	public void setOvertime(String overtime) {
		this.overtime = overtime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLen() {
		return len;
	}
	public void setLen(String len) {
		this.len = len;
	}
}
