package bean;

import java.io.Serializable;
import java.util.List;

public class HxGame implements Serializable {

	private List<HX_Game_NameResponseModel> HX_Game_Name;
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

	public List<HX_Game_NameResponseModel> getHX_Game_Name() {
		return HX_Game_Name;
	}

	public void setHX_Game_Name(List<HX_Game_NameResponseModel> hX_Game_Name) {
		HX_Game_Name = hX_Game_Name;
	}
}
