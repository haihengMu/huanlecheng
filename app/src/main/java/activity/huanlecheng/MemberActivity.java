package activity.huanlecheng;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import adapter.MemberAdapter;
import adapter.PopupWindowAdapter;
import bean.MemberGuanliBean;
import bean.MsgBean;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import http.AjaxParams;
import util.ShowToast;
import view.XListView;

/**
 * 会员管理
 * Created by Administrator on 2016/12/7.
 */

public class MemberActivity extends BaseActivity implements View.OnClickListener {

    private EditText etname;
    private EditText min_money;
    private EditText max_money;
    private Button button;
    private TextView title_textview;
    private XListView huiyuan_listview;
    private MemberAdapter memberAdapter;
    private List<MsgBean> mlist;
    private String time;
    private LinearLayout ll_saixuan;
    private RelativeLayout tv_zhuangtai;
    private PopupWindow mPopupWindow;
    private TextView tv_memebr_state;
    private String[] arr = new String[]{
            "全部", "在线", "离线"
    };
    private List<String> mlistp;
    private String u_id;
    private LinearLayout view_loading;
    private LinearLayout view_load_fail;
    private TextView txt_neterr;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        Date date = new Date();
        time = date.getTime() + "";
        u_id = getIntent().getStringExtra("a");
        initHead();
        initView();
        initIntent();
    }

    /**
     * 接受的广播
     */
    private void initIntent() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action.success");
        registerReceiver(bdr, intentFilter);
    }

    BroadcastReceiver bdr = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("action.success")) {
                String type = tv_memebr_state.getText().toString();//选择查询状态
                String key = etname.getText().toString();//帐号
                String minmoney = min_money.getText().toString();
                String maxmoney = max_money.getText().toString();
                if (type.equals("全部")) {
                    getUrlList(u_id, key, "0", minmoney, maxmoney);
                } else if (type.equals("在线")) {
                    getUrlList(u_id, key, "1", minmoney, maxmoney);
                } else if (type.equals("离线")) {
                    getUrlList(u_id, key, "2", minmoney, maxmoney);

                }
            }
        }
    };

    /**
     * 初始话头信息
     */
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
        title_textview.setText("会员管理");
        view_loading = (LinearLayout) findViewById(R.id.view_loading);//载入中
        //载入失败
        view_load_fail = (LinearLayout) findViewById(R.id.view_load_fail);
        txt_neterr = (TextView) findViewById(R.id.txt_neterr);//载入失败

    }

    /**
     * 初始化界面
     */
    private void initView() {
        etname = (EditText) findViewById(R.id.et_name);
        min_money = (EditText) findViewById(R.id.min_money);
        max_money = (EditText) findViewById(R.id.max_money);
        ll_saixuan = (LinearLayout) findViewById(R.id.ll_saixuan);
        tv_memebr_state = (TextView) findViewById(R.id.tv_memebr_state);
        ll_saixuan.setOnClickListener(this);
        huiyuan_listview = (XListView) findViewById(R.id.huiyuan_listview);
        getUrlList(u_id, "", "0", "", "");
        min_money.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        max_money.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        mlist = new ArrayList<>();
        memberAdapter = new MemberAdapter(getApplicationContext());
        huiyuan_listview.setAdapter(memberAdapter);
        huiyuan_listview.setPullLoadEnable(false);
        huiyuan_listview.setPullRefreshEnable(false);
        showPopupWindow();

    }

    private ListView popupWindowList() {
        final ListView popupwindowList = new ListView(getApplicationContext());
        popupwindowList.setDividerHeight(0);
        popupwindowList.setBackgroundColor(Color.rgb(63, 65, 78));
        // 去掉右侧垂直滑动条
        popupwindowList.setVerticalScrollBarEnabled(false);
        popupwindowList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv_memebr_state.setText(mlistp.get(position));
                mPopupWindow.dismiss();
            }
        });
        return popupwindowList;
    }

    private void showPopupWindow() {
        tv_zhuangtai = (RelativeLayout) findViewById(R.id.tv_zhuangtai);
        mlistp = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            mlistp.add(arr[i]);
        }
        final ListView pwlist = popupWindowList();
        PopupWindowAdapter bankLieAdapter = new PopupWindowAdapter(getApplication());
        pwlist.setAdapter(bankLieAdapter);
        bankLieAdapter.setData(mlistp);
        tv_zhuangtai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow = new PopupWindow(pwlist, v.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
                //设置popupwindow点击外部可以被关闭
                mPopupWindow.setOutsideTouchable(true);
                //设置pw的背景
                mPopupWindow.setBackgroundDrawable(new BitmapDrawable(String.valueOf(R.drawable.back)));
                //显示pw
                mPopupWindow.showAsDropDown(v, 2, -5);
            }
        });
    }

    /**
     * listview的网络请求
     *
     * @param key
     * @param type
     * @param minmoney
     * @param maxmoney
     */
    public void getUrlList(String uid, String key, String type, String minmoney, String maxmoney) {
        //  http://lf.client.cool/?Model=User&Action=MyTeam_List&pagenum=100&showpagenum=1&UserId=17080
        AjaxParams params = new AjaxParams();
        params.put("Model", "User");
        params.put("Action", "MyTeam_List");
        params.put("pagenum", "100");
        params.put("showpagenum", "1");
        params.put("UserId", uid);
        params.put("key", key);
        params.put("type", type);
        params.put("minmoney", minmoney);
        params.put("maxmoney", maxmoney);
        wh.configCookieStore(RUser.cookieStore);
        wh.post(Constants.getUrl(), params, new AjaxCallBack<String>() {
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
                ShowToast.showMsg(MemberActivity.this, Constants.NETERROR);
                view_loading.setVisibility(View.GONE);
                view_load_fail.setVisibility(View.VISIBLE);
                txt_neterr.setText(Constants.NETERROR + "点击屏幕加载重试");
                txt_neterr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getUrlList(u_id, "", "0", "", "");
                    }
                });
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    String string = (String) msg.obj;
                    java.lang.reflect.Type type = new TypeToken<MemberGuanliBean>() {
                    }.getType();
                    Gson gson = new Gson();
                    List<MsgBean> list = new ArrayList<>();
                    MemberGuanliBean memberGuanliBean = gson.fromJson(string, type);
                    if (memberGuanliBean.getMsg().size() != 0) {
                        for (int i = 0; i < memberGuanliBean.getMsg().size(); i++) {
                            list.add(memberGuanliBean.getMsg().get(i));
                        }
                    }
                    memberAdapter.setData(list, time);
                    view_load_fail.setVisibility(View.GONE);
                    view_loading.setVisibility(View.GONE);
                    break;
                default:
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_saixuan:
                //Model=User&Action=MyTeam_List&pagenum=10&showpagenum=1&UserId=传本页大id就好&key=帐号 &type=1&minmoney=最小金额&maxmoney=最大金额
                String type = tv_memebr_state.getText().toString();//选择查询状态
                String key = etname.getText().toString();//帐号
                String minmoney = min_money.getText().toString();
                String maxmoney = max_money.getText().toString();
                if (type.equals("全部")) {
                    getUrlList(u_id, key, "0", minmoney, maxmoney);
                } else if (type.equals("在线")) {
                    getUrlList(u_id, key, "1", minmoney, maxmoney);
                } else if (type.equals("离线")) {
                    getUrlList(u_id, key, "2", minmoney, maxmoney);
                }
                break;
            default:
        }
    }
}
