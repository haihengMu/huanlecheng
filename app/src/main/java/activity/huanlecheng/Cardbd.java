package activity.huanlecheng;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import org.json.JSONException;
import org.json.JSONObject;

import adapter.BankAdapter;
import bean.BankResponseModel;
import bean.LoginBean;
import bean.NamePBean;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import http.AjaxParams;
import util.ShowToast;
import util.Utils;

public class Cardbd extends BaseActivity {
    private Button title_left_btn;
    private TextView title_textview;
    private Spinner spin_bank;
    private EditText et_mm;// 卡号
    private EditText et_pw;// 开户地址
    private EditText et_pwd;// 取款密码
    private Button bt_set;
    private BankAdapter bankAdapter;
    private BankResponseModel loginBean;
    private String bank_id;
    private LinearLayout load_layout;// 载入中
    private LinearLayout load_layout_fail;// 载入失败
    private TextView txt_neterr;// 载入失败
    private EditText et_bank_num;
    private EditText et_name;
    private LinearLayout ll_layout_xm;
    private ImageView xm_iv_chacha;
    private TextView xm_tv_queren;
    private EditText xm_et_name_qian;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_bd);
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
        title_textview.setText("卡号绑定");
        load_layout = (LinearLayout) findViewById(R.id.view_loading);
        load_layout_fail = (LinearLayout) findViewById(R.id.view_load_fail);
        load_layout_fail.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                load_layout.setVisibility(View.VISIBLE);
                load_layout_fail.setVisibility(View.GONE);
                DownBank();
            }
        });
        txt_neterr = (TextView) findViewById(R.id.txt_neterr);
        loginBean = new BankResponseModel();
        spin_bank = (Spinner) findViewById(R.id.spin_bank);
        bankAdapter = new BankAdapter(this);
        spin_bank.setAdapter(bankAdapter);
        spin_bank.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                bank_id = loginBean.getHX_Bank_Config().get(position)
                        .getB_C_Id();
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        initView();
        et_mm = (EditText) findViewById(R.id.et_mm);
        et_bank_num = (EditText) findViewById(R.id.et_bank_num);
        et_name = (EditText) findViewById(R.id.et_name);
        et_pw = (EditText) findViewById(R.id.et_pw);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        bt_set = (Button) findViewById(R.id.bt_set);

        et_mm.setTextColor(Color.parseColor("#ffffff"));
        et_bank_num.setTextColor(Color.parseColor("#ffffff"));
        et_name.setTextColor(Color.parseColor("#ffffff"));
        et_pw.setTextColor(Color.parseColor("#ffffff"));
        et_pwd.setTextColor(Color.parseColor("#ffffff"));
        bt_set.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub
                if (Utils.isEmpty(et_mm.getText().toString())
                        || Utils.isEmpty(et_pw.getText().toString())
                        || Utils.isEmpty(et_pwd.getText().toString())
                        || !et_mm.getText().toString().equals(et_bank_num.getText().toString())
                        || Utils.isEmpty(et_mm.getText().toString())) {
                    showToast("请填写完整信息");
                } else {
                    loadingWindow.showDialog(Constants.tjing);
                    Updatemm(bank_id, et_mm.getText().toString(), et_pw
                            .getText().toString(), et_pwd.getText().toString());
                    UpBankName(et_name.getText().toString());
                }
            }
        });
        DownBank();
    }

    /**
     * 进入界面初始化绑定姓名页
     */
    private void initView() {
        ll_layout_xm = (LinearLayout) findViewById(R.id.ll_layout);
        xm_tv_queren = (TextView) findViewById(R.id.tv_queding);
        xm_et_name_qian = (EditText) findViewById(R.id.et_name_qian);
        xm_tv_queren.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isEmpty(xm_et_name_qian.getText().toString())) {
                    showToast("请填写姓名");
                } else {
                    loadingWindow.showDialog(Constants.tjing);
                    GetXmUrl(xm_et_name_qian.getText().toString());
                }
            }
        });
    }


    public void GetXmUrl(String name) {
        AjaxParams params = new AjaxParams();
        params.put("name", name);
        wh.configCookieStore(RUser.cookieStore);
        wh.post(Constants.getUrl()+Constants.bankname, params, new AjaxCallBack<String>() {
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
                msg.what = 3;
                msg.obj = s;
                handle.sendMessage(msg);
            }
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }


    public void DownBank() {
        AjaxParams params = new AjaxParams();
        params.put("Model", "System");
        params.put("Action", "GetSystemConfig");
        params.put("models", "HX_Bank_Config");
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

    public void Updatemm(String U_PassWord, String U_PassWord1,
                         String U_PassWord2, String password) {

        AjaxParams params = new AjaxParams();
        params.put("Model", "User");
        params.put("Action", "set_user_bank_list");
        params.put("H_U_B_L_Bank_Id", U_PassWord);
        params.put("H_U_B_L_Bank_Account", U_PassWord1);
        params.put("H_U_B_L_Opening_Address", U_PassWord2);
        params.put("bank_password", password);
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
            JSONObject jsonObject;
            Intent intent;
            switch (msg.what) {
                case 0:
                    try {
                        loadingWindow.cancel();
                        String str = (String) msg.obj;
                        jsonObject = new JSONObject(str);
                        java.lang.reflect.Type type = new TypeToken<BankResponseModel>() {
                        }.getType();
                        loginBean = gson.fromJson(str, type);
                        if (!loginBean.getError().equals("")) {
                            load_layout.setVisibility(View.GONE);
                            load_layout_fail.setVisibility(View.VISIBLE);
                            txt_neterr.setText(loginBean.getMsg() + " 点击屏幕重新加载");
                        } else {
                            bankAdapter.setData(loginBean.getHX_Bank_Config());
                            load_layout.setVisibility(View.GONE);
                            load_layout_fail.setVisibility(View.GONE);
                        }
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    break;
                case 1:
                    try {
                        loadingWindow.cancel();
                        String str = (String) msg.obj;
                        System.out.println(str);
                        jsonObject = new JSONObject(str);
                        java.lang.reflect.Type type = new TypeToken<LoginBean>() {
                        }.getType();
                        LoginBean loginBean = gson.fromJson(str, type);
                       /* if (!loginBean.getError().equals("")) {
                            showToast(loginBean.getMsg());
                        } else {
                            showToast("修改成功！");
                        }*/
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        loadingWindow.cancel();
                        String str = (String) msg.obj;
                        System.out.println(str);
                        jsonObject = new JSONObject(str);
                        java.lang.reflect.Type type = new TypeToken<LoginBean>() {
                        }.getType();
                        LoginBean loginBean = gson.fromJson(str, type);
                      /*  if (!loginBean.getError().equals("")) {
                            showToast(loginBean.getMsg());
                        } else {
                            showToast("修改成功！");
                        }*/
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    String str = (String) msg.obj;
                    java.lang.reflect.Type type = new TypeToken<NamePBean>() {
                    }.getType();
                    NamePBean namePBean = gson.fromJson(str, type);
                    if (namePBean.getError().equals("0")) {
                        showToast("已经绑定无法更改");
                        ll_layout_xm.setVisibility(View.GONE);
                        loadingWindow.cancel();
                    } else {
                        showToast(namePBean.getMsg());
                        ll_layout_xm.setVisibility(View.GONE);
                        loadingWindow.cancel();
                    }
                    break;
            }
        }
    };
}
