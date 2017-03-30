package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import activity.huanlecheng.R;
import activity.huanlecheng.SuccessActivity;
import adapter.ChaseAdapter;
import adapter.SpinnerAdapter;
import bean.ChaseBean;
import bean.ChaseResponseModel;
import bean.LoginBean;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import http.AjaxParams;
import util.ShowToast;
import util.Utils;
import view.XListView;

public class ChaseOrderFragment extends BaseFragment implements
        OnClickListener, XListView.IXListViewListener {
    private String channel_id;
    private LinearLayout load_layout;// 载入中
    private LinearLayout load_layout_fail;// 载入失败
    private TextView txt_neterr;// 载入失败
    private View parentView;
    private XListView lv;
    private List<ChaseBean> list;// 临时数据
    private ChaseAdapter gAdapter;
    private Spinner spinner;
    private SpinnerAdapter sa;
    private LinearLayout wid01, wid02, wid03, wid04, wid05;
    private TextView zqs;
    private TextView zje;
    private TextView add_code;
    private String spString = "1";
    private EditText et_zhqs;
    private EditText et_qsbs;
    private EditText et_ge;
    private EditText et_bei;
    private EditText et_syl;
    private String nameid, url, zero, num, qh, mount, zhucount, today, bei;
    private ImageView add_btn;
    private ImageView iv_zhuihaot;
    private TextView add_codez;
    private String isChaseCodet;
    private FrameLayout fl_code;
    private String string = "0";
    private String mode;
    private String title;

    /**
     * 明天解决,新传过来了一个mode(模式),看下传过来的钱数注数,倍数,对不对,就差不多哦了
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        channel_id = args != null ? args.getString("id") : "";
        nameid = args.getString("nameid");
        url = args.getString("url");
        zero = args.getString("zero");
        num = args.getString("num");
        qh = args.getString("qh");
        mount = args.getString("mount");
        zhucount = args.getString("zhucount");
        today = args.getString("today");
        bei = args.getString("bei");
        title = args.getString("title");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        parentView = inflater.inflate(R.layout.chase_fragment_order, container,
                false);
        iv_zhuihaot = (ImageView) parentView.findViewById(R.id.iv_zhuihaot);
        load_layout = (LinearLayout) parentView.findViewById(R.id.view_loading);
        load_layout_fail = (LinearLayout) parentView
                .findViewById(R.id.view_load_fail);
        load_layout_fail.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                load_layout.setVisibility(View.VISIBLE);
                load_layout_fail.setVisibility(View.GONE);
                try {
                    GetData(channel_id);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        txt_neterr = (TextView) parentView.findViewById(R.id.txt_neterr);
        add_code = (TextView) parentView.findViewById(R.id.add_code);
        add_codez = (TextView) parentView.findViewById(R.id.add_codez);
        fl_code = (FrameLayout) parentView.findViewById(R.id.fl_code);
        fl_code.setOnClickListener(this);
        add_code.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                add_codez.setVisibility(View.VISIBLE);
                add_code.setVisibility(View.INVISIBLE);
                iv_zhuihaot.setBackgroundResource(R.drawable.zhuihaodian);
                string = "1";

            }
        });
        add_codez.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                add_codez.setVisibility(View.INVISIBLE);
                add_code.setVisibility(View.VISIBLE);
                iv_zhuihaot.setBackgroundResource(R.drawable.weixuan);
                string = "0";

            }
        });

        //  add_code.setOnClickListener(this);

        add_btn = (ImageView) parentView.findViewById(R.id.add_btn);
        add_btn.setOnClickListener(this);
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.headview,
                null);
        list = new ArrayList<ChaseBean>();
        gAdapter = new ChaseAdapter(getActivity());
        sa = new SpinnerAdapter(getActivity());
        spinner = (Spinner) v.findViewById(R.id.spinner);
        spinner.setAdapter(sa);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                spString = position + 1 + "";
                if (Integer.parseInt(spString) >= 2) {
                    initData();
                } else {
                    showToast("追号最少俩期,请重新选择");
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        zqs = (TextView) v.findViewById(R.id.zqs);
        zje = (TextView) v.findViewById(R.id.zje);
        et_zhqs = (EditText) v.findViewById(R.id.et_zhqs);
        et_qsbs = (EditText) v.findViewById(R.id.et_qsbs);
        //  et_qsbs.setText(bei);
        et_ge = (EditText) v.findViewById(R.id.et_ge);
        et_bei = (EditText) v.findViewById(R.id.et_bei);
        et_syl = (EditText) v.findViewById(R.id.et_syl);
        wid01 = (LinearLayout) v.findViewById(R.id.wid01);
        wid02 = (LinearLayout) v.findViewById(R.id.wid02);
        wid03 = (LinearLayout) v.findViewById(R.id.wid03);
        wid04 = (LinearLayout) v.findViewById(R.id.wid04);
        wid05 = (LinearLayout) v.findViewById(R.id.wid05);
        if (channel_id.equals("0")) {
            wid01.setVisibility(View.VISIBLE);
            wid02.setVisibility(View.VISIBLE);
            wid03.setVisibility(View.GONE);
            wid04.setVisibility(View.GONE);
            wid05.setVisibility(View.VISIBLE);

        } else if (channel_id.equals("1")) {
            wid01.setVisibility(View.VISIBLE);
            wid02.setVisibility(View.GONE);
            wid03.setVisibility(View.GONE);
            wid04.setVisibility(View.GONE);
            wid05.setVisibility(View.VISIBLE);
        } else {
            wid01.setVisibility(View.GONE);
            wid02.setVisibility(View.GONE);
            wid03.setVisibility(View.VISIBLE);
            wid04.setVisibility(View.VISIBLE);
            wid05.setVisibility(View.VISIBLE);
        }
        lv = (XListView) parentView.findViewById(R.id.main_lv_list);
        lv.addHeaderView(v);
        lv.setXListViewListener(this);
        lv.setPullLoadEnable(false);
        lv.setCanRefresh(false);
        lv.setAdapter(gAdapter);
        try {
            GetData(RUser.id);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        et_qsbs.addTextChangedListener(mtextWatcher);//利润率追号倍数监听
        et_ge.addTextChangedListener(fgtextWatcher);//翻倍追号隔几期监听
        et_bei.addTextChangedListener(fbtextWatcher);//翻倍追号倍数监听

        return parentView;
    }

    /**
     * 翻倍追号倍数监听
     */
    public TextWatcher fbtextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!et_bei.getText().toString().equals("")) {
                initData();
            }
        }
    };
    /**
     * 翻倍追号隔几期监听
     */
    public TextWatcher fgtextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!et_ge.getText().toString().equals("")) {
                initData();
            }
        }
    };
    /**
     * 利润率追号倍数监听
     */
    public TextWatcher mtextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!et_qsbs.getText().toString().equals("")) {
                initData();
            }
        }
    };

    private void initData() {
        if (spString.equals("1")) {
            showToast("追号至少选择两期");
        } else {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setB(false);
            }
            if (channel_id.equals("0")) {
                DecimalFormat dFormat = new DecimalFormat("0");
                DecimalFormat dFormat1 = new DecimalFormat("0.0000");
                boolean syl = true;
                zqs.setText(spString);
                et_zhqs.setText(spString);
                double txt_zje = 0;
                for (int i = 0; i < Integer.valueOf(spString); i++) {
                    list.get(i).setB(true);
                }
                for (int i = 0; i < Integer.valueOf(spString); i++) {
                    if (i == 0) {
                        // 第一位
                        list.get(i).setBs(et_qsbs.getText().toString());
                        list.get(i).setJz(dFormat1.format(Double.parseDouble(num) * Double.parseDouble(et_qsbs.getText().toString())));
                        //  }
                        txt_zje = txt_zje
                                + Double.parseDouble(list.get(i).getJz());
                    } else {
                        // 计算同利润追号
                        // 公式:ceil（上N级倍数的和值*g_p_amount*注数*元角分厘*（1+最低收益率）除以上一页的奖金-（g_p_amount*注数*元角分厘）*（1+最低收益率））
                        // 计算上N级倍数的和值
                        double count = 0;
                        for (int j = 0; j < i; j++) {
                            count = count
                                    + Double.parseDouble(list.get(j)
                                    .getBs());
                        }
                        if (userInfo.getCorner().equals("元")) {
                            double fz = count
                                    * Double.parseDouble(mount)
                                    * Integer.valueOf(zhucount)
                                    * 1
                                    * (1 + Double.parseDouble(et_syl
                                    .getText().toString()) / 100);
                            double fm = Double.parseDouble(today)
                                    - (Double.parseDouble(mount)
                                    * Integer.valueOf(zhucount) * 1 * (1 + Double
                                    .parseDouble(et_syl.getText()
                                            .toString()) / 100));
                            if (fm <= 0) {
                                syl = false;
                            } else {
                                double dm = fz / fm;
                                if (dm >= 8000) {
                                    dm = 8000;
                                }
                                if (dm <= Integer.valueOf(et_qsbs.getText()
                                        .toString())) {
                                    dm = Integer.valueOf(et_qsbs.getText()
                                            .toString());
                                }
                                list.get(i).setBs(
                                        dFormat.format(Math.ceil(dm)));
                                list.get(i).setJz(dFormat1.format(Double.parseDouble(list.get(i).getBs()) * Double.parseDouble(num)));
                            }
                        } else if (userInfo.getCorner().equals("角")) {
                            double fz = count
                                    * Double.parseDouble(mount)
                                    * Integer.valueOf(zhucount)
                                    * 0.1
                                    * (1 + Double.parseDouble(et_syl
                                    .getText().toString()) / 100);
                            double fm = Double.parseDouble(today)
                                    - (Double.parseDouble(mount)
                                    * Integer.valueOf(zhucount)
                                    * 0.1 * (1 + Double
                                    .parseDouble(et_syl.getText()
                                            .toString()) / 100));
                            if (fm <= 0) {
                                syl = false;
                            } else {
                                double dm = fz / fm;
                                if (dm >= 8000) {
                                    dm = 8000;
                                }
                                if (dm <= Integer.valueOf(et_qsbs.getText()
                                        .toString())) {
                                    dm = Integer.valueOf(et_qsbs.getText()
                                            .toString());
                                }
                                list.get(i).setBs(
                                        dFormat.format(Math.ceil(dm)));
                                list.get(i).setJz(dFormat1.format(Double.parseDouble(list.get(i).getBs()) * Double.parseDouble(num)));

                            }
                        } else if (userInfo.getCorner().equals("分")) {
                            double fz = count
                                    * Double.parseDouble(mount)
                                    * Integer.valueOf(zhucount)
                                    * 0.01
                                    * (1 + Double.parseDouble(et_syl
                                    .getText().toString()) / 100);
                            double fm = Double.parseDouble(today)
                                    - (Double.parseDouble(mount)
                                    * Integer.valueOf(zhucount)
                                    * 0.01 * (1 + Double
                                    .parseDouble(et_syl.getText()
                                            .toString()) / 100));
                            if (fm <= 0) {
                                syl = false;
                            } else {
                                double dm = fz / fm;
                                if (dm >= 8000) {
                                    dm = 8000;
                                }
                                if (dm <= Integer.valueOf(et_qsbs.getText()
                                        .toString())) {
                                    dm = Integer.valueOf(et_qsbs.getText()
                                            .toString());
                                }
                                list.get(i).setBs(
                                        dFormat.format(Math.ceil(dm)));
                                list.get(i).setJz(dFormat1.format(Double.parseDouble(list.get(i).getBs()) * Double.parseDouble(num)));

                            }
                        } else {
                            double fz = count
                                    * Double.parseDouble(mount)
                                    * Integer.valueOf(zhucount)
                                    * 0.001
                                    * (1 + Double.parseDouble(et_syl
                                    .getText().toString()) / 100);
                            double fm = Double.parseDouble(today)
                                    - (Double.parseDouble(mount)
                                    * Integer.valueOf(zhucount)
                                    * 0.001 * (1 + Double
                                    .parseDouble(et_syl.getText()
                                            .toString()) / 100));
                            if (fm <= 0) {
                                syl = false;
                            } else {
                                int dm = (int) Math.ceil(fz / fm);
                                if (dm >= 8000) {
                                    dm = 8000;
                                }
                                if (dm <= Integer.valueOf(et_qsbs.getText()
                                        .toString())) {
                                    dm = Integer.valueOf(et_qsbs.getText()
                                            .toString());
                                }
                                list.get(i).setBs(
                                        dFormat.format(Math.ceil(dm)));
                                list.get(i).setJz(dFormat1.format(Double.parseDouble(list.get(i).getBs()) * Double.parseDouble(num)));
                            }
                        }
                        txt_zje = txt_zje
                                + Double.parseDouble(list.get(i).getJz());
                    }
                }
                if (syl) {
                    ChaseResponseModel rm = new ChaseResponseModel();
                    rm.setList(list);
                    gAdapter.setData(rm);
                    zje.setText(dFormat1.format(txt_zje));
                } else {
                    showToast("购买金额过多,超过奖金无意义");
                }
            } else if (channel_id.equals("1")) {
                DecimalFormat df = new DecimalFormat("0.0000");
                zqs.setText(spString);//总期数
                zje.setText(df.format(Double.parseDouble(num) * Double.parseDouble(spString) * Double.parseDouble(et_qsbs.getText().toString())));
                et_zhqs.setText(spString);
                for (int i = 0; i < Integer.valueOf(spString); i++) {
                    list.get(i).setB(true);
                }
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setBs(et_qsbs.getText().toString());
                    list.get(i).setJz(df.format(Double.parseDouble(num) * Double.parseDouble(et_qsbs.getText().toString())));
                }
                ChaseResponseModel rm = new ChaseResponseModel();
                rm.setList(list);
                gAdapter.setData(rm);
            } else {
                zqs.setText(spString);
                et_zhqs.setText(spString);
                DecimalFormat df = new DecimalFormat("0.0000");
                for (int i = 0; i < Integer.valueOf(spString); i++) {
                    list.get(i).setB(true);
                }
                for (int i = 0; i < list.size(); i++) {
                    //  list.get(i).setBs(et_bei.getText().toString());
                    list.get(i).setBs((int) Math.pow(Double.parseDouble(et_bei.getText().toString()), i / Integer.valueOf(et_ge.getText().toString())) + "");
                    list.get(i).setJz(df.format((Double.parseDouble(num)/ Double.parseDouble(bei))* Double.parseDouble(list.get(i).getBs())));
                    //   String a=df.format(  Double.parseDouble(num) * Double.parseDouble(list.get(i).getBs()));
                }
                // 总金额
                double money = 0;
                for (int i = 0; i < Integer.valueOf(spString); i++) {
                    if (list.get(i).isB()) {
                        money = money + Double.valueOf(list.get(i)
                                .getJz());
                    }
                }
                zje.setText(df.format(money));
                ChaseResponseModel rm = new ChaseResponseModel();
                rm.setList(list);
                gAdapter.setData(rm);
            }
        }
    }

    /**
     * 获取数据
     *
     * @throws Exception
     */
    private void GetData(String id) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("Model", "Game");
        params.put("Action", "GetChaseCodeList");
        params.put("Id", id);
        wh.configCookieStore(RUser.cookieStore);
        wh.post(Constants.getUrl(), params, new AjaxCallBack<String>() {
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
                msg.what = 0;// 第一次
                msg.obj = t;
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) { // 加载失败的时候回调
                // TODO Auto-generated method stub
                super.onFailure(t, errorNo, strMsg);
                load_layout.setVisibility(View.GONE);
                lv.setVisibility(View.GONE);
                lv.stopFailRefresh();
                load_layout_fail.setVisibility(View.VISIBLE);
                txt_neterr.setText(Constants.NETERROR + "  点击上方按钮重新加载");

            }

        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            JSONObject jsonObject;
            switch (msg.what) {
                case 0:
                    list.clear();
                    String str = (String) msg.obj;
                    System.out.println(str);
                    try {
                        jsonObject = new JSONObject(str);
                        java.lang.reflect.Type type = new TypeToken<ChaseResponseModel>() {
                        }.getType();
                        ChaseResponseModel rmModel = gson.fromJson(str, type);
                        if (!rmModel.getError().equals("")) {
                            load_layout.setVisibility(View.GONE);
                            lv.setVisibility(View.GONE);
                            load_layout_fail.setVisibility(View.VISIBLE);
                            txt_neterr.setText(rmModel.getMsg() + "  点击屏幕重新加载");
                        } else {
                            list.addAll(rmModel.getList());
                            sa.setData(rmModel.getList());
                            spinner.setSelection(1);
                            gAdapter.setData(rmModel);
                            lv.setVisibility(View.VISIBLE);
                            load_layout.setVisibility(View.GONE);
                            load_layout_fail.setVisibility(View.GONE);
                        }
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case 1:

                    break;

                default:
                    break;
            }
            super.handleMessage(msg);
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
        switch (v.getId()) {
            case R.id.add_btn:
                if (et_zhqs.getText().toString().equals("0")
                        || et_zhqs.getText().toString().equals("1")) {
                    showToast("请选择正确格式");
                } else {
                    if ((float) Double.parseDouble(userInfo.getU_Money()) < (float) Double
                            .parseDouble(zje.getText().toString())) {
                        showToast("金额不足，请充值");
                    } else {
                        loadingWindow.showDialog(Constants.tjing);
                        if (channel_id.equals("0")) {
                            String chaseurl = "";
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).isB()) {
                                    if (Utils.isEmpty(chaseurl)) {
                                        if (userInfo.getCorner().equals("元")) {
                                            chaseurl = chaseurl + "#"
                                                    + list.get(i).getG_T_Issue() + "+"
                                                    + list.get(i).getBs();
                                        } else if (userInfo.getCorner().equals("角")) {
                                            chaseurl = chaseurl + "#"
                                                    + list.get(i).getG_T_Issue() + "+"
                                                    + Integer.valueOf(list.get(i).getBs())*0.1+"";
                                        } else if (userInfo.getCorner().equals("分")) {
                                            chaseurl = chaseurl + "#"
                                                    + list.get(i).getG_T_Issue() + "+"
                                                    + Integer.valueOf(list.get(i).getBs())*0.01+"";
                                        } else if (userInfo.getCorner().equals("厘")) {
                                            chaseurl = chaseurl + "#"
                                                    + list.get(i).getG_T_Issue() + "+"
                                                    + Integer.valueOf(list.get(i).getBs())*0.001+"";
                                        }
                                    } else {
                                        if (userInfo.getCorner().equals("元")) {
                                            chaseurl = chaseurl + "#"
                                                    + list.get(i).getG_T_Issue() + "+"
                                                    + list.get(i).getBs();
                                        } else if (userInfo.getCorner().equals("角")) {
                                            chaseurl = chaseurl + "#"
                                                    + list.get(i).getG_T_Issue() + "+"
                                                    + Integer.valueOf(list.get(i).getBs())*0.1+"";
                                        } else if (userInfo.getCorner().equals("分")) {
                                            chaseurl = chaseurl + "#"
                                                    + list.get(i).getG_T_Issue() + "+"
                                                    + Integer.valueOf(list.get(i).getBs())*0.01+"";
                                        } else if (userInfo.getCorner().equals("厘")) {
                                            chaseurl = chaseurl + "#"
                                                    + list.get(i).getG_T_Issue() + "+"
                                                    + Integer.valueOf(list.get(i).getBs())*0.001+"";
                                        }
                                    }
                                }
                            }
                            //倍数问题应该是,加上元角分厘模式的问题
                           requestUserinfo(nameid, qh, url, et_qsbs.getText().toString(), zero, zje.getText().toString(), chaseurl, string);
                            userInfo.setCorner(mode);

                        } else if (channel_id.equals("1")) {
                            String chaseurl = "";
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).isB()) {
                                    if (Utils.isEmpty(chaseurl)) {
                                        if (userInfo.getCorner().equals("元")) {
                                            chaseurl = chaseurl + "#"
                                                    + list.get(i).getG_T_Issue() + "+"
                                                    + list.get(i).getBs();
                                        } else if (userInfo.getCorner().equals("角")) {
                                            chaseurl = chaseurl + "#"
                                                    + list.get(i).getG_T_Issue() + "+"
                                                    + Integer.valueOf(list.get(i).getBs())*0.1+"";
                                        } else if (userInfo.getCorner().equals("分")) {
                                            chaseurl = chaseurl + "#"
                                                    + list.get(i).getG_T_Issue() + "+"
                                                    + Integer.valueOf(list.get(i).getBs())*0.01+"";
                                        } else if (userInfo.getCorner().equals("厘")) {
                                            chaseurl = chaseurl + "#"
                                                    + list.get(i).getG_T_Issue() + "+"
                                                    + Integer.valueOf(list.get(i).getBs())*0.001+"";
                                        }
                                    } else {
                                        if (userInfo.getCorner().equals("元")) {
                                            chaseurl = chaseurl + "#"
                                                    + list.get(i).getG_T_Issue() + "+"
                                                    + list.get(i).getBs();
                                        } else if (userInfo.getCorner().equals("角")) {
                                            chaseurl = chaseurl + "#"
                                                    + list.get(i).getG_T_Issue() + "+"
                                                    + Integer.valueOf(list.get(i).getBs())*0.1+"";
                                        } else if (userInfo.getCorner().equals("分")) {
                                            chaseurl = chaseurl + "#"
                                                    + list.get(i).getG_T_Issue() + "+"
                                                    + Integer.valueOf(list.get(i).getBs())*0.01+"";
                                        } else if (userInfo.getCorner().equals("厘")) {
                                            chaseurl = chaseurl + "#"
                                                    + list.get(i).getG_T_Issue() + "+"
                                                    + Integer.valueOf(list.get(i).getBs())*0.001+"";
                                        }

                                    }
                                }
                            }
                            requestUserinfo(nameid, qh, url, et_qsbs.getText().toString(), zero, zje.getText().toString(), chaseurl, string);
                            userInfo.setCorner(mode);

                        } else {
                            String chaseurl = "";
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).isB()) {
                                    if (Utils.isEmpty(chaseurl)) {
                                        if (userInfo.getCorner().equals("元")) {
                                            chaseurl = list.get(i).getG_T_Issue() + "+"
                                                    + list.get(i).getBs();
                                        } else if (userInfo.getCorner().equals("角")) {
                                            chaseurl = list.get(i).getG_T_Issue() + "+"
                                                    + Integer.valueOf(list.get(i).getBs())*0.1+"";
                                        } else if (userInfo.getCorner().equals("分")) {
                                            chaseurl = list.get(i).getG_T_Issue() + "+"
                                                    + Integer.valueOf(list.get(i).getBs())*0.01+"";
                                        } else if (userInfo.getCorner().equals("厘")) {
                                            chaseurl = list.get(i).getG_T_Issue() + "+"
                                                    + Integer.valueOf(list.get(i).getBs())*0.001+"";
                                        }
                                    } else {
                                        if (userInfo.getCorner().equals("元")) {
                                            chaseurl = chaseurl + "#"
                                                    + list.get(i).getG_T_Issue() + "+"
                                                    + list.get(i).getBs();
                                        } else if (userInfo.getCorner().equals("角")) {
                                            chaseurl = chaseurl + "#"
                                                    + list.get(i).getG_T_Issue() + "+"
                                                    + Integer.valueOf(list.get(i).getBs())*0.1+"";
                                        } else if (userInfo.getCorner().equals("分")) {
                                            chaseurl = chaseurl + "#"
                                                    + list.get(i).getG_T_Issue() + "+"
                                                    + Integer.valueOf(list.get(i).getBs())*0.01+"";
                                        } else if (userInfo.getCorner().equals("厘")) {
                                            chaseurl = chaseurl + "#"
                                                    + list.get(i).getG_T_Issue() + "+"
                                                    + Integer.valueOf(list.get(i).getBs())*0.001+"";
                                        }
                                    }
                                }
                            }
                           requestUserinfo(nameid, qh, url, et_bei.getText().toString()
                                , zero, zje.getText().toString(), chaseurl, string);
                        }
                    }
                }
                break;
            default:

        }
    }

    /**
     * 投注
     */

    public void requestUserinfo(String gameID, String qihao, String url,
                                String beishu, String fandian, String amout, String chase, String string) {

        AjaxParams params = new AjaxParams();
        params.put("Model", "Game");
        params.put("Action", "AddBuy");
        params.put("Id", gameID);
        params.put("Issue", qihao);
        params.put("BuyCode", url);
        params.put("BuyChase", chase);
        //选中传1没选中传0
        params.put("IsChaseCode", string);
        params.put("Multiple", beishu);
        params.put("Rebate", fandian);
        params.put("TheTotalAmount", amout);
        System.out.println(params);
        wh.configCookieStore(RUser.cookieStore);
        wh.post(Constants.getUrl(), params, new AjaxCallBack<String>() {
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
                ShowToast.showMsg(getActivity(), Constants.NETERROR);

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
                    loadingWindow.cancel();
                    String str = (String) msg.obj;
                    System.out.println(str);
                    java.lang.reflect.Type type = new TypeToken<LoginBean>() {
                    }.getType();
                    Gson gson = new Gson();
                    LoginBean hp = gson.fromJson(str, type);
                    //if (!hp.getError().equals("")) {
                      //  showToast(hp.getMsg());
                  //  } else {
                        intent = new Intent(getActivity(), SuccessActivity.class);
                        intent.putExtra("money", zje.getText().toString());
                        intent.putExtra("id", "1");
                        intent.putExtra("title",title);
                        startActivity(intent);
                        getActivity().finish();
                  //  }
                    break;
                case 1:

                    break;

                default:
                    break;
            }
        }
    };
}
