package activity.huanlecheng;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import bean.LoginBean;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import http.AjaxParams;
import util.ShowToast;
import util.Utils;

public class OpenActivity extends BaseActivity {
	private Button title_left_btn;
	private TextView title_textview;
	private EditText et_dlzhm, et_yhnc, et_fdsz, et_dlmm, et_qrmm;
	private Button bt_set;
	private TextView tv_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.open_user);
		title_left_btn = (Button) findViewById(R.id.title_left_btn);
		title_left_btn.setBackgroundResource(R.drawable.aar);
		title_left_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		title_textview = (TextView) findViewById(R.id.title_textview);
		title_textview.setText("开户中心");
		et_dlzhm = (EditText) findViewById(R.id.et_dlzhm);
		et_dlzhm.setTextColor(Color.parseColor("#ffffff"));
		et_yhnc = (EditText) findViewById(R.id.et_yhnc);
		et_fdsz = (EditText) findViewById(R.id.et_fdsz);
		et_dlmm = (EditText) findViewById(R.id.et_dlmm);
		et_qrmm = (EditText) findViewById(R.id.et_qrmm);

		et_dlmm.setTextColor(Color.parseColor("#ffffff"));
		et_qrmm.setTextColor(Color.parseColor("#ffffff"));
		et_yhnc.setTextColor(Color.parseColor("#ffffff"));
		et_fdsz.setTextColor(Color.parseColor("#ffffff"));

		bt_set = (Button) findViewById(R.id.bt_set);
		bt_set.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (Utils.isEmpty(et_dlzhm.getText().toString())) {
					showToast("请填写正确登录账号名");
				} else if (et_dlzhm.getText().toString().length() < 6
						|| et_dlzhm.getText().toString().length() > 10) {
					showToast("请填写正确登录账号名");
				} else if (Utils.isEmpty(et_fdsz.getText().toString())
						|| (float) Double.parseDouble(et_fdsz.getText()
								.toString()) < 0
						|| (float) Double.parseDouble(et_fdsz.getText()
								.toString()) > 12.8) {
					showToast("请填写正确返点");
				} else if (Utils.isEmpty(et_dlmm.getText().toString())
						|| Utils.isEmpty(et_qrmm.getText().toString())) {
					showToast("请输完整信息");
				} else if (!et_dlmm.getText().toString()
						.equals(et_qrmm.getText().toString())) {
					showToast("两次密码输入不一致");
				} else {
					loadingWindow.showDialog(Constants.tjing);
					Updatemm(et_dlzhm.getText().toString(), et_yhnc.getText()
							.toString(), et_fdsz.getText().toString(), et_dlmm
							.getText().toString());
				}
			}
		});
	}

	public void Updatemm(String username, String nickname, String rebatea,
						 String userpass) {

		AjaxParams params = new AjaxParams();
		params.put("Model", "User");
		params.put("Action", "MyTeam_Add");
		params.put("U_Type", "1");
		params.put("U_UserName", username);
		params.put("U_Nickname", nickname);
		params.put("U_RebateA", rebatea);
		params.put("U_UserPass", userpass);
		wh.configCookieStore(RUser.cookieStore);
		wh.post(Constants.getUrl(), params, new AjaxCallBack<String>() {
			@Override
			public void onStart() {// 开始http请求的时候回调
				// TODO Auto-generated method stub
				super.onStart();

			}

			@Override
			public void onLoading(long count, long current) {// 每1秒钟自动被回调一次
				// TODO Auto-generated method stub
				super.onLoading(count, current);

			}

			@Override
			public void onSuccess(String t) {// 加载成功的时候回调
				// TODO Auto-generated method stub
				super.onSuccess(t);

				Message msg = new Message();
				msg.what = 0;
				msg.obj = t;
				handle.sendMessage(msg);
			}

			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) { // 加载失败的时候回调
				// TODO Auto-generated method stub
				super.onFailure(t, errorNo, strMsg);
				ShowToast.showMsg(OpenActivity.this, Constants.NETERROR);
				loadingWindow.cancel();
			}

		});

	}

	Handler handle = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			JSONObject jsonObject;
			Intent intent;

			switch (msg.what) {
			case 0:
				try {
					loadingWindow.cancel();
					String str = (String) msg.obj;
					System.out.println(str);
					jsonObject = new JSONObject(str);
					java.lang.reflect.Type type = new TypeToken<LoginBean>() {
					}.getType();
					/*LoginBean loginBean = gson.fromJson(str, type);
					if (!loginBean.getError().equals("")) {
						showToast(loginBean.getMsg());
					} else {
						showToast("添加成功！");
					}*/
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			}
		}
	};
}
