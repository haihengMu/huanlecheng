package bean;

import java.io.Serializable;
import java.util.List;

public class BankResponseModel implements Serializable {

	private List<DataBean> data;

	public List<DataBean> getData() {
		return data;
	}

	public void setData(List<DataBean> data) {
		this.data = data;
	}

	public static class DataBean implements Serializable{
		/**
		 * id : 1
		 * name : 中国工商银行
		 * link : https://mybank.icbc.com.cn/icbc/perbank/index.jsp
		 * min_money : 100.00
		 * max_money : 49999.00
		 */

		private String id;
		private String name;
		private String link;
		private String min_money;
		private String max_money;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}

		public String getMin_money() {
			return min_money;
		}

		public void setMin_money(String min_money) {
			this.min_money = min_money;
		}

		public String getMax_money() {
			return max_money;
		}

		public void setMax_money(String max_money) {
			this.max_money = max_money;
		}
	}
}
