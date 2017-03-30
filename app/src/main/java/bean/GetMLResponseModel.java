package bean;

import java.io.Serializable;
import java.util.List;

public class GetMLResponseModel implements Serializable {

	/**
	 * data : {"total_pages":34,"current_page":"2","count":"336","list":[{"h_f_d_oid":"B_20170328121329522079","h_f_d_type":"3","h_f_d_before_balance":"65.5761","h_f_d_money":"-1.0000","h_f_d_afterpt_balance":"64.5761","h_f_d_state":"1","h_f_d_remarks":"发起代购","h_f_d_addtime":"2017-03-28 12:13:29"},{"h_f_d_oid":"A_20170328020510882171","h_f_d_type":"7","h_f_d_before_balance":"65.2761","h_f_d_money":"0.3000","h_f_d_afterpt_balance":"65.5761","h_f_d_state":"1","h_f_d_remarks":"参与:[日工资 - 直属]活动 时间:[2017-03-27 - 2017-03-27] 金额:[0.3000元].","h_f_d_addtime":"2017-03-28 02:05:10"},{"h_f_d_oid":"B_20170327203432540475","h_f_d_type":"5","h_f_d_before_balance":"62.6761","h_f_d_money":"2.6000","h_f_d_afterpt_balance":"65.2761","h_f_d_state":"1","h_f_d_remarks":"系统返点","h_f_d_addtime":"2017-03-27 20:35:48"},{"h_f_d_oid":"B_20170327203432540475","h_f_d_type":"3","h_f_d_before_balance":"82.6761","h_f_d_money":"-20.0000","h_f_d_afterpt_balance":"62.6761","h_f_d_state":"1","h_f_d_remarks":"发起代购","h_f_d_addtime":"2017-03-27 20:34:32"},{"h_f_d_oid":"A_20170326020510572093","h_f_d_type":"7","h_f_d_before_balance":"24.8061","h_f_d_money":"57.8700","h_f_d_afterpt_balance":"82.6761","h_f_d_state":"1","h_f_d_remarks":"参与:[日工资 - 直属]活动 时间:[2017-03-25 - 2017-03-25] 金额:[57.8700元].","h_f_d_addtime":"2017-03-26 02:05:10"},{"h_f_d_oid":"B_20170325171228146651","h_f_d_type":"3","h_f_d_before_balance":"1102.8061","h_f_d_money":"-1078.0000","h_f_d_afterpt_balance":"24.8061","h_f_d_state":"1","h_f_d_remarks":"发起代购","h_f_d_addtime":"2017-03-25 17:12:28"},{"h_f_d_oid":"B_20170325171157459257","h_f_d_type":"3","h_f_d_before_balance":"2302.8061","h_f_d_money":"-1200.0000","h_f_d_afterpt_balance":"1102.8061","h_f_d_state":"1","h_f_d_remarks":"发起代购","h_f_d_addtime":"2017-03-25 17:11:57"},{"h_f_d_oid":"B_20170325170447257442","h_f_d_type":"3","h_f_d_before_balance":"2902.8061","h_f_d_money":"-600.0000","h_f_d_afterpt_balance":"2302.8061","h_f_d_state":"1","h_f_d_remarks":"发起代购","h_f_d_addtime":"2017-03-25 17:04:47"},{"h_f_d_oid":"B_20170325170356861810","h_f_d_type":"3","h_f_d_before_balance":"3392.8061","h_f_d_money":"-490.0000","h_f_d_afterpt_balance":"2902.8061","h_f_d_state":"1","h_f_d_remarks":"发起代购","h_f_d_addtime":"2017-03-25 17:03:56"},{"h_f_d_oid":"B_20170325165830594619","h_f_d_type":"4","h_f_d_before_balance":"2902.8062","h_f_d_money":"489.9999","h_f_d_afterpt_balance":"3392.8061","h_f_d_state":"1","h_f_d_remarks":"系统派奖","h_f_d_addtime":"2017-03-25 17:01:18"}]}
	 */

