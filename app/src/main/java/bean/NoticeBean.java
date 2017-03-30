package bean;

import java.io.Serializable;

public class NoticeBean implements Serializable {

	private String W_M_Id;
	private String W_M_Title;
	private String W_M_Content;
	private String W_M_AddTime;
	public String getW_M_Id() {
		return W_M_Id;
	}
	public void setW_M_Id(String w_M_Id) {
		W_M_Id = w_M_Id;
	}
	public String getW_M_Title() {
		return W_M_Title;
	}
	public void setW_M_Title(String w_M_Title) {
		W_M_Title = w_M_Title;
	}
	public String getW_M_Content() {
		return W_M_Content;
	}
	public void setW_M_Content(String w_M_Content) {
		W_M_Content = w_M_Content;
	}
	public String getW_M_AddTime() {
		return W_M_AddTime;
	}
	public void setW_M_AddTime(String w_M_AddTime) {
		W_M_AddTime = w_M_AddTime;
	}
}
