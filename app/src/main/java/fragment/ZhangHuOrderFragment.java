package fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import activity.huanlecheng.R;
import adapter.MLAdapter;
import bean.GetMLResponseModel;
import bean.LoginBean;
import bean.MLbean;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import http.AjaxParams;
import util.ShowToast;
import view.XListView;


public class ZhangHuOrderFragment extends BaseFragment implements
		View.OnClickListener, XListView.IXListViewListener {
	private String channel_id;
	private LinearLayout load_layout;// 载入中
	private LinearLayout load_layout_fail;// 载入失败
	private TextView txt_neterr;// 载入失败
	private ImageView fail_btn;// 失败按钮 点击重新加载
	private View parentView;
	private XListView lv;
	private List<MLbean> list;// 临时数据
	private MLAdapter gAdapter;
	private int n = 1;
	private GetMLResponseModel rrm;
	private String lo_id;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle args = getArguments();
		channel_id = args != null ? args.getString("id") : "";
		lo_id = args != null ? args.getString("lo_id") : "";
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		parentView = inflater
				.inflate(R.layout.fragment_order, container, false);
		load_layout = (LinearLayout) parentView.findViewById(R.id.view_loading);
		load_layout_fail = (LinearLayout) parentView
				.findViewById(R.id.view_load_fail);
		load_layout_fail.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				load_layout.setVisibility(View.VISIBLE);
				load_layout_fail.setVisibility(View.GONE);
				try {
					n = 1;
					GetData();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		txt_neterr = (TextView) parentView.findViewById(R.id.txt_neterr);
		fail_btn = (ImageView) parentView.findViewById(R.id.fail_btn);
		fail_btn.setOnClickListener(this);
		list = new ArrayList<MLbean>();
		rrm = new GetMLResponseModel();
		gAdapter = new MLAdapter(getActivity());
		lv = (XListView) parentView.findViewById(R.id.main_lv_list);
		lv.setXListViewListener(this);
		lv.setPullLoadEnable(false);
		lv.setAdapter(gAdapter);
		try {
			n = 1;
			GetData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parentView;
	}

	/**
	 * 获取数据
	 * 
	 * @throws Exception
	 */
	private void GetData() throws Exception {
		AjaxParams params = new AjaxParams();
		params.put("Model", "User");
		params.put("Action", "DetailsOfFunding_List");
		params.put("pagenum", RUser.page_size + "");
		params.put("showpagenum", n + "");
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
				if (n == 1) {
					msg.what = 0;// 第一次
				} else {
					msg.what = 1;// loadmore
				}
				msg.obj = t;
				handler.sendMessage(msg);
			}

			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) { // 加载失败的时候回调
				// TODO Auto-generated method stub
				super.onFailure(t, errorNo, strMsg);
				if (n == 1) {
					if (lv.getVisibility() == 0) {
						lv.stopFailRefresh();
						ShowToast.showMsg(getActivity(), Constants.NETERROR);
					} else {
						load_layout.setVisibility(View.GONE);
						lv.setVisibility(View.GONE);
						lv.stopFailRefresh();
						load_layout_fail.setVisibility(View.VISIBLE);
						txt_neterr.setText(Constants.NETERROR + "  点击上方按钮重新加载");
					}
				} else {
					n = n - 1;
					lv.stopLoadMore();
					ShowToast.showMsg(getActivity(), Constants.NETERROR);
				}

			}

		});
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			JSONObject jsonObject;
			switch (msg.what) {
			case 0:
				lv.stopRefresh();
				list.clear();
				String str = (String) msg.obj;
				System.out.println(str);
				try {
					jsonObject = new JSONObject(str);
					java.lang.reflect.Type type = new TypeToken<GetMLResponseModel>() {
					}.getType();
					java.lang.reflect.Type type1 = new TypeToken<LoginBean>() {
					}.getType();
					if (str.indexOf("error") > -1) {
						LoginBean rmModel = gson.fromJson(str, type1);
						load_layout.setVisibility(View.GONE);
						lv.setVisibility(View.GONE);
						load_layout_fail.setVisibility(View.VISIBLE);
//						txt_neterr.setText(rmModel.getMsg() + "  点击屏幕重新加载");
					} else {
						GetMLResponseModel rmModel = gson.fromJson(str, type);
						// 判断中奖状态
						if (channel_id.equals("0")) {
							for (int i = 0; i < rmModel.getMsg().size(); i++) {
								MLbean grlp = new MLbean();
								grlp.setM_L_Id(rmModel.getMsg().get(i)
										.getM_L_Id());
								grlp.setM_L_Order(rmModel.getMsg().get(i)
										.getM_L_Order());
								grlp.setM_L_UserName(rmModel.getMsg().get(i)
										.getM_L_UserName());
								grlp.setM_L_UserId(rmModel.getMsg().get(i)
										.getM_L_UserId());
								grlp.setM_L_Type(rmModel.getMsg().get(i)
										.getM_L_Type());
								grlp.setM_L_Money(rmModel.getMsg().get(i)
										.getM_L_Money());
								grlp.setM_L_beforMoney(rmModel.getMsg().get(i)
										.getM_L_beforMoney());
								grlp.setM_L_AfterMoney(rmModel.getMsg().get(i)
										.getM_L_AfterMoney());
								grlp.setM_L_State(rmModel.getMsg().get(i)
										.getM_L_State());
								grlp.setM_L_Content(rmModel.getMsg().get(i)
										.getM_L_Content());
								grlp.setM_L_AddTime(rmModel.getMsg().get(i)
										.getM_L_AddTime());

								list.add(grlp);
							}
							rrm.setCount(rmModel.getCount());
							rrm.setMsg(list);
							gAdapter.setData(rrm);
							if (RUser.page_size > rmModel.getMsg().size()) {
								lv.setPullLoadEnable(false);
							} else {
								lv.setPullLoadEnable(true);
							}
						} else if (channel_id.equals("1")) {
							for (int i = 0; i < rmModel.getMsg().size(); i++) {
								if (rmModel.getMsg().get(i).getM_L_Type()
										.equals("3")) {
									MLbean grlp = new MLbean();
									grlp.setM_L_Id(rmModel.getMsg().get(i)
											.getM_L_Id());
									grlp.setM_L_Order(rmModel.getMsg().get(i)
											.getM_L_Order());
									grlp.setM_L_UserName(rmModel.getMsg()
											.get(i).getM_L_UserName());
									grlp.setM_L_UserId(rmModel.getMsg().get(i)
											.getM_L_UserId());
									grlp.setM_L_Type(rmModel.getMsg().get(i)
											.getM_L_Type());
									grlp.setM_L_Money(rmModel.getMsg().get(i)
											.getM_L_Money());
									grlp.setM_L_beforMoney(rmModel.getMsg()
											.get(i).getM_L_beforMoney());
									grlp.setM_L_AfterMoney(rmModel.getMsg()
											.get(i).getM_L_AfterMoney());
									grlp.setM_L_State(rmModel.getMsg().get(i)
											.getM_L_State());
									grlp.setM_L_Content(rmModel.getMsg().get(i)
											.getM_L_Content());
									grlp.setM_L_AddTime(rmModel.getMsg().get(i)
											.getM_L_AddTime());
									list.add(grlp);
								}
							}
							rrm.setCount(rmModel.getCount());
							rrm.setMsg(list);
							gAdapter.setData(rrm);
							if (RUser.page_size > rmModel.getMsg().size()) {
								lv.setPullLoadEnable(false);
							} else {
								lv.setPullLoadEnable(true);
							}
						} else if (channel_id.equals("2")) {
							for (int i = 0; i < rmModel.getMsg().size(); i++) {
								if (rmModel.getMsg().get(i).getM_L_Type()
										.equals("4")) {
									MLbean grlp = new MLbean();
									grlp.setM_L_Id(rmModel.getMsg().get(i)
											.getM_L_Id());
									grlp.setM_L_Order(rmModel.getMsg().get(i)
											.getM_L_Order());
									grlp.setM_L_UserName(rmModel.getMsg()
											.get(i).getM_L_UserName());
									grlp.setM_L_UserId(rmModel.getMsg().get(i)
											.getM_L_UserId());
									grlp.setM_L_Type(rmModel.getMsg().get(i)
											.getM_L_Type());
									grlp.setM_L_Money(rmModel.getMsg().get(i)
											.getM_L_Money());
									grlp.setM_L_beforMoney(rmModel.getMsg()
											.get(i).getM_L_beforMoney());
									grlp.setM_L_AfterMoney(rmModel.getMsg()
											.get(i).getM_L_AfterMoney());
									grlp.setM_L_State(rmModel.getMsg().get(i)
											.getM_L_State());
									grlp.setM_L_Content(rmModel.getMsg().get(i)
											.getM_L_Content());
									grlp.setM_L_AddTime(rmModel.getMsg().get(i)
											.getM_L_AddTime());
									list.add(grlp);
								}
							}
							rrm.setCount(rmModel.getCount());
							rrm.setMsg(list);
							gAdapter.setData(rrm);
							if (RUser.page_size > rmModel.getMsg().size()) {
								lv.setPullLoadEnable(false);
							} else {
								lv.setPullLoadEnable(true);
							}
						} else if (channel_id.equals("3")) {
							for (int i = 0; i < rmModel.getMsg().size(); i++) {
								if (rmModel.getMsg().get(i).getM_L_Type()
										.equals("1")) {
									MLbean grlp = new MLbean();
									grlp.setM_L_Id(rmModel.getMsg().get(i)
											.getM_L_Id());
									grlp.setM_L_Order(rmModel.getMsg().get(i)
											.getM_L_Order());
									grlp.setM_L_UserName(rmModel.getMsg()
											.get(i).getM_L_UserName());
									grlp.setM_L_UserId(rmModel.getMsg().get(i)
											.getM_L_UserId());
									grlp.setM_L_Type(rmModel.getMsg().get(i)
											.getM_L_Type());
									grlp.setM_L_Money(rmModel.getMsg().get(i)
											.getM_L_Money());
									grlp.setM_L_beforMoney(rmModel.getMsg()
											.get(i).getM_L_beforMoney());
									grlp.setM_L_AfterMoney(rmModel.getMsg()
											.get(i).getM_L_AfterMoney());
									grlp.setM_L_State(rmModel.getMsg().get(i)
											.getM_L_State());
									grlp.setM_L_Content(rmModel.getMsg().get(i)
											.getM_L_Content());
									grlp.setM_L_AddTime(rmModel.getMsg().get(i)
											.getM_L_AddTime());
									list.add(grlp);
								}
							}
							rrm.setCount(rmModel.getCount());
							rrm.setMsg(list);
							gAdapter.setData(rrm);
							if (RUser.page_size > rmModel.getMsg().size()) {
								lv.setPullLoadEnable(false);
							} else {
								lv.setPullLoadEnable(true);
							}
						} else if (channel_id.equals("4")) {
							for (int i = 0; i < rmModel.getMsg().size(); i++) {
								if (rmModel.getMsg().get(i).getM_L_Type()
										.equals("2")) {
									MLbean grlp = new MLbean();
									grlp.setM_L_Id(rmModel.getMsg().get(i)
											.getM_L_Id());
									grlp.setM_L_Order(rmModel.getMsg().get(i)
											.getM_L_Order());
									grlp.setM_L_UserName(rmModel.getMsg()
											.get(i).getM_L_UserName());
									grlp.setM_L_UserId(rmModel.getMsg().get(i)
											.getM_L_UserId());
									grlp.setM_L_Type(rmModel.getMsg().get(i)
											.getM_L_Type());
									grlp.setM_L_Money(rmModel.getMsg().get(i)
											.getM_L_Money());
									grlp.setM_L_beforMoney(rmModel.getMsg()
											.get(i).getM_L_beforMoney());
									grlp.setM_L_AfterMoney(rmModel.getMsg()
											.get(i).getM_L_AfterMoney());
									grlp.setM_L_State(rmModel.getMsg().get(i)
											.getM_L_State());
									grlp.setM_L_Content(rmModel.getMsg().get(i)
											.getM_L_Content());
									grlp.setM_L_AddTime(rmModel.getMsg().get(i)
											.getM_L_AddTime());
									list.add(grlp);
								}
							}
							rrm.setCount(rmModel.getCount());
							rrm.setMsg(list);
							gAdapter.setData(rrm);
							if (RUser.page_size > rmModel.getMsg().size()) {
								lv.setPullLoadEnable(false);
							} else {
								lv.setPullLoadEnable(true);
							}
						}

						lv.setVisibility(View.VISIBLE);
						load_layout.setVisibility(View.GONE);
						load_layout_fail.setVisibility(View.GONE);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 1:
				lv.stopLoadMore();
				// list.clear();
				String str1 = (String) msg.obj;
				System.out.println(str1);
				try {
					jsonObject = new JSONObject(str1);
					java.lang.reflect.Type type = new TypeToken<GetMLResponseModel>() {
					}.getType();
					java.lang.reflect.Type type1 = new TypeToken<LoginBean>() {
					}.getType();
					if (str1.indexOf("error") > -1) {
						n = n - 1;
						LoginBean rmModel = gson.fromJson(str1, type1);
						load_layout.setVisibility(View.GONE);
						lv.setVisibility(View.GONE);
						load_layout_fail.setVisibility(View.VISIBLE);
					//	txt_neterr.setText(rmModel.getMsg() + "  点击屏幕重新加载");
					} else {
						GetMLResponseModel rmModel = gson.fromJson(str1, type);
						// 判断中奖状态
						if (channel_id.equals("0")) {
							for (int i = 0; i < rmModel.getMsg().size(); i++) {
								MLbean grlp = new MLbean();
								grlp.setM_L_Id(rmModel.getMsg().get(i)
										.getM_L_Id());
								grlp.setM_L_Order(rmModel.getMsg().get(i)
										.getM_L_Order());
								grlp.setM_L_UserName(rmModel.getMsg().get(i)
										.getM_L_UserName());
								grlp.setM_L_UserId(rmModel.getMsg().get(i)
										.getM_L_UserId());
								grlp.setM_L_Type(rmModel.getMsg().get(i)
										.getM_L_Type());
								grlp.setM_L_Money(rmModel.getMsg().get(i)
										.getM_L_Money());
								grlp.setM_L_beforMoney(rmModel.getMsg().get(i)
										.getM_L_beforMoney());
								grlp.setM_L_AfterMoney(rmModel.getMsg().get(i)
										.getM_L_AfterMoney());
								grlp.setM_L_State(rmModel.getMsg().get(i)
										.getM_L_State());
								grlp.setM_L_Content(rmModel.getMsg().get(i)
										.getM_L_Content());
								grlp.setM_L_AddTime(rmModel.getMsg().get(i)
										.getM_L_AddTime());

								list.add(grlp);
							}
							rrm.setCount(rmModel.getCount());
							rrm.setMsg(list);
							gAdapter.setData(rrm);
							if (RUser.page_size > rmModel.getMsg().size()) {
								lv.setPullLoadEnable(false);
							} else {
								lv.setPullLoadEnable(true);
							}
						} else if (channel_id.equals("1")) {
							for (int i = 0; i < rmModel.getMsg().size(); i++) {
								if (rmModel.getMsg().get(i).getM_L_Type()
										.equals("3")) {
									MLbean grlp = new MLbean();
									grlp.setM_L_Id(rmModel.getMsg().get(i)
											.getM_L_Id());
									grlp.setM_L_Order(rmModel.getMsg().get(i)
											.getM_L_Order());
									grlp.setM_L_UserName(rmModel.getMsg()
											.get(i).getM_L_UserName());
									grlp.setM_L_UserId(rmModel.getMsg().get(i)
											.getM_L_UserId());
									grlp.setM_L_Type(rmModel.getMsg().get(i)
											.getM_L_Type());
									grlp.setM_L_Money(rmModel.getMsg().get(i)
											.getM_L_Money());
									grlp.setM_L_beforMoney(rmModel.getMsg()
											.get(i).getM_L_beforMoney());
									grlp.setM_L_AfterMoney(rmModel.getMsg()
											.get(i).getM_L_AfterMoney());
									grlp.setM_L_State(rmModel.getMsg().get(i)
											.getM_L_State());
									grlp.setM_L_Content(rmModel.getMsg().get(i)
											.getM_L_Content());
									grlp.setM_L_AddTime(rmModel.getMsg().get(i)
											.getM_L_AddTime());
									list.add(grlp);
								}
							}
							rrm.setCount(rmModel.getCount());
							rrm.setMsg(list);
							gAdapter.setData(rrm);
							if (RUser.page_size > rmModel.getMsg().size()) {
								lv.setPullLoadEnable(false);
							} else {
								lv.setPullLoadEnable(true);
							}
						} else if (channel_id.equals("2")) {
							for (int i = 0; i < rmModel.getMsg().size(); i++) {
								if (rmModel.getMsg().get(i).getM_L_Type()
										.equals("4")) {
									MLbean grlp = new MLbean();
									grlp.setM_L_Id(rmModel.getMsg().get(i)
											.getM_L_Id());
									grlp.setM_L_Order(rmModel.getMsg().get(i)
											.getM_L_Order());
									grlp.setM_L_UserName(rmModel.getMsg()
											.get(i).getM_L_UserName());
									grlp.setM_L_UserId(rmModel.getMsg().get(i)
											.getM_L_UserId());
									grlp.setM_L_Type(rmModel.getMsg().get(i)
											.getM_L_Type());
									grlp.setM_L_Money(rmModel.getMsg().get(i)
											.getM_L_Money());
									grlp.setM_L_beforMoney(rmModel.getMsg()
											.get(i).getM_L_beforMoney());
									grlp.setM_L_AfterMoney(rmModel.getMsg()
											.get(i).getM_L_AfterMoney());
									grlp.setM_L_State(rmModel.getMsg().get(i)
											.getM_L_State());
									grlp.setM_L_Content(rmModel.getMsg().get(i)
											.getM_L_Content());
									grlp.setM_L_AddTime(rmModel.getMsg().get(i)
											.getM_L_AddTime());
									list.add(grlp);
								}
							}
							rrm.setCount(rmModel.getCount());
							rrm.setMsg(list);
							gAdapter.setData(rrm);
							if (RUser.page_size > rmModel.getMsg().size()) {
								lv.setPullLoadEnable(false);
							} else {
								lv.setPullLoadEnable(true);
							}
						} else if (channel_id.equals("3")) {
							for (int i = 0; i < rmModel.getMsg().size(); i++) {
								if (rmModel.getMsg().get(i).getM_L_Type()
										.equals("1")) {
									MLbean grlp = new MLbean();
									grlp.setM_L_Id(rmModel.getMsg().get(i)
											.getM_L_Id());
									grlp.setM_L_Order(rmModel.getMsg().get(i)
											.getM_L_Order());
									grlp.setM_L_UserName(rmModel.getMsg()
											.get(i).getM_L_UserName());
									grlp.setM_L_UserId(rmModel.getMsg().get(i)
											.getM_L_UserId());
									grlp.setM_L_Type(rmModel.getMsg().get(i)
											.getM_L_Type());
									grlp.setM_L_Money(rmModel.getMsg().get(i)
											.getM_L_Money());
									grlp.setM_L_beforMoney(rmModel.getMsg()
											.get(i).getM_L_beforMoney());
									grlp.setM_L_AfterMoney(rmModel.getMsg()
											.get(i).getM_L_AfterMoney());
									grlp.setM_L_State(rmModel.getMsg().get(i)
											.getM_L_State());
									grlp.setM_L_Content(rmModel.getMsg().get(i)
											.getM_L_Content());
									grlp.setM_L_AddTime(rmModel.getMsg().get(i)
											.getM_L_AddTime());
									list.add(grlp);
								}
							}
							rrm.setCount(rmModel.getCount());
							rrm.setMsg(list);
							gAdapter.setData(rrm);
							if (RUser.page_size > rmModel.getMsg().size()) {
								lv.setPullLoadEnable(false);
							} else {
								lv.setPullLoadEnable(true);
							}
						} else if (channel_id.equals("4")) {
							for (int i = 0; i < rmModel.getMsg().size(); i++) {
								if (rmModel.getMsg().get(i).getM_L_Type()
										.equals("2")) {
									MLbean grlp = new MLbean();
									grlp.setM_L_Id(rmModel.getMsg().get(i)
											.getM_L_Id());
									grlp.setM_L_Order(rmModel.getMsg().get(i)
											.getM_L_Order());
									grlp.setM_L_UserName(rmModel.getMsg()
											.get(i).getM_L_UserName());
									grlp.setM_L_UserId(rmModel.getMsg().get(i)
											.getM_L_UserId());
									grlp.setM_L_Type(rmModel.getMsg().get(i)
											.getM_L_Type());
									grlp.setM_L_Money(rmModel.getMsg().get(i)
											.getM_L_Money());
									grlp.setM_L_beforMoney(rmModel.getMsg()
											.get(i).getM_L_beforMoney());
									grlp.setM_L_AfterMoney(rmModel.getMsg()
											.get(i).getM_L_AfterMoney());
									grlp.setM_L_State(rmModel.getMsg().get(i)
											.getM_L_State());
									grlp.setM_L_Content(rmModel.getMsg().get(i)
											.getM_L_Content());
									grlp.setM_L_AddTime(rmModel.getMsg().get(i)
											.getM_L_AddTime());
									list.add(grlp);
								}
							}
							rrm.setCount(rmModel.getCount());
							rrm.setMsg(list);
							gAdapter.setData(rrm);
							if (RUser.page_size > rmModel.getMsg().size()) {
								lv.setPullLoadEnable(false);
							} else {
								lv.setPullLoadEnable(true);
							}
						}

						lv.setVisibility(View.VISIBLE);
						load_layout.setVisibility(View.GONE);
						load_layout_fail.setVisibility(View.GONE);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;

			default:
				break;
			}
			super.handleMessage(msg);
		}
	};

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		try {
			n = 1;
			GetData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		n = n + 1;
		try {
			GetData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
