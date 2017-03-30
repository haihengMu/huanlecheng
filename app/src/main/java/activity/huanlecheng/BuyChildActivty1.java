package activity.huanlecheng;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import adapter.BuyChildListAdapter;
import adapter.MoreDownListAdapter;
import adapter.MyPagerAdaper;
import bean.CaiPiaoNewTopBean;
import bean.GetHistoryNewWinCodeBean;
import bean.GetTheIssueAndTimeBean;
import bean.HX_Game_PlayResponseModel;
import bean.NewPlayGameName;
import constants.Constants;
import constants.RUser;
import fragment.DanFragment;
import fragment.FSFragment;
import http.AjaxCallBack;
import http.AjaxParams;
import http.WiseHttp;
import util.CaipiaoDao;
import util.PagerSlidingTab;
import view.HelpHorizontalScrollView;
import view.XListView;

/**
 * 够彩大厅第一页
 * Created by Administrator on 2016/10/17.
 */

public class BuyChildActivty1 extends BaseActivity implements
        XListView.IXListViewListener {

    private String id;
    private String title;
    private List<HX_Game_PlayResponseModel> mList;//recycleview解析数据的集合
    private Button title_left_btn;
    private TextView title_textview;
    private XListView buy_child_listview;
    private LinearLayout view_loading;
    private LinearLayout view_load_fail;
    private FrameLayout fl_fragment;
    private TextView txt_neterr;
    // private List<GetHistoryWinCodeBean> myList;//开奖信息listview的数据集合
    private BuyChildListAdapter buyChildListAdapter;
    private TextView tv_overtime;
    private GetTheIssueAndTimeBean gtatb;
    private GetTheIssueAndTimeBean date;
    private MyCountDownTimer msd;
    private TextView tv_kaijiang;
    private TextView tv_fen;
    private ImageView iv_back;
    private ImageView tv_item;
    private int visibility;
    private ImageView imageView;
    private int a = 1;
    private TextView tv_dian;
    private PagerSlidingTab pst;
    private WiseHttp wh;
    private HelpHorizontalScrollView hhsv;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private MyPagerAdaper myPagerAdaper;
    private ViewPager vp_viewpager;
    private TextView iv_more;
    private String[] arr;
    private String[] arr1;
    private CaipiaoDao caipiaoDao;
    private List<CaiPiaoNewTopBean> caiPiaoNewTopBeen;
    private List<HX_Game_PlayResponseModel> mmlist;
    private List<HX_Game_PlayResponseModel> mmmlist;
    private MoreDownListAdapter moreDownListAdapter;
    private int n = 1;
    private LinearLayout ll_layout;
    private TextView tv_jiesu;
    private LinearLayout ll_layout_kj;
    private ProgressBar pb_waite;
    private RelativeLayout rl_yindao;
    private RelativeLayout rl_yindao1;
    private SharedPreferences sharedPreferences;
    private String guidejm;
    private String packageInfostr;
    private LinearLayout ll_kj;
    private LinearLayout ll_kj1;
    private List<Fragment> fragmentlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_buy_child);

        title_left_btn = (Button) findViewById(R.id.title_left_btn);
        title_textview = (TextView) findViewById(R.id.title_textview);
        view_loading = (LinearLayout) findViewById(R.id.view_loading);
        view_load_fail = (LinearLayout) findViewById(R.id.view_load_fail);
        ll_layout = (LinearLayout) findViewById(R.id.ll_layout);
        ll_kj = (LinearLayout) findViewById(R.id.ll_lisikaijiang);
        ll_kj1 = (LinearLayout) findViewById(R.id.ll_lisikaijiang1);
        ll_kj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* buy_child_listview.setVisibility(View.VISIBLE);
                ll_kj.setVisibility(View.GONE);
                ll_kj1.setVisibility(View.VISIBLE);
                Intent intent = new Intent();
                intent.setAction("action.down");
                sendBroadcast(intent);*/

            }
        });
        ll_kj1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buy_child_listview.setVisibility(View.GONE);
                ll_kj.setVisibility(View.VISIBLE);
                ll_kj1.setVisibility(View.GONE);
                Intent intent = new Intent();
                intent.setAction("action.up");
                sendBroadcast(intent);
            }
        });
        initYinDao();
        //fragment页的id
        title_left_btn.setBackgroundResource(R.drawable.aar);
        title_left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //点击事件传递过来记录点击的id和title
        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");
        title_textview.setText(title);
        mList = new ArrayList<>();
        iv_more = (TextView) findViewById(R.id.iv_more);
        iv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MoreActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("id", id);
                startActivity(intent);

            }
        });
        //   initListView();//隐藏头的listview
        // initData();//倒计时
        //  initTabHor();
        // initLoad();//等待页面
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action.topview");
        intentFilter.addAction("action.topviewdel");
        registerReceiver(mbrod, intentFilter);
        rl_yindao1 = (RelativeLayout) findViewById(R.id.rl_yindao);
        rl_yindao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl_yindao.setVisibility(View.GONE);
            }
        });
        initTabHor();
    }

    private void initYinDao() {
        rl_yindao = (RelativeLayout) findViewById(R.id.rl_yindao);
        rl_yindao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl_yindao.setVisibility(View.GONE);
            }
        });
        sharedPreferences = getSharedPreferences("guideJmian1", Activity.MODE_PRIVATE);
        guidejm = sharedPreferences.getString("guideJmian1", "");
        try {
            PackageInfo packageInfo = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
            packageInfostr = packageInfo.versionCode + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (guidejm.equals(packageInfostr)) {//相等进来过
            rl_yindao.setVisibility(View.GONE);
        } else {//不想等 没进来过
            rl_yindao.setVisibility(View.VISIBLE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("guideJmian1", "" + info.versionCode);
            editor.commit();
        }
    }

    private BroadcastReceiver mbrod = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("action.topview") || action.equals("action.topviewdel")) {
                initTabHor();
            }
        }
    };

    private void initTabHor() {
        caipiaoDao = new CaipiaoDao(getApplicationContext());
        hhsv = (HelpHorizontalScrollView) findViewById(R.id.hhsvd_tab);
        vp_viewpager = (ViewPager) findViewById(R.id.vp_viewpager);
        initObjd();
    }

    private void initObjd() {
        caiPiaoNewTopBeen = caipiaoDao.findAllnewTop();
        List<CaiPiaoNewTopBean> mlist = new ArrayList<>();
        if (caiPiaoNewTopBeen.size() <= 0) {
               getUrl(id);
        } else {
            for (int i = 0; i < caiPiaoNewTopBeen.size(); i++) {
                if (caiPiaoNewTopBeen.get(i).getTitle().equals(title)) {
                    mlist.add(caiPiaoNewTopBeen.get(i));
                }
            }
            if (mlist.size() <= 0) {
                   getUrl(id);
            } else {
                initObj(mlist);
            }
        }
    }

    private void initObj(List<CaiPiaoNewTopBean> tablist) {
        arr = new String[tablist.size()];
        for (int i = 0; i < tablist.size(); i++) {
            arr[i] = tablist.get(i).getH_g_p_name();
        }
        hhsv.setTitle(arr);
        hhsv.setTextColor(Color.parseColor("#ffffff"));
        hhsv.setTextColorResourceId(R.color.golden);
        fragmentlist = new ArrayList<>();
        for (int i = 0; i < tablist.size(); i++) {
            if (tablist.get(i).getH_g_p_name().indexOf("单式") != -1 || tablist.get(i).getH_g_p_name().indexOf("混合") != -1) {
                DanFragment danFragment = new DanFragment();
                danFragment.initDataD(tablist.get(i), title);
                fragmentlist.add(danFragment);
            } else {
                FSFragment fsFragment = new FSFragment();
                 fsFragment.initDataF(tablist.get(i), title,id,getApplication());
                fragmentlist.add(fsFragment);
            }
        }
        hhsv.setspace(50);
        myPagerAdaper = new MyPagerAdaper(this.getSupportFragmentManager(), fragmentlist, tablist, getApplication());
        vp_viewpager.setAdapter(myPagerAdaper);
        hhsv.setViewPager(vp_viewpager);
      /*  for (int i = 0; i < tablist.size(); i++) {
            Fragment1 fragment1 = new Fragment1();
            if (tablist.get(i).getH_g_p_name().indexOf("单式") == -1 || tablist.get(i).getH_g_p_name().indexOf("混合") == -1) {//复试
                // 区分玩法
                String idiea = "";
                if (tablist.get(i).getH_g_p_name().indexOf("定位胆") > -1) {
                    if (tablist.get(i).getH_g_p_name().equals("定位胆")) {
                        idiea = "定位胆";
                    } else {
                        idiea = "不定位胆";
                    }
                } else if (tablist.get(i).getH_g_p_name().equals("任三和值")) {
                    idiea = "任三和值";
                } else  if (tablist.get(i).getH_g_p_name().equals("任三和值")) {
                    idiea = "任三和值";
                } else if (tablist.get(i).getH_g_p_name().equals("任三组三")) {
                    idiea = "任三组三";
                } else if (tablist.get(i).getH_g_p_name().equals("任三组六")) {
                    idiea = "任三组六";
                } else if (tablist.get(i).getH_g_p_name().equals("任二组选")) {
                    idiea = "任二组选";
                } else if (tablist.get(i).getH_g_p_name().equals("三同号通选")) {
                    idiea = "三同号通选";
                } else if (tablist.get(i).getH_g_p_name().indexOf("组六")!=-1) {
                    idiea = "组六";
                } else if (tablist.get(i).getH_g_p_name().indexOf("组三")!=-1) {
                    idiea = "组三";
                } else if (tablist.get(i).getH_g_p_name().indexOf("和值")!=-1) {
                    idiea = "和值";
                } else if (tablist.get(i).getH_g_p_name().indexOf("不定位")!=-1) {
                    idiea = "不定位";
                } else if (tablist.get(i).getH_g_p_name().indexOf("组选")!=-1) {
                    idiea = "组选";
                } else if (tablist.get(i).getH_g_p_name().indexOf("定单双")!=-1) {
                    idiea = "定单双";
                } else if (tablist.get(i).getH_g_p_name().indexOf("猜中位")!=-1) {
                    idiea = "猜中位";
                } else if (tablist.get(i).getH_g_p_name().indexOf("任选一")!=-1) {
                    idiea = "任选一";
                } else if (tablist.get(i).getH_g_p_name().indexOf("任选二")!=-1) {
                    idiea = "任选二";
                } else if (tablist.get(i).getH_g_p_name().indexOf("任选三")!=-1) {
                    idiea = "任选三";
                } else if (tablist.get(i).getH_g_p_name().indexOf("任选四")!=-1) {
                    idiea = "任选四";
                } else if (tablist.get(i).getH_g_p_name().indexOf("任选五")!=-1) {
                    idiea = "任选五";
                } else if (tablist.get(i).getH_g_p_name().indexOf("任选六")!=-1) {
                    idiea = "任选六";
                } else if (tablist.get(i).getH_g_p_name().indexOf("任选七")!=-1) {
                    idiea = "任选七";
                } else if (tablist.get(i).getH_g_p_name().indexOf("任选八")!=-1) {
                    idiea = "任选八";
                } else if (tablist.get(i).getH_g_p_name().indexOf("第一位") !=-1&& tablist.get(i).getH_g_p_name().equals("前二直选复式")) {
                    idiea = "前二直选复式";
                } else if (tablist.get(i).getH_g_p_name().indexOf("第一位")!=-1) {
                    idiea = "第一位";
                } else  if (tablist.get(i).getH_g_p_name().indexOf("第一名")!=-1) {
                    idiea = "第一名";
                } else if (tablist.get(i).getH_g_p_name().equals("前三")) {
                    idiea = "猜前三常规录入";
                } else if (tablist.get(i).getH_g_p_name().equals("组120")){
                    idiea = "组120";
                }else  {
                    idiea = "任复试";
                }
                //复试
                fragment1.initData(tablist.get(i), idiea, getApplication(), title);
                fragmentlist.add(fragment1);
            } else {
                //单式,手写
                String idiea = null;
                fragment1.initDataD(tablist.get(i), title);
                fragmentlist.add(fragment1);
            }
        }*/
 /*       hhsv.setspace(50);
        myPagerAdaper = new MyPagerAdaper(this.getSupportFragmentManager(), fragmentlist, tablist, getApplication());
        vp_viewpager.setAdapter(myPagerAdaper);
        hhsv.setViewPager(vp_viewpager);*/

    }
    /**
     * 初始化开奖时间
     */
    private void initData() {
        tv_overtime = (TextView) findViewById(R.id.tv_overtime);
        tv_kaijiang = (TextView) findViewById(R.id.tv_kaijiang);
        tv_dian = (TextView) findViewById(R.id.tv_dian);
        tv_fen = (TextView) findViewById(R.id.tv_fen);
        tv_jiesu = (TextView) findViewById(R.id.tv_jiesu);
        ll_layout_kj = (LinearLayout) findViewById(R.id.ll_layout_kj);
        pb_waite = (ProgressBar) findViewById(R.id.pb_waite);
        gtatb = new GetTheIssueAndTimeBean();
    }

    /**
     * 初始化等待加载界面
     */
    private void initLoad() {
        view_load_fail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view_loading.setVisibility(View.VISIBLE);//显示正在加载
                view_load_fail.setVisibility(View.GONE);//无数据页面显示
                vp_viewpager.setVisibility(View.GONE);//下面的fragment页隐藏
                try {
                    getListViewURL(id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 重写onTouchEvent方法,继承了activity的onTouchEvent,直接监听屏幕的滑动
     *
     * @param event
     * @return
     */

    /**
     * 初始化标题头的listview
     */
    private void initListView() {
        buy_child_listview = (XListView) findViewById(R.id.buy_child_listview);
        //   myList = new ArrayList<>();
        buyChildListAdapter = new BuyChildListAdapter(this);
        buy_child_listview.setAdapter(buyChildListAdapter);
        try {
            getListViewURL(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            getDataURL(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        buy_child_listview.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                try {
                    getListViewURL(id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onLoadMore() {
                buy_child_listview.setPullLoadEnable(false);
            }
        });

    }

    /**
     * 标题头的listview开奖信息的网络请求
     *
     * @throws Exception
     */
    public void getListViewURL(String id) throws Exception {

        wh.configCookieStore(RUser.cookieStore);
        wh.get(Constants.getUrl() + Constants.kjjilu + id, new AjaxCallBack<String>() {
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
                buy_child_listview.setVisibility(View.GONE);
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
                    java.lang.reflect.Type type1 = new TypeToken<GetHistoryNewWinCodeBean>() {
                    }.getType();
                    Gson gson = new Gson();
                    GetHistoryNewWinCodeBean myList = gson.fromJson(string, type1);
                    if (myList.getData().equals("")) {
                        buy_child_listview.setVisibility(View.GONE);
                        Toast.makeText(BuyChildActivty1.this, "暂无数据", Toast.LENGTH_SHORT).show();
                    } else {
                        buyChildListAdapter.setDate(myList, title);
                    }
                    buy_child_listview.stopRefresh();
                    break;
                case 1:

                    String st = (String) msg.obj;
                    java.lang.reflect.Type type2 = new TypeToken<GetTheIssueAndTimeBean>() {
                    }.getType();
                    Gson gson1 = new Gson();
                    gtatb = gson1.fromJson(st, type2);
                    msd = new MyCountDownTimer(
                            Long.parseLong(gtatb.getOvertime()) * 1000, 1000);
                    msd.start();
                    int f = 0;
                    String w = "";
                    String p = null;
                    if (gtatb.getLast_Issue().indexOf("-") != -1) {
                        String[] strings = gtatb.getLast_Issue().split("-");
                        f = Integer.parseInt(strings[1]) + 1;
                        String k = f + "";
                        if (strings[1].length() - k.length() == 1) {
                            w = "0" + k;
                        } else if (strings[1].length() - k.length() == 2) {
                            w = "00" + k;
                        } else if (strings[1].length() - k.length() == 3) {
                            w = "000" + k;
                        }
                        if (w.equals("")) {
                            p = strings[0] + "-" + k;
                        } else {
                            p = strings[0] + "-" + w;
                        }
                    } else {
                        p = Integer.parseInt(gtatb.getLast_Issue()) + 1 + "";
                    }
                    tv_kaijiang.setText("距" + p + "期开奖:");
                    view_loading.setVisibility(View.GONE);//显示正在加载
                    view_load_fail.setVisibility(View.GONE);
                    vp_viewpager.setVisibility(View.VISIBLE);
                    pb_waite.setVisibility(View.GONE);
                    tv_kaijiang.setVisibility(View.VISIBLE);
                    tv_fen.setVisibility(View.VISIBLE);
                    tv_dian.setVisibility(View.VISIBLE);
                    tv_overtime.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    tv_fen.invalidate();//刷新界面
                    tv_dian.invalidate();
                    tv_overtime.invalidate();
                    break;
                case 3:
                    String str = (String) msg.obj;
                    java.lang.reflect.Type type = new TypeToken<NewPlayGameName>() {
                    }.getType();
                    Gson gson2 = new Gson();
                    NewPlayGameName newPlayGameName = gson2.fromJson(str, type);
                    if (newPlayGameName.getData().size() != 0) {
                        for (int i = 0; i < newPlayGameName.getData().size(); i++) {
                            if (newPlayGameName.getData().get(i).getH_g_p_name().equals("定位胆")) {
                                caipiaoDao.addnewtop(newPlayGameName.getData().get(i).getH_g_p_name(), newPlayGameName.getData().get(i).getH_g_p_id(), newPlayGameName.getData().get(i).getH_g_p_cid(), newPlayGameName.getData().get(i).getH_g_p_nid(),
                                        newPlayGameName.getData().get(i).getH_g_p_tid(), newPlayGameName.getData().get(i).getH_g_p_gid(), newPlayGameName.getData().get(i).getH_g_p_rid(),
                                        newPlayGameName.getData().get(i).getH_g_p_one_amount(), newPlayGameName.getData().get(i).getH_g_p_max_bet_mum(), newPlayGameName.getData().get(i).getH_g_p_bonus(),
                                        newPlayGameName.getData().get(i).getH_g_p_amount_step(), newPlayGameName.getData().get(i).getH_g_p_rebate_step(),
                                        newPlayGameName.getData().get(i).getH_g_p_decimal(), newPlayGameName.getData().get(i).getH_g_p_return_off(), newPlayGameName.getData().get(i).getH_g_p_introduction(),
                                        newPlayGameName.getData().get(i).getH_g_p_example(), newPlayGameName.getData().get(i).getH_g_p_max_imumbonus_rebate(), newPlayGameName.getData().get(i).getH_g_p_mini_mumbonus_rebate(),
                                        newPlayGameName.getData().get(i).getH_g_p_mini_bet_money(), newPlayGameName.getData().get(i).getH_g_p_max_bet_money(), newPlayGameName.getData().get(i).getH_g_p_max_bonus(),
                                        newPlayGameName.getData().get(i).getH_g_p_max_bonus_mode(), newPlayGameName.getData().get(i).getH_g_p_not_bet_code(), newPlayGameName.getData().get(i).getH_g_p_singled_num(), newPlayGameName.getData().get(i).getH_g_p_max_bonus(), title);
                            }
                        }
                    }
                    initObjd();
                    break;
                default:
            }
        }
    };


    /**
     * 距离开奖剩余时间的接口
     *
     * @param id
     * @throws Exception
     */
    public void getDataURL(String id) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("Model", "Game");
        params.put("Action", "GetTheIssueAndTime");
        params.put("Id", id);
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

    /**
     * 游戏玩法
     */
    public void getUrl(String id) {
       // wh.configCookieStore(RUser.cookieStore);
        // wh.configCookieStore(RUser.cookieStore);
        WiseHttp wh=new WiseHttp();
        wh.post(Constants.getUrl() + Constants.game_play_new_xiangqing + id + ".json", new AjaxCallBack<String>() {
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
                msg.what = 3;
                msg.obj = s;
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }


    class MyCountDownTimer extends CountDownTimer {
        /**
         * @param millisInFuture    表示以毫秒为单位 倒计时的总数
         *                          <p>
         *                          例如 millisInFuture=1000 表示1秒
         * @param countDownInterval 表示 间隔 多少微秒 调用一次 onTick 方法
         *                          <p>
         *                          例如: countDownInterval =1000 ; 表示每1000毫秒调用一次onTick()
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
//       Toast.makeText(getApplicationContext(),millisInFuture+"qwe",Toast.LENGTH_SHORT).show();
//       Toast.makeText(getApplicationContext(),countDownInterval+"qaz",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFinish() {
            //    tv_kaijiang.setVisibility(View.GONE);
            tv_fen.setVisibility(View.GONE);
            tv_dian.setVisibility(View.GONE);
            tv_overtime.setVisibility(View.GONE);
            tv_jiesu.setVisibility(View.VISIBLE);
            tv_kaijiang.setText("本期已结束，请重新进入此页面或点击刷新");
            tv_kaijiang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pb_waite.setVisibility(View.VISIBLE);
                    tv_jiesu.setVisibility(View.GONE);
                   /* initData();
                    initListView();*/
                    try {
                        getDataURL(id);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Log.i("MainActivity", millisUntilFinished + "");
            long l = (millisUntilFinished / 1000);
            if (l >= 60) {
                if (l / 60 < 10) {
                    if (l % 60 < 10) {
                        tv_fen.setText("0" + l / 60);
                        tv_overtime.setText("0" + l % 60);
                    } else {
                        tv_fen.setText("0" + l / 60);
                        tv_overtime.setText("" + l % 60);
                    }
                } else {
                    tv_fen.setText("" + l / 60);
                    tv_overtime.setText("" + l % 60);
                }
            } else {
                if (l % 60 < 10) {
                    tv_fen.setText("00");
                    tv_overtime.setText("0" + l);
                } else {
                    tv_fen.setText("00");
                    tv_overtime.setText("" + l);
                }

            }

        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
