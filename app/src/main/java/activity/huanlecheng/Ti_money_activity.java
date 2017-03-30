package activity.huanlecheng;

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

import com.google.gson.reflect.TypeToken;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import adapter.BankLieAdapter;
import bean.BankLieBean;
import bean.TiXianNameBean;
import bean.TixianBean;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import http.AjaxParams;
import util.Utils;

/**
 * Created by Administrator on 2016/11/12.
 */

public class Ti_money_activity extends BaseActivity {

    private Button title_left_btn;
    private TextView title_textview;
    private TextView tv_money;
    private String money;
    private RelativeLayout rl_recharge;
    private PopupWindow mPopupWindow;
    private BankLieAdapter bankLieAdapter;
    private List<BankLieBean> mlist;
    private EditText et_bank_address;
    private EditText et_name;
    private EditText et_bank_number;
    private String bankId;
    private TextView tv_queren;
    private EditText et_money;
    private EditText et_password;
    private TixianBean tixianBean;
    private TiXianNameBean tiXianNameBean;
    private TextView tv_names;
    private BankLieBean bankLieBean1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tixian);
        tv_names= (TextView) findViewById(R.id.tv_names);
        mlist = new ArrayList<>();
        tiXianNameBean = new TiXianNameBean();
        bankLieBean1 = new BankLieBean();
        // iniLoading();
        initHeadView();
        initView();
    }


    private void initView() {
        tv_money = (TextView) findViewById(R.id.tv_money);
        et_bank_address = (EditText) findViewById(R.id.et_bank_address);
        et_name = (EditText) findViewById(R.id.et_name);
        et_bank_number = (EditText) findViewById(R.id.et_bank_number);
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
        final ListView popupwindowList = new ListView(getApplicationContext());
        popupwindowList.setDividerHeight(0);
        popupwindowList.setBackgroundColor(Color.rgb(63,65,78));
        // 去掉右侧垂直滑动条
        popupwindowList.setVerticalScrollBarEnabled(false);
        popupwindowList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bankId = mlist.get(position).getH_U_B_L_Id();
                et_bank_number.setText(mlist.get(position).getH_U_B_L_Bank_Account());
                et_bank_address.setText(mlist.get(position).getH_U_B_L_Opening_Address());
                et_name.setText(tv_names.getText());
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
        getTiName();
        rl_recharge.setOnClickListener(new View.OnClickListener() {
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

    private void initHeadView() {
        title_left_btn = (Button) findViewById(R.id.title_left_btn);
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
                    getTiXian(et_password.getText().toString(), bankId, et_money.getText().toString());
                }
            }
        });
    }

    /**
     * 绑定银行卡姓名
     */
    public void getTiName() {
        AjaxParams params = new AjaxParams();
        params.put("Model", "User");
        params.put("Action", "GetUserInfo");
        params.put("r", "no");
        wh.configCookieStore(RUser.cookieStore);
        wh.post(Constants.getUrl(), params, new AjaxCallBack<String>() {
            @Override
            public AjaxCallBack<String> progress(boolean progress, int rate) {
                return super.progress(progress, rate);
            }

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
                msg.what = 2;
                msg.obj = s;
                handle.sendMessage(msg);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }

    public void getTiXian(String password, String bankid, String money) {
    /*    AjaxParams params = new AjaxParams();
        params.put("Model", "User");
        params.put("Action", "ApplicationWithdrawal_Add");
        params.put("Data", "T_PassWord=" + password + "&bank=" + bankid + "&T_Money=" + money);*/
        String url="T_PassWord=" + password + "&bank=" + bankid + "&T_Money=" + money;
        wh.configCookieStore(RUser.cookieStore);
        wh.get(Constants.getUrl()+"?Model=User&Action=ApplicationWithdrawal_Add&Data="+ URLEncoder.encode(url), new AjaxCallBack<String>() {
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
        params.put("Model", "User");
        params.put("Action", "get_user_bank_list");
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
                    String str = (String) msg.obj;
                    java.lang.reflect.Type type = new TypeToken<List<BankLieBean>>() {
                    }.getType();
                    if (!str.equals("[]")){
                        mlist=gson.fromJson(str,type);
                        bankLieAdapter.setData(mlist);
                    }else {
                       rl_recharge.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               showToast("还没有绑定银行卡");
                           }
                       });
                        return;
                    }
                    break;
                case 1:
                    //姓名在个人资料里传过来
                    String string = (String) msg.obj;
                    System.out.print(string);
                    java.lang.reflect.Type type1=new TypeToken<TixianBean>(){}.getType();
                   tixianBean=gson.fromJson(string,type1);
                    if (tixianBean.getError()==1){
                        showToast(tixianBean.getMsg());
                    }else {
                        showToast(tixianBean.getMsg());
                    }
                    break;
                case 2:
                    String s1 = (String) msg.obj;
                    java.lang.reflect.Type type2 = new TypeToken<TiXianNameBean>() {
                    }.getType();
                    tiXianNameBean = gson.fromJson(s1, type2);
                    if (!tiXianNameBean.getUserinfo().getU_WithdrawalsName().equals("")) {
                        tv_names.setText(tiXianNameBean.getUserinfo().getU_WithdrawalsName());
                    }else {

                    }
                    break;
                default:
            }
        }


    };
}
