package fragment;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import WanFa.ErXing;
import WanFa.FivePlay;
import WanFa.QerZX;
import WanFa.QianSi;
import WanFa.Sanxing;
import WanFa.SiXing;
import WanFa.SiZhuhe;
import WanFa.WXZhe;
import WanFa.ZuLiu;
import WanFa.ZuSanDT;
import WanFa.ZuXuanS;
import WanFa.ZuXuanY;
import WanFa.ZuXuanwusi;
import WanFa.ZuxuanLiu;
import activity.huanlecheng.BuyCar;
import activity.huanlecheng.R;
import adapter.NewPlayAdapter;
import bean.CaiPiaoNewTopBean;
import bean.CaipiaoBean;
import bean.DQIssuseBean;
import bean.FirstEvent;
import bean.NewPlayGameName;
import bean.NewPlayGameNameChild;
import bean.NewPlayGameNameChildMode;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import http.AjaxParams;
import util.CaipiaoDao;
import util.JsonUtil;
import util.ShowToast;
import util.Utils;
import view.XListView;

/**
 * Created by Administrator on 2017/3/16.
 */

public class FSFragment extends BaseFragment implements View.OnClickListener, XListView.IXListViewListener, AdapterView.OnItemClickListener {
    private static final String TAG = "FSFragment";
    private CaiPiaoNewTopBean caiPiaoNewTopBean;
    private String title;
    private View view;
    private LinearLayout jx_check_lin;
    private CheckBox jx_wan;
    private CheckBox jx_qian;
    private CheckBox jx_bai;
    private CheckBox jx_shi;
    private CheckBox jx_ge;
    private ImageButton jia;
    private ImageButton jian;
    private RadioButton jx_ib_yuan;
    private RadioButton jx_ib_jiao;
    private RadioButton jx_ib_fen;
    private RadioButton jx_ib_li;
    private TextView one_zhu;
    private TextView one_money;
    private ImageView jx_change;
    private ImageView del;
    private ImageView compound_btn;
    private ImageView buy_car;
    private TextView remark;
    private XListView main_lv_list;
    private String count = "";//当前总奖金
    private double amString;// 基数 乘法算总价
    private double cc;// 接口本金
    private String s11 = "";// 试试
    private int z = 1;//注数
    private TextView jx_today_money;
    private EditText code_txt;
    private SeekBar seekBar;
    private TextView zero;
    private TextView seventen;
    private TextView conner;
    private int b = 0;// 滑动监听
    private String pid;

