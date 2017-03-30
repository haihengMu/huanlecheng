package bean;

import java.io.Serializable;

public class ThousandsChildBean implements Serializable {
	
	private String id;
	private boolean b;
	public boolean isB() {
		return b;
	}

	public void setB(boolean b) {
		this.b = b;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
