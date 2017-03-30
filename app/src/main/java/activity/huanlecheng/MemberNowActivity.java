package activity.huanlecheng;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapter.MemberNowAdapter;
import bean.MemberNowBean;
import bean.MsgNowBean;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import http.AjaxParams;
import util.JsonUtil;

import static android.R.attr.type;

/**
 * Created by Administrator on 2016/12/13.
 */

public class MemberNowActivity extends BaseActivity {

    private static final String TAG = "MemberNowActivity";
    private Button title_left_btn;
    private TextView title_textview;
    private ListView mlistview;
    private MemberNowAdapter memberNowAdapter;
    private List<MemberNowBean.DataBean.ListBean> mylist;
    private LinearLayout view_loading;
    private LinearLayout view_load_fail;
    private TextView txt_neterr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_now);
        initHead();
        initView();

    }

    private void initHead() {
        title_left_btn = (Button) findViewById(R.id.title_left_btn);
        title_textview = (TextView) findViewById(R.id.title_textview);
        title_textview.setText("在线会员");
        title_left_btn.setBackgroundResource(R.drawable.aar);
        title_left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        view_loading = (LinearLayout) findViewById(R.id.view_loading);
        view_load_fail = (LinearLayout) findViewById(R.id.view_load_fail);
        txt_neterr = (TextView) findViewById(R.id.txt_neterr);

        mlistview = (ListView) findViewById(R.id.mylistview);
        mylist = new ArrayList<>();
        memberNowAdapter = new MemberNowAdapter(getApplicationContext());
        mlistview.setAdapter(memberNowAdapter);
        getUrl();
    }

    public void getUrl() {
        AjaxParams params = new AjaxParams();
        wh.configCookieStore(RUser.cookieStore);
        wh.post(Constants.getUrl() + Constants.teamOnlineUsers, params, new AjaxCallBack<String>() {
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
             /*   view_loading = (LinearLayout) findViewById(R.id.view_loading);
                view_load_fail = (LinearLayout) findViewById(R.id.view_load_fail);
                txt_neterr = (TextView) findViewById(R.id.txt_neterr);*/
                view_load_fail.setVisibility(View.VISIBLE);
                view_loading.setVisibility(View.GONE);
                txt_neterr.setText(Constants.NETERROR + "点击屏幕加载重试");
                txt_neterr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadingWindow.showDialog(Constants.jiazaizhong);
                        getUrl();
                    }
                });

            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //{"data":{"total_pages":0,"current_page":0,"count":"0","list":[]}}
            switch (msg.what) {
                case 0:
                    loadingWindow.cancel();
                    String string = (String) msg.obj;
                    Log.i(TAG, string);
                    MemberNowBean memberNowBean = JsonUtil.parseJsonToBean(string, MemberNowBean.class);

                    if (!"0".equals(memberNowBean.getData().getCount())) {
//                        for (int i = 0; i < memberNowBean.getMsg().size(); i++) {
//                            MsgNowBean msgNowBean = new MsgNowBean();
//                            msgNowBean.setU_Money(memberNowBean.getMsg().get(i).getU_Money());
//                            msgNowBean.setU_TheLoginTime(memberNowBean.getMsg().get(i).getU_TheLoginTime());
//                            msgNowBean.setU_UpAgent(memberNowBean.getMsg().get(i).getU_UpAgent());
//                            msgNowBean.setU_UserName(memberNowBean.getMsg().get(i).getU_UserName());
//                            mylist.add(msgNowBean);
//                        }
                        mylist.addAll(memberNowBean.getData().getList());
                        memberNowAdapter.setData(mylist);
                    } else {
                        showToast("暂无会员");
                    }
                    view_loading.setVisibility(View.GONE);
                    view_load_fail.setVisibility(View.GONE);
                    break;
                default:
            }
        }
    };
}
