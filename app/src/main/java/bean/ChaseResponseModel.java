package bean;

import java.io.Serializable;
import java.util.List;

public class ChaseResponseModel implements Serializable {

	private String error="";
	private String msg;
	private List<ChaseBean> list;
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
	public List<ChaseBean> getList() {
		return list;
	}
	public void setList(List<ChaseBean> list) {
		this.list = list;
	}
}
