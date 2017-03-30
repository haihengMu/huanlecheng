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
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.ArrayList;
import java.util.List;

import activity.huanlecheng.BuyChildActivty1;
import activity.huanlecheng.CustomActivity;
import activity.huanlecheng.NoticeActivity;
import activity.huanlecheng.R;
import adapter.MainGridAdapter;
import bean.CustomBean;
import bean.HXGameNameBean;
import bean.HX_Game_NameResponseModel;
import bean.HxGame;
import bean.NameBean;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import util.CaipiaoDao;
import util.Utils;
import view.ClassGridView;

public class HomeFragment extends BaseFragment {
    private View parentView;
    private Button title_left_btn;
    private TextView title_textview;
    private TextView title_right_txt;
    private LinearLayout load_layout;// 载入中
    private LinearLayout load_layout_fail;// 载入失败
    private TextView txt_neterr;// 载入失败

    private ViewPager vpPager;// 广告
    private RadioGroup groupPoint;
    private static int size = 5;
    private AutoPlayHandler autoPlayHandler = new AutoPlayHandler();
    private List<String> imgurl;
    String s[] = {
            "http://static.public.ms/pw/Template/Skin_1/images/banner/banner-2.jpg",
            "http://static.public.ms/pw/Template/Skin_1/images/banner/banner-3.jpg",
            "http://static.public.ms/pw/Template/Skin_1/images/banner/banner-4.jpg",
            "http://static.public.ms/pw/Template/Skin_1/images/banner/banner-5.jpg",
            "http://static.public.ms/pw/Template/Skin_1/images/banner/banner-6.jpg"};

    private HxGame hGame;
    private HxGame put_hGame;
    private List<HX_Game_NameResponseModel> put_hgn;
    private ClassGridView gridView;
    private MainGridAdapter mga;

