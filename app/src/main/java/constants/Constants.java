package constants;

public class Constants {

	public static final String NETERROR = "网络异常，请检查您的网络";
	public static final String sending = "发送中...";
	public static final String logining = "登录中...";
	public static final String tjing = "提交中...";
	public static final String loading = "载入中...";
	public static final String del = "删除中...";
	public static final String addcar = "加入中...";
	public static final String order_do = "支付中...";
	public static final String sava = "保存中...";
	public static final String denglu = "登录中...";
	public static final String jiazaizhong = "正在加载....";

	/**
	 * 数据库名称
	 */
	public static final String DATABASE_NAME = "user_manager.db";
	/**
	 * 访问地址 base_url 正是地址 test_url 测试地址 是否使用测试baseUrl，true为是
	 */
	public static final String base_url =  "http://hlc.client.cool/";
	public static final String img_url = "http://static.public.ms/pw/Template/Skin_1/images/activity/";
	public static final String test_url = "http://182.92.224.199/";
	public static boolean IS_TEST_URL = false;

	public static final String logincode = "images/set_check_code?";
	public static final String tuichulogin = "users/sign_out";
	public static final String login = "users/login?";
	public static final String ggx = "system/get_notice_details?";
	public static final String grxx = "users/get_user_info";
	public static final String game_play = "static/model/public/json/game_name.json";//游戏名称
	public static final String game = "/static/model/public/json/game_type.json";//游戏分类
	public static final String kjjilu = "lottery/get_history_win_code";//开奖记录
	public static final String gonggao = "/static/model/public/json/notice.json";//公告列表
	public static final String huodongliebiao = "/activity/get_activity_list?";//活动列表
	public static final String tuichu = "/users/sign_out";//退出登录
	public static final String all_che = "/lottery/undo_bet_order_all";//全部撤单
	public static final String d_che = "/lottery/undo_bet_order";//全部撤单
	public static final String game_play_new = "/static/model/public/json/game_play.json";//
	public static final String game_play_new_xiangqing = "/static/model/public/json/game_play_config_";//
	public static final String newpass = "users/set_user_login_pass?";//修改密码
	public static final String moneypass = "users/set_user_bank_pass?";//修改安全密码
	public static final String setsafe = "users/set_user_security_question";//设置安全问题
	public static final String xiugsafe = "users/edit_user_security_question?";//修改安全问题
	public static final String bankname = "/users/set_user_real_name?";//绑定银行账户姓名
	public static final String qihao = "lottery/get_lottery_times?";//绑定银行账户姓名
	public static final String touzhu = "lottery/add_bet?";//投注




	/**
	 * 判断地址方法
	 */
	public static String getBaseUrl() {
		return Constants.IS_TEST_URL ? test_url : base_url;
	}

	public static String getUrl() {
		return getBaseUrl();
	}
}
