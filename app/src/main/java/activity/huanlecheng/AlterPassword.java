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
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import bean.GaiMiBean;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import http.AjaxParams;
import util.ShowToast;

public class AlterPassword extends BaseActivity

{
    private EditText et_dqmm;
    private EditText et_newmm;
    private Button bt_xg;
    private Button title_left_btn;
    private TextView title_textview;
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xiugai_pw);
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
        title_textview.setText("修改密码");
        et_dqmm = (EditText) findViewById(R.id.pr_mm);//旧
        et_newmm = (EditText) findViewById(R.id.et_mm);//新1
        bt_xg = (Button) findViewById(R.id.bt_set);

        et_dqmm.setTextColor(Color.parseColor("#ffffff"));
        et_newmm.setTextColor(Color.parseColor("#ffffff"));

        bt_xg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String U_PassWord = et_dqmm.getText().toString();
                String U_PassWord2 = et_newmm.getText().toString();
             if (et_dqmm.getText().toString().equals("")
                        || et_newmm.getText().toString().equals("")) {
                    Toast.makeText(AlterPassword.this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else {
                    loadingWindow.showDialog(Constants.tjing);
                    Updatemm(U_PassWord, U_PassWord2);
                }

            }

        });

    }

    public void Updatemm(String U_PassWord,
                         String U_PassWord2) {
        AjaxParams params=new AjaxParams();
        params.put("pass_old",U_PassWord);
        params.put("pass_new",U_PassWord2);
        wh.configCookieStore(RUser.cookieStore);
        wh.post(Constants.getUrl()+Constants.newpass,params, new AjaxCallBack<String>() {
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
                System.out.println(strMsg);
                ShowToast.showMsg(AlterPassword.this, Constants.NETERROR);
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
