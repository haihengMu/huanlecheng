package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import activity.huanlecheng.R;
import adapter.BuyFragmentAdapter;
import bean.CustomBean;
import bean.HXGameNameBean;
import bean.HX_Game_NameResponseModel;
import bean.HxGame;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import util.ShowToast;
import util.Utils;
import view.XListView;

public class BuyFragment extends BaseFragment implements OnClickListener,
		XListView.IXListViewListener {
	private View parentView;
	private Button title_left_btn;
	private TextView title_textview;
	private XListView lv;
	private LinearLayout load_layout;// 载入中
	private LinearLayout load_layout_fail;// 载入失败
	private TextView txt_neterr;// 载入失败
	private CustomBean hGame;
	private CustomBean put_hGame;
	private List<HXGameNameBean> put_hgn;
	private BuyFragmentAdapter ina;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		parentView = inflater.inflate(R.layout.fragment_buy, container, false);
		title_left_btn = (Button) parentView.findViewById(R.id.title_left_btn);
		title_left_btn.setVisibility(View.GONE);
		title_textview = (TextView) parentView
				.findViewById(R.id.title_textview);
		title_textview.setText("购彩大厅");
		load_layout = (LinearLayout) parentView.findViewById(R.id.view_loading);
		load_layout_fail = (LinearLayout) parentView
				.findViewById(R.id.view_load_fail);
		ina = new BuyFragmentAdapter(getActivity());
		lv = (XListView) parentView.findViewById(R.id.main_lv_list);
		lv.setXListViewListener(this);
		lv.setPullLoadEnable(false);
		lv.setCanRefresh(false);
		lv.setAdapter(ina);
		put_hGame = new CustomBean();
		put_hgn = new ArrayList<HXGameNameBean>();
		load_layout_fail.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				load_layout.setVisibility(View.VISIBLE);
				lv.setVisibility(View.GONE);
				load_layout_fail.setVisibility(View.GONE);
				requestUserinfo();
			}
		});
		txt_neterr = (TextView) parentView.findViewById(R.id.txt_neterr);
		requestUserinfo();

		return parentView;
	}

	public void requestUserinfo() {
		wh.configCookieStore(RUser.cookieStore);
		wh.get(Constants.getUrl()+Constants.game_play, new AjaxCallBack<String>() {
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
				ShowToast.showMsg(getActivity(), Constants.NETERROR);
				load_layout.setVisibility(View.GONE);
				lv.setVisibility(View.GONE);
				lv.stopFailRefresh();
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
				java.lang.reflect.Type type = new TypeToken<CustomBean>() {
				}.getType();
				System.out.println("我试试返回了啥" + str);
				if (!Utils.isGoodJson(str)) {
					load_layout.setVisibility(View.GONE);
					lv.setVisibility(View.GONE);
					lv.stopFailRefresh();
					load_layout_fail.setVisibility(View.VISIBLE);
					txt_neterr.setText(Constants.NETERROR + " 点击屏幕重新加载");
				} else {
					CustomBean customBean = gson.fromJson(str, type);
					if (customBean.getData().equals("")) {
						load_layout.setVisibility(View.GONE);
						lv.setVisibility(View.GONE);
						load_layout_fail.setVisibility(View.VISIBLE);
						txt_neterr.setText("  点击屏幕重新加载");
					} else {
						hGame = gson.fromJson(str, type);
						for (int i = 0; i < hGame.getData().size(); i++) {
							if (!hGame.getData().get(i).getH_g_n_off()
									.equals("0")) {// 1显示 0隐藏
								HXGameNameBean hgn = new HXGameNameBean();
								hgn.setH_g_n_id(hGame.getData().get(i)
										.getH_g_n_id());
								hgn.setH_g_n_title(hGame.getData().get(i)
										.getH_g_n_title());
								hgn.setH_g_n_off(hGame.getData().get(i)
										.getH_g_n_off());
								put_hgn.add(hgn);
							}
						}
						put_hGame.setData(put_hgn);
						ina.setData(put_hGame);
						lv.setVisibility(View.VISIBLE);
						load_layout.setVisibility(View.GONE);
						load_layout_fail.setVisibility(View.GONE);
					}
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
	public void onRefresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
}
