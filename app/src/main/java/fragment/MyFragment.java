package fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import activity.huanlecheng.KefuActivity;
import activity.huanlecheng.MemberActivity;
import activity.huanlecheng.MemberNowActivity;
import activity.huanlecheng.OpenActivity;
import activity.huanlecheng.OrderMainActivity;
import activity.huanlecheng.Person_Set;
import activity.huanlecheng.R;
import activity.huanlecheng.RechargeActivity;
import activity.huanlecheng.Ti_money_activity;
import activity.huanlecheng.TuanduiMoneyActivity;
import bean.TiXianNameBean;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import http.AjaxParams;


public class MyFragment extends BaseFragment implements OnClickListener {
    private View showview;
    private ImageView iv_shezhi;
    private RelativeLayout setLayout, touzhuLayout, zhuihaoLayout,
            zhanghuLayout, zhanneiLayout, countszLayout, kscz, sdtx,
            openlayout;

    private TextView tv_kc, tv_sx, moneyTextView;
    private Button title_left_btn;
    private TextView title_textview;
    private ImageView iv_head;
    private TextView tv_username;
    private RelativeLayout kaihu_center;
    private RelativeLayout kaihu;
    private TextView tv_dingdan;
    private TextView tv_daili;
    private ImageView iv_jian_left;
    private ImageView iv_jian_right;
    private LinearLayout ll_left;
    private LinearLayout ll_right;
    private RelativeLayout huiyuan_guanli;
    private RelativeLayout zaixian_huiyuan;
    private RelativeLayout tuandui_qukuan;
    private ImageView iv_kefu;
    private ImageView iv_yindao;
    private LinearLayout ll_chuantou;
    private RelativeLayout rl_yindao;
    private SharedPreferences sharedPreferences;
    private String guidejm;
    private String packageInfostr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        showview = inflater.inflate(R.layout.myaccount, container, false);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action.center");
        getActivity().registerReceiver(mRefreshBroadcastReceiver, intentFilter);
        tv_dingdan = (TextView) showview.findViewById(R.id.tv_dingdan);//订单报表
        tv_daili = (TextView) showview.findViewById(R.id.tv_daili);//代理管理
        iv_jian_left = (ImageView) showview.findViewById(R.id.iv_jian_left);
        iv_jian_right = (ImageView) showview.findViewById(R.id.iv_jian_right);
        ll_left = (LinearLayout) showview.findViewById(R.id.ll_left);
        ll_right = (LinearLayout) showview.findViewById(R.id.ll_right);
        iv_kefu = (ImageView) showview.findViewById(R.id.iv_kefu);
        rl_yindao = (RelativeLayout) showview.findViewById(R.id.rl_yindao);
        initView();
        iv_kefu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), KefuActivity.class);
                startActivity(intent);
            }
        });
        tv_dingdan.setOnClickListener(this);
        tv_daili.setOnClickListener(this);
        huiyuan_guanli = (RelativeLayout) showview.findViewById(R.id.huiyuan_guanli);
        huiyuan_guanli.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingWindow.showDialog(Constants.jiazaizhong);
                getUrlId();

            }
        });
        zaixian_huiyuan = (RelativeLayout) showview.findViewById(R.id.zaixian_huiyuan);
        zaixian_huiyuan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MemberNowActivity.class);
                startActivity(intent);

            }
        });
        tuandui_qukuan = (RelativeLayout) showview.findViewById(R.id.tuandui_qukuan);
        tuandui_qukuan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), TuanduiMoneyActivity.class);
                startActivity(intent);

            }
        });
        title_left_btn = (Button) showview.findViewById(R.id.title_left_btn);
        title_left_btn.setVisibility(View.GONE);
        /*title_textview = (TextView) showview.findViewById(R.id.title_textview);
        title_textview.setText("我的账户");*/
        iv_shezhi = (ImageView) showview.findViewById(R.id.iv_set);
        //开户中心
        kaihu_center = (RelativeLayout) showview.findViewById(R.id.kaihu_center);
        kaihu_center.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getActivity(), OpenActivity.class);
                startActivity(intent);
            }
        });
        setLayout = (RelativeLayout) showview.findViewById(R.id.shezhi);
        touzhuLayout = (RelativeLayout) showview
                .findViewById(R.id.touzhulayout);
        zhuihaoLayout = (RelativeLayout) showview
                .findViewById(R.id.zhuihaolayout);
        zhanghuLayout = (RelativeLayout) showview
                .findViewById(R.id.zhanghulayout);
        kscz = (RelativeLayout) showview.findViewById(R.id.kscz);
        sdtx = (RelativeLayout) showview.findViewById(R.id.sdtx);
        touzhuLayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getActivity(),
                        OrderMainActivity.class);
                intent.putExtra("change_id", "0");// 投注记录
                startActivity(intent);
            }
        });
        zhuihaoLayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getActivity(),
                        OrderMainActivity.class);
                intent.putExtra("change_id", "1");// 追号记录
                startActivity(intent);
            }
        });
        zhanghuLayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getActivity(),
                        OrderMainActivity.class);
                intent.putExtra("change_id", "2");// 账户明细
                startActivity(intent);
            }
        });
        tv_kc = (TextView) showview.findViewById(R.id.tv_kuaichong);
        tv_kc.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RechargeActivity.class);
                intent.putExtra("M_money", userInfo.getU_Money());
                startActivity(intent);
            }
        });
        tv_sx = (TextView) showview.findViewById(R.id.tv_shanti);
        tv_sx.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Ti_money_activity.class);
                intent.putExtra("M_money", userInfo.getU_Money());
                startActivity(intent);
            }
        });
        tv_username = (TextView) showview.findViewById(R.id.tv_username);
        moneyTextView = (TextView) showview.findViewById(R.id.tv_money);
        iv_shezhi.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getActivity(), Person_Set.class);
                startActivity(intent);
            }
        });

        iv_head = (ImageView) showview.findViewById(R.id.iv_head);
        il.displayImage(Constants.base_url + userInfo.getU_Head(), iv_head);
        tv_username.setText(userInfo.getU_UserName());
        moneyTextView.setText(userInfo.getU_Money());
        IntentFilter inf = new IntentFilter();
        inf.addAction("action.success");
        getActivity().registerReceiver(bmdr, inf);
        return showview;
    }
    private void initView() {
        rl_yindao.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                rl_yindao.setVisibility(View.GONE);
            }
        });
        sharedPreferences = getActivity().getSharedPreferences("guideJmian3", Activity.MODE_PRIVATE);
        guidejm = sharedPreferences.getString("guideJmian3","");
        try {
            PackageInfo packageInfo=getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(),0);
            packageInfostr = packageInfo.versionCode+"";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (guidejm.equals(packageInfostr)){//相等进来过
            rl_yindao.setVisibility(View.GONE);
        }else {//不想等 没进来过
            rl_yindao.setVisibility(View.VISIBLE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("guideJmian3", "" + info.versionCode);
            editor.commit();
        }
    }

    BroadcastReceiver bmdr = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("action.success")) {
                getUrlId();
            }
        }
    };

    public DisplayImageOptions getListOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(false) // 设置图片不缓存于内存中
                .cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565) // 设置图片的质量
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT) // 设置图片的缩放类型，该方
                .build();
        return options;
    }

    // broadcast receiver
    private BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("action.center")) {
                moneyTextView.setText(userInfo.getU_Money());

            }
        }
    };

    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mRefreshBroadcastReceiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_dingdan:
                ll_left.setVisibility(View.VISIBLE);
                iv_jian_left.setVisibility(View.VISIBLE);
                iv_jian_right.setVisibility(View.GONE);
                ll_right.setVisibility(View.GONE);
                break;
            case R.id.tv_daili:
                ll_left.setVisibility(View.GONE);
                iv_jian_left.setVisibility(View.GONE);
                iv_jian_right.setVisibility(View.VISIBLE);
                ll_right.setVisibility(View.VISIBLE);
                break;
            default:
        }
    }

    public void getUrlId() {
        AjaxParams params = new AjaxParams();
        params.put("Model", "User");
        params.put("Action", "GetUserInfo");
        params.put("r", "no");
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
                loadingWindow.cancel();
                showToast("无法连接,请检查你的网络");
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
                    java.lang.reflect.Type type2 = new TypeToken<TiXianNameBean>() {
                    }.getType();
                    Gson gson1 = new Gson();
                    TiXianNameBean tiXianNameBean = gson1.fromJson(str, type2);
                    moneyTextView.setText(tiXianNameBean.getUserinfo().getU_Money());
                    String u_id = tiXianNameBean.getUserinfo().getU_Id();
                    Intent intent = new Intent(getActivity(), MemberActivity.class);
                    intent.putExtra("a", u_id);
                    startActivity(intent);
                    break;
                default:
            }
        }
    };
}
