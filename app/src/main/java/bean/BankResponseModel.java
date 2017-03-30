package bean;

import java.io.Serializable;
import java.util.List;

public class BankResponseModel implements Serializable {
	private String msg;
	private String error="";
	private List<BankBean> HX_Bank_Config;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List<BankBean> getHX_Bank_Config() {
		return HX_Bank_Config;
	}

	public void setHX_Bank_Config(List<BankBean> hX_Bank_Config) {
		HX_Bank_Config = hX_Bank_Config;
	}
}
