package bean;

import java.io.Serializable;
import java.util.List;

public class ThousandsBean implements Serializable {

	private String thouString;
	private List<ThousandsChildBean> thousandsChildBeans;
	public String getThouString() {
		return thouString;
	}
	public void setThouString(String thouString) {
		this.thouString = thouString;
	}
	public List<ThousandsChildBean> getThousandsChildBeans() {
		return thousandsChildBeans;
	}
	public void setThousandsChildBeans(List<ThousandsChildBean> thousandsChildBeans) {
		this.thousandsChildBeans = thousandsChildBeans;
	}
}
