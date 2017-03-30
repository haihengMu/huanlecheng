package activity.huanlecheng;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import bean.UpDataBean;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;


public class WelcomeActivity extends BaseActivity {
    private ImageView iView;
    AlphaAnimation aa = new AlphaAnimation(0.1f, 1.0f);
    private String guide_id;// 判断是否需要引导页
    private String pk_infoString;
    private TextView tv_vison;
    private PackageInfo pkinfo;
    private ProgressDialog pd;//进度条对话框
    private RelativeLayout rl_layout;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        SharedPreferences sPreferences = getSharedPreferences("guide",
                Activity.MODE_PRIVATE);
        getURL();
        iView = (ImageView) findViewById(R.id.ImageView01);
        tv_vison = (TextView) findViewById(R.id.tv_vison);
        guide_id = sPreferences.getString("guide", "");
        try {
            pkinfo = this.getPackageManager().getPackageInfo(
                    this.getPackageName(), 0);
            pk_infoString = "" + pkinfo.versionCode;
            tv_vison.setText("" + pkinfo.versionName);
        } catch (NameNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        rl_layout = (RelativeLayout) findViewById(R.id.rl_layout);

        //获取服务器版本号
    }

    private void initView() {
        aa.setDuration(2000);
        iView.startAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                if (!guide_id.equals(pk_infoString)) {
                    Intent i = new Intent(WelcomeActivity.this,
                            GuideActivity.class);
                    i.putExtra("g", "0");
                    startActivity(i);
                    finish();
                } else {
                            Intent i = new Intent(WelcomeActivity.this,
                                    LoginActivity.class);
                  //  Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationStart(Animation arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    /**
     * 请求更新服务器
     */
    public void getURL() {
 /*       AjaxParams params = new AjaxParams();
        params.put("Model", "Mobile");
        params.put("Action", "get_mobile_version");*/
        wh.configCookieStore(RUser.cookieStore);
        wh.post("https://appdownload.public.ms/hlc/version", new AjaxCallBack<String>() {
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
                loadingWindow.cancel();
                showToast("网络请求失败,请检查你的网络");
                rl_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadingWindow.showDialog(Constants.denglu);
                        getURL();
                    }
                });
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    loadingWindow.cancel();
                    String string = (String) msg.obj;
                    java.lang.reflect.Type type = new TypeToken<UpDataBean>() {
                    }.getType();
                    UpDataBean upDataBean = gson.fromJson(string, type);
                    String vserionname = upDataBean.getAndroid().getVersion();
                    // List<String> list = new ArrayList<>();
                    //如果网络请求回来的版本比本地版本大提示更新
                    if (Double.parseDouble(vserionname)> Double.parseDouble(pkinfo.versionName)){
                        showUpdateDialog(upDataBean.getAndroid().getNote(), upDataBean.getAndroid().getUrl());
                    }else {
                        initView();
                    }

                    break;
                default:
            }
        }
    };

    /**
     * 显示自定更新对话框
     */
    private void showUpdateDialog(final String str, final String url) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("版本更新");
        builder.setMessage(str);
        builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(url);
                intent.setData(content_url);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                initView();
            }
        });
        builder.show();
    }

}
