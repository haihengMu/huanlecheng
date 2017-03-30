package activity.huanlecheng;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import bean.GaoXiangBean;

import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import http.AjaxParams;


public class BroswerActivity extends BaseActivity {
    private Button title_left_btn;
    private TextView title_textview;
    private TextView browser_detail_date;
    private TextView tv_shuoming;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broswer_detail);
        String id=getIntent().getStringExtra("model");
        getUUrl(id);
        initHead();
        initView();
    }

    private void initHead() {
        title_left_btn = (Button) findViewById(R.id.title_left_btn);
        title_left_btn.setBackgroundResource(R.drawable.aar);
        title_left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });
        title_textview = (TextView) findViewById(R.id.title_textview);
        title_textview.setText("公告详情");
    }

    private void initView() {
        browser_detail_date = (TextView) findViewById(R.id.browser_detail_date);
        tv_shuoming = (TextView) findViewById(R.id.tv_shuoming);

    }

    private void initObject(GaoXiangBean gaoXiangBean) {
        browser_detail_date.setText(gaoXiangBean.getData().getPrev().getH_n_title());
        tv_shuoming.setText(gaoXiangBean.getData().getPrev().getH_n_text());
    }

    public void getUUrl(String h_n_id) {
        AjaxParams params = new AjaxParams();
        params.put("id", h_n_id);
        wh.configCookieStore(RUser.cookieStore);
        wh.post(Constants.getUrl() + Constants.ggx, params, new AjaxCallBack<String>() {
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
                showToast("网络加载失败");
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    String str = (String) msg.obj;
                    java.lang.reflect.Type type = new TypeToken<GaoXiangBean>() {
                    }.getType();
                    GaoXiangBean gaoXiangBean = gson.fromJson(str, type);
                    if (!gaoXiangBean.getData().equals("")) {
                        initObject(gaoXiangBean);
                    }
                    break;
                default:
            }
        }
    };


}