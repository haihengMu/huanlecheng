package activity.huanlecheng;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.logging.LogRecord;

import bean.AllcheBean;
import bean.GetRecordsListResponseModel;
import bean.LotteryDetailBean;
import bean.UserBettingInfo;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import http.AjaxParams;
import util.JsonUtil;
import util.ShowToast;

public class LotteryDetailActivity extends BaseActivity {
    private static final String TAG = "LotteryDetailActivity";
    private UserBettingInfo.DataBean.ListBean grm;
    private Button title_left_btn;
    private TextView title_textview;

    private TextView tv_zhuangtai,
            tv_number, tv_tzqh, tv_buy, tv_zhongjiangmoney, tv_buywinkui,
            tv_beishu, tv_zhushu, tv_zjzhushu, tv_returnbuy, tv_returnmoney,
            tv_moneymoshi, tv_account, tv_tztime, tv_tznum;
    private String name;
    private Button goon_btn;
    private TextView chedan_btn;
    private LinearLayout ll_chedan;
    private LinearLayout ll_chedand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        grm = (UserBettingInfo.DataBean.ListBean) getIntent().getExtras()
                .get("model");
        getBetOrderDetails();
        name = getIntent().getStringExtra("name");
        title_left_btn = (Button) findViewById(R.id.title_left_btn);
//		tv_title = (TextView) findViewById(R.id.tv_ssc);
//		tv_play = (TextView) findViewById(R.id.tv_play);
//		tv_date = (TextView) findViewById(R.id.tv_date);
        //	tv_time = (TextView) findViewById(R.id.tv_time);
        tv_zhuangtai = (TextView) findViewById(R.id.tv_zhuangtai);
        tv_tznum = (TextView) findViewById(R.id.tv_tznum);

        tv_number = (TextView) findViewById(R.id.tv_number);
        tv_tzqh = (TextView) findViewById(R.id.tv_tzqh);
        tv_buy = (TextView) findViewById(R.id.tv_buy);
        tv_zhongjiangmoney = (TextView) findViewById(R.id.tv_zhongjiangmoney);
        tv_buywinkui = (TextView) findViewById(R.id.tv_buywinkui);
        tv_beishu = (TextView) findViewById(R.id.tv_beishu);
        tv_zhushu = (TextView) findViewById(R.id.tv_zhushu);
        tv_zjzhushu = (TextView) findViewById(R.id.tv_zjzhushu);
        tv_returnbuy = (TextView) findViewById(R.id.tv_returnbuy);
        tv_returnmoney = (TextView) findViewById(R.id.tv_returnmoney);
        tv_moneymoshi = (TextView) findViewById(R.id.tv_moneymoshi);
        tv_account = (TextView) findViewById(R.id.tv_account);
        tv_tztime = (TextView) findViewById(R.id.tv_tztime);
//        goon_btn = (Button) findViewById(R.id.goon_btn);
        chedan_btn = (TextView) findViewById(R.id.chedan_btn);
        /**
         * 全部撤单
         */
        ll_chedan = (LinearLayout) findViewById(R.id.ll_chedan);
        ll_chedand = (LinearLayout) findViewById(R.id.ll_chedand);
        ll_chedan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingWindow.showDialog(Constants.tjing);
                UpdatemAll();
            }
        });
        /**
         * 单个撤单
         */

        ll_chedand.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                loadingWindow.showDialog(Constants.tjing);
                Updatemm(grm.getH_b_d_oid());
            }
        });
