package activity.huanlecheng;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import bean.LoginGXBean;
import bean.TuichuBean;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import util.ShowToast;

public class Person_Set extends BaseActivity {
    private RelativeLayout xiugaimmLayout, safeLayout, xgmonpwLayout,
            cardbdLayout, cardbdname, circleflms;
    //private ToggleButton mToggleButton;
    private Button title_left_btn;
    private TextView title_textview;
    private TextView ytext;
    private Button bt_tuichu;

    //private TextView dqbb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myset);
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
        title_textview.setText("账户设置");
        xiugaimmLayout = (RelativeLayout) findViewById(R.id.xgloginmm);
        safeLayout = (RelativeLayout) findViewById(R.id.safepro);
        xgmonpwLayout = (RelativeLayout) findViewById(R.id.xgzjmm);
        cardbdLayout = (RelativeLayout) findViewById(R.id.cardbd);
        circleflms = (RelativeLayout) findViewById(R.id.circleflms);
        //mToggleButton = (ToggleButton) findViewById(R.id.tglSound);
        //cardbdname = (RelativeLayout) findViewById(R.id.cardbdname);
        ytext = (TextView) findViewById(R.id.ytext);
        ytext.setText(userInfo.getCorner());
        //dqbb=(TextView)findViewById(R.id.dqbb);
        PackageInfo pkinfo;
        try {
            pkinfo = this.getPackageManager().getPackageInfo(
                    this.getPackageName(), 0);
            //	dqbb.setText("当前版本：" + pkinfo.versionName);
        } catch (NameNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        bt_tuichu = (Button) findViewById(R.id.bt_tuichu);
        bt_tuichu.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                loadingWindow.showDialog("正在安全退出账号");
                Updatemm();
            }
        });
        /*mToggleButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

			}
		});*/
        cardbdLayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Person_Set.this, Cardbd.class);
                startActivity(intent);
            }
        });
        xgmonpwLayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Person_Set.this, Update_Mpw.class);
                startActivity(intent);
            }
        });

        safeLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                getUrl();

            }
        });
        xiugaimmLayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Person_Set.this, AlterPassword.class);
                startActivity(intent);
            }
        });
    /*	cardbdname.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Person_Set.this, Cardbd_name.class);
				startActivity(intent);
			}
		});*/
        circleflms.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int i = 0;
                if (ytext.getText().equals("元")) {
                    i = 0;
                } else if (ytext.getText().equals("角")) {
                    i = 1;
                } else if (ytext.getText().equals("分")) {
                    i = 2;
                } else {
                    i = 3;
                }
                final String string[] = new String[]{"元", "角", "分", "厘"};
                new AlertDialog.Builder(Person_Set.this)
                        .setTitle("请选择")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setSingleChoiceItems(string, i,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        userInfo.setCorner(string[which]);
                                        ytext.setText(string[which]);
                                        dialog.dismiss();
                                    }
                                }).setNegativeButton("取消", null).show();
            }
        });
    }

    public void Updatemm() {
        wh.configCookieStore(RUser.cookieStore);
        wh.get(Constants.getUrl()+Constants.tuichu, new AjaxCallBack<String>() {
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
                ShowToast.showMsg(Person_Set.this, Constants.NETERROR);
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
                        java.lang.reflect.Type type = new TypeToken<TuichuBean>() {
                        }.getType();
                        TuichuBean tuichuBean = gson.fromJson(str, type);
                        MyApplication.getInstance().isAutoLogin(false);
                    //    if (loginBean.getData().equals("")) {
                            //showToast(loginBean.getMsg());
                            userInfo.cleanUserinfo();
                            MyApplication.finishAllActivity();
                            intent = new Intent(getApplication(), LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            android.os.Process.killProcess(android.os.Process.myPid());
                            finish();
                   /*     } else {
                            userInfo.cleanUserinfo();
                            MyApplication.finishAllActivity();
                            intent = new Intent(getApplication(), LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            android.os.Process.killProcess(android.os.Process.myPid());
                            finish();
                        }*/
                        MainActivity.closeAll();
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    String str = (String) msg.obj;
                    java.lang.reflect.Type type2 = new TypeToken<LoginGXBean>() {
                    }.getType();
                    LoginGXBean loginGXBean = gson.fromJson(str, type2);
                    if (loginGXBean.getData().getH_u_safety_qid().equals("0")){//没有设置密保,设置
                        Intent intent1 = new Intent(Person_Set.this, Safe_Page.class);
                        startActivity(intent1);
                    }else {//设置了修改
                        Intent in = new Intent(Person_Set.this, XiuGaiActivity.class);
                        String a=loginGXBean.getData().getH_u_safety_qid();
                        in.putExtra("wenti", loginGXBean.getData().getH_u_safety_qid());
                        startActivity(in);
                    }
                    break;
            }
        }
    };

    public void getUrl() {
        wh.configCookieStore(RUser.cookieStore);
        wh.post(Constants.getUrl() + Constants.grxx, new AjaxCallBack<String>() {
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
                showToast("无法连接,请检查你的网络");
            }
        });
    }
}