	private DataBean data;

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public static class DataBean {
		/**
		 * total_pages : 34
		 * current_page : 2
		 * count : 336
		 * list : [{"h_f_d_oid":"B_20170328121329522079","h_f_d_type":"3","h_f_d_before_balance":"65.5761","h_f_d_money":"-1.0000","h_f_d_afterpt_balance":"64.5761","h_f_d_state":"1","h_f_d_remarks":"发起代购","h_f_d_addtime":"2017-03-28 12:13:29"},{"h_f_d_oid":"A_20170328020510882171","h_f_d_type":"7","h_f_d_before_balance":"65.2761","h_f_d_money":"0.3000","h_f_d_afterpt_balance":"65.5761","h_f_d_state":"1","h_f_d_remarks":"参与:[日工资 - 直属]活动 时间:[2017-03-27 - 2017-03-27] 金额:[0.3000元].","h_f_d_addtime":"2017-03-28 02:05:10"},{"h_f_d_oid":"B_20170327203432540475","h_f_d_type":"5","h_f_d_before_balance":"62.6761","h_f_d_money":"2.6000","h_f_d_afterpt_balance":"65.2761","h_f_d_state":"1","h_f_d_remarks":"系统返点","h_f_d_addtime":"2017-03-27 20:35:48"},{"h_f_d_oid":"B_20170327203432540475","h_f_d_type":"3","h_f_d_before_balance":"82.6761","h_f_d_money":"-20.0000","h_f_d_afterpt_balance":"62.6761","h_f_d_state":"1","h_f_d_remarks":"发起代购","h_f_d_addtime":"2017-03-27 20:34:32"},{"h_f_d_oid":"A_20170326020510572093","h_f_d_type":"7","h_f_d_before_balance":"24.8061","h_f_d_money":"57.8700","h_f_d_afterpt_balance":"82.6761","h_f_d_state":"1","h_f_d_remarks":"参与:[日工资 - 直属]活动 时间:[2017-03-25 - 2017-03-25] 金额:[57.8700元].","h_f_d_addtime":"2017-03-26 02:05:10"},{"h_f_d_oid":"B_20170325171228146651","h_f_d_type":"3","h_f_d_before_balance":"1102.8061","h_f_d_money":"-1078.0000","h_f_d_afterpt_balance":"24.8061","h_f_d_state":"1","h_f_d_remarks":"发起代购","h_f_d_addtime":"2017-03-25 17:12:28"},{"h_f_d_oid":"B_20170325171157459257","h_f_d_type":"3","h_f_d_before_balance":"2302.8061","h_f_d_money":"-1200.0000","h_f_d_afterpt_balance":"1102.8061","h_f_d_state":"1","h_f_d_remarks":"发起代购","h_f_d_addtime":"2017-03-25 17:11:57"},{"h_f_d_oid":"B_20170325170447257442","h_f_d_type":"3","h_f_d_before_balance":"2902.8061","h_f_d_money":"-600.0000","h_f_d_afterpt_balance":"2302.8061","h_f_d_state":"1","h_f_d_remarks":"发起代购","h_f_d_addtime":"2017-03-25 17:04:47"},{"h_f_d_oid":"B_20170325170356861810","h_f_d_type":"3","h_f_d_before_balance":"3392.8061","h_f_d_money":"-490.0000","h_f_d_afterpt_balance":"2902.8061","h_f_d_state":"1","h_f_d_remarks":"发起代购","h_f_d_addtime":"2017-03-25 17:03:56"},{"h_f_d_oid":"B_20170325165830594619","h_f_d_type":"4","h_f_d_before_balance":"2902.8062","h_f_d_money":"489.9999","h_f_d_afterpt_balance":"3392.8061","h_f_d_state":"1","h_f_d_remarks":"系统派奖","h_f_d_addtime":"2017-03-25 17:01:18"}]
		 */

		private int total_pages;
		private String current_page;
		private String count;
		private List<ListBean> list;

		public int getTotal_pages() {
			return total_pages;
		}

		public void setTotal_pages(int total_pages) {
			this.total_pages = total_pages;
		}

		public String getCurrent_page() {
			return current_page;
		}

		public void setCurrent_page(String current_page) {
			this.current_page = current_page;
		}

		public String getCount() {
			return count;
		}

		public void setCount(String count) {
			this.count = count;
		}

		public List<ListBean> getList() {
			return list;
		}

		public void setList(List<ListBean> list) {
			this.list = list;
		}

		public static class ListBean {
			/**
			 * h_f_d_oid : B_20170328121329522079
			 * h_f_d_type : 3
			 * h_f_d_before_balance : 65.5761
			 * h_f_d_money : -1.0000
			 * h_f_d_afterpt_balance : 64.5761
			 * h_f_d_state : 1
			 * h_f_d_remarks : 发起代购
			 * h_f_d_addtime : 2017-03-28 12:13:29
			 */

			private String h_f_d_oid;
			private String h_f_d_type;
			private String h_f_d_before_balance;
			private String h_f_d_money;
			private String h_f_d_afterpt_balance;
			private String h_f_d_state;
			private String h_f_d_remarks;
			private String h_f_d_addtime;

			public String getH_f_d_oid() {
				return h_f_d_oid;
			}

			public void setH_f_d_oid(String h_f_d_oid) {
				this.h_f_d_oid = h_f_d_oid;
			}

			public String getH_f_d_type() {
				return h_f_d_type;
			}

			public void setH_f_d_type(String h_f_d_type) {
				this.h_f_d_type = h_f_d_type;
			}

			public String getH_f_d_before_balance() {
				return h_f_d_before_balance;
			}

			public void setH_f_d_before_balance(String h_f_d_before_balance) {
				this.h_f_d_before_balance = h_f_d_before_balance;
			}

			public String getH_f_d_money() {
				return h_f_d_money;
			}

			public void setH_f_d_money(String h_f_d_money) {
				this.h_f_d_money = h_f_d_money;
			}

			public String getH_f_d_afterpt_balance() {
				return h_f_d_afterpt_balance;
			}

			public void setH_f_d_afterpt_balance(String h_f_d_afterpt_balance) {
				this.h_f_d_afterpt_balance = h_f_d_afterpt_balance;
			}

			public String getH_f_d_state() {
				return h_f_d_state;
			}

			public void setH_f_d_state(String h_f_d_state) {
				this.h_f_d_state = h_f_d_state;
			}

			public String getH_f_d_remarks() {
				return h_f_d_remarks;
			}

			public void setH_f_d_remarks(String h_f_d_remarks) {
				this.h_f_d_remarks = h_f_d_remarks;
			}

			public String getH_f_d_addtime() {
				return h_f_d_addtime;
			}

			public void setH_f_d_addtime(String h_f_d_addtime) {
				this.h_f_d_addtime = h_f_d_addtime;
			}
		}
	}
}
