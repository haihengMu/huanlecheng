package bean;

import java.io.Serializable;

public class HX_Game_NameResponseModel implements Serializable {

	private String G_N_Id;
	private String G_N_Title;
	private String G_N_Off;
	public String getG_N_Id() {
		return G_N_Id;
	}
	public void setG_N_Id(String g_N_Id) {
		G_N_Id = g_N_Id;
	}
	public String getG_N_Title() {
		return G_N_Title;
	}
	public void setG_N_Title(String g_N_Title) {
		G_N_Title = g_N_Title;
	}
	public String getG_N_Off() {
		return G_N_Off;
	}
	public void setG_N_Off(String g_N_Off) {
		G_N_Off = g_N_Off;
	}
}