    private LinearLayout kscz;
    private LinearLayout sdtx;
    private LinearLayout tzjl;
    private ImageView custom;
    private String title;
    private RelativeLayout rl_yindao;
    private String pkinfocode;
    private SharedPreferences sharedPreferences;
    private String packageInfostr;
    private String guidejm;
    private List<HXGameNameBean> mlist;
    private CaipiaoDao caipiaoDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
//		title = getActivity().getIntent().getStringExtra("title");
        parentView = inflater.inflate(R.layout.fragment_home, container, false);
        title_left_btn = (Button) parentView.findViewById(R.id.title_left_btn);
        title_left_btn.setBackgroundResource(R.drawable.benefit);
        title_left_btn.setVisibility(View.VISIBLE);
        title_left_btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
             /*   Intent intent = new Intent(getActivity(), BenefitActivity.class);
                startActivity(intent);*/
                showToast("正在维护");
            }
        });
        title_textview = (TextView) parentView
                .findViewById(R.id.title_textview);
        title_textview.setText("欢乐城");
        title_right_txt = (TextView) parentView
                .findViewById(R.id.title_right_txt);
        title_right_txt.setBackgroundResource(R.drawable.notice);
        title_right_txt.setVisibility(View.VISIBLE);
        title_right_txt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getActivity(), NoticeActivity.class);
                startActivity(intent);

            }
        });
        //自定义彩种id
        custom = (ImageView) parentView.findViewById(R.id.custom);
        custom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CustomActivity.class));
            }
        });
        load_layout = (LinearLayout) parentView.findViewById(R.id.view_loading);
        load_layout_fail = (LinearLayout) parentView
                .findViewById(R.id.view_load_fail);
        put_hGame = new HxGame();
        put_hgn = new ArrayList<HX_Game_NameResponseModel>();

        vpPager = (ViewPager) parentView.findViewById(R.id.viewpager_ad);
        groupPoint = (RadioGroup) parentView.findViewById(R.id.guide_ad);
        vpPager.setOnPageChangeListener(new MyPageChangeListener());
        imgurl = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            imgurl.add(s[i]);
        }
        addPointView(size);
        vpPager.setAdapter(new MyAdapter());
        autoPlay();
        gridView = (ClassGridView) parentView.findViewById(R.id.gridview);
        mlist = new ArrayList<>();
        initTianjia();
        initView();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action.gradview");
        intentFilter.addAction("action.upgridview");
        getActivity().registerReceiver(mre, intentFilter);

        //requestUserinfo();
        return parentView;
    }

    /**
     * 界面引导
     */
    private void initView() {
        rl_yindao = (RelativeLayout) parentView.findViewById(R.id.rl_yindao);
        rl_yindao.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                rl_yindao.setVisibility(View.GONE);
            }
        });
        sharedPreferences = getActivity().getSharedPreferences("guideJmian", Activity.MODE_PRIVATE);
        guidejm = sharedPreferences.getString("guideJmian","");
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
            editor.putString("guideJmian", "" + info.versionCode);
            editor.commit();
        }
    }

    private BroadcastReceiver mre = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("action.gradview") || action.equals("action.upgridview")) {
                initTianjia();
            }
        }
    };

    private void initTianjia() {
        caipiaoDao = new CaipiaoDao(getActivity());
        List<NameBean> nameBeanList = caipiaoDao.findAllname();
        mga = new MainGridAdapter(getActivity());
        gridView.setAdapter(mga);
        if (nameBeanList.size() == 0) {
            requestUserinfo();
        } else {
            mga.setData(nameBeanList);
        }
    }

    /**
     * 当ViewPager中页面的状态发生改变时调用
     *
     * @author Administrator
     */
    private class MyPageChangeListener implements OnPageChangeListener {
        private int oldPosition = 0;

        /**
         * This method will be invoked when a new page becomes selected.
         * position: Position index of the new selected page.
         */
        public void onPageSelected(int position) {

            oldPosition = position;
            View child = groupPoint.getChildAt(position % 5);
            if (child instanceof RadioButton) {
                RadioButton radBtn = (RadioButton) child;
                radBtn.setChecked(true);
            }
            autoPlayHandler.sendEmptyMessage(2);
        }

        public void onPageScrollStateChanged(int arg0) {
            autoPlayHandler.sendEmptyMessage(2);
            // System.out.println("onPageScrollStateChanged");
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
            autoPlayHandler.sendEmptyMessage(2);
            // System.out.println("onPageScrolled");
        }
    }

    private void addPointView(int count) {
        groupPoint.removeAllViews();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                RadioButton radBtn = new RadioButton(getActivity());
                radBtn.setClickable(false);
                int scale = Utils.convertDipOrPx(getActivity(), 16);
                RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(
                        scale, scale);
                radBtn.setButtonDrawable(R.drawable.page_control_sel);
                int pading = Utils.convertDipOrPx(getActivity(), 5);
                params.setMargins(pading, 0, 0, 0);
                groupPoint.addView(radBtn, params);
            }
        }
        View v = groupPoint.getChildAt(0);
        RadioButton radioBtn = (RadioButton) v;
        radioBtn.setChecked(true);
    }

    private void autoPlay() {
        Message message = autoPlayHandler.obtainMessage(1);
        autoPlayHandler.sendMessageDelayed(message, 4000);// 延迟5秒发送消息
    }

    // 自动播放句柄
    class AutoPlayHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    removeMessages(2);
                    if (size != 0) {
                        vpPager.setCurrentItem((vpPager.getCurrentItem() + 1)
                                % size);// 换页，同时实现了循环播放
                    }
                    sendEmptyMessageDelayed(1, 4000L);
                    break;

                default:
                    break;
            }

        }
    }

    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return size;
        }

        @Override
        public Object instantiateItem(View arg0, final int arg1) {
            View convertView = LayoutInflater.from(getActivity()).inflate(
                    R.layout.avdert_item, null);
            ImageView iv = (ImageView) convertView.findViewById(R.id.image);
            // final imgurl vo = ad_list.getAd_list().get(arg1);

            il.displayImage(imgurl.get(arg1), iv, getListOptions());
            ((ViewPager) arg0).addView(convertView);
            iv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (arg1%size==4){//菲律宾1.5
                        Intent intent = new Intent(getActivity(), BuyChildActivty1.class);
                        intent.putExtra("id","20");
                        intent.putExtra("title", "菲律宾1.5分彩");
                        RUser.id = 20+"";
                        getActivity().startActivity(intent);
                    }else if (arg1%size==3){//韩国1.5   14
                        Intent intent = new Intent(getActivity(), BuyChildActivty1.class);
                        intent.putExtra("id","14");
                        intent.putExtra("title", "韩国1.5分彩");
                        RUser.id = 14+"";
                        getActivity().startActivity(intent);
                    }else {
                        return;
                    }
                }
            });
            return convertView;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

        @Override
        public void finishUpdate(View arg0) {

        }
    }

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
            /*String action = intent.getAction();
            if (action.equals("action.main")) {
				yueTextView.setText("余额￥ " + userInfo.getU_Money());

			}*/
        }
    };

    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mRefreshBroadcastReceiver);
    }
    /**
     * 请求网络数据
     */
    private void requestUserinfo() {
        wh.configCookieStore(RUser.cookieStore);
        wh.get(Constants.getUrl()+Constants.game_play, new AjaxCallBack<String>() {
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
                    String str = (String) msg.obj;
                    java.lang.reflect.Type type = new TypeToken<CustomBean>() {
                    }.getType();
                    CustomBean customBean = gson.fromJson(str, type);
                    for (int i = 0; i < customBean.getData().size(); i++) {
                        if (!customBean.getData().get(i).getH_g_n_off().equals("0")) {//1显示,0隐藏
                            HXGameNameBean hnb = new HXGameNameBean();
                            hnb.setH_g_n_id(customBean.getData().get(i).getH_g_n_id());
                            hnb.setH_g_n_off(customBean.getData().get(i).getH_g_n_off());
                            hnb.setH_g_n_title(customBean.getData().get(i).getH_g_n_title());
                            mlist.add(hnb);
                        }
                        //   mga.setData(mlist);
                        // customAdapter.setData(hxList);20   14   1  8   12  13
                    }
                    if (mlist.size()!=0){
                        for (int i=0;i<mlist.size();i++){
                            if (mlist.get(i).getH_g_n_id().equals("20")||mlist.get(i).getH_g_n_id().equals("14")||mlist.get(i).getH_g_n_id().equals("1")||
                                    mlist.get(i).getH_g_n_id().equals("8")||mlist.get(i).getH_g_n_id().equals("12")||mlist.get(i).getH_g_n_id().equals("13")){
                                caipiaoDao.addname(mlist.get(i).getH_g_n_title(), mlist.get(i).getH_g_n_id());
                            }
                        }
                        initTianjia();
                        mlist.clear();
                    }
                    break;
                default:
            }
        }
    };
}
