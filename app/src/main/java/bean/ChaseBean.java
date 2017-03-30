package bean;

import java.io.Serializable;

public class ChaseBean implements Serializable {

	private String G_T_Issue;
	private String G_T_EndTime;
	private boolean b = false;
	private String bs="1";
	private String jz="1";
	public String getJz() {
		return jz;
	}

	public void setJz(String jz) {
		this.jz = jz;
	}

	public String getBs() {
		return bs;
	}

	public void setBs(String bs) {
		this.bs = bs;
	}

	public boolean isB() {
		return b;
	}

	public void setB(boolean b) {
		this.b = b;
	}

	public String getG_T_Issue() {
		return G_T_Issue;
	}

	public void setG_T_Issue(String g_T_Issue) {
		G_T_Issue = g_T_Issue;
	}

	public String getG_T_EndTime() {
		return G_T_EndTime;
	}

	public void setG_T_EndTime(String g_T_EndTime) {
		G_T_EndTime = g_T_EndTime;
	}
}
