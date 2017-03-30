package bean;

import java.io.Serializable;
import java.util.List;

public class RecordsResponseModel implements Serializable {

	private String count;
	private String error="";
	private List<GetRecordsListResponseModel> msg;
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public List<GetRecordsListResponseModel> getMsg() {
		return msg;
	}
	public void setMsg(List<GetRecordsListResponseModel> msg) {
		this.msg = msg;
	}
}
