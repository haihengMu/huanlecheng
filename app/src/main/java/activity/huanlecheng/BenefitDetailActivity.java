package activity.huanlecheng;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapter.BenefitAdapter;
import bean.BenefitListBean;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import http.AjaxParams;
import util.ShowToast;
import view.XListView;

public class BenefitDetailActivity extends BaseActivity implements OnClickListener {
	private String id, title;
	private Button title_left_btn;
	private TextView title_textview;
	private ListView lv;
	private LinearLayout load_layout;// 载入中
	private LinearLayout load_layout_fail;// 载入失败
	private TextView txt_neterr;// 载入失败
	private List<BenefitListBean> mList;
	private BenefitAdapter ina;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_benefit_detail);
		id = getIntent().getStringExtra("id");
		title = getIntent().getStringExtra("title");
		title_left_btn = (Button) findViewById(R.id.title_left_btn);
		title_left_btn.setBackgroundResource(R.drawable.aar);
		title_left_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		mList = new ArrayList<BenefitListBean>();
		title_textview = (TextView) findViewById(R.id.title_textview);
		title_textview.setText(title);
		load_layout = (LinearLayout) findViewById(R.id.view_loading);
		load_layout_fail = (LinearLayout) findViewById(R.id.view_load_fail);
		ina = new BenefitAdapter(this);
		lv = (XListView) findViewById(R.id.main_lv_list);
		lv.setAdapter(ina);
		load_layout_fail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				load_layout.setVisibility(View.GONE);
				lv.setVisibility(View.GONE);
				load_layout_fail.setVisibility(View.VISIBLE);
				txt_neterr.setText(Constants.NETERROR + " 点击屏幕重新加载");
				requestUserinfo(id);
			}
		});
		txt_neterr = (TextView) findViewById(R.id.txt_neterr);

		requestUserinfo(id);
	}
	public void requestUserinfo(String id) {

		AjaxParams params = new AjaxParams();
		params.put("Model", "Events");
		params.put("Action", "get_event_details");
		params.put("Id", id);
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
				ShowToast.showMsg(BenefitDetailActivity.this, Constants.NETERROR);
				load_layout.setVisibility(View.GONE);
				lv.setVisibility(View.GONE);
				load_layout_fail.setVisibility(View.VISIBLE);
				txt_neterr.setText(Constants.NETERROR + " 点击屏幕重新加载");
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
				String str = (String) msg.obj;
				System.out.println(str);
				java.lang.reflect.Type type = new TypeToken<List<BenefitListBean>>() {
				}.getType();
				Gson gson = new Gson();
				mList = gson.fromJson(str, type);
				if (!mList.get(0).getError().equals("")) {
					load_layout.setVisibility(View.GONE);
					lv.setVisibility(View.GONE);
					load_layout_fail.setVisibility(View.VISIBLE);
					txt_neterr.setText(mList.get(0).getMsg() + "  点击屏幕重新加载");
				} else {
					//ina.setData(mList);
					lv.setVisibility(View.VISIBLE);
					load_layout.setVisibility(View.GONE);
					load_layout_fail.setVisibility(View.GONE);
				}

				break;
			case 1:

				break;

			default:
				break;
			}
		}
	};
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