//		goon_btn.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				finish();
//			}
//		});
        title_left_btn.setBackgroundResource(R.drawable.aar);
        title_left_btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });
        title_textview = (TextView) findViewById(R.id.title_textview);
        title_textview.setText("方案详情");
        System.out.println(grm.getH_b_d_id());
        /*tv_title.setText(name.split("-")[0]);
        tv_play.setText(name.split("-")[1]);
		tv_date.setText(grm.getB_L_Issue());
		tv_time.setText(grm.getB_L_AddTime());*/
        DecimalFormat df = new DecimalFormat("0.00");
        if (grm.getH_b_d_state().equals("1")) {
            ll_chedan.setVisibility(View.VISIBLE);
            ll_chedand.setVisibility(View.VISIBLE);
            tv_zhuangtai.setText("未开奖");
            tv_buywinkui.setText("-" + grm.getH_b_d_money() + "元");
        } else if (grm.getH_b_d_state().equals("2")) {
            tv_zhuangtai.setText("已中奖");
            tv_buywinkui
                    .setText(df.format((float) Double.parseDouble(grm
                            .getH_b_d_win_money())
                            - (float) Double.parseDouble(grm.getH_b_d_money()))
                            + "0000元");
        } else if (grm.getH_b_d_state().equals("3")) {
            tv_zhuangtai.setText("未中奖");
            tv_buywinkui.setText("-" + grm.getH_b_d_money() + "元");
        } else if (grm.getH_b_d_state().equals("4")) {
            tv_zhuangtai.setText("派奖中");
            tv_buywinkui
                    .setText(df.format((float) Double.parseDouble(grm
                            .getH_b_d_win_money())
                            - (float) Double.parseDouble(grm.getH_b_d_money()))
                            + "0000元");
        } else if (grm.getH_b_d_state().equals("5")) {
            tv_zhuangtai.setText("已撤单");
            tv_buywinkui.setText("0.0000元");
        } else if (grm.getH_b_d_state().equals("6")) {
            tv_zhuangtai.setText("已完成");
            tv_buywinkui
                    .setText(df.format((float) Double.parseDouble(grm
                            .getH_b_d_win_money())
                            - (float) Double.parseDouble(grm.getH_b_d_money()))
                            + "0000元");
        }
        tv_number.setText(grm.getH_b_d_oid());
        tv_tzqh.setText(grm.getH_b_d_issue());
        tv_buy.setText(grm.getH_b_d_money());
        tv_zhongjiangmoney.setText(grm.getH_b_d_win_money());
        tv_beishu.setText(grm.getH_b_d_multiple());
        tv_zhushu.setText(grm.getH_b_d_bet_note());
        tv_zjzhushu.setText(grm.getH_b_d_win_note());
        tv_returnbuy.setText(grm.getH_b_d_bet_rebate());
        tv_returnmoney.setText(grm.getH_b_d_rebate_money());
        tv_moneymoshi.setText(grm.getH_b_d_bonus_mode());

        tv_tztime.setText(grm.getH_b_d_addtime());
    }

    private void getBetOrderDetails() {
        AjaxParams params = new AjaxParams();
        params.put("oid", grm.getH_b_d_oid());
        params.put("gid", grm.getH_b_d_nid());
        params.put("issue", grm.getH_b_d_issue());
        wh.configCookieStore(RUser.cookieStore);
        String url = Constants.getUrl() + "lottery/get_bet_order_details";
        Log.e(TAG, url);
        wh.get(url, params, new AjaxCallBack<String>() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onLoading(long count, long current) {
                super.onLoading(count, current);
            }

            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Message msg = new Message();
                msg.what = 1;
                msg.obj = s;
                handle.sendMessage(msg);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }

    private void UpdatemAll() {
        wh.configCookieStore(RUser.cookieStore);
        wh.post(Constants.getUrl() + Constants.all_che, new AjaxCallBack<String>() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onLoading(long count, long current) {
                super.onLoading(count, current);
            }

            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Message msg = new Message();
                msg.what = 0;
                msg.obj = s;
                handle.sendMessage(msg);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }


    public void Updatemm(String type) {
        AjaxParams params = new AjaxParams();
        params.put("oid", type);
        wh.configCookieStore(RUser.cookieStore);
        wh.post(Constants.getUrl() + Constants.d_che, params, new AjaxCallBack<String>() {
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
                ShowToast.showMsg(LotteryDetailActivity.this,
                        Constants.NETERROR);
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
                        java.lang.reflect.Type type = new TypeToken<AllcheBean>() {
                        }.getType();
                        AllcheBean loginBean = gson.fromJson(str, type);
                        showToast(loginBean.getError());
                        if (loginBean.getData().equals("")) {
                            showToast(loginBean.getError());
                        } else {
                            showToast("撤单成功!");
                            finish();
                        }
                    /*if (!loginBean.getError().equals("")) {
                        showToast(loginBean.getMsg());
					} else {
						showToast("撤单成功！");
						finish();
					}*/
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    String str = (String) msg.obj;
                    Log.e(TAG, str);
                    LotteryDetailBean lotteryDetailBean = JsonUtil.parseJsonToBean(str, LotteryDetailBean.class);
                    tv_account.setText(lotteryDetailBean.getData().getWin_info().getCode());
                    tv_tznum.setText(lotteryDetailBean.getData().getBet_info());
                    break;
            }
        }
    };
}
