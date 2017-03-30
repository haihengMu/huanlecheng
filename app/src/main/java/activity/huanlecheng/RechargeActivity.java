package activity.huanlecheng;


import android.content.Intent;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import adapter.BankCZAdapter;
import adapter.BankLieAdapter;
import bean.BankCZBean;
import bean.BankCZQrenBean;
import bean.GetPayListBean;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import http.AjaxParams;
import util.JsonUtil;
import util.ToastUtil;

/**
 * 快速充值页
 * Created by Administrator on 2016/10/21.
 */

public class RechargeActivity extends BaseActivity {

    private static final String TAG = "RechargeActivity";
    private TextView tv_money;
    private String money;
    private Button title_left_btn;
    private TextView title_textview;
    private RelativeLayout rl_recharge;
    private PopupWindow mPopupWindow;
    private BankLieAdapter bankLieAdapter;

    private TextView tv_queren;
    private BankCZAdapter bankCZAdapter;
    private Object URl;
    private EditText ed_money;
    private ListView pwlist;
    private ArrayList<GetPayListBean.DataBean> mlist;
    private TextView textView2;
    private String paytye;
    private String bankid;
    private String imgstring;
    private String imgmsg;
    private GetPayListBean getPayListBean;
    private EditText et_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        money = getIntent().getStringExtra("M_money");
        mlist = new ArrayList<>();
        initView();
        try {
            getLieBank();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        title_left_btn = (Button) findViewById(R.id.title_left_btn);
        title_textview = (TextView) findViewById(R.id.title_textview);
        title_left_btn.setBackgroundResource(R.drawable.aar);
        title_textview.setText("充值");
        title_textview.setTextSize(22);
        tv_queren = (TextView) findViewById(R.id.tv_queren);
        ed_money = (EditText) findViewById(R.id.ed_money);
        et_name = (EditText) findViewById(R.id.ed_name);
        tv_queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".equals(ed_money.getText().toString())) {
                    if (Double.parseDouble(ed_money.getText().toString()) <= 0) {
                        showToast("请输入正确的金额");
                    } else {
                        if (bankid == null || paytye == null || bankid.equals("") || paytye.equals("")) {
                            showToast("暂时无法充值");
                        } else {
                            try {
                                loadingWindow.showDialog(Constants.tjing);
                                getURl(paytye, bankid, ed_money.getText().toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else {
                    showToast("请输入充值金额");
                }
            }
        });
    }

    /**
     * 充值方式列表
     *
     * @throws Exception
     */
    public void getLieBank() throws Exception {
        AjaxParams params = new AjaxParams();
        wh.configCookieStore(RUser.cookieStore);
        String url = Constants.getUrl() + Constants.getpaylist;
        Log.d(TAG, url);
        wh.post(url, params, new AjaxCallBack<String>() {
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
                    loadingWindow.cancel();
                    String str = (String) msg.obj;
                    Log.e(TAG, str);
                    if (!str.equals("[]")) {
                        getPayListBean = JsonUtil.parseJsonToBean(str, GetPayListBean.class);
                        bankid = getPayListBean.getData().get_$1().getList().get(0).getId();
                        paytye = "1";
                    } else {
                        // mPopupWindow.dismiss();
                        rl_recharge.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showToast("充值通道正在维护");
                            }
                        });
                    }
                    break;
                case 1:
                    loadingWindow.cancel();
                    String string = (String) msg.obj;
                    Log.e(TAG, string);
                    BankCZQrenBean bankCZQrenBean = JsonUtil.parseJsonToBean(string, BankCZQrenBean.class);
                    if (bankCZQrenBean.getData().getManual().getMsg().contains("订单创建成功")) {
                        goActivity(bankCZQrenBean);
                    } else {
                        ToastUtil.showToast(getApplicationContext(), "订单创建失败");
                    }
                    break;
                default:
            }
        }
    };

    private void goActivity(BankCZQrenBean bankCZQrenBean) {
        Intent intent = new Intent();
        intent.setClass(this, BankCZQrenAvtivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", bankCZQrenBean);
        bundle.putString("et_name", et_name.getText().toString());
        bundle.putString("ed_money", ed_money.getText().toString());
        intent.putExtras(bundle);
        this.startActivity(intent);
        finish();
    }

    private void initObject(String imgstring, String imgmsg) {
        Intent intent = new Intent(getApplicationContext(), ActivityMap.class);
        intent.putExtra("img", imgstring);
        intent.putExtra("msg", imgmsg);
        startActivity(intent);
    }

    public void getURl(String paytye, String bankid, String paymoney) throws Exception {
        //["Model":"Mobile","Action":"ready_pay","paytype":paytype!,"bankid":bankid!,"paymoney":firstTF.text!]
        AjaxParams params = new AjaxParams();
        params.put("bank_code", "ALIPAY");
        params.put("remark", et_name.getText().toString());
        params.put("type", paytye);//
        params.put("bank_id", bankid);//
        params.put("money", paymoney);//
        wh.configCookieStore(RUser.cookieStore);
        wh.post(Constants.getUrl() + "users/deposit", params, new AjaxCallBack<String>() {
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
                loadingWindow.cancel();
                ToastUtil.showToast(getApplicationContext(), strMsg);
            }
        });
    }
}
