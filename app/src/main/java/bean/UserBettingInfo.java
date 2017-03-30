package bean;


import java.io.Serializable;
import java.util.List;

public class UserBettingInfo implements Serializable{

	/**
	 * data : {"total_pages":7,"current_page":1,"count":"68","list":[{"h_b_d_id":"2457052","h_b_d_mid":"10875284","h_b_d_oid":"B_20170325171228146651","h_b_d_issue":"20170325-068","h_b_d_nid":"1","h_b_d_pid":"73","h_b_d_uid":"16561","h_b_d_uname":"aijinnew09","h_b_d_money":"1078.0000","h_b_d_bet_note":"490","h_b_d_multiple":"22.00","h_b_d_amount_mode":"0.1000","h_b_d_bet_rebate":"0.00","h_b_d_user_rebate":"13.00","h_b_d_chase_code_order":"0","h_b_d_is_win_stop":"0","h_b_d_bonus_mode":"0.0000","h_b_d_win_money":"0.0000","h_b_d_win_note":"0","h_b_d_rebate_money":"0.0000","h_b_d_state":"3","h_b_d_single":"1","h_b_d_change_orders":"0","h_b_d_md5":"c3c9624b3fd49bcc608f56734958b9cc","h_b_d_sid":"1","h_b_d_addtime":"2017-03-25 17:12:28"},{"h_b_d_id":"2456933","h_b_d_mid":"10874812","h_b_d_oid":"B_20170325171157459257","h_b_d_issue":"20170325-068","h_b_d_nid":"1","h_b_d_pid":"70","h_b_d_uid":"16561","h_b_d_uname":"aijinnew09","h_b_d_money":"1200.0000","h_b_d_bet_note":"600","h_b_d_multiple":"2.00","h_b_d_amount_mode":"1.0000","h_b_d_bet_rebate":"0.00","h_b_d_user_rebate":"13.00","h_b_d_chase_code_order":"0","h_b_d_is_win_stop":"0","h_b_d_bonus_mode":"0.0000","h_b_d_win_money":"0.0000","h_b_d_win_note":"0","h_b_d_rebate_money":"0.0000","h_b_d_state":"3","h_b_d_single":"1","h_b_d_change_orders":"0","h_b_d_md5":"b5e85be0f6abc850a0bbb5014b6bc9b5","h_b_d_sid":"1","h_b_d_addtime":"2017-03-25 17:11:57"},{"h_b_d_id":"2455720","h_b_d_mid":"10869853","h_b_d_oid":"B_20170325170447257442","h_b_d_issue":"20170325-067","h_b_d_nid":"1","h_b_d_pid":"70","h_b_d_uid":"16561","h_b_d_uname":"aijinnew09","h_b_d_money":"600.0000","h_b_d_bet_note":"600","h_b_d_multiple":"1.00","h_b_d_amount_mode":"1.0000","h_b_d_bet_rebate":"0.00","h_b_d_user_rebate":"13.00","h_b_d_chase_code_order":"0","h_b_d_is_win_stop":"0","h_b_d_bonus_mode":"0.0000","h_b_d_win_money":"0.0000","h_b_d_win_note":"0","h_b_d_rebate_money":"0.0000","h_b_d_state":"3","h_b_d_single":"1","h_b_d_change_orders":"0","h_b_d_md5":"b5e85be0f6abc850a0bbb5014b6bc9b5","h_b_d_sid":"1","h_b_d_addtime":"2017-03-25 17:04:47"},{"h_b_d_id":"2455591","h_b_d_mid":"10869594","h_b_d_oid":"B_20170325170356861810","h_b_d_issue":"20170325-067","h_b_d_nid":"1","h_b_d_pid":"73","h_b_d_uid":"16561","h_b_d_uname":"aijinnew09","h_b_d_money":"490.0000","h_b_d_bet_note":"490","h_b_d_multiple":"1.00","h_b_d_amount_mode":"1.0000","h_b_d_bet_rebate":"0.00","h_b_d_user_rebate":"13.00","h_b_d_chase_code_order":"0","h_b_d_is_win_stop":"0","h_b_d_bonus_mode":"0.0000","h_b_d_win_money":"0.0000","h_b_d_win_note":"0","h_b_d_rebate_money":"0.0000","h_b_d_state":"3","h_b_d_single":"1","h_b_d_change_orders":"0","h_b_d_md5":"c3c9624b3fd49bcc608f56734958b9cc","h_b_d_sid":"1","h_b_d_addtime":"2017-03-25 17:03:56"},{"h_b_d_id":"2454905","h_b_d_mid":"10865548","h_b_d_oid":"B_20170325165830594619","h_b_d_issue":"20170325-066","h_b_d_nid":"1","h_b_d_pid":"73","h_b_d_uid":"16561","h_b_d_uname":"aijinnew09","h_b_d_money":"490.0000","h_b_d_bet_note":"490","h_b_d_multiple":"1.00","h_b_d_amount_mode":"1.0000","h_b_d_bet_rebate":"0.00","h_b_d_user_rebate":"13.00","h_b_d_chase_code_order":"0","h_b_d_is_win_stop":"0","h_b_d_bonus_mode":"163.3333","h_b_d_win_money":"489.9999","h_b_d_win_note":"3","h_b_d_rebate_money":"0.0000","h_b_d_state":"2","h_b_d_single":"1","h_b_d_change_orders":"0","h_b_d_md5":"c3c9624b3fd49bcc608f56734958b9cc","h_b_d_sid":"1","h_b_d_addtime":"2017-03-25 16:58:30"}]}
	 */

