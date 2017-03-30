package constants;


import org.apache.http.client.CookieStore;

public class RUser {
	public static int yzm_time = 60;// 验证码计时 忘记密码
	public static int yzm_time_reg = 60;// 验证码计时 注册
	public static boolean finished = false;// 控制线程停止
	public static int complete_time = 1;// 下拉刷新成功显示时间
	public static int page_size = 10;// 列表条数
	public static String id="1";//玩法g_n_id
	public static CookieStore cookieStore=null;
//	 DefaultHttpClient client=(DefaultHttpClient)finalHttp.getHttpClient();
//     MyCookieStore.cookieStore = client.getCookieStore();
//	 finalHttp.configCookieStore(MyCookieStore.cookieStore);
}
