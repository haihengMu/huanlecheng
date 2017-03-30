package activity.huanlecheng;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import adapter.MemberZAdapter;
import bean.Zhuanzhangbean;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;

/**
 * Created by Administrator on 2016/12/9.
 */

public class ActivityZhuan extends BaseActivity implements View.OnClickListener {

    private Button title_left_btn;
    private TextView title_textview;
    private String name;
    private TextView tv_name;
    private EditText et_money;
    private RelativeLayout rl_changes;
    private TextView tv_state;
    private EditText et_mima;
    private LinearLayout ll_sure;
    private PopupWindow pw;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        name = getIntent().getStringExtra("name");
        initHead();
        initView();
    }

    /**
     * 标题初始化
     */
    private void initHead() {
        title_left_btn = (Button) findViewById(R.id.title_left_btn);
        title_textview = (TextView) findViewById(R.id.title_textview);
        title_left_btn.setBackgroundResource(R.drawable.aar);
        title_textview.setText("下级转账");
        title_left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 初始化
     */
    private void initView() {
        tv_name = (TextView) findViewById(R.id.tv_name);
        et_money = (EditText) findViewById(R.id.et_money);
        rl_changes = (RelativeLayout) findViewById(R.id.rl_changes);
        tv_state = (TextView) findViewById(R.id.tv_state);
        et_mima = (EditText) findViewById(R.id.et_password);
        ll_sure = (LinearLayout) findViewById(R.id.ll_make_sure);
        ll_sure.setOnClickListener(this);
        tv_name.setText(name);
        list = new ArrayList<>();
        list.add("活动");
        list.add("转账");
        list.add("其他");
        showPopupWindow();
    }

    public ListView popupWindowListview() {
        ListView popupwindowlist = new ListView(getApplicationContext());
        popupwindowlist.setBackgroundColor(Color.rgb(63, 65, 78));
        popupwindowlist.setVerticalScrollBarEnabled(false);
        popupwindowlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv_state.setText(list.get(position));
                pw.dismiss();
            }
        });
        return popupwindowlist;
    }

    public void showPopupWindow() {
        final ListView listView = popupWindowListview();
        MemberZAdapter memberAdapter = new MemberZAdapter(getApplicationContext());
        listView.setAdapter(memberAdapter);
        memberAdapter.setData(list);
        rl_changes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw = new PopupWindow(listView, v.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
                pw.setOutsideTouchable(true);
                pw.setBackgroundDrawable(new BitmapDrawable(String.valueOf(R.drawable.back)));
                pw.showAsDropDown(v, -2, 5);
            }
        });

    }

    public void getUrl(String u_name, String u_moeny, String u_state, String u_pass) {
        String str = "U_UserName="+u_name+"&U_Money="+u_moeny+"&U_Type="+u_state+"&U_UserPass="+u_pass;
        String string = URLEncoder.encode(str);
        wh.configCookieStore(RUser.cookieStore);
        wh.get(Constants.getUrl() + "?Action=MyTeam_Transfer&Data=" + string, new AjaxCallBack<String>() {
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
                    String string = (String) msg.obj;
                    java.lang.reflect.Type type=new TypeToken<Zhuanzhangbean>(){}.getType();
                    Gson gson=new Gson();
                    Zhuanzhangbean zhuanzhangbean=gson.fromJson(string,type);
                    showToast(zhuanzhangbean.getMsg());
                    Intent intent=new Intent();
                    intent.setAction("action.success");
                    sendBroadcast(intent);
                    break;
                default:
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_make_sure:
                String money = et_money.getText().toString();
                String state = tv_state.getText().toString();
                String password = et_mima.getText().toString();
                if (money.equals("")) {
                    showToast("请输入转账金额");
                } else if (state.equals("请选择交易类型")) {
                    showToast("请选择交易类型");
                } else if (password.equals("")) {
                    showToast("请输入取款密码");
                } else {
                    if (state.equals("活动")){
                        getUrl(name,money,"6",password);
                    }else if (state.equals("其他")){
                        getUrl(name,money,"10",password);
                    }else if (state.equals("转账")){
                        getUrl(name,money,"9",password);
                    }
                }
                break;
            default:
        }
    }
}
