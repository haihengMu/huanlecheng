package activity.huanlecheng;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapter.CustomAdapter;
import adapter.UpAdapter;
import bean.CustomBean;
import bean.HXGameNameBean;
import bean.NameBean;
import constants.Constants;
import http.AjaxCallBack;
import util.CaipiaoDao;
import util.ShowToast;

/**
 * 自定义彩种的activity
 * Created by Administrator on 2016/10/14.
 */

public class CustomActivity extends BaseActivity {

    private static final String TAG = "CustomActivity";
    private ArrayList<HXGameNameBean> hxList;
    private CustomAdapter customAdapter;
    private GridView viewBlew;
    private ImageView iv_back;
    private GridView cgv_up;
    private CaipiaoDao caipiaoDao;
    private UpAdapter upAdapter;
    private LinearLayout view_loading;
    private LinearLayout view_load_fail;
    private TextView txt_neterr;
    private CustomBean custom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        caipiaoDao = new CaipiaoDao(getApplicationContext());
        cgv_up = (GridView) findViewById(R.id.cgv_up);
        view_loading = (LinearLayout) findViewById(R.id.view_loading);//正在加载
        view_load_fail = (LinearLayout) findViewById(R.id.view_load_fail);
        txt_neterr = (TextView) findViewById(R.id.txt_neterr);
        initView();
        view_load_fail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                view_loading.setVisibility(View.VISIBLE);
                view_load_fail.setVisibility(View.GONE);
                initView();//初始化自定义彩种
                viewBlew.setVisibility(View.GONE);
                cgv_up.setVisibility(View.GONE);
            }
        });
        initUp();//初始化上面布局的自定义
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action.gradview");
        registerReceiver(mRefreshBroadcastReceiver, intentFilter);
    }

    private void initUp() {
        List<NameBean> nameBeanList = caipiaoDao.findAllname();
        upAdapter = new UpAdapter(getApplicationContext());
        cgv_up.setAdapter(upAdapter);
        if (nameBeanList.size() < 0) {

        } else {
            upAdapter.setData(nameBeanList);
        }

    }

    private BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("action.gradview")) {
                initUp();
            }
        }
    };

    /**
     * 初始化自定义彩种
     */
    private void initView() {
        hxList = new ArrayList<>();
        viewBlew = (GridView) findViewById(R.id.cgv_change);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        customAdapter = new CustomAdapter(getApplicationContext());
        viewBlew.setAdapter(customAdapter);
        getCustomURL();
    }

    public void getCustomURL() {
    /*    AjaxParams params = new AjaxParams();
        params.put("Model", "System");
        params.put("Action", "GetSystemConfig");
        params.put("models", "HX_Game_Name");*/
        wh.post(Constants.getUrl() + Constants.game_play, new AjaxCallBack<String>() {
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
                ShowToast.showMsg(getApplicationContext(), Constants.NETERROR);
                view_loading.setVisibility(View.GONE);
                view_load_fail.setVisibility(View.VISIBLE);
                viewBlew.setVisibility(View.GONE);
                cgv_up.setVisibility(View.GONE);
                txt_neterr.setText(Constants.NETERROR + " 点击屏幕重新加载");
            }
        });
    }


    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    JSONObject jsonObject = new JSONObject();
                    java.lang.reflect.Type type = new TypeToken<CustomBean>() {
                    }.getType();
                    String str = (String) msg.obj;
                    Log.e(TAG, str);
                    Gson gson = new Gson();
                    CustomBean customBean = gson.fromJson(str, type);
                    if (customBean.getData().equals("")) {
                        Toast.makeText(getApplicationContext(), "错了", Toast.LENGTH_SHORT).show();
                    } else {
                        custom = gson.fromJson(str, type);
                        for (int i = 0; i < customBean.getData().size(); i++) {
                            if (!custom.getData().get(i).getH_g_n_off().equals("0")) {//1显示,0隐藏
                                HXGameNameBean hnb = new HXGameNameBean();
                                hnb.setH_g_n_id(custom.getData().get(i).getH_g_n_id());
                                hnb.setH_g_n_off(custom.getData().get(i).getH_g_n_off());
                                hnb.setH_g_n_title(custom.getData().get(i).getH_g_n_title());
                                hxList.add(hnb);
                            }
                            customAdapter.setData(hxList);

                        }
                        view_load_fail.setVisibility(View.GONE);
                        view_loading.setVisibility(View.GONE);
                        viewBlew.setVisibility(View.VISIBLE);
                        cgv_up.setVisibility(View.VISIBLE);
                    }
                    break;
                default:
            }
        }
    };


}