    private List<NewPlayGameNameChildMode> mlist;
    private NewPlayAdapter newPlayAdapter;
    private String name;
    private Context context;
    private String pid1;
    private char[] chars;
    private char[] chars1;
    private int num;
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private char[] charss;
    private char[] chars2;
    private char[] chars3;
    private char[] chars4;
    private char[] chars5;
    private FivePlay fivePlay = new FivePlay();
    private QianSi qianSi = new QianSi();
    private Sanxing sanxing = new Sanxing();
    private ErXing erXing = new ErXing();
    private WXZhe wxZhe = new WXZhe();
    private SiZhuhe siZhuhe = new SiZhuhe();
    private ZuXuanwusi zuXuanwusi = new ZuXuanwusi();
    private ZuxuanLiu zuxuanLiu = new ZuxuanLiu();
    private ZuXuanY zuXuanY = new ZuXuanY();
    private SiXing siXing = new SiXing();
    private ZuXuanS zuXuanS = new ZuXuanS();
    private QerZX qerZX = new QerZX();
    private ZuSanDT zuSanDT = new ZuSanDT();
    private ZuLiu zuLiu = new ZuLiu();
    private long dtime;
    private String url = "";
    private CaipiaoDao caipiaoDao;
    private List<CaipiaoBean> caipiaoBeanList;
    private EventBus eventBus;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fshi, container, false);
        //   initEvent();
        caipiaoDao = new CaipiaoDao(getActivity());
        initView();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action.compound");
        intentFilter.addAction("action.big");
        context.registerReceiver(mRefreshBroadcastReceiver, intentFilter);
        eventBus = EventBus.getDefault();
        boolean registered = eventBus.isRegistered(this);
        if (!registered) {
            eventBus.register(this);
        }
        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        eventBus.unregister(this);
    }

    @Subscribe
    public void onEventMainThread(FirstEvent event) {
        String msg = "FSFragment收到了消息：" + event.toString();
        Log.e("FSFragment", msg);
    }

    public void initDataF(CaiPiaoNewTopBean caiPiaoNewTopBean, String title, String id, Application application) {
        this.caiPiaoNewTopBean = caiPiaoNewTopBean;
        this.title = title;
        this.pid = id;
        this.context = application;
    }

    private void initView() {
        mlist = new ArrayList<>();
        jx_check_lin = (LinearLayout) view.findViewById(R.id.jx_check_lin);
        jx_wan = (CheckBox) view.findViewById(R.id.jx_wan);
        jx_qian = (CheckBox) view.findViewById(R.id.jx_qian);
        jx_bai = (CheckBox) view.findViewById(R.id.jx_bai);
        jx_shi = (CheckBox) view.findViewById(R.id.jx_shi);
        jx_ge = (CheckBox) view.findViewById(R.id.jx_ge);
        jia = (ImageButton) view.findViewById(R.id.jx_jia);
        jia.setOnClickListener(this);
        jian = (ImageButton) view.findViewById(R.id.jx_jian);
        jian.setOnClickListener(this);
        jx_ib_yuan = (RadioButton) view.findViewById(R.id.jx_ib_yuan);
        jx_ib_jiao = (RadioButton) view.findViewById(R.id.jx_ib_jiao);
        jx_ib_fen = (RadioButton) view.findViewById(R.id.jx_ib_fen);
        jx_ib_li = (RadioButton) view.findViewById(R.id.jx_ib_li);
        jx_ib_yuan.setOnClickListener(this);
        jx_ib_jiao.setOnClickListener(this);
        jx_ib_fen.setOnClickListener(this);
        jx_ib_li.setOnClickListener(this);
        /**
         * 原投注页的一些id
         */
        one_zhu = (TextView) view.findViewById(R.id.one_zhu);
        one_money = (TextView) view.findViewById(R.id.one_money);
        jx_change = (ImageView) view.findViewById(R.id.jx_change);
        jx_change.setOnClickListener(this);
        del = (ImageView) view.findViewById(R.id.del);
        del.setOnClickListener(this);
        compound_btn = (ImageView) view.findViewById(R.id.compound_btn);
        compound_btn.setOnClickListener(this);
        buy_car = (ImageView) view.findViewById(R.id.buy_car);
        buy_car.setOnClickListener(this);
        remark = (TextView) view.findViewById(R.id.jx_remark);
        String name = caiPiaoNewTopBean.getH_g_p_name();
        if (name.equals("任三和值") || name.equals("任三组三")
                || name.equals("任三组六") || name.equals("任二组选")) {
            jx_check_lin.setVisibility(View.VISIBLE);
        } else {
            jx_check_lin.setVisibility(View.GONE);
        }
        main_lv_list = (XListView) view.findViewById(R.id.main_lv_list);
        main_lv_list.setXListViewListener(this);
        main_lv_list.setPullLoadEnable(false);
        main_lv_list.setCanRefresh(false);
        main_lv_list.setOnItemClickListener(this);
        getFSUrl(pid);
        getIssuesUrl(caiPiaoNewTopBean.getH_g_p_nid());
        newPlayAdapter = new NewPlayAdapter(getActivity());
        main_lv_list.setAdapter(newPlayAdapter);
        initDData();
    }

    private BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("action.compound")) {
                String posString = intent.getStringExtra("position");//选择的第几个
                String tf = intent.getStringExtra("tf");//0false 1true
                String it = intent.getStringExtra("it");//出现在什么位
                String gpid = intent.getStringExtra("gpid");
                if (gpid.equals(caiPiaoNewTopBean.getH_g_p_id())) {
                    if (name.equals("组选20") || name.equals("前四组选12") || name.equals("后四组选12")) {
                        if (it.equals("0")) {
                            chars = posString.toCharArray();
                        } else {
                            chars1 = posString.toCharArray();
                        }
                        if (chars != null && chars1 != null) {
                            List<String> mllist = new ArrayList<>();
                            for (int i = 0; i < chars1.length; i++) {
                                for (int j = i + 1; j < chars1.length; j++) {
                                    mllist.add(chars1[i] + "" + chars1[j]);
                                }
                            }
                            int a = 0;
                            for (int p = 0; p < chars.length; p++) {
                                for (int k = 0; k < mllist.size(); k++) {
                                    if (mllist.get(k).indexOf(chars[p]) == -1) {
                                        a++;
                                    }
                                }
                            }
                            String urls = "";
                            String urlss = "";
                            for (int i = 0; i < chars.length; i++) {
                                if (i == chars.length - 1) {
                                    urls += chars[i] + "";
                                } else {
                                    urls = urls + chars[i] + ",";
                                }
                            }
                            for (int i = 0; i < chars1.length; i++) {
                                if (i == chars1.length - 1) {
                                    urlss += chars1[i] + "";
                                } else {
                                    urlss = urlss + chars1[i] + ",";
                                }
                            }
                            url = urls + "|" + urlss;
                            one_zhu.setText(a + "");
                            initFDian();
                        } else {
                            one_zhu.setText(0 + "");
                            initFDian();
                        }
                    } else if (name.equals("组选30")) {
                        if (it.equals("0")) {
                            chars = posString.toCharArray();
                        } else {
                            chars1 = posString.toCharArray();
                        }
                        if (chars != null && chars1 != null) {
                            List<String> mllist = new ArrayList<>();
                            for (int i = 0; i < chars.length; i++) {
                                for (int j = i + 1; j < chars.length; j++) {
                                    mllist.add(chars[i] + "" + chars[j]);
                                }
                            }
                            int a = 0;
                            for (int p = 0; p < chars1.length; p++) {
                                for (int k = 0; k < mllist.size(); k++) {
                                    if (mllist.get(k).indexOf(chars1[p]) == -1) {
                                        a++;
                                    }
                                }
                            }
                            String urls = "";
                            String urlss = "";
                            for (int i = 0; i < chars1.length; i++) {
                                if (i == chars1.length - 1) {
                                    urls += chars1[i] + "";
                                } else {
                                    urls = urls + chars1[i] + ",";
                                }
                            }
                            for (int i = 0; i < chars.length; i++) {
                                if (i == chars.length - 1) {
                                    urlss += chars[i] + "";
                                } else {
                                    urlss = urlss + chars[i] + ",";
                                }
                            }
                            url = urlss + "|" + urls;
                            one_zhu.setText(a + "");
                            initFDian();
                        } else {
                            one_zhu.setText(0 + "");
                            initFDian();
                        }
                    } else if (name.equals("定位胆")) {
                        if (it.equals("0")) {
                            num = posString.length();
                            charss = posString.toCharArray();
                        } else if (it.equals("1")) {
                            num1 = posString.length();
                            chars2 = posString.toCharArray();
                        } else if (it.equals("2")) {
                            num2 = posString.length();
                            chars3 = posString.toCharArray();
                        } else if (it.equals("3")) {
                            num3 = posString.length();
                            chars4 = posString.toCharArray();
                        } else if (it.equals("4")) {
                            num4 = posString.length();
                            chars5 = posString.toCharArray();
                        }
                        int numz = num + num1 + num2 + num3 + num4;
                        if (numz != 0) {
                            String urls = "";
                            String urlss = "";
                            String urlss1 = "";
                            String urlss2 = "";
                            String urlss3 = "";
                            if (charss != null) {
                                for (int i = 0; i < charss.length; i++) {
                                    if (i == charss.length - 1) {
                                        urls += charss[i] + "";
                                    } else {
                                        urls = urls + charss[i] + ",";
                                    }
                                }
                            }
                            if (chars2 != null) {
                                for (int i = 0; i < chars2.length; i++) {
                                    if (i == chars2.length - 1) {
                                        urlss += chars2[i] + "";
                                    } else {
                                        urlss = urlss + chars2[i] + ",";
                                    }
                                }
                            }
                            if (chars3 != null) {
                                for (int i = 0; i < chars3.length; i++) {
                                    if (i == chars3.length - 1) {
                                        urlss1 += chars3[i] + "";
                                    } else {
                                        urlss1 = urlss1 + chars3[i] + ",";
                                    }
                                }
                            }
                            if (chars4 != null) {
                                for (int i = 0; i < chars4.length; i++) {
                                    if (i == chars4.length - 1) {
                                        urlss2 += chars4[i] + "";
                                    } else {
                                        urlss2 = urlss2 + chars4[i] + ",";
                                    }
                                }
                            }
                            if (chars5 != null) {
                                for (int i = 0; i < chars5.length; i++) {
                                    if (i == chars5.length - 1) {
                                        urlss3 += chars5[i] + "";
                                    } else {
                                        urlss3 = urlss3 + chars5[i] + ",";
                                    }
                                }
                            }
                            url = urls + "|" + urlss + "|" + urlss1 + "|" + urlss2 + "|" + urlss3;
                            one_zhu.setText(numz + "");
                            initFDian();
                        } else {
                            one_zhu.setText(0 + "");
                            initFDian();
                        }
                    } else if (name.equals("五星复式")) {
                        one_zhu.setText(fivePlay.FiveGame(it, posString));
                        url = fivePlay.FiveUrl(it, posString);
                        initFDian();
                    } else if (name.equals("前四复式") || name.equals("后四复式")) {
                        one_zhu.setText(qianSi.FiveGame(it, posString));
                        url = qianSi.FiveUrl(it, posString);
                        initFDian();
                    } else if (name.equals("前三复式") || name.equals("中三复式") || name.equals("后三复式")) {
                        one_zhu.setText(sanxing.FiveGame(it, posString));
                        url = sanxing.FiveUrl(it, posString);
                        initFDian();
                    } else if (name.equals("前二复式直选") || name.equals("后二复式直选")) {
                        one_zhu.setText(erXing.FiveGame(it, posString));
                        url = erXing.FiveUrl(it, posString);
                        initFDian();
                    } else if (name.equals("五星组合")) {
                        one_zhu.setText(wxZhe.FiveGame(it, posString));
                        url = wxZhe.FiveUrl(it, posString);
                        initFDian();
                    } else if (name.equals("前四组合") || name.equals("后四组合")) {
                        one_zhu.setText(siZhuhe.FiveGame(it, posString));
                        url = siZhuhe.FiveUrl(it, posString);
                        initFDian();
                    } else if (name.equals("组选5") || name.equals("组选10") || name.equals("前四组选4") || name.equals("后四组选4")) {
                        one_zhu.setText(zuXuanwusi.FiveGame(it, posString));
                        url = zuXuanwusi.FiveUrl(it, posString);
                        initFDian();
                    } else if (name.equals("组选60")) {
                        one_zhu.setText(zuxuanLiu.zuliu(it, posString));
                        url = zuxuanLiu.purl(it, posString);
                        initFDian();
                    } else if (name.equals("组选120")) {
                        one_zhu.setText(zuXuanY.FiveGame(posString));
                        url = zuXuanY.purl(posString);
                        initFDian();
                    } else if (name.equals("前四组选24") || name.equals("后四组选24")) {
                        one_zhu.setText(siXing.FiveGame(posString));
                        url = siXing.purl(posString);
                        initFDian();
                    } else if (name.equals("前四组选6") || name.equals("后四组选6") || name.equals("前二复式组选") || name.equals("后二复式组选")) {
                        one_zhu.setText(zuXuanS.zuliu(posString));
                        url = zuXuanS.purl(posString);
                        initFDian();
                    } else if (name.equals("前三组三") || name.equals("中三组三") || name.equals("后三组三")) {
                        one_zhu.setText(qerZX.FiveGame(posString));
                        url = qerZX.FiveUrl(posString);
                        initFDian();
                    } else if (name.equals("前三组三胆拖") || name.equals("中三组三胆拖") || name.equals("后三组三胆拖")) {
                        one_zhu.setText(zuSanDT.FiveGame(it, posString));
                        url = zuSanDT.FiveUrl(it, posString);
                        initFDian();
                    } else if (name.equals("前三组六") || name.equals("中三组六") || name.equals("后三组六")) {
                        one_zhu.setText(zuLiu.zuliu(posString));
                        url = zuLiu.purl(posString);
                        initFDian();
                        // }else if (){

                    }
                }
            } else if (action.equals("action.big")) {
                String posString = intent.getStringExtra("position");
                String gpid = intent.getStringExtra("gpid");
                String positionnumber = intent.getStringExtra("positionnumber");
                if (gpid.equals(caiPiaoNewTopBean.getH_g_p_id())) {
                    if (intent.getStringExtra("b").equals("大")) {
                        String danumber = positionnumber.substring(positionnumber.length() / 2);
                        Intent stintent = new Intent();
                        stintent.setAction("action.compound");
                        stintent.putExtra("position", danumber);
                        stintent.putExtra("it", posString);
                        stintent.putExtra("tf", "1");// 0false 1true
                        stintent.putExtra("gpid", gpid);
                        context.sendBroadcast(stintent);
                    } else if (intent.getStringExtra("b").equals("小")) {
                        String danumber = positionnumber.substring(0, positionnumber.length() / 2);
                        Intent stintent = new Intent();
                        stintent.setAction("action.compound");
                        stintent.putExtra("position", danumber);
                        stintent.putExtra("it", posString);
                        stintent.putExtra("tf", "1");// 0false 1true
                        stintent.putExtra("gpid", gpid);
                        context.sendBroadcast(stintent);
                    } else if (intent.getStringExtra("b").equals("全")) {
                        Intent stintent = new Intent();
                        stintent.setAction("action.compound");
                        stintent.putExtra("position", positionnumber);
                        stintent.putExtra("it", posString);
                        stintent.putExtra("tf", "1");// 0false 1true
                        stintent.putExtra("gpid", gpid);
                        context.sendBroadcast(stintent);
                    } else if (intent.getStringExtra("b").equals("奇")) {
                        Intent stintent = new Intent();
                        char[] chars = positionnumber.toCharArray();
                        List<String> lllist = new ArrayList<>();
                        for (int i = 0; i < chars.length; i++) {
                            lllist.add(chars[i] + "");
                        }
                        String newnumber = "";
                        for (int i = 0; i < lllist.size(); i++) {
                            if (Integer.parseInt(lllist.get(i)) % 2 != 0) {
                                newnumber += lllist.get(i);
                            }
                        }
                        stintent.setAction("action.compound");
                        stintent.putExtra("position", newnumber);
                        stintent.putExtra("it", posString);
                        stintent.putExtra("tf", "1");// 0false 1true
                        stintent.putExtra("gpid", gpid);
                        context.sendBroadcast(stintent);
                    } else if (intent.getStringExtra("b").equals("偶")) {
                        char[] chars = positionnumber.toCharArray();
                        List<String> lllist = new ArrayList<>();
                        for (int i = 0; i < chars.length; i++) {
                            lllist.add(chars[i] + "");
                        }
                        String newnumber = "";
                        for (int i = 0; i < lllist.size(); i++) {
                            if (Integer.parseInt(lllist.get(i)) % 2 == 0) {
                                newnumber += lllist.get(i);
                            }
                        }
                        Intent stintent = new Intent();
                        stintent.setAction("action.compound");
                        stintent.putExtra("position", newnumber);
                        stintent.putExtra("it", posString);
                        stintent.putExtra("tf", "1");// 0false 1true
                        stintent.putExtra("gpid", gpid);
                        context.sendBroadcast(stintent);
                    } else if (intent.getStringExtra("b").equals("清")) {
                        Intent stintent = new Intent();
                        stintent.setAction("action.compound");
                        stintent.putExtra("position", "");
                        stintent.putExtra("it", posString);
                        stintent.putExtra("tf", "1");// 0false 1true
                        stintent.putExtra("gpid", gpid);
                        context.sendBroadcast(stintent);
                    }
                }
            }
        }
    };

    private void getIssuesUrl(String h_g_p_nid) {
        AjaxParams params = new AjaxParams();
        params.put("id", h_g_p_nid);
        wh.configCookieStore(RUser.cookieStore);
        wh.post(Constants.getUrl() + Constants.qihao, params, new AjaxCallBack<String>() {
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

    private DQIssuseBean dqIssuseBean;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    String str = (String) msg.obj;
                    java.lang.reflect.Type type = new TypeToken<DQIssuseBean>() {
                    }.getType();
                    dqIssuseBean = gson.fromJson(str, type);
                    if (!dqIssuseBean.getData().getError().equals("")) {
                        main_lv_list.setVisibility(View.GONE);
                    } else {

                    }
                    break;
                case 1:
                    String str1 = (String) msg.obj;
                    NewPlayGameName newPlayGameName = JsonUtil.parseJsonToBean(str1, NewPlayGameName.class);
                    if (newPlayGameName.getError().equals("")) {
                        List<NewPlayGameNameChild> data = newPlayGameName.getData();
                        List<NewPlayGameNameChildMode.ListBean> h_g_p_configList = null;
                        for (int i = 0; i < data.size(); i++) {
                            NewPlayGameNameChild newPlayGameNameChild = data.get(i);
                            if (caiPiaoNewTopBean.getH_g_p_id().equals(newPlayGameNameChild.getH_g_p_id())) {
                                name = caiPiaoNewTopBean.getH_g_p_name();
                                pid1 = newPlayGameNameChild.getH_g_p_id();
                                Log.e(TAG, name);
                                NewPlayGameNameChildMode h_g_p_config = newPlayGameNameChild.getH_g_p_config();
                                h_g_p_configList = h_g_p_config.getList();
                            }
                        }
                        newPlayAdapter.setDate(h_g_p_configList, name, pid1);
                    }
                    setListViewHeightBasedOnChildren(main_lv_list, context);
                    break;
            }
        }
    };

    // 算listview高度
    public static void setListViewHeightBasedOnChildren(ListView listView,
                                                        Context mcContext) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    /**
     * 进页计算奖金
     */
    private void initDData() {
        jx_today_money = (TextView) view.findViewById(R.id.jx_today_money);
        /**
         * 分隔bonus
         */
        String bonus[] = caiPiaoNewTopBean.getH_g_p_bonus().split(",");
        for (int i = 0; i < bonus.length; i++) {
            for (int j = i + 1; j < bonus.length; j++) {
                if ((float) Double.parseDouble(bonus[i]) < (float) Double
                        .parseDouble(bonus[j])) {
                    String dtemp = bonus[i];
                    bonus[i] = bonus[i + 1];
                    bonus[i + 1] = dtemp;
                }
            }
        }
        /**
         * 分隔AmountStep
         */
        String dgpString[] = caiPiaoNewTopBean.getH_g_p_amount_step().split(",");
        for (int i = 0; i < dgpString.length - 1; i++) {
            for (int j = i + 1; j < dgpString.length; j++) {
                if (Double.parseDouble(dgpString[i].split("=")[0]) < Double
                        .parseDouble(dgpString[j].split("=")[0])) {
                    String dtemp = dgpString[i];
                    dgpString[i] = dgpString[i + 1];
                    dgpString[i + 1] = dtemp;
                }
            }
        }
        // 如果有=需要分隔
        if (dgpString[0].indexOf("=") > -1) {
            dgpString[0] = dgpString[0].split("=")[0];
        }
        System.out.println("dmout" + dgpString[0]);
        if (userInfo.getCorner().equals("元")) {
            count = bonus[0];
            cc = Double.parseDouble(bonus[0]);
            amString = (float) Double.parseDouble(dgpString[0]);
            DecimalFormat df = new DecimalFormat("0.0000");// 格式化小数
        } else if (userInfo.getCorner().equals("角")) {
            DecimalFormat df = new DecimalFormat("0.0000");// 格式化小数
            String s = df.format((float) Double.parseDouble(bonus[0]) / 10);// 返回的是String类型
            count = s;
            cc = Double.parseDouble(bonus[0]) / 10;
            amString = (float) Double.parseDouble(dgpString[0]) / 10;
        } else if (userInfo.getCorner().equals("分")) {
            DecimalFormat df = new DecimalFormat("0.0000");// 格式化小数
            String s = df.format((float) Double.parseDouble(bonus[0]) / 100);// 返回的是String类型
            count = s;
            cc = Double.parseDouble(bonus[0]) / 100;
            amString = (float) Double.parseDouble(dgpString[0]) / 100;
        } else {
            DecimalFormat df = new DecimalFormat("0.000000");// 格式化小数
            String s = df.format((float) Double.parseDouble(bonus[0]) / 1000);// 返回的是String类型
            count = s;
            cc = Double.parseDouble(bonus[0]) / 1000;
            amString = Double.parseDouble(dgpString[0]) / 1000;
        }
        // 初始化当前奖金
        double num = (float) Double.parseDouble(userInfo.getU_RebateB() + "");
        DecimalFormat df11 = new DecimalFormat("#0.0000");// 格式化小数
        double du = Utils.sub(Double.parseDouble(userInfo.getU_RebateA()), num);
        double dp = Utils.mul(du, amString);
        double db = Utils.add(dp, cc) * z;
        s11 = df11.format((float) db);// 返回的是String类型
        count = s11;
        jx_today_money.setText(s11);
        code_txt = (EditText) view.findViewById(R.id.jx_code_txt);
        code_txt.setText(z + "");
        code_txt.addTextChangedListener(mTextWatcher);
        seekBar = (SeekBar) view.findViewById(R.id.jx_seekbar);
        zero = (TextView) view.findViewById(R.id.jx_zero);
        seventen = (TextView) view.findViewById(R.id.jx_seventen);
        float inum = (float) Double.parseDouble(userInfo.getU_RebateA())
                - (float) Double.parseDouble(userInfo.getU_RebateB());
        DecimalFormat df1 = new DecimalFormat("0");// 格式化小数
        String s1 = df1.format(1700 + inum * 10 * 2);// 返回的是String类型
        seventen.setText(s1);
        conner = (TextView) view.findViewById(R.id.conner);
        conner.setText(userInfo.getCorner());
        if (userInfo.getCorner().equals("元")) {
            jx_ib_yuan.setBackgroundResource(R.drawable.yuanzhong);
            jx_ib_jiao.setBackgroundResource(R.drawable.jiano);
            jx_ib_fen.setBackgroundResource(R.drawable.fenno);
            jx_ib_li.setBackgroundResource(R.drawable.lino);
        } else if (userInfo.getCorner().equals("角")) {
            jx_ib_yuan.setBackgroundResource(R.drawable.yuanno);
            jx_ib_jiao.setBackgroundResource(R.drawable.jiazhong);
            jx_ib_fen.setBackgroundResource(R.drawable.fenno);
            jx_ib_li.setBackgroundResource(R.drawable.lino);
        } else if (userInfo.getCorner().equals("分")) {
            jx_ib_yuan.setBackgroundResource(R.drawable.yuanno);
            jx_ib_jiao.setBackgroundResource(R.drawable.jiano);
            jx_ib_fen.setBackgroundResource(R.drawable.fenzhong);
            jx_ib_li.setBackgroundResource(R.drawable.lino);
        } else if (userInfo.getCorner().equals("厘")) {
            jx_ib_yuan.setBackgroundResource(R.drawable.yuanno);
            jx_ib_jiao.setBackgroundResource(R.drawable.jiano);
            jx_ib_fen.setBackgroundResource(R.drawable.fenno);
            jx_ib_li.setBackgroundResource(R.drawable.lizhong);
        }
        seekBar.setMax((int) (Double.parseDouble(userInfo.getU_RebateA()) * 10));

        seekBar.setOnSeekBarChangeListener(change);
        seekBar.setProgress((int) (Double.parseDouble(userInfo.getU_RebateA())
                * 10) - (int) (Double.parseDouble(userInfo.getU_RebateB()) * 10));
        zero.setText(userInfo.getU_RebateB());
        setEditTextSelection(code_txt);
    }

    private void setEditTextSelection(EditText et) {
        CharSequence text = code_txt.getText();
        if (text instanceof Spannable) {
            Spannable spanText = (Spannable) text;
            Selection.setSelection(spanText, text.length());
        }
    }

    // 滑动监听
    private SeekBar.OnSeekBarChangeListener change = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // 当进度条停止修改的时候触发
            b = 1;
            // count = s11;
            userInfo.setU_RebateB(zero.getText().toString());
            System.out.println("停止滑动");
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            b = 1;
            System.out.println("开始滑动");
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            if (b == 0) {
                /**
                 * 第一次进入算当前奖金
                 */
                double num = (float) Double
                        .parseDouble(userInfo.getU_RebateB());
                DecimalFormat df11 = new DecimalFormat("#0.0000");// 格式化小数
                double du = Utils.sub(
                        Double.parseDouble(userInfo.getU_RebateA()), num);
                double dp = Utils.mul(du, amString);
                double db = Utils.add(dp, cc) * z;
                s11 = df11.format((float) db);// 返回的是String类型
                count = s11;
                jx_today_money.setText(s11);
                b = 2;
            }
            if (b == 1) {
                float num = (float) Double.parseDouble(userInfo.getU_RebateA())
                        - (float) progress / 10;
                float inum = (float) Double
                        .parseDouble(userInfo.getU_RebateA()) - num;
                DecimalFormat df = new DecimalFormat("0.00");// 格式化小数
                String s = df.format(num);// 返回的是String类型
                zero.setText(s);
                DecimalFormat df1 = new DecimalFormat("0");// 格式化小数
                String s1 = df1.format(1700 + inum * 10 * 2);// 返回的是String类型
                seventen.setText(s1);
                DecimalFormat df11 = new DecimalFormat("0.0000");// 格式化小数
                float gp;
                if (z == 1) {
                    double du = Utils.sub(
                            Double.parseDouble(userInfo.getU_RebateA()), num);
                    double dp = Utils.mul(du, amString);
                    double db = Utils.add(dp, cc);
                    gp = (float) db;
                } else {
                    double du = Utils.sub(
                            Double.parseDouble(userInfo.getU_RebateA()), num);
                    double dp = Utils.mul(du, amString);
                    double db = Utils.add(dp, cc) * z;
                    gp = (float) db;
                }
                s11 = df11.format(gp);// 返回的是String类型
                count = s11;
                jx_today_money.setText(s11);
            }

        }
    };
    private TextWatcher mTextWatcher = new TextWatcher() {

        public void afterTextChanged(Editable s) {
            if (!"".equals(s.toString())) {// 输入框不为空
                if (!one_zhu.getText().toString().equals("0")) {
                    if (!s.toString().equals(String.valueOf(z))) {
                        DecimalFormat df = new DecimalFormat("#0.####");// 格式化小数
                        int tz = Integer.valueOf(s.toString());
                        String s1 = df.format((float) Double.parseDouble(count) / z
                                * tz);// 返回的是String类型
                        jx_today_money.setText(s1);
                        count = s1;
                        z = tz;
                    }
                    DecimalFormat dff = new DecimalFormat("0.0000");
                    if (userInfo.getCorner().equals("元")) {
                        one_money.setText(dff.format(Double.parseDouble(code_txt.getText().toString()) * Double.parseDouble(one_zhu.getText().toString())));
                    } else if (userInfo.getCorner().equals("角")) {
                        one_money.setText(dff.format(Double.parseDouble(code_txt.getText().toString()) * Double.parseDouble(one_zhu.getText().toString()) / 10));
                    } else if (userInfo.getCorner().equals("分")) {
                        one_money.setText(dff.format(Double.parseDouble(code_txt.getText().toString()) * Double.parseDouble(one_zhu.getText().toString()) / 100));
                    } else if (userInfo.getCorner().equals("厘")) {
                        one_money.setText(dff.format(Double.parseDouble(code_txt.getText().toString()) * Double.parseDouble(one_zhu.getText().toString()) / 1000));
                    }
                } else {
                    one_money.setText("0.0000");
                }
            } else {// 输入框内容为空
                one_money.setText("0.0000");
            }
        }

        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before,
                                  int scount) {
            if (s.toString().startsWith("0")
                    || s.toString().trim().length() == 0) {
                z = 1;
                double num = (float) Double.parseDouble(userInfo.getU_RebateB() + "");
                DecimalFormat df11 = new DecimalFormat("#0.0000");// 格式化小数
                double du = Utils.sub(Double.parseDouble(userInfo.getU_RebateA()), num);
                double dp = Utils.mul(du, amString);
                double db = Utils.add(dp, cc) * z;
                s11 = df11.format((float) db);// 返回的是String类型
                count = s11;
                jx_today_money.setText(s11);
            }
        }

    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jx_jia:
                if (Integer.parseInt(one_zhu.getText().toString()) != 0) {
                    DecimalFormat df = new DecimalFormat("0.0000");
                    if (code_txt.getText().equals("0") || code_txt.length() == 0) {
                        code_txt.setText(z + "");
                        double num = (float) Double.parseDouble(userInfo.getU_RebateB() + "");
                        DecimalFormat df11 = new DecimalFormat("#0.0000");// 格式化小数
                        double du = Utils.sub(Double.parseDouble(userInfo.getU_RebateA()), num);
                        double dp = Utils.mul(du, amString);
                        double db = Utils.add(dp, cc) * z;
                        s11 = df11.format((float) db);// 返回的是String类型
                        count = s11;
                        jx_today_money.setText(s11);
                    } else {
                        z = z + 1;
                        code_txt.setText(z + "");
                        initFDian();
                        String s = df.format((float) Double.parseDouble(count) / (z - 1)
                                * z);// 返回的是String类型
                        jx_today_money.setText(s);
                        count = s;
                    }
                }
                break;
            case R.id.jx_jian:
                if (z == 1) {
                    code_txt.setText(z + "");
                    initFDian();
                    jx_today_money.setText(count);
                } else {
                    z = z - 1;
                    code_txt.setText(z + "");
                    initFDian();
                    DecimalFormat df6 = new DecimalFormat("0.0000");// 格式化小数
                    String s6 = df6.format((float) Double.parseDouble(count)
                            / (z + 1) * z);// 返回的是String类型
                    count = s6;
                    jx_today_money.setText(s6);
                }
                break;
            case R.id.jx_ib_yuan:
                jx_ib_yuan.setBackgroundResource(R.drawable.yuanzhong);
                jx_ib_jiao.setBackgroundResource(R.drawable.jiano);
                jx_ib_fen.setBackgroundResource(R.drawable.fenno);
                jx_ib_li.setBackgroundResource(R.drawable.lino);
                String moneString = count;
                if (userInfo.getCorner().equals("元")) {//获取单位也是元
                    moneString = count;
                    DecimalFormat df = new DecimalFormat("0.0000");
                    one_money.setText(df.format(Double.parseDouble(code_txt.getText().toString()) * Double.parseDouble(one_zhu.getText().toString())));
                } else if (userInfo.getCorner().equals("角")) {//获取的角
                    DecimalFormat df = new DecimalFormat(
                            "0.0000");// 格式化小数
                    String s = df.format((float) Double
                            .parseDouble(count) * 10);// 返回的是String类型
                    moneString = s;
                    amString = amString * 10;
                    cc = cc * 10;
                    one_money.setText(df.format(Double.parseDouble(code_txt.getText().toString()) * Double.parseDouble(one_zhu.getText().toString())));

                } else if (userInfo.getCorner().equals("分")) {
                    DecimalFormat df = new DecimalFormat(
                            "0.0000");// 格式化小数
                    String s = df.format((float) Double
                            .parseDouble(count) * 100);// 返回的是String类型
                    moneString = s;
                    amString = amString * 100;
                    cc = cc * 100;
                    one_money.setText(df.format((float) Double
                            .parseDouble(one_money
                                    .getText()
                                    .toString()) * 100));
                    one_money.setText(df.format((float) Double
                            .parseDouble(Integer.valueOf(code_txt.getText().toString()) * Integer.valueOf(one_zhu.getText().toString()) + "")));

                } else {
                    DecimalFormat df = new DecimalFormat(
                            "0.0000");// 格式化小数
                    String s = df.format((float) Double
                            .parseDouble(count) * 1000);// 返回的是String类型
                    moneString = s;
                    amString = amString * 1000;
                    cc = cc * 1000;
                    one_money.setText(df.format(Double.parseDouble(code_txt.getText().toString()) * Double.parseDouble(one_zhu.getText().toString())));
                }
                jx_today_money.setText(moneString);
                userInfo.setCorner("元");
                conner.setText("元");
                count = moneString;
                break;
            case R.id.jx_ib_jiao:
                jx_ib_yuan.setBackgroundResource(R.drawable.yuanno);
                jx_ib_jiao.setBackgroundResource(R.drawable.jiazhong);
                jx_ib_fen.setBackgroundResource(R.drawable.fenno);
                jx_ib_li.setBackgroundResource(R.drawable.lino);
                /**
                 * 当前点击的是角
                 */
                moneString = count;
                if (userInfo.getCorner().equals("元")) {
                    DecimalFormat df = new DecimalFormat(
                            "0.0000");// 格式化小数
                    String s = df.format((float) Double
                            .parseDouble(count) / 10);// 返回的是String类型
                    moneString = s;
                    amString = amString / 10;
                    cc = cc / 10;
                    one_money.setText(df.format(Double.parseDouble(code_txt.getText().toString()) * Double.parseDouble(one_zhu.getText().toString()) / 10));
                } else if (userInfo.getCorner().equals("角")) {
                    moneString = count;
                    DecimalFormat df = new DecimalFormat("0.0000");
                    one_money.setText(df.format(Double.parseDouble(code_txt.getText().toString()) * Double.parseDouble(one_zhu.getText().toString()) / 10));
                } else if (userInfo.getCorner().equals("分")) {
                    DecimalFormat df = new DecimalFormat(
                            "0.0000");// 格式化小数
                    String s = df.format((float) Double
                            .parseDouble(count) * 10);// 返回的是String类型
                    moneString = s;
                    amString = amString * 10;
                    cc = cc * 10;
                    one_money.setText(df.format(Double.parseDouble(code_txt.getText().toString()) * Double.parseDouble(one_zhu.getText().toString()) / 10));
                } else {
                    DecimalFormat df = new DecimalFormat(
                            "0.0000");// 格式化小数
                    String s = df.format((float) Double
                            .parseDouble(count) * 100);// 返回的是String类型
                    moneString = s;
                    amString = amString * 100;
                    cc = cc * 100;
                    one_money.setText(df.format(Double.parseDouble(code_txt.getText().toString()) * Double.parseDouble(one_zhu.getText().toString()) / 10));
                }
                jx_today_money.setText(moneString);
                userInfo.setCorner("角");
                conner.setText("角");
                count = moneString;

                break;
            case R.id.jx_ib_fen:
                jx_ib_yuan.setBackgroundResource(R.drawable.yuanno);
                jx_ib_jiao.setBackgroundResource(R.drawable.jiano);
                jx_ib_fen.setBackgroundResource(R.drawable.fenzhong);
                jx_ib_li.setBackgroundResource(R.drawable.lino);
                moneString = count;
                if (userInfo.getCorner().equals("元")) {
                    DecimalFormat df = new DecimalFormat(
                            "0.0000");// 格式化小数
                    String s = df.format((float) Double
                            .parseDouble(count) / 100);// 返回的是String类型
                    moneString = s;
                    amString = amString / 100;
                    cc = cc / 100;
                    one_money.setText(df.format(Double.parseDouble(code_txt.getText().toString()) * Double.parseDouble(one_zhu.getText().toString()) / 100));

                } else if (userInfo.getCorner().equals("角")) {
                    DecimalFormat df = new DecimalFormat(
                            "0.0000");// 格式化小数
                    String s = df.format((float) Double
                            .parseDouble(count) / 10);// 返回的是String类型
                    moneString = s;
                    amString = amString / 10;
                    cc = cc / 10;
                    one_money.setText(df.format(Double.parseDouble(code_txt.getText().toString()) * Double.parseDouble(one_zhu.getText().toString()) / 100));
                } else if (userInfo.getCorner().equals("分")) {
                    moneString = count;
                    DecimalFormat df = new DecimalFormat(
                            "0.0000");// 格式化小数
                    one_money.setText(df.format(Double.parseDouble(code_txt.getText().toString()) * Double.parseDouble(one_zhu.getText().toString()) / 100));
                } else {
                    DecimalFormat df = new DecimalFormat(
                            "0.0000");// 格式化小数
                    String s = df.format((float) Double
                            .parseDouble(count) * 10);// 返回的是String类型
                    moneString = s;
                    amString = amString * 10;
                    cc = cc * 10;
                    one_money.setText(df.format(Double.parseDouble(code_txt.getText().toString()) * Double.parseDouble(one_zhu.getText().toString()) / 100));
                }
                jx_today_money.setText(moneString);
                userInfo.setCorner("分");
                conner.setText("分");
                count = moneString;
                break;
            case R.id.jx_ib_li:
                jx_ib_yuan.setBackgroundResource(R.drawable.yuanno);
                jx_ib_jiao.setBackgroundResource(R.drawable.jiano);
                jx_ib_fen.setBackgroundResource(R.drawable.fenno);
                jx_ib_li.setBackgroundResource(R.drawable.lizhong);
                moneString = count;
                if (userInfo.getCorner().equals("元")) {
                    DecimalFormat df = new DecimalFormat(
                            "0.0000");// 格式化小数
                    String s = df.format((float) Double
                            .parseDouble(count) / 1000);// 返回的是String类型
                    moneString = s;
                    amString = amString / 1000;
                    cc = cc / 1000;
                    one_money.setText(df.format(Double.parseDouble(code_txt.getText().toString()) * Double.parseDouble(one_zhu.getText().toString()) / 1000));
                } else if (userInfo.getCorner().equals("角")) {
                    DecimalFormat df = new DecimalFormat(
                            "0.0000");// 格式化小数
                    String s = df.format((float) Double
                            .parseDouble(count) / 100);// 返回的是String类型
                    moneString = s;
                    amString = amString / 100;
                    cc = cc / 100;
                    one_money.setText(df.format(Double.parseDouble(code_txt.getText().toString()) * Double.parseDouble(one_zhu.getText().toString()) / 1000));
                } else if (userInfo.getCorner().equals("分")) {
                    DecimalFormat df = new DecimalFormat(
                            "0.0000");// 格式化小数
                    String s = df.format((float) Double
                            .parseDouble(count) / 10);// 返回的是String类型
                    moneString = s;
                    amString = amString / 10;
                    cc = cc / 10;
                    one_money.setText(df.format(Double.parseDouble(code_txt.getText().toString()) * Double.parseDouble(one_zhu.getText().toString()) / 1000));
                } else {
                    moneString = count;
                    DecimalFormat df = new DecimalFormat(
                            "0.0000");// 格式化小数
                    one_money.setText(df.format(Double.parseDouble(code_txt.getText().toString()) * Double.parseDouble(one_zhu.getText().toString()) / 1000));
                }
                jx_today_money.setText(moneString);
                userInfo.setCorner("厘");
                conner.setText("厘");
                count = moneString;
                break;
            case R.id.buy_car:
                /**
                 * 查找数据库,不为空则进入购物车,空则提醒
                 */
                caipiaoBeanList = caipiaoDao.findgamenewAll();
                if (!caipiaoBeanList.isEmpty()) {
                    for (int i = 0; i < caipiaoBeanList.size(); i++) {
                        if (caipiaoBeanList.get(i).getHx_name().equals(title)) {
                            Intent intent2 = new Intent(getActivity(), BuyCar.class);
                            Bundle bundle = new Bundle();
                            intent2.putExtra("dtitle", title);
                            bundle.putSerializable("model", dqIssuseBean);
                            intent2.putExtra("gameId", caipiaoBeanList.get(i).getNameId());
                            intent2.putExtra("beishu", code_txt.getText().toString());
                            intent2.putExtras(bundle);
                            startActivity(intent2);
                            break;
                        } else {
                            Toast.makeText(getActivity(), "当前购物车为空", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(getActivity(), "购物车为空,请选择号码", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.compound_btn:
                showUpdataTime();
                String a = url;
                if (!one_zhu.getText().toString().equals("0") && !url.equals("")) {
                    boolean result = caipiaoDao.addnewnumber(caiPiaoNewTopBean.getH_g_p_nid(), zero.getText().toString(), jx_today_money.getText().toString(), title, caiPiaoNewTopBean.getH_g_p_name(), url,
                            one_zhu.getText().toString(), code_txt.getText().toString(), userInfo.getCorner(),
                            one_money.getText().toString(), caiPiaoNewTopBean.getH_g_p_id(), caiPiaoNewTopBean.getH_g_p_one_amount(), caiPiaoNewTopBean.getH_g_p_tid(), "1", dtime + "");
                    if (result) {
                        Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "添加失败", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    /**
     * 复试数据的网络请求
     */
    public void getFSUrl(String p_id) {
        wh.configCookieStore(RUser.cookieStore);
        wh.post(Constants.getUrl() + Constants.game_play_new_xiangqing + p_id + ".json", new AjaxCallBack<String>() {
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
                msg.what = 1;
                handler.sendMessage(msg);
            }

            //加载失败回调此方法
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                ShowToast.showMsg(getActivity(), Constants.NETERROR);
             /*   view_loading.setVisibility(View.GONE);
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
                });*/
            }
        });
    }

    /**
     * 点击listview中的小圆点判断钱数
     */
    private void initFDian() {
        if (userInfo.getCorner().equals("元")) {
            DecimalFormat df = new DecimalFormat("0.0000");
            one_money.setText(df.format(Double.parseDouble(one_zhu.getText().toString()) * Double.parseDouble(code_txt.getText().toString())));
        } else if (userInfo.getCorner().equals("角")) {
            DecimalFormat df = new DecimalFormat("0.0000");
            one_money.setText(df.format(Double.parseDouble(one_zhu.getText().toString()) * Double.parseDouble(code_txt.getText().toString()) / 10));
        } else if (userInfo.getCorner().equals("分")) {
            DecimalFormat df = new DecimalFormat("0.0000");
            one_money.setText(df.format(Double.parseDouble(one_zhu.getText().toString()) * Double.parseDouble(code_txt.getText().toString()) / 100));
        } else {
            DecimalFormat df = new DecimalFormat("0.0000");
            one_money.setText(df.format(Double.parseDouble(one_zhu.getText().toString()) * Double.parseDouble(code_txt.getText().toString()) / 1000));
        }
    }

    /**
     * 获取毫秒值
     */
    public void showUpdataTime() {
        Date date = new Date();
        dtime = date.getTime();

    }
}
