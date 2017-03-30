package activity.huanlecheng;

import android.content.Intent;
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

public class Cardbd_name extends BaseActivity {
	private Button title_left_btn;
	private TextView title_textview;
	private EditText et_mm;
	private Button btn_set;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.card_bd_name);
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
		title_textview.setText("卡号绑定");
		et_mm = (EditText) findViewById(R.id.et_mm);
		btn_set = (Button) findViewById(R.id.bt_set);
		btn_set.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (Utils.isEmpty(et_mm.getText().toString())) {
					showToast("请输入姓名");
				} else {
					loadingWindow.showDialog(Constants.tjing);
					Updatemm(et_mm.getText().toString());
				}
			}
		});
	}

	public void Updatemm(String U_PassWord) {

		AjaxParams params = new AjaxParams();
		params.put("Model", "User");
		params.put("Action", "bind_bank_username");
		params.put("U_WithdrawalsName", U_PassWord);
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
				ShowToast.showMsg(Cardbd_name.this, Constants.NETERROR);
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
					LoginBean loginBean = gson.fromJson(str, type);
				/*	if (!loginBean.getError().equals("")) {
						showToast(loginBean.getMsg());
					} else {
						showToast("修改成功！");
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
