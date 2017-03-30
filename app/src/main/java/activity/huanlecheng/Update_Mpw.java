package activity.huanlecheng;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import bean.GaiMiBean;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import http.AjaxParams;
import util.ShowToast;

public class Update_Mpw extends BaseActivity {
    private Button title_left_btn;
    private TextView title_textview;
    private EditText pr_mm, et_mm;
    private Button bt_set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xiugai_moneypw);
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
        title_textview.setText("修改资金密码");
        pr_mm = (EditText) findViewById(R.id.pr_mm);
        et_mm = (EditText) findViewById(R.id.et_mm);
        bt_set = (Button) findViewById(R.id.bt_set);

        pr_mm.setTextColor(Color.parseColor("#ffffff"));
        et_mm.setTextColor(Color.parseColor("#ffffff"));
        bt_set.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
              if (pr_mm.getText().toString().equals("")
                        || et_mm.getText().toString().equals("")
                   ) {
                    showToast("请输入密码");
                } else {
                    loadingWindow.showDialog(Constants.tjing);
                    Updatemm(pr_mm.getText().toString(), et_mm.getText()
                            .toString());
                }
            }
        });
    }

    public void Updatemm(String U_PassWord, String U_PassWord1
                       ) {

        AjaxParams params=new AjaxParams();
        params.put("pass_old",U_PassWord);
        params.put("pass_new",U_PassWord1);
        wh.configCookieStore(RUser.cookieStore);
        wh.post(Constants.getUrl() +Constants.moneypass,params, new AjaxCallBack<String>() {
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
                ShowToast.showMsg(Update_Mpw.this, Constants.NETERROR);
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
                        System.out.println(str);
                        jsonObject = new JSONObject(str);
                        java.lang.reflect.Type type = new TypeToken<GaiMiBean>() {
                        }.getType();
                        GaiMiBean loginBean = gson.fromJson(str, type);
                        if (str.indexOf("修改成功")!=-1) {
                            showToast(loginBean.getData());
                        }else {
                            showToast(loginBean.getError());
                        }
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    break;
            }
        }
    };
}
