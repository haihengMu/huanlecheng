package activity.huanlecheng;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import adapter.BuyChildAdapter;
import adapter.MoreDownListAdapter;
import adapter.UpLieAdapter;
import bean.CaiPiaoNewTopBean;
import bean.NewPlayGameName;
import bean.NewPlayGameNameChild;
import bean.PlayGameNewChildBean;
import bean.PlayGameNewType;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import util.CaipiaoDao;
import util.ShowToast;

/**
 * Created by Administrator on 2016/11/12.
 */

public class MoreActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Button title_left_btn;
    private TextView title_textview;
    private NewPlayGameName hp;
    private List<NewPlayGameNameChild> mList;
    private String id;
    private BuyChildAdapter ina;
    private GridView gv_up;
    private CaipiaoDao caipiaoDao;
    private String title;
    private LinearLayout ll_lay;
    private ListView lv_down;
    private MoreDownListAdapter moreDownListAdapter;
    private PlayGameNewType playGameType;
    private List<PlayGameNewChildBean> playGameTypeList;
    private LinearLayout view_loading;//正在加载
    private LinearLayout view_load_fail;//加载失败
    private TextView txt_neterr;
    private ImageView iv_imageview;
    private RelativeLayout rl_yindao;
    private SharedPreferences sharedPreferences;
    private String guidejm;
    private String packageInfostr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        initHeadView();
        caipiaoDao = new CaipiaoDao(getApplication());
        gv_up = (GridView) findViewById(R.id.gv_up);
        title = getIntent().getStringExtra("title");
        id = getIntent().getStringExtra("id");
        initUp();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action.topview");
        registerReceiver(mre, intentFilter);
        initView();
    }

    private BroadcastReceiver mre = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("action.topview")) {
                initUp();
            }
        }
    };

    private void initUp() {
        List<CaiPiaoNewTopBean> liePlaynames = caipiaoDao.findAllnewTop();
        List<CaiPiaoNewTopBean> mlist = new ArrayList<>();
        UpLieAdapter lieAdapter = new UpLieAdapter(getApplicationContext());
        gv_up.setAdapter(lieAdapter);
        if (liePlaynames.size() < 0) {
        } else {
            for (int i = 0; i < liePlaynames.size(); i++) {
                if (title.equals(liePlaynames.get(i).getTitle())) {
                    mlist.add(liePlaynames.get(i));
                }
            }
            lieAdapter.setData(mlist);
        }
    }

    private void initHeadView() {
        title_left_btn = (Button) findViewById(R.id.title_left_btn);
        title_left_btn.setBackgroundResource(R.drawable.aar);
        title_left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title_textview = (TextView) findViewById(R.id.title_textview);
        title_textview.setText("更多玩法");
        view_loading = (LinearLayout)findViewById(R.id.view_loading);
        view_load_fail = (LinearLayout)findViewById(R.id.view_load_fail);
        txt_neterr = (TextView)findViewById(R.id.txt_neterr);
        lv_down = (ListView) findViewById(R.id.lv_down);
        iv_imageview = (ImageView) findViewById(R.id.iv_imageview);
        ll_lay= (LinearLayout) findViewById(R.id.ll_lay);
        initYinDao();

        view_load_fail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                view_loading.setVisibility(View.GONE);
                ll_lay.setVisibility(View.GONE);
                iv_imageview.setVisibility(View.GONE);
                lv_down.setVisibility(View.GONE);
                view_load_fail.setVisibility(View.VISIBLE);
                txt_neterr.setText(Constants.NETERROR + " 点击屏幕重新加载");
                try {
                    requestInfoFen();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void initYinDao() {
        rl_yindao = (RelativeLayout) findViewById(R.id.rl_yindao);
        rl_yindao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl_yindao.setVisibility(View.GONE);
            }
        });
        sharedPreferences = getSharedPreferences("guideJmian2", Activity.MODE_PRIVATE);
        guidejm = sharedPreferences.getString("guideJmian2","");
        try {
            PackageInfo packageInfo=this.getPackageManager().getPackageInfo(this.getPackageName(),0);
            packageInfostr = packageInfo.versionCode+"";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (guidejm.equals(packageInfostr)){//相等进来过
            rl_yindao.setVisibility(View.GONE);
        }else {//不想等 没进来过
            rl_yindao.setVisibility(View.VISIBLE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("guideJmian2", "" + info.versionCode);
            editor.commit();
        }
    }

    private void initView() {
        hp = new NewPlayGameName();
        mList = new ArrayList<NewPlayGameNameChild>();
        playGameType = new PlayGameNewType();
        playGameTypeList = new ArrayList<>();
        moreDownListAdapter = new MoreDownListAdapter(this);
        lv_down.setAdapter(moreDownListAdapter);
        try {
            requestInfoFen();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 游戏分类
     */
    public void requestInfoFen() throws Exception {
        wh.configCookieStore(RUser.cookieStore);
        wh.post(Constants.getUrl()+Constants.game, new AjaxCallBack<String>() {
            @Override
            public AjaxCallBack<String> progress(boolean progress, int rate) {
                return super.progress(progress, rate);
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
                ShowToast.showMsg(MoreActivity.this, Constants.NETERROR);
                view_loading.setVisibility(View.GONE);
                ll_lay.setVisibility(View.GONE);
                iv_imageview.setVisibility(View.GONE);
                lv_down.setVisibility(View.GONE);
                view_load_fail.setVisibility(View.VISIBLE);
                txt_neterr.setText(Constants.NETERROR + " 点击屏幕重新加载");
                txt_neterr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            requestInfoFen();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    /**
     * 游戏名称
     *
     * @throws Exception
     */
    public void requestInfo(final String id) throws Exception {
        wh.configCookieStore(RUser.cookieStore);
        wh.post(Constants.getUrl()+Constants.game_play_new_xiangqing+id+".json", new AjaxCallBack<String>() {
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
                msg.obj = s;
                msg.what = 0;
                handler.sendMessage(msg);
            }

            //加载失败回调此方法
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                ShowToast.showMsg(MoreActivity.this, Constants.NETERROR);
                ShowToast.showMsg(MoreActivity.this, Constants.NETERROR);
                view_loading.setVisibility(View.GONE);
                ll_lay.setVisibility(View.GONE);
                iv_imageview.setVisibility(View.GONE);
                lv_down.setVisibility(View.GONE);
                view_load_fail.setVisibility(View.VISIBLE);
                txt_neterr.setText(Constants.NETERROR + " 点击屏幕重新加载");
                txt_neterr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            requestInfo(id);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
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
                    String str = (String) msg.obj;
                    java.lang.reflect.Type type = new TypeToken<NewPlayGameName>() {
                    }.getType();
                    Gson gson = new Gson();
                    hp = gson.fromJson(str, type);
                    if (!hp.getError().equals("")) {
                        view_loading.setVisibility(View.GONE);
                        ll_lay.setVisibility(View.GONE);
                        iv_imageview.setVisibility(View.GONE);
                        lv_down.setVisibility(View.GONE);
                        view_load_fail.setVisibility(View.VISIBLE);
                        txt_neterr.setText("  点击屏幕重新加载");
                    } else {
                        for (int i = 0; i < hp.getData().size(); i++) {
                            if (hp.getData().get(i).getH_g_p_nid()
                                    .equals(id)) {
                                mList.add(hp.getData().get(i));
                                System.out.println("有相同的!!" + i);
                            }
                        }

                        moreDownListAdapter.setData(mList, title);
                        ll_lay.setVisibility(View.VISIBLE);
                        iv_imageview.setVisibility(View.VISIBLE);
                        lv_down.setVisibility(View.VISIBLE);
                        view_load_fail.setVisibility(View.GONE);
                        view_loading.setVisibility(View.GONE);
                    }
                    break;
                case 1:
                    String string = (String) msg.obj;
                    java.lang.reflect.Type type1 = new TypeToken<PlayGameNewType>() {
                    }.getType();
                    Gson gson1 = new Gson();
                    playGameType = gson1.fromJson(string, type1);
                    if (playGameType.getData().size() <= 0) {
                    } else {
                        for (int i = 0; i < playGameType.getData().size(); i++) {
                            if (playGameType.getData().get(i).getH_g_t_nid().equals(id)&&!playGameType.getData().get(i).getH_g_t_off().equals("0")) {
                                playGameTypeList.add(playGameType.getData().get(i));
                            }
                        }
                        try {
                            requestInfo(id);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        moreDownListAdapter.setDate(playGameTypeList);

                    }
                    break;
                default:
            }
        }
    };

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
