package activity.huanlecheng;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import bean.LoginBean;
import bean.LoginErrorBean;
import bean.LoginGXBean;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import http.AjaxParams;
import util.ShowToast;
import util.Utils;

public class LoginActivity extends BaseActivity {
    private EditText username;
    private EditText password;
    private EditText code;
    private Button btn_login;
    Random ra = new Random();
    private ImageView iv;
    RequestQueue mRequestQueue;
    HttpStack httpStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DefaultHttpClient httpclient = new DefaultHttpClient();
        CookieStore cookieStore = new BasicCookieStore();
        RUser.cookieStore = cookieStore;
        httpclient.setCookieStore(cookieStore);
        httpStack = new HttpClientStack(httpclient);
        this.mRequestQueue = Volley.newRequestQueue(this, httpStack);
        // 修改引导页唯一标示
        PackageInfo info;
        try {
            info = this.getPackageManager().getPackageInfo(
                    this.getPackageName(), 0);
            SharedPreferences sharedPreferences = getSharedPreferences("guide",
                    Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("guide", "" + info.versionCode);
            editor.commit();
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        username = (EditText) findViewById(R.id.username);
//		username.setText("ceshi1122");
//		username.setText("sonicc11");
        username.setText(userInfo.getLogin_Name());
        password = (EditText) findViewById(R.id.password);
//		password.setText("a112233");
//		password.setText("123456");
        password.setText(userInfo.getPassword());
        code = (EditText) findViewById(R.id.code);
        iv = (ImageView) findViewById(R.id.iv);
        iv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                reimg();
            }
        });
        reimg();
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (username.getText().toString().equals("")
                        || password.getText().toString().equals("")
                        || code.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "请填写完整信息", Toast.LENGTH_SHORT).show();
                } else {
                    loadingWindow.showDialog(Constants.logining);
                    requestString(username.getText().toString(), password
                            .getText().toString(), code.getText().toString());
                }

            }
        });
    }

    /**
     * 请求图片
     */
    public void reimg() {

        ImageRequest imageRequest = new ImageRequest(Constants.base_url
                + Constants.logincode + "session_name=user_login&type=4&rand=" + ra.nextInt(10) + 1,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        iv.setImageBitmap(response);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iv.setImageResource(R.drawable.ic_launcher);
            }
        });

        mRequestQueue.add(imageRequest);
    }

    /**
     * 请求登陆
     *
     * @param username
     * @param password
     * @param code
     */
    public void requestString(String username, String password, String code) {

        AjaxParams params = new AjaxParams();
        params.put("username", username);
        params.put("password", password);
        params.put("verification", code);
        wh.configCookieStore(RUser.cookieStore);
        wh.post(Constants.getUrl() + Constants.login, params, new AjaxCallBack<String>() {
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
                ShowToast.showMsg(LoginActivity.this, Constants.NETERROR);
                loadingWindow.cancel();
            }

        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            MyApplication.finishAllActivity();
            System.exit(0);
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 请求个人信息
     */
    public void requestUserinfo() {
        wh.configCookieStore(RUser.cookieStore);
        wh.post(Constants.getUrl() + Constants.grxx, new AjaxCallBack<String>() {
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
                ShowToast.showMsg(LoginActivity.this, Constants.NETERROR);
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
                        String str = (String) msg.obj;
                        jsonObject = new JSONObject(str);
                        Gson gson = new Gson();
                        int a = str.indexOf("error");
                        if (a == -1) {
                            java.lang.reflect.Type type = new TypeToken<LoginBean>() {
                            }.getType();
                            LoginBean loginBean = gson.fromJson(str, type);
                            showToast("登录成功");
                            requestUserinfo();
                        } else {
                            java.lang.reflect.Type type = new TypeToken<LoginErrorBean>() {
                            }.getType();
                            LoginErrorBean loginErrorBean = gson.fromJson(str, type);
                            loadingWindow.cancel();
                            showToast("登录失败");
                        }
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    break;
                case 1:
                    String str = (String) msg.obj;
                    if (str.indexOf("error") != -1) {
                        java.lang.reflect.Type type = new TypeToken<LoginErrorBean>() {
                        }.getType();
                        LoginErrorBean loginErrorBean = gson.fromJson(str, type);
                        showToast(loginErrorBean.getError());
                        loadingWindow.cancel();
                    } else {
                        java.lang.reflect.Type type = new TypeToken<LoginGXBean>() {
                        }.getType();
                        LoginGXBean loginBean = gson.fromJson(str, type);
                            userInfo.setU_UserName(loginBean.getData().getH_u_name());
                            userInfo.setU_Money(loginBean.getData().getH_u_balance());
                         //   userInfo.setU_Head(ur.getU_Head());
                            userInfo.setU_RebateA(loginBean.getData().getH_u_max_rebate());
                            userInfo.setU_RebateB(loginBean.getData().getH_u_same_rebate());
                            userInfo.setLogin_Name(loginBean.getData().getH_u_nickname());
                            userInfo.setPassword(password.getText().toString());
                            if (Utils.isEmpty(userInfo.getCorner())) {
                                userInfo.setCorner("元");
                            }
                            intent = new Intent(LoginActivity.this,
                                    MainActivity.class);
                            MyApplication.getInstance().isAutoLogin(true);
                            startActivity(intent);
                            loadingWindow.cancel();
                            finish();
                    }
                    break;
                default:
                    break;
            }
        }

    } ;
    }
