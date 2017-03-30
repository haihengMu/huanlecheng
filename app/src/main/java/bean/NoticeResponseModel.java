package bean;

import java.io.Serializable;
import java.util.List;

public class NoticeResponseModel implements Serializable {

	private String error="";
	private String msg;
	private List<NoticeBean> HX_Web_Msg;
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
	public List<NoticeBean> getHX_Web_Msg() {
		return HX_Web_Msg;
	}
	public void setHX_Web_Msg(List<NoticeBean> hX_Web_Msg) {
		HX_Web_Msg = hX_Web_Msg;
	}
}
