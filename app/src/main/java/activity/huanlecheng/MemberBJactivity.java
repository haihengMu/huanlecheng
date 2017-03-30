package activity.huanlecheng;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import bean.MemberBJbean;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import http.AjaxParams;

/**
 * 会员编辑页
 * Created by Administrator on 2016/12/12.
 */

public class MemberBJactivity extends BaseActivity implements View.OnClickListener {

    private Button title_left_btn;
    private TextView title_textview;
    private String u_lasttime;
    private String u_regtime;
    private String u_username;
    private String u_name;
    private String u_id;
    private EditText et_fandian;
    private LinearLayout ll_quren;
    private TextView tv_zhanghao;
    private TextView tv_nicheng;
    private TextView tv_time;
    private TextView tv_last_time;
    private TextView tv_fandian1;
    private TextView tv_shengyu1;
    private TextView tv_zhuce1;
    private TextView tv_all1;
    private TextView tv_fandian2;
    private TextView tv_shengyu2;
    private TextView tv_zhuce2;
    private TextView tv_all2;
    private TextView tv_fandian;
    private TextView tv_shengyu;
    private TextView tv_zhuce;
    private TextView tv_all;
    private LinearLayout ll_all;
    private LinearLayout ll_all1;
    private LinearLayout ll_all2;
    private LinearLayout ll_all3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_bianji);
        initJSView();
        initHead();
        initView();
    }

    /**
     * 接收的传过来的数据
     */
    private void initJSView() {
        u_name = getIntent().getStringExtra("u_name");
        u_username = getIntent().getStringExtra("u_username");
        u_regtime = getIntent().getStringExtra("u_regtime");
        u_lasttime = getIntent().getStringExtra("u_lasttime");
        u_id = getIntent().getStringExtra("u_id");
    }


    /**
     * 初始化头
     */
    private void initHead() {
        title_left_btn = (Button) findViewById(R.id.title_left_btn);
        title_textview = (TextView) findViewById(R.id.title_textview);
        title_left_btn.setBackgroundResource(R.drawable.aar);
        title_textview.setText("会员编辑");
        title_left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 初始化界面
     */
    private void initView() {
        et_fandian = (EditText) findViewById(R.id.et_fandian);
        ll_quren = (LinearLayout) findViewById(R.id.ll_quren);
        ll_quren.setOnClickListener(this);
        tv_zhanghao = (TextView) findViewById(R.id.tv_zhanghao);
        tv_nicheng = (TextView) findViewById(R.id.tv_nicheng);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_last_time = (TextView) findViewById(R.id.tv_qian_time);
        tv_zhanghao.setText(u_username);
        tv_nicheng.setText(u_name);
        tv_time.setText(u_regtime);
        tv_last_time.setText(u_lasttime);
        tv_fandian = (TextView) findViewById(R.id.tv_fandian);
        tv_shengyu = (TextView) findViewById(R.id.tv_shengyu);
        tv_zhuce = (TextView) findViewById(R.id.tv_zhuce);
        tv_all = (TextView) findViewById(R.id.tv_all);
        tv_fandian1 = (TextView) findViewById(R.id.tv_fandian1);
        tv_shengyu1 = (TextView) findViewById(R.id.tv_shengyu1);
        tv_zhuce1 = (TextView) findViewById(R.id.tv_zhuce1);
        tv_all1 = (TextView) findViewById(R.id.tv_all1);
        tv_fandian2 = (TextView) findViewById(R.id.tv_fandian2);
        tv_shengyu2 = (TextView) findViewById(R.id.tv_shengyu2);
        tv_zhuce2 = (TextView) findViewById(R.id.tv_zhuce2);
        tv_all2 = (TextView) findViewById(R.id.tv_all2);


        getBjListUrl();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_quren:
                String fandian = et_fandian.getText().toString();
                if (fandian.equals("")) {
                    showToast("请设置返点数");
                } else {
                    loadingWindow.showDialog(Constants.tjing);
                    getRebateUrl(u_id, fandian);
                }
                break;
            default:
        }
    }

    /**
     * 会员编辑网络请求
     */
    public void getRebateUrl(String uid, String fand) {
        //http://lf.client.cool/?Action=MyTeam_Edit&Uid=76175&Rebate=11
        AjaxParams params = new AjaxParams();
        params.put("Action", "MyTeam_Edit");
        params.put("Uid", uid);
        params.put("Rebate", fand);
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
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
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
                    String str = (String) msg.obj;
                    java.lang.reflect.Type type = new TypeToken<MemberBJbean>() {
                    }.getType();
                    MemberBJbean memberBJbean = gson.fromJson(str, type);
                    showToast(memberBJbean.getMsg());
                    break;
                case 1:
                    String string = (String) msg.obj;
                    String jqstr = string.substring(1, string.length() - 1);
                    String[] strlist = jqstr.split("\\}");
                    List<String> mlist = new ArrayList<>();
                    for (int i = 0; i < strlist.length; i++) {
                        if (i < 3) {
                            mlist.add(strlist[i]);
                        }
                    }
                    String stlist = "";
                    for (int i = 0; i < mlist.size(); i++) {
                        stlist += mlist.get(i);
                    }
                    String[] arr = stlist.split("\\{");
                    String stlist1 = "";
                    for (int i = 0; i < arr.length; i++) {
                        stlist1 += arr[i];
                    }
                    String[] arr1 = stlist1.split("surplus");
                    String stlist2 = "";
                    for (int i = 0; i < arr1.length; i++) {
                        stlist2 += arr1[i];
                    }
                    String[] arr2 = stlist2.split("regnum");
                    String stlist3 = "";
                    for (int i = 0; i < arr2.length; i++) {
                        stlist3 += arr2[i];
                    }
                    String[] arr3 = stlist3.split("total");
                    String stlist4 = "";
                    for (int i = 0; i < arr3.length; i++) {
                        stlist4 += arr3[i];
                    }
                    String[] arr4 = stlist4.split(",");
                    String stlist5 = "";
                    for (int i = 0; i < arr4.length; i++) {
                        stlist5 += arr4[i];
                    }
                    String[] arr5 = stlist5.split(":");
                    String stlist6 = "";
                    for (int i = 0; i < arr5.length; i++) {
                        stlist6 += arr5[i];
                    }
                    String[] arr6 = stlist6.split("\"");
                    List<String> mylist = new ArrayList<>();
                    for (int i = 0; i < arr6.length; i++) {
                        if (!arr6[i].equals("")) {
                            mylist.add(arr6[i]);
                        }
                    }
                    initObject(mylist);
                    break;
                default:
            }
        }
    };

    /**
     * 下面返点数据
     * @param mlist
     */
    private void initObject(List<String> mlist) {
        ll_all = (LinearLayout) findViewById(R.id.ll_all);
        ll_all1 = (LinearLayout) findViewById(R.id.ll_all1);
        ll_all2 = (LinearLayout) findViewById(R.id.ll_all2);
        ll_all3 = (LinearLayout) findViewById(R.id.ll_all3);
        if (mlist == null) {
            ll_all.setVisibility(View.GONE);
        } else {
            if (mlist.size() == 4) {
                tv_fandian.setText(mlist.get(0));
                tv_shengyu.setText(mlist.get(1));
                tv_zhuce.setText(mlist.get(2));
                tv_all.setText(mlist.get(3));
                ll_all2.setVisibility(View.GONE);
                ll_all3.setVisibility(View.GONE);
            }else if (mlist.size()>4 && mlist.size()<=8){
                tv_fandian.setText(mlist.get(0));
                tv_shengyu.setText(mlist.get(1));
                tv_zhuce.setText(mlist.get(2));
                tv_all.setText(mlist.get(3));
                tv_fandian1.setText(mlist.get(4));
                tv_shengyu1.setText(mlist.get(5));
                tv_zhuce1.setText(mlist.get(6));
                tv_all1.setText(mlist.get(7));
                ll_all3.setVisibility(View.GONE);
            }else if (mlist.size()>8){
                tv_fandian.setText(mlist.get(0));
                tv_shengyu.setText(mlist.get(1));
                tv_zhuce.setText(mlist.get(2));
                tv_all.setText(mlist.get(3));
                tv_fandian1.setText(mlist.get(4));
                tv_shengyu1.setText(mlist.get(5));
                tv_zhuce1.setText(mlist.get(6));
                tv_all1.setText(mlist.get(7));
                tv_fandian2.setText(mlist.get(8));
                tv_shengyu2.setText(mlist.get(9));
                tv_zhuce2.setText(mlist.get(10));
                tv_all2.setText(mlist.get(11));
            }
        }
    }

    /**
     * 下面返点的网络请求
     */
    public void getBjListUrl() {
        AjaxParams params = new AjaxParams();
        params.put("Action", "Quota_Get");
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
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }
}
