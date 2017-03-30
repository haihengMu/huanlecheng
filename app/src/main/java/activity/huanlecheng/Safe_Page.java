package activity.huanlecheng;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import bean.MiBaoBean;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import http.AjaxParams;
import util.ShowToast;
import util.Utils;

public class Safe_Page extends BaseActivity {
	private Spinner spinner;
	private Button title_left_btn;
	private TextView title_textview;
	private EditText et_mm;// 答案
	private EditText et_pw;// 密码
	private Button bt_set;//

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.safe_problem);
		spinner = (Spinner) findViewById(R.id.spin);
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				TextView tv=(TextView)view;
				tv.setTextColor(Safe_Page.this.getResources().getColor(R.color.white));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
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
		title_textview.setText("设置密保");
		// String choose = spinner.getSelectedItem().toString();
		et_mm = (EditText) findViewById(R.id.et_mm);
		et_pw = (EditText) findViewById(R.id.et_pw);
		bt_set = (Button) findViewById(R.id.bt_set);

		et_mm.setTextColor(Color.parseColor("#ffffff"));
		et_pw.setTextColor(Color.parseColor("#ffffff"));
		bt_set.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (spinner.getSelectedItem().toString().equals("请选择问题")) {
					showToast("请选择密保问题");
				}else if ( Utils.isEmpty(et_mm.getText().toString())){
					showToast("问题答案不能为空");
				}else if (Utils.isEmpty(et_pw.getText().toString())){
					showToast("安全密码不能为空");
				}else {
					loadingWindow.showDialog(Constants.tjing);
					Updatemm(spinner.getSelectedItemPosition()+"", et_mm
							.getText().toString(), et_pw.getText().toString());
				}
			}
		});
	}

	public void Updatemm(String U_PassWord, String U_PassWord1,
						 String U_PassWord2) {
		AjaxParams params=new AjaxParams();
		params.put("qid",U_PassWord);
		params.put("answer",U_PassWord1);
		params.put("pass",U_PassWord2);
		wh.configCookieStore(RUser.cookieStore);
		wh.post(Constants.getUrl()+Constants.setsafe,params, new AjaxCallBack<String>() {
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
				ShowToast.showMsg(Safe_Page.this, Constants.NETERROR);
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
						if (str.indexOf("绑定成功")!=-1){
							jsonObject = new JSONObject(str);
							java.lang.reflect.Type type = new TypeToken<MiBaoBean>() {
							}.getType();
							MiBaoBean loginBean = gson.fromJson(str, type);
							showToast(loginBean.getData());
							finish();
						}else {
							showToast("绑定失败");
						}

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;
			}
		}
	};
}
