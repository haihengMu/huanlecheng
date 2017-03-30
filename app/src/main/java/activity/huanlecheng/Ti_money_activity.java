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
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import adapter.BankLieAdapter;
import bean.BankLieBean;
import bean.BindBankBean;
import bean.TiXianNameBean;
import bean.TixianBean;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import http.AjaxParams;
import util.JsonUtil;
import util.ToastUtil;
import util.Utils;

/**
 * Created by Administrator on 2016/11/12.
 */

public class Ti_money_activity extends BaseActivity {

    private static final String TAG = "Ti_money_activity";
    private Button title_left_btn;
    private TextView title_textview;
    private TextView tv_money;
    private String money;
    private RelativeLayout rl_recharge;
    private PopupWindow mPopupWindow;
    private BankLieAdapter bankLieAdapter;
    private List<BankLieBean.DataBean> mlist;
    private TextView et_bank_address;
    private TextView et_name;
    private TextView et_bank_number;
    private String bankId;
    private TextView tv_queren;
    private EditText et_money;
    private EditText et_password;
    private TixianBean tixianBean;
    private TiXianNameBean tiXianNameBean;
    private TextView tv_names;
    private BankLieBean bankLieBean1;
    private TextView tv_tvchongzhi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tixian);
        tv_names = (TextView) findViewById(R.id.tv_names);
        mlist = new ArrayList<>();
        tiXianNameBean = new TiXianNameBean();
        bankLieBean1 = new BankLieBean();
//         iniLoading();
        initHeadView();
        initView();
    }


    private void initView() {
        tv_money = (TextView) findViewById(R.id.tv_money);
        et_bank_address = (TextView) findViewById(R.id.et_bank_address);
        et_name = (TextView) findViewById(R.id.et_name);
        et_bank_number = (TextView) findViewById(R.id.et_bank_number);
        money = getIntent().getStringExtra("M_money");
        tv_money.setText(money);
        showPopupWindow();
    }

    /**
     * 下拉的listview
     *
     * @return
     */
    private ListView popupWindowList() {
        final ListView popupwindowList = new ListView(this);
        popupwindowList.setDividerHeight(0);
        popupwindowList.setBackgroundColor(Color.rgb(63, 65, 78));
        // 去掉右侧垂直滑动条
        popupwindowList.setVerticalScrollBarEnabled(false);
        popupwindowList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bankId = mlist.get(position).getId();
                et_bank_number.setText(mlist.get(position).getCard());
                et_bank_address.setText(mlist.get(position).getAddress());
                et_name.setText(userInfo.getRealName());
                tv_tvchongzhi.setText(mlist.get(position).getCard());
                mPopupWindow.dismiss();
            }
        });
        return popupwindowList;
    }

    private void showPopupWindow() {
        rl_recharge = (RelativeLayout) findViewById(R.id.rl_recharge);
        final ListView pwlist = popupWindowList();
        bankLieAdapter = new BankLieAdapter(getApplication());
        pwlist.setAdapter(bankLieAdapter);
        try {
            getlieBank();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        getTiName();
        rl_recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow = new PopupWindow(pwlist, v.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
                //设置popupwindow点击外部可以被关闭
                mPopupWindow.setOutsideTouchable(true);
                mPopupWindow.setFocusable(true);
                //设置pw的背景
                mPopupWindow.setBackgroundDrawable(new BitmapDrawable(String.valueOf(R.drawable.back)));
                //显示pw
                mPopupWindow.showAsDropDown(v, 2, -5);
            }
        });
    }

    private void initHeadView() {
        title_left_btn = (Button) findViewById(R.id.title_left_btn);
        tv_tvchongzhi = (TextView) findViewById(R.id.tv_tvchongzhi);
        title_left_btn.setBackgroundResource(R.drawable.aar);
        title_left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title_textview = (TextView) findViewById(R.id.title_textview);
        title_textview.setText("提现");
        tv_queren = (TextView) findViewById(R.id.tv_queren);
        et_money = (EditText) findViewById(R.id.et_money);
        et_password = (EditText) findViewById(R.id.et_password);
        tixianBean = new TixianBean();
        tv_queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Double.parseDouble(money) <= 0) {
                    showToast("账户余额不足");
                } else if (Utils.isEmpty(et_name.getText().toString())
                        || Utils.isEmpty(et_bank_address.getText().toString())
                        ) {
                    showToast("请填写完整的登录信息");
                } else if (Utils.isEmpty(et_money.getText().toString())) {
                    showToast("请输入取款金额");
                } else if (Utils.isEmpty(et_password.getText().toString())) {
                    showToast("请输入取款密码");
                } else {
                    loadingWindow.showDialog(Constants.tjing);
                    getTiXian(et_password.getText().toString(), bankId, et_money.getText().toString());
                }
            }
        });
    }

    public void getTiXian(String password, String bankid, String money) {
        AjaxParams params = new AjaxParams();
        params.put("bid", bankid);
        params.put("money", money);
        params.put("password", password);
        wh.configCookieStore(RUser.cookieStore);
        wh.post(Constants.getUrl() + "users/withdrawals", params, new AjaxCallBack<String>() {
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

    public void getlieBank() {
        AjaxParams params = new AjaxParams();
        wh.configCookieStore(RUser.cookieStore);
        wh.post(Constants.getUrl() + "users/get_user_banks", params, new AjaxCallBack<String>() {
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

    Handler handle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    String str = (String) msg.obj;
                    Log.i(TAG, str);
                    BankLieBean bankLieBean = JsonUtil.parseJsonToBean(str, BankLieBean.class);
                    if (bankLieBean.getData().size() == 0) {
                        rl_recharge.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showToast("还没有绑定银行卡");
                            }
                        });
                    } else {
                        mlist = bankLieBean.getData();
                        bankLieAdapter.setData(mlist);
                    }
                    break;
                case 1:
                    loadingWindow.dismiss();
                    //姓名在个人资料里传过来
                    String string = (String) msg.obj;
                    Log.i(TAG, string);
                    BindBankBean bindBankBean = JsonUtil.parseJsonToBean(string, BindBankBean.class);
                    ToastUtil.showToast(Ti_money_activity.this, bindBankBean.getData());
                    ToastUtil.showToast(Ti_money_activity.this, bindBankBean.getError());
                    break;
                default:
            }
        }


    };
}
