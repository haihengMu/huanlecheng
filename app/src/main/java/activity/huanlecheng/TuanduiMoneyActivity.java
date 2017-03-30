package activity.huanlecheng;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import adapter.TuanduiAdapter;
import adapter.TuanduiMoneyAdapter;
import bean.MsgTuanBean;
import bean.TuanduiBean;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import http.AjaxParams;
import util.JsonUtil;
import view.XListView;

/**
 * 团队取款
 * Created by Administrator on 2016/12/13.
 */

public class TuanduiMoneyActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "TuanduiMoneyActivity";
    private Button button;
    private TextView title_textview;
    private XListView mlistview;
    private TuanduiAdapter tuanduiAdapter;
    private LinearLayout ll_change;
    private List<TuanduiBean.DataBean.ListBean> mylist;
    private String[] arr = new String[]{
            "全部状态", "已支付", "处理中", "已拒绝"
    };
    private TuanduiMoneyAdapter tuanduiMoneyAdapter;
    private RelativeLayout ll_xuanzhe;
    private PopupWindow pw;
    private TextView tv_state_ding;
    private List<String> mspwlist;
    private EditText start_time;
    private EditText end_time;
    private LinearLayout view_loading;
    private LinearLayout view_load_fail;
    private TextView txt_neterr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuan_money);
        mylist = new ArrayList<>();
        initHead();
        initView();
        showPopupWindow();
    }


    private void initHead() {
        button = (Button) findViewById(R.id.title_left_btn);
        title_textview = (TextView) findViewById(R.id.title_textview);
        button.setBackgroundResource(R.drawable.aar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title_textview.setText("团队取款");
    }

    private void initView() {
        mlistview = (XListView) findViewById(R.id.mylistview);
        ll_change = (LinearLayout) findViewById(R.id.ll_change);
        ll_change.setOnClickListener(this);
        tuanduiAdapter = new TuanduiAdapter(getApplicationContext());
        mlistview.setAdapter(tuanduiAdapter);
        mlistview.setPullLoadEnable(false);
        mlistview.setPullRefreshEnable(false);
        mlistview.setItemsCanFocus(false);
        tv_state_ding = (TextView) findViewById(R.id.tv_state_ding);
        start_time = (EditText) findViewById(R.id.et_start_time);
        end_time = (EditText) findViewById(R.id.et_end_time);
        view_loading = (LinearLayout) findViewById(R.id.view_loading);
        view_load_fail = (LinearLayout) findViewById(R.id.view_load_fail);
        txt_neterr = (TextView) findViewById(R.id.txt_neterr);
        txt_neterr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getURL("0", "", "");
            }
        });
        getURL("0", "", "");
    }

    /**
     * popupwindow订单状态的listview
     */
    public ListView PopupwindowListview() {

        ListView pwListview = new ListView(getApplicationContext());
        pwListview.setDividerHeight(0);//去掉listview中间的分割线
        pwListview.setBackgroundColor(Color.rgb(63, 65, 78));
        pwListview.setVerticalScrollBarEnabled(false);
        pwListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv_state_ding.setText(mspwlist.get(position));
                pw.dismiss();
            }
        });
        return pwListview;
    }

    /**
     * 显示popupwindoe
     */
    public void showPopupWindow() {
        ll_xuanzhe = (RelativeLayout) findViewById(R.id.ll_xuanzhe);
        mspwlist = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            mspwlist.add(arr[i]);
        }
        final ListView mylistview = PopupwindowListview();
        tuanduiMoneyAdapter = new TuanduiMoneyAdapter(getApplicationContext());
        mylistview.setAdapter(tuanduiMoneyAdapter);
        tuanduiMoneyAdapter.setData(mspwlist);
        ll_xuanzhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw = new PopupWindow(mylistview, v.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
                pw.setBackgroundDrawable(new BitmapDrawable(String.valueOf(R.drawable.denglu)));
                pw.setOutsideTouchable(true);
                pw.setFocusable(true);
                pw.showAsDropDown(v, -2, 5);
            }
        });

    }

    /**
     * 下面listview的网络请求
     */
    public void getURL(String type, String starttime, String endtime) {
        //http://lf.client.cool/?Model=User&Action=WithdrawalsInquiry_List&type=0&pagenum=100&showpagenum=1&start_date=""&end_date=""
        //type          0：全部状态   1：已支付    2：处理中   3：已拒绝
        AjaxParams params = new AjaxParams();
        params.put("type1", type);
        params.put("date_start", starttime);
        params.put("date_end", endtime);
        wh.configCookieStore(RUser.cookieStore);
        wh.post(Constants.getUrl() + "users/team_withdrawals_log", params, new AjaxCallBack<String>() {
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
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                view_load_fail.setVisibility(View.VISIBLE);
                view_loading.setVisibility(View.GONE);
                txt_neterr.setVisibility(View.VISIBLE);
                txt_neterr.setText(Constants.NETERROR + "点击屏幕加载重试");
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    loadingWindow.cancel();
                    String str = (String) msg.obj;
                    Log.e(TAG, str);
                    TuanduiBean tuanduiBean = JsonUtil.parseJsonToBean(str, TuanduiBean.class);
                    if ("0".equals(tuanduiBean.getData().getCount())) {
                        mylist.clear();
                        view_load_fail.setVisibility(View.VISIBLE);
                        view_loading.setVisibility(View.GONE);
                        txt_neterr.setVisibility(View.VISIBLE);
                        txt_neterr.setText("暂无数据");
                    }else{
                        mylist.clear();
                        mylist.addAll(tuanduiBean.getData().getList());
                    }
                    tuanduiAdapter.setData(mylist);
                    view_load_fail.setVisibility(View.GONE);
                    view_loading.setVisibility(View.GONE);
                    txt_neterr.setVisibility(View.GONE);
                    break;
                default:
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_change:
                loadingWindow.showDialog(Constants.jiazaizhong);
                String state = tv_state_ding.getText().toString();
                String s_time = start_time.getText().toString();
                String e_time = end_time.getText().toString();
                if (state.equals("全部状态")) {
                    getURL("0", s_time, e_time);
                } else if (state.equals("已支付")) {
                    getURL("1", s_time, e_time);
                } else if (state.equals("处理中")) {
                    getURL("2", s_time, e_time);
                } else if (state.equals("已拒绝")) {
                    getURL("3", s_time, e_time);
                }
                break;
            default:
        }
    }
}
