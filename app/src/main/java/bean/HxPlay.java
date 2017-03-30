package bean;

import java.util.List;

public class HxPlay {
	private List<HX_Game_PlayResponseModel> HX_Game_Play;
	private String error="";
	private String msg;
	public List<HX_Game_PlayResponseModel> getHX_Game_Play() {
		return HX_Game_Play;
	}
	public void setHX_Game_Play(List<HX_Game_PlayResponseModel> hX_Game_Play) {
		HX_Game_Play = hX_Game_Play;
	}
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