	private DataBean data;

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public static class DataBean implements Serializable{
		/**
		 * total_pages : 7
		 * current_page : 1
		 * count : 68
		 * list : [{"h_b_d_id":"2457052","h_b_d_mid":"10875284","h_b_d_oid":"B_20170325171228146651","h_b_d_issue":"20170325-068","h_b_d_nid":"1","h_b_d_pid":"73","h_b_d_uid":"16561","h_b_d_uname":"aijinnew09","h_b_d_money":"1078.0000","h_b_d_bet_note":"490","h_b_d_multiple":"22.00","h_b_d_amount_mode":"0.1000","h_b_d_bet_rebate":"0.00","h_b_d_user_rebate":"13.00","h_b_d_chase_code_order":"0","h_b_d_is_win_stop":"0","h_b_d_bonus_mode":"0.0000","h_b_d_win_money":"0.0000","h_b_d_win_note":"0","h_b_d_rebate_money":"0.0000","h_b_d_state":"3","h_b_d_single":"1","h_b_d_change_orders":"0","h_b_d_md5":"c3c9624b3fd49bcc608f56734958b9cc","h_b_d_sid":"1","h_b_d_addtime":"2017-03-25 17:12:28"},{"h_b_d_id":"2456933","h_b_d_mid":"10874812","h_b_d_oid":"B_20170325171157459257","h_b_d_issue":"20170325-068","h_b_d_nid":"1","h_b_d_pid":"70","h_b_d_uid":"16561","h_b_d_uname":"aijinnew09","h_b_d_money":"1200.0000","h_b_d_bet_note":"600","h_b_d_multiple":"2.00","h_b_d_amount_mode":"1.0000","h_b_d_bet_rebate":"0.00","h_b_d_user_rebate":"13.00","h_b_d_chase_code_order":"0","h_b_d_is_win_stop":"0","h_b_d_bonus_mode":"0.0000","h_b_d_win_money":"0.0000","h_b_d_win_note":"0","h_b_d_rebate_money":"0.0000","h_b_d_state":"3","h_b_d_single":"1","h_b_d_change_orders":"0","h_b_d_md5":"b5e85be0f6abc850a0bbb5014b6bc9b5","h_b_d_sid":"1","h_b_d_addtime":"2017-03-25 17:11:57"},{"h_b_d_id":"2455720","h_b_d_mid":"10869853","h_b_d_oid":"B_20170325170447257442","h_b_d_issue":"20170325-067","h_b_d_nid":"1","h_b_d_pid":"70","h_b_d_uid":"16561","h_b_d_uname":"aijinnew09","h_b_d_money":"600.0000","h_b_d_bet_note":"600","h_b_d_multiple":"1.00","h_b_d_amount_mode":"1.0000","h_b_d_bet_rebate":"0.00","h_b_d_user_rebate":"13.00","h_b_d_chase_code_order":"0","h_b_d_is_win_stop":"0","h_b_d_bonus_mode":"0.0000","h_b_d_win_money":"0.0000","h_b_d_win_note":"0","h_b_d_rebate_money":"0.0000","h_b_d_state":"3","h_b_d_single":"1","h_b_d_change_orders":"0","h_b_d_md5":"b5e85be0f6abc850a0bbb5014b6bc9b5","h_b_d_sid":"1","h_b_d_addtime":"2017-03-25 17:04:47"},{"h_b_d_id":"2455591","h_b_d_mid":"10869594","h_b_d_oid":"B_20170325170356861810","h_b_d_issue":"20170325-067","h_b_d_nid":"1","h_b_d_pid":"73","h_b_d_uid":"16561","h_b_d_uname":"aijinnew09","h_b_d_money":"490.0000","h_b_d_bet_note":"490","h_b_d_multiple":"1.00","h_b_d_amount_mode":"1.0000","h_b_d_bet_rebate":"0.00","h_b_d_user_rebate":"13.00","h_b_d_chase_code_order":"0","h_b_d_is_win_stop":"0","h_b_d_bonus_mode":"0.0000","h_b_d_win_money":"0.0000","h_b_d_win_note":"0","h_b_d_rebate_money":"0.0000","h_b_d_state":"3","h_b_d_single":"1","h_b_d_change_orders":"0","h_b_d_md5":"c3c9624b3fd49bcc608f56734958b9cc","h_b_d_sid":"1","h_b_d_addtime":"2017-03-25 17:03:56"},{"h_b_d_id":"2454905","h_b_d_mid":"10865548","h_b_d_oid":"B_20170325165830594619","h_b_d_issue":"20170325-066","h_b_d_nid":"1","h_b_d_pid":"73","h_b_d_uid":"16561","h_b_d_uname":"aijinnew09","h_b_d_money":"490.0000","h_b_d_bet_note":"490","h_b_d_multiple":"1.00","h_b_d_amount_mode":"1.0000","h_b_d_bet_rebate":"0.00","h_b_d_user_rebate":"13.00","h_b_d_chase_code_order":"0","h_b_d_is_win_stop":"0","h_b_d_bonus_mode":"163.3333","h_b_d_win_money":"489.9999","h_b_d_win_note":"3","h_b_d_rebate_money":"0.0000","h_b_d_state":"2","h_b_d_single":"1","h_b_d_change_orders":"0","h_b_d_md5":"c3c9624b3fd49bcc608f56734958b9cc","h_b_d_sid":"1","h_b_d_addtime":"2017-03-25 16:58:30"}]
		 */

		private int total_pages;
		private int current_page;
		private String count;
		private List<ListBean> list;

		public int getTotal_pages() {
			return total_pages;
		}

		public void setTotal_pages(int total_pages) {
			this.total_pages = total_pages;
		}

		public int getCurrent_page() {
			return current_page;
		}

		public void setCurrent_page(int current_page) {
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

		public static class ListBean implements Serializable{
			/**
			 "h_b_d_id": "2841763",
			 "h_b_d_mid": "12545541",
			 "h_b_d_oid": "B_20170328154921391464",
			 "h_b_d_issue": "20170328-059",
			 "h_b_d_nid": "1",
			 "h_b_d_pid": "42",
			 "h_b_d_uid": "16561",
			 "h_b_d_uname": "aijinnew09",
			 "h_b_d_money": "56.0000",
			 "h_b_d_bet_note": "56",
			 "h_b_d_multiple": "1.00",
			 "h_b_d_amount_mode": "1.0000",
			 "h_b_d_bet_rebate": "13.00",
			 "h_b_d_user_rebate": "13.00",
			 "h_b_d_chase_code_order": "0",
			 "h_b_d_is_win_stop": "0",
			 "h_b_d_bonus_mode": "0.0000",
			 "h_b_d_win_money": "0.0000",
			 "h_b_d_win_note": "0",
			 "h_b_d_rebate_money": "7.2800",
			 "h_b_d_state": "3",
			 "h_b_d_single": "1",
			 "h_b_d_change_orders": "0",
			 "h_b_d_md5": "0cdd7837c16a3807a92a35403dc81e49",
			 "h_b_d_sid": "1",
			 "h_b_d_addtime": "2017-03-28 15:49:21"
			 */

			private String h_b_d_id;
			private String h_b_d_mid;
			private String h_b_d_oid;
			private String h_b_d_issue;
			private String h_b_d_nid;
			private String h_b_d_pid;
			private String h_b_d_uid;
			private String h_b_d_uname;
			private String h_b_d_money;
			private String h_b_d_bet_note;
			private String h_b_d_multiple;
			private String h_b_d_amount_mode;
			private String h_b_d_bet_rebate;
			private String h_b_d_user_rebate;
			private String h_b_d_chase_code_order;
			private String h_b_d_is_win_stop;
			private String h_b_d_bonus_mode;
			private String h_b_d_win_money;
			private String h_b_d_win_note;
			private String h_b_d_rebate_money;
			private String h_b_d_state;
			private String h_b_d_single;
			private String h_b_d_change_orders;
			private String h_b_d_md5;
			private String h_b_d_sid;
			private String h_b_d_addtime;

			public String getH_b_d_id() {
				return h_b_d_id;
			}

			public void setH_b_d_id(String h_b_d_id) {
				this.h_b_d_id = h_b_d_id;
			}

			public String getH_b_d_mid() {
				return h_b_d_mid;
			}

			public void setH_b_d_mid(String h_b_d_mid) {
				this.h_b_d_mid = h_b_d_mid;
			}

			public String getH_b_d_oid() {
				return h_b_d_oid;
			}

			public void setH_b_d_oid(String h_b_d_oid) {
				this.h_b_d_oid = h_b_d_oid;
			}

			public String getH_b_d_issue() {
				return h_b_d_issue;
			}

			public void setH_b_d_issue(String h_b_d_issue) {
				this.h_b_d_issue = h_b_d_issue;
			}

			public String getH_b_d_nid() {
				return h_b_d_nid;
			}

			public void setH_b_d_nid(String h_b_d_nid) {
				this.h_b_d_nid = h_b_d_nid;
			}

			public String getH_b_d_pid() {
				return h_b_d_pid;
			}

			public void setH_b_d_pid(String h_b_d_pid) {
				this.h_b_d_pid = h_b_d_pid;
			}

			public String getH_b_d_uid() {
				return h_b_d_uid;
			}

			public void setH_b_d_uid(String h_b_d_uid) {
				this.h_b_d_uid = h_b_d_uid;
			}

			public String getH_b_d_uname() {
				return h_b_d_uname;
			}

			public void setH_b_d_uname(String h_b_d_uname) {
				this.h_b_d_uname = h_b_d_uname;
			}

			public String getH_b_d_money() {
				return h_b_d_money;
			}

			public void setH_b_d_money(String h_b_d_money) {
				this.h_b_d_money = h_b_d_money;
			}

			public String getH_b_d_bet_note() {
				return h_b_d_bet_note;
			}

			public void setH_b_d_bet_note(String h_b_d_bet_note) {
				this.h_b_d_bet_note = h_b_d_bet_note;
			}

			public String getH_b_d_multiple() {
				return h_b_d_multiple;
			}

			public void setH_b_d_multiple(String h_b_d_multiple) {
				this.h_b_d_multiple = h_b_d_multiple;
			}

			public String getH_b_d_amount_mode() {
				return h_b_d_amount_mode;
			}

			public void setH_b_d_amount_mode(String h_b_d_amount_mode) {
				this.h_b_d_amount_mode = h_b_d_amount_mode;
			}

			public String getH_b_d_bet_rebate() {
				return h_b_d_bet_rebate;
			}

			public void setH_b_d_bet_rebate(String h_b_d_bet_rebate) {
				this.h_b_d_bet_rebate = h_b_d_bet_rebate;
			}

			public String getH_b_d_user_rebate() {
				return h_b_d_user_rebate;
			}

			public void setH_b_d_user_rebate(String h_b_d_user_rebate) {
				this.h_b_d_user_rebate = h_b_d_user_rebate;
			}

			public String getH_b_d_chase_code_order() {
				return h_b_d_chase_code_order;
			}

			public void setH_b_d_chase_code_order(String h_b_d_chase_code_order) {
				this.h_b_d_chase_code_order = h_b_d_chase_code_order;
			}

			public String getH_b_d_is_win_stop() {
				return h_b_d_is_win_stop;
			}

			public void setH_b_d_is_win_stop(String h_b_d_is_win_stop) {
				this.h_b_d_is_win_stop = h_b_d_is_win_stop;
			}

			public String getH_b_d_bonus_mode() {
				return h_b_d_bonus_mode;
			}

			public void setH_b_d_bonus_mode(String h_b_d_bonus_mode) {
				this.h_b_d_bonus_mode = h_b_d_bonus_mode;
			}

			public String getH_b_d_win_money() {
				return h_b_d_win_money;
			}

			public void setH_b_d_win_money(String h_b_d_win_money) {
				this.h_b_d_win_money = h_b_d_win_money;
			}

			public String getH_b_d_win_note() {
				return h_b_d_win_note;
			}

			public void setH_b_d_win_note(String h_b_d_win_note) {
				this.h_b_d_win_note = h_b_d_win_note;
			}

			public String getH_b_d_rebate_money() {
				return h_b_d_rebate_money;
			}

			public void setH_b_d_rebate_money(String h_b_d_rebate_money) {
				this.h_b_d_rebate_money = h_b_d_rebate_money;
			}

			public String getH_b_d_state() {
				return h_b_d_state;
			}

			public void setH_b_d_state(String h_b_d_state) {
				this.h_b_d_state = h_b_d_state;
			}

			public String getH_b_d_single() {
				return h_b_d_single;
			}

			public void setH_b_d_single(String h_b_d_single) {
				this.h_b_d_single = h_b_d_single;
			}

			public String getH_b_d_change_orders() {
				return h_b_d_change_orders;
			}

			public void setH_b_d_change_orders(String h_b_d_change_orders) {
				this.h_b_d_change_orders = h_b_d_change_orders;
			}

			public String getH_b_d_md5() {
				return h_b_d_md5;
			}

			public void setH_b_d_md5(String h_b_d_md5) {
				this.h_b_d_md5 = h_b_d_md5;
			}

			public String getH_b_d_sid() {
				return h_b_d_sid;
			}

			public void setH_b_d_sid(String h_b_d_sid) {
				this.h_b_d_sid = h_b_d_sid;
			}

			public String getH_b_d_addtime() {
				return h_b_d_addtime;
			}

			public void setH_b_d_addtime(String h_b_d_addtime) {
				this.h_b_d_addtime = h_b_d_addtime;
			}
		}
	}
}
