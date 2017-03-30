package activity.huanlecheng;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import http.AjaxParams;

/**
 * 快速充值页
 * Created by Administrator on 2016/10/21.
 */

public class RechargeActivity extends BaseActivity {

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
    private List<BankCZBean> mlist;
    private TextView textView2;
    private String paytye;
    private String bankid;
    private String imgstring;
    private String imgmsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        money = getIntent().getStringExtra("M_money");
        textView2 = (TextView) findViewById(R.id.textView2);
        mlist = new ArrayList<>();
        initView();
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
                textView2.setText(mlist.get(position).getH_T_P_T_Nickname());
                if (mlist.get(position).getH_T_P_T_BankCode().size() == 0) {
                    showToast("正在维护,请选择其他充值方式");
                    mPopupWindow.dismiss();
                } else {
                    paytye = mlist.get(position).getH_T_P_T_Id();
                    bankid = mlist.get(position).getH_T_P_T_BankCode().get(0).getId();
                    mPopupWindow.dismiss();
                }
            }
        });
        return popupwindowList;
    }

    private void showPopupWindow() {
        rl_recharge = (RelativeLayout) findViewById(R.id.rl_recharge);
        pwlist = popupWindowList();
        bankCZAdapter = new BankCZAdapter(getApplicationContext());
        pwlist.setAdapter(bankCZAdapter);
        try {
            getLieBank();
        } catch (Exception e) {
            e.printStackTrace();
        }
        rl_recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow = new PopupWindow(pwlist, v.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
                //设置popupwindow点击外部可以被关闭
                mPopupWindow.setOutsideTouchable(true);
                //设置pw的背景
//                mPopupWindow.setBackgroundDrawable(new BitmapDrawable(String.valueOf(R.drawable.changfangs)));
                mPopupWindow.setBackgroundDrawable(new BitmapDrawable(String.valueOf(R.drawable.back)));
                //显示pw
                mPopupWindow.showAsDropDown(v, 2, -5);
            }
        });
    }

    private void initView() {
        title_left_btn = (Button) findViewById(R.id.title_left_btn);
        title_textview = (TextView) findViewById(R.id.title_textview);
        tv_money = (TextView) findViewById(R.id.tv_money);

        tv_money.setText(money);
        title_left_btn.setBackgroundResource(R.drawable.aar);
        title_textview.setText("充值");
        title_textview.setTextSize(22);
        tv_queren = (TextView) findViewById(R.id.tv_queren);
        ed_money = (EditText) findViewById(R.id.ed_money);
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
        params.put("Model", "Mobile");
        params.put("Action", "get_pay_list");
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
                    java.lang.reflect.Type type = new TypeToken<List<BankCZBean>>() {
                    }.getType();
                    Gson gson = new Gson();
                    mlist = gson.fromJson(str, type);
                    if (!str.equals("[]")) {
                        bankCZAdapter.setData(mlist);
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
                    java.lang.reflect.Type type1 = new TypeToken<BankCZQrenBean>() {
                    }.getType();
                    Gson gson1 = new Gson();
                    BankCZQrenBean bankCZQrenBean = gson1.fromJson(string, type1);
                    if (bankCZQrenBean.getError() == 1) {
                        showToast(bankCZQrenBean.getMsg());
                    } else {
                        imgstring = bankCZQrenBean.getImg();
                        imgmsg = bankCZQrenBean.getMsg();
                        initObject(imgstring, imgmsg);
                    }
                    break;
                default:
            }
        }
    };

    private void initObject(String imgstring, String imgmsg) {
        Intent intent = new Intent(getApplicationContext(), ActivityMap.class);
        intent.putExtra("img", imgstring);
        intent.putExtra("msg", imgmsg);
        startActivity(intent);
    }

    public void getURl(String paytye, String bankid, String paymoney) throws Exception {
        //["Model":"Mobile","Action":"ready_pay","paytype":paytype!,"bankid":bankid!,"paymoney":firstTF.text!]
        AjaxParams params = new AjaxParams();
        params.put("Model", "Mobile");
        params.put("Action", "ready_pay");
        params.put("paytype", paytye);
        params.put("bankid", bankid);
        params.put("paymoney", paymoney);
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
                msg.what = 1;
                msg.obj = s;
                handle.sendMessage(msg);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                loadingWindow.cancel();
            }
        });
    }
}
