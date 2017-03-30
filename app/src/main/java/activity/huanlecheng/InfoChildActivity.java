package activity.huanlecheng;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import adapter.InfoChildAdapter;
import bean.GetHistoryNewWinCodeBean;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import util.ShowToast;
import view.XListView;

public class InfoChildActivity extends BaseActivity implements OnClickListener,
        XListView.IXListViewListener {
    private String id, title;
    private Button title_left_btn;
    private TextView title_textview;
    private XListView lv;
    private LinearLayout load_layout;// 载入中
    private LinearLayout load_layout_fail;// 载入失败
    private TextView txt_neterr;// 载入失败
   // private List<GetHistoryWinCodeBean> mList;
    private InfoChildAdapter ina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");
        title_left_btn = (Button) findViewById(R.id.title_left_btn);
        title_left_btn.setBackgroundResource(R.drawable.aar);
        title_left_btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });
      //  mList = new ArrayList<GetHistoryWinCodeBean>();
        title_textview = (TextView) findViewById(R.id.title_textview);
        title_textview.setText(title);
        load_layout = (LinearLayout) findViewById(R.id.view_loading);
        load_layout_fail = (LinearLayout) findViewById(R.id.view_load_fail);
        ina = new InfoChildAdapter(this);
        lv = (XListView) findViewById(R.id.main_lv_list);
        lv.setXListViewListener(this);
        lv.setPullLoadEnable(false);
       // lv.setCanRefresh(false);
        lv.setAdapter(ina);
        lv.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                requestUserinfo(id);
            }

            @Override
            public void onLoadMore() {
                lv.setPullLoadEnable(false);
            }
        });
        load_layout_fail.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                load_layout.setVisibility(View.GONE);
                lv.setVisibility(View.GONE);
                load_layout_fail.setVisibility(View.VISIBLE);
                txt_neterr.setText(Constants.NETERROR + " 点击屏幕重新加载");
                requestUserinfo(id);
            }
        });
        txt_neterr = (TextView) findViewById(R.id.txt_neterr);

        requestUserinfo(id);
    }

    /**
     * 开奖信息
     */
    public void requestUserinfo(String id) {
   /*     AjaxParams params = new AjaxParams();
        params.put("Id", id);*/
        wh.configCookieStore(RUser.cookieStore);
        wh.get("http://hlc.hxgzs.net:81/lottery/get_history_win_code?id="+id, new AjaxCallBack<String>() {
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
                ShowToast.showMsg(InfoChildActivity.this, Constants.NETERROR);
                load_layout.setVisibility(View.GONE);
                lv.setVisibility(View.GONE);
                lv.stopFailRefresh();
                load_layout_fail.setVisibility(View.VISIBLE);
                txt_neterr.setText(Constants.NETERROR + " 点击屏幕重新加载");
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
                    lv.stopRefresh();
                    String str = (String) msg.obj;
                    java.lang.reflect.Type type = new TypeToken<GetHistoryNewWinCodeBean>() {
                    }.getType();
                    Gson gson = new Gson();
                    GetHistoryNewWinCodeBean mList = gson.fromJson(str, type);
                    if (mList.getData().equals("")) {
                        load_layout.setVisibility(View.GONE);
                        lv.setVisibility(View.GONE);
                        load_layout_fail.setVisibility(View.VISIBLE);
                        txt_neterr.setText("点击屏幕重新加载");
                    } else {
                        ina.setData(mList,title);
                        lv.setVisibility(View.VISIBLE);
                        load_layout.setVisibility(View.GONE);
                        load_layout_fail.setVisibility(View.GONE);
                    }

                    break;
                case 1:

                    break;

                default:
                    break;
            }
        }
    };

    @Override
    public void onRefresh() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onLoadMore() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }
}
