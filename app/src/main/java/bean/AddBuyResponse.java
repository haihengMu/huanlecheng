package bean;

import java.io.Serializable;

public class AddBuyResponse implements Serializable {
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
}
