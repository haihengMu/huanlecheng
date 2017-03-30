package activity.huanlecheng;

import android.graphics.Path;
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

import bean.BindBankBean;
import butterknife.ButterKnife;
import butterknife.InjectView;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import http.AjaxParams;
import util.JsonUtil;
import util.ShowToast;
import util.ToastUtil;

public class OpenActivity extends BaseActivity {

    private static final String TAG = "OpenActivity";
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
    @InjectView(R.id.tv_ptdl)
    TextView tvPtdl;
    @InjectView(R.id.et_yhnc)
    EditText etYhnc;
    @InjectView(R.id.et_fdsz)
    EditText etFdsz;
    @InjectView(R.id.bt_queren)
    Button btqueren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_user);
        ButterKnife.inject(this);
        initView();
        setOnClickListeners();
    }

    private void initView() {
        titleLeftBtn.setBackgroundResource(R.drawable.aar);
    }

    private void setOnClickListeners() {
        titleLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btqueren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etYhnc.getText().toString().isEmpty() || etFdsz.getText().toString().isEmpty()) {
                    ToastUtil.showToast(OpenActivity.this, "请填写完整信息");
                } else {
                    loadingWindow.show();
                    Updatemm();
                }
            }
        });
    }

    public void Updatemm() {
        AjaxParams params = new AjaxParams();
        params.put("gid", "10");
        params.put("uname", etYhnc.getText().toString());
        params.put("rebate", etFdsz.getText().toString());
        wh.configCookieStore(RUser.cookieStore);
        wh.post(Constants.getUrl() + Constants.addTeam, params, new AjaxCallBack<String>() {
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
                ShowToast.showMsg(OpenActivity.this, Constants.NETERROR);
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
                    BindBankBean bindBankBean = JsonUtil.parseJsonToBean(str, BindBankBean.class);
                    String data = bindBankBean.getData();
                    String error = bindBankBean.getError();
                    if ("注册成功".equals(data)) {
                        ToastUtil.showToast(OpenActivity.this, data);
                        finish();
                    } else {
                        ToastUtil.showToast(OpenActivity.this, error);
                    }
                    break;
            }
        }
    };
}
