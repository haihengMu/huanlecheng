package bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class BenefitDetailResponseModel implements Serializable {

	private List<InfoListBean> info;
	private Map<String, String> rules;
	public List<InfoListBean> getInfo() {
		return info;
	}
	public void setInfo(List<InfoListBean> info) {
		this.info = info;
	}
	public Map<String, String> getRules() {
		return rules;
	}
	public void setRules(Map<String, String> rules) {
		this.rules = rules;
	}
}
