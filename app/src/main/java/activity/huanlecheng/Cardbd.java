package activity.huanlecheng;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import adapter.BankAdapter;
import bean.BankResponseModel;
import bean.BindBankBean;
import bean.LoginBean;
import bean.MessageEvent;
import bean.NamePBean;
import bean.ProvinceCityBean;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import http.AjaxParams;
import util.JsonUtil;
import util.ShowToast;
import util.ToastUtil;
import util.Utils;

public class Cardbd extends BaseActivity {
    private static final String TAG = "Cardbd";
    private Button title_left_btn;
    private TextView title_textview;
    private Spinner spin_bank;
    private EditText et_mm;// 卡号
    private EditText et_bank_num;
    private TextView et_pw;// 开户地址
    private EditText et_pwd;// 取款密码
    private Button bt_set;
    private BankAdapter bankAdapter;
    private BankResponseModel BankResponseModelBean;
    private String bank_id;
    private LinearLayout load_layout;// 载入中
    private LinearLayout load_layout_fail;// 载入失败
    private TextView txt_neterr;// 载入失败
    private EditText et_name;
    private LinearLayout ll_layout_xm;
    private ImageView xm_iv_chacha;
    private TextView xm_tv_queren;
    private EditText xm_et_name_qian;
    private ProvinceCityBean.DataBean mdataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_bd);
        initView();
        setOnClickListeners();
        et_mm.setTextColor(Color.parseColor("#ffffff"));
        et_bank_num.setTextColor(Color.parseColor("#ffffff"));
        et_name.setTextColor(Color.parseColor("#ffffff"));
        et_pw.setTextColor(Color.parseColor("#ffffff"));
        et_pwd.setTextColor(Color.parseColor("#ffffff"));
        BankResponseModelBean = new BankResponseModel();
        bankAdapter = new BankAdapter(this);
        spin_bank.setAdapter(bankAdapter);
        EventBus.getDefault().register(this);
        DownBank();
    }

    /**
     * 进入界面初始化绑定姓名页
     */
    private void initView() {
        title_left_btn = (Button) findViewById(R.id.title_left_btn);
        title_left_btn.setBackgroundResource(R.drawable.aar);
        title_textview = (TextView) findViewById(R.id.title_textview);
        title_textview.setText("卡号绑定");
        load_layout = (LinearLayout) findViewById(R.id.view_loading);
        load_layout_fail = (LinearLayout) findViewById(R.id.view_load_fail);
        txt_neterr = (TextView) findViewById(R.id.txt_neterr);
        spin_bank = (Spinner) findViewById(R.id.spin_bank);
        et_mm = (EditText) findViewById(R.id.et_mm);
        et_bank_num = (EditText) findViewById(R.id.et_bank_num);
        et_name = (EditText) findViewById(R.id.et_name);
        et_pw = (TextView) findViewById(R.id.et_pw);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        bt_set = (Button) findViewById(R.id.bt_set);
    }

    private void setOnClickListeners() {
        //        load_layout_fail.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                load_layout.setVisibility(View.VISIBLE);
//                load_layout_fail.setVisibility(View.GONE);
//                DownBank();
//            }
//        });

        et_pw.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cardbd.this,ProvinceActivity.class);
                startActivity(intent);
            }
        });
        spin_bank.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                bank_id = BankResponseModelBean.getData().get(position)
                        .getId();
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        bt_set.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isEmpty(et_mm.getText().toString()) || Utils.isEmpty(et_pw.getText().toString()) || Utils.isEmpty(et_pwd.getText().toString()) || !et_mm.getText().toString().equals(et_bank_num.getText().toString()) || Utils.isEmpty(et_mm.getText().toString()) || Utils.isEmpty(et_bank_num.getText().toString())) {
                    showToast("请填写完整信息");
                } else {
                    loadingWindow.showDialog(Constants.tjing);
                    Updatemm(bank_id, et_mm.getText().toString(), et_pw.getText().toString(), et_pwd.getText().toString());
                }
            }
        });
        title_left_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Subscribe
    public void onEventMainThread(ProvinceCityBean.DataBean dataBean) {
        mdataBean = dataBean;
        Log.e(TAG, mdataBean.getName());
        et_pw.setText(mdataBean.getName());
    }

    public void DownBank() {
        AjaxParams params = new AjaxParams();
        wh.configCookieStore(RUser.cookieStore);
        wh.post(Constants.getUrl() + Constants.getbanks, params, new AjaxCallBack<String>() {
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
                ShowToast.showMsg(Cardbd.this, Constants.NETERROR);
                load_layout.setVisibility(View.GONE);
                load_layout_fail.setVisibility(View.VISIBLE);
                txt_neterr.setText(Constants.NETERROR + " 点击屏幕重新加载");
            }

        });
    }

    /**
     * 银行卡姓名
     */
    public void UpBankName(String U_PassWord) {
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
                msg.what = 2;
                msg.obj = t;
                handle.sendMessage(msg);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) { // 加载失败的时候回调
                // TODO Auto-generated method stub
                super.onFailure(t, errorNo, strMsg);
                ShowToast.showMsg(Cardbd.this, Constants.NETERROR);
                loadingWindow.cancel();
            }

        });
    }

    public void Updatemm(String U_PassWord, String U_PassWord1, String U_PassWord2, String password) {
        AjaxParams params = new AjaxParams();
        params.put("h_u_b_bid", bank_id);// 银行ID
        params.put("h_u_b_account_card", U_PassWord1);// 银行卡号
        params.put("h_u_b_account_address", U_PassWord2); // 开户银行地址(调用地址列表绑定)
        params.put("pass", password); //取款密码
        wh.configCookieStore(RUser.cookieStore);
        wh.post(Constants.getUrl() + Constants.setUserBank, params, new AjaxCallBack<String>() {
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
                msg.what = 1;
                msg.obj = t;
                handle.sendMessage(msg);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) { // 加载失败的时候回调
                // TODO Auto-generated method stub
                super.onFailure(t, errorNo, strMsg);
                ShowToast.showMsg(Cardbd.this, Constants.NETERROR);
                loadingWindow.cancel();
            }

        });

    }

    Handler handle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    loadingWindow.cancel();
                    String str = (String) msg.obj;
                    Log.i(TAG, str);
                    BankResponseModelBean = JsonUtil.parseJsonToBean(str, BankResponseModel.class);
                    if (BankResponseModelBean.getData().size() == 0) {
                        load_layout.setVisibility(View.GONE);
                        load_layout_fail.setVisibility(View.VISIBLE);
                        txt_neterr.setText("点击屏幕重新加载");
                    } else {
                        bankAdapter.setData(BankResponseModelBean.getData());
                        load_layout.setVisibility(View.GONE);
                        load_layout_fail.setVisibility(View.GONE);
                    }
                    break;
                case 1:
                    loadingWindow.cancel();
                    String str1 = (String) msg.obj;
                    Log.i(TAG, str1);
                    BindBankBean bindBankBean = JsonUtil.parseJsonToBean(str1, BindBankBean.class);
                    String data = bindBankBean.getData();
                    String error = bindBankBean.getError();
                    if ("添加成功".equals(data)) {
                        ToastUtil.showToast(Cardbd.this, data);
                        finish();
                    } else {
                        ToastUtil.showToast(Cardbd.this, error);
                    }
                    break;
            }
        }
    };
}
