package bean;

import java.io.Serializable;

public class BenefitListBean implements Serializable {

	private String A_L_Id;
	private String A_L_Picture;
	private String A_L_Title;
	private String A_L_Info;
	private String A_L_Time;
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
	public String getA_L_Id() {
		return A_L_Id;
	}
	public void setA_L_Id(String a_L_Id) {
		A_L_Id = a_L_Id;
	}
	public String getA_L_Picture() {
		return A_L_Picture;
	}
	public void setA_L_Picture(String a_L_Picture) {
		A_L_Picture = a_L_Picture;
	}
	public String getA_L_Title() {
		return A_L_Title;
	}
	public void setA_L_Title(String a_L_Title) {
		A_L_Title = a_L_Title;
	}
	public String getA_L_Info() {
		return A_L_Info;
	}
	public void setA_L_Info(String a_L_Info) {
		A_L_Info = a_L_Info;
	}
	public String getA_L_Time() {
		return A_L_Time;
	}
	public void setA_L_Time(String a_L_Time) {
		A_L_Time = a_L_Time;
	}
}
