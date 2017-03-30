package activity.huanlecheng;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import bean.BankNameBean;
import butterknife.ButterKnife;
import butterknife.InjectView;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import http.AjaxParams;
import util.JsonUtil;
import util.ToastUtil;

/**
 * Created by Mhc on 2017/3/20.
 */

public class BindingBankNameActivity extends BaseActivity {
    private static final String TAG = "BindingBankNameActivity";
    @InjectView(R.id.title_left_btn)
    Button titleLeftBtn;
    @InjectView(R.id.title_left_r)
    RelativeLayout titleLeftR;
    @InjectView(R.id.title_textview)
    TextView titleTextview;
    @InjectView(R.id.title_relavite)
    RelativeLayout titleRelavite;
    @InjectView(R.id.title_right_txt)
    TextView titleRightTxt;
    @InjectView(R.id.title_right_click)
    RelativeLayout titleRightClick;
    @InjectView(R.id.progressBar1)
    ProgressBar progressBar1;
    @InjectView(R.id.test)
    RelativeLayout test;
    @InjectView(R.id.et_bank_name)
    EditText etBankName;
    @InjectView(R.id.bt_queren)
    Button btQueren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_bank_name);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        titleTextview.setText("绑定银行姓名");
        titleLeftBtn.setBackgroundResource(R.drawable.aar);
        titleLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btQueren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(etBankName.getText().toString())) {
                    ToastUtil.showToast(BindingBankNameActivity.this, "请填写姓名");
                } else {
                    BindingBankName();
                }
            }
        });
    }

    private void BindingBankName() {
        AjaxParams params = new AjaxParams();
        params.put("name", etBankName.getText().toString());
        wh.configCookieStore(RUser.cookieStore);
        String url = Constants.getUrl() + Constants.setUserRealName;
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
                    String str = (String) msg.obj;
                    Log.i(TAG, str);
                    BankNameBean bankNameBean = JsonUtil.parseJsonToBean(str, BankNameBean.class);
                    String data = bankNameBean.getData();
                    if ("操作成功".equals(data)) {
                        ToastUtil.showToast(getApplicationContext(), data);
                        finish();
                    } else {
                        String error = bankNameBean.getError();
                        ToastUtil.showToast(getApplicationContext(), error);
                    }
                    break;
            }
        }
    };
}
