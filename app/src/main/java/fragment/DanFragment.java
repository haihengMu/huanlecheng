package fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Html;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import activity.huanlecheng.BuyCar;
import activity.huanlecheng.R;
import bean.CaiPiaoNewTopBean;
import bean.CaipiaoBean;
import bean.DQIssuseBean;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import http.AjaxParams;
import util.CaipiaoDao;
import util.Utils;

/**
 * Created by Administrator on 2017/3/16.
 */

public class DanFragment extends BaseFragment implements View.OnClickListener {

    private View view;
    private TextView ds_one_zhu;
    private RadioButton ds_ib_yuan;
    private RadioButton ds_ib_jiao;
    private RadioButton ds_ib_fen;
    private RadioButton ds_ib_li;
    private LinearLayout ll_danshi;
    private ImageView ds_compound_btn;
    private ImageView ds_del;
    private ImageView ds_buy_car;
    private TextView ds_today_money;
    private TextView ds_num;
    private TextView ds_one_money;
    private CaiPiaoNewTopBean caiPiaoNewTopBean;
    private String title;
    private String dcount = "";// 当前总奖金
    private double dcc;// 接口本金
    private String ds11;
    private SeekBar ds_seekbar;
    private TextView ds_zero;
    private TextView ds_seventen;
    private double amdString;// 基数 乘法算总价
    private ImageButton ds_jia;
    private ImageButton ds_jian;
    private TextView ds_conner;
    private int dz = 1;
    private EditText ds_code_txt;//倍数
    private int z = 1;//注数
    private int db = 1;//滑动监听
    private EditText ds_et;
    private LinearLayout ds_check_lin;
    private TextView ds_remark;
    private CheckBox ds_wan;
    private CheckBox ds_qian;
    private CheckBox ds_bai;
    private CheckBox ds_shi;
    private CheckBox ds_ge;
    private String urls;
    private CaipiaoDao caipiaoDao;
    private Long dtime;
    private List<CaipiaoBean> caipiaoBeanList;
    private DQIssuseBean dqIssuseBean;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dan, container, false);
        /**
         * 1700+返点*(1-调节百分比)*20
         */
        caipiaoDao = new CaipiaoDao(getActivity());
        initDDView();
        return view;
    }

    public void initDataD(CaiPiaoNewTopBean caiPiaoNewTopBean, String title) {
        this.caiPiaoNewTopBean = caiPiaoNewTopBean;
        this.title = title;
    }

    private void initDDView() {
        ds_one_zhu = (TextView) view.findViewById(R.id.ds_one_zhu);
        ds_ib_yuan = (RadioButton) view.findViewById(R.id.ds_ib_yuan);
        ds_ib_jiao = (RadioButton) view.findViewById(R.id.ds_ib_jiao);
        ds_ib_fen = (RadioButton) view.findViewById(R.id.ds_ib_fen);
        ds_ib_li = (RadioButton) view.findViewById(R.id.ds_ib_li);
        ll_danshi = (LinearLayout) view.findViewById(R.id.ll_danshi);
        ds_check_lin = (LinearLayout) view.findViewById(R.id.ds_check_lin);
        ds_ib_yuan.setOnClickListener(this);
        ds_ib_jiao.setOnClickListener(this);
        ds_ib_fen.setOnClickListener(this);
        ds_ib_li.setOnClickListener(this);
        ds_compound_btn = (ImageView) view.findViewById(R.id.ds_compound_btn);
        ds_del = (ImageView) view.findViewById(R.id.ds_del);
        ds_del.setOnClickListener(this);
        ds_buy_car = (ImageView) view.findViewById(R.id.ds_buy_car);
        ds_buy_car.setOnClickListener(this);
        ds_compound_btn.setOnClickListener(this);
        ds_today_money = (TextView) view.findViewById(R.id.ds_today_money);
        ds_num = (TextView) view.findViewById(R.id.ds_num);
        ds_one_money = (TextView) view.findViewById(R.id.ds_one_money);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action.up");
        intentFilter.addAction("action.down");
        getActivity().registerReceiver(mre, intentFilter);
        initDData();
        initDView();

    }

    private void initDView() {
        ds_remark = (TextView) view.findViewById(R.id.ds_remark);
        ds_remark.setText(Html.fromHtml(caiPiaoNewTopBean.getH_g_p_example()));
        ds_check_lin = (LinearLayout) view.findViewById(R.id.ds_check_lin);
        ds_wan = (CheckBox) view.findViewById(R.id.ds_wan);
        ds_qian = (CheckBox) view.findViewById(R.id.ds_qian);
        ds_bai = (CheckBox) view.findViewById(R.id.ds_bai);
        ds_shi = (CheckBox) view.findViewById(R.id.ds_shi);
        ds_ge = (CheckBox) view.findViewById(R.id.ds_ge);

        if (caiPiaoNewTopBean.getH_g_p_name().indexOf("任") != -1) {
            ds_check_lin.setVisibility(View.VISIBLE);
        } else {
            ds_check_lin.setVisibility(View.GONE);
        }
        initDshu();
    }

    /**
     * 单式个十百千万
     */
    private void initDshu() {
        ds_wan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ds_wan.isChecked()) {
                    ds_wan.setBackgroundResource(R.drawable.wanzhong);
                } else {
                    ds_wan.setBackgroundResource(R.drawable.wanno);
                }
                initDPan();
            }
        });
        ds_qian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ds_qian.isChecked()) {
                    ds_qian.setBackgroundResource(R.drawable.qianzhong);
                } else {
                    ds_qian.setBackgroundResource(R.drawable.qianno);
                }
                initDPan();
            }
        });
        ds_bai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ds_bai.isChecked()) {
                    ds_bai.setBackgroundResource(R.drawable.baizhong);
                } else {
                    ds_bai.setBackgroundResource(R.drawable.baino);
                }
                initDPan();
            }
        });
        ds_shi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ds_shi.isChecked()) {
                    ds_shi.setBackgroundResource(R.drawable.addzhong);
                } else {
                    ds_shi.setBackgroundResource(R.drawable.shino);
                }
                initDPan();
            }
        });
        ds_ge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ds_ge.isChecked()) {
                    ds_ge.setBackgroundResource(R.drawable.gezhong);
                } else {
                    ds_ge.setBackgroundResource(R.drawable.geno);
                }
                initDPan();
            }
        });
    }

    private void initDData() {
        /**
         * 分隔bonus
         */
        String dbonus[] = caiPiaoNewTopBean.getH_g_p_bonus().split(",");
        for (int i = 0; i < dbonus.length; i++) {
            for (int j = i + 1; j < dbonus.length; j++) {
                if ((float) Double.parseDouble(dbonus[i]) < (float) Double
                        .parseDouble(dbonus[j])) {
                    String dtemp = dbonus[i];
                    dbonus[i] = dbonus[i + 1];
                    dbonus[i + 1] = dtemp;
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
        if (userInfo.getCorner().equals("元")) {
            dcount = dbonus[0];
            dcc = Double.parseDouble(dbonus[0]);
            amdString = (float) Double.parseDouble(dgpString[0]);
            DecimalFormat df = new DecimalFormat("0.00000");// 格式化小数
        } else if (userInfo.getCorner().equals("角")) {
            DecimalFormat df = new DecimalFormat("0.00000");// 格式化小数
            String s = df.format((float) Double.parseDouble(dbonus[0]) / 10);// 返回的是String类型
            dcount = s;
            dcc = Double.parseDouble(dbonus[0]) / 10;
            amdString = (float) Double.parseDouble(dgpString[0]) / 10;
        } else if (userInfo.getCorner().equals("分")) {
            DecimalFormat df = new DecimalFormat("0.00000");// 格式化小数
            String s = df.format((float) Double.parseDouble(dbonus[0]) / 100);// 返回的是String类型
            dcount = s;
            dcc = Double.parseDouble(dbonus[0]) / 100;
            amdString = (float) Double.parseDouble(dgpString[0]) / 100;

        } else {
            DecimalFormat df = new DecimalFormat("0.000000");// 格式化小数
            String s = df.format((float) Double.parseDouble(dbonus[0]) / 1000);// 返回的是String类型
            dcount = s;
            dcc = Double.parseDouble(dbonus[0]) / 1000;
            amdString = Double.parseDouble(dgpString[0]) / 1000;

        }

        ds_jia = (ImageButton) view.findViewById(R.id.ds_jia);
        ds_jia.setOnClickListener(this);
        ds_jian = (ImageButton) view.findViewById(R.id.ds_jian);
        ds_jian.setOnClickListener(this);
        ds_seekbar = (SeekBar) view.findViewById(R.id.ds_seekbar);
        ds_zero = (TextView) view.findViewById(R.id.ds_zero);
        ds_zero.setText(userInfo.getU_RebateA());
        ds_seventen = (TextView) view.findViewById(R.id.ds_seventen);
        float inum = (float) Double.parseDouble(userInfo.getU_RebateA() + "")
                - (float) Double.parseDouble(userInfo.getU_RebateB() + "");
        DecimalFormat df1 = new DecimalFormat("0");// 格式化小数
        String s1 = df1.format(1700 + inum * 10 * 2);// 返回的是String类型
        ds_seventen.setText(s1);
        ds_conner = (TextView) view.findViewById(R.id.ds_conner);
        ds_conner.setText(userInfo.getCorner());
        ds_seekbar.setMax((int) (Double.parseDouble(userInfo.getU_RebateA()) * 10));
        ds_seekbar.setOnSeekBarChangeListener(dchange);
        ds_seekbar.setProgress((int) (Double.parseDouble(userInfo.getU_RebateA())
                * 10) - (int) (Double.parseDouble(userInfo.getU_RebateB()) * 10));

        if (userInfo.getCorner().equals("元")) {
            ds_ib_yuan.setBackgroundResource(R.drawable.yuanzhong);
            ds_ib_jiao.setBackgroundResource(R.drawable.jiano);
            ds_ib_fen.setBackgroundResource(R.drawable.fenno);
            ds_ib_li.setBackgroundResource(R.drawable.lino);
        } else if (userInfo.getCorner().equals("角")) {
            ds_ib_yuan.setBackgroundResource(R.drawable.yuanno);
            ds_ib_jiao.setBackgroundResource(R.drawable.jiazhong);
            ds_ib_fen.setBackgroundResource(R.drawable.fenno);
            ds_ib_li.setBackgroundResource(R.drawable.lino);
        } else if (userInfo.getCorner().equals("分")) {
            ds_ib_yuan.setBackgroundResource(R.drawable.yuanno);
            ds_ib_jiao.setBackgroundResource(R.drawable.jiano);
            ds_ib_fen.setBackgroundResource(R.drawable.fenzhong);
            ds_ib_li.setBackgroundResource(R.drawable.lino);
        } else if (userInfo.getCorner().equals("厘")) {
            ds_ib_yuan.setBackgroundResource(R.drawable.yuanno);
            ds_ib_jiao.setBackgroundResource(R.drawable.jiano);
            ds_ib_fen.setBackgroundResource(R.drawable.fenno);
            ds_ib_li.setBackgroundResource(R.drawable.lizhong);
        }
        // 初始化当前奖金
        double dnum = (float) Double.parseDouble(userInfo.getU_RebateB());
        DecimalFormat df11 = new DecimalFormat("#0.0000");// 格式化小数
        double ddu = Utils.sub(Double.parseDouble(userInfo.getU_RebateA()), dnum);
        double ddp = Utils.mul(ddu, amdString);
        double ddb = Utils.add(ddp, dcc) * dz;
        ds11 = df11.format((float) ddb);// 返回的是String类型
        dcount = ds11;
        ds_today_money.setText(ds11);
        ds_code_txt = (EditText) view.findViewById(R.id.ds_code_txt);
        ds_code_txt.addTextChangedListener(mdTextWatcher);
        setDEditTextSelection(ds_code_txt);
        ds_code_txt.setText(dz + "");
        ds_et = (EditText) view.findViewById(R.id.ds_et);
        ds_et.addTextChangedListener(mddTextWatcher);
    }

    private void setDEditTextSelection(EditText et) {
        CharSequence text = ds_code_txt.getText();
        if (text instanceof Spannable) {
            Spannable spanText = (Spannable) text;
            Selection.setSelection(spanText, text.length());
        }
    }

    private BroadcastReceiver mre = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("action.up")) {
                ll_danshi.setVisibility(View.VISIBLE);
            } else if (action.equals("action.down")) {
                ll_danshi.setVisibility(View.GONE);
            }
        }
    };

    /**
     * 单式edittext监听
     */
    private TextWatcher mddTextWatcher = new TextWatcher() {
        private CharSequence temp;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            temp = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!ds_code_txt.getText().toString().equals("")) {
                initDPan();
            }
        }
    };

    public void initDPan() {
        String dputString = ds_et.getText().toString();
        String dp[] = dputString.split("#");
        if (ds_check_lin.getVisibility() == 0) {
            int inseck = 0;
            String wanString = "";
            String qianString = "";
            String baiString = "";
            String shiString = "";
            String geString = "";
            boolean dwc = ds_wan.isChecked();
            boolean dqc = ds_qian.isChecked();
            boolean dsc = ds_shi.isChecked();
            boolean dbc = ds_bai.isChecked();
            boolean dgc = ds_ge.isChecked();
            if (dwc) {
                inseck = inseck + 1;
                wanString = "万";
            }
            if (dqc) {
                inseck = inseck + 1;
                qianString = "千";
            }
            if (dbc) {
                inseck = inseck + 1;
                baiString = "百";
            }
            if (dsc) {
                inseck = inseck + 1;
                shiString = "十";
            }
            if (dgc) {
                inseck = inseck + 1;
                geString = "个";
            }
            urls = "";
            int jilu = 0;
            List<String> mlister = new ArrayList<>();
            for (int i = 0; i < dp.length; i++) {
                if (caiPiaoNewTopBean.getH_g_p_name().indexOf("任二") != -1) {
                    if (dp[i].length() == 2) {
                        mlister.add(dp[i]);
                    }
                } else if (caiPiaoNewTopBean.getH_g_p_name().indexOf("任三") != -1) {
                    if (dp[i].length() == 3) {
                        mlister.add(dp[i]);
                    }
                } else if (caiPiaoNewTopBean.getH_g_p_name().indexOf("任四") != -1) {
                    if (dp[i].length() == 4) {
                        mlister.add(dp[i]);
                    }
                } else {
                }
            }
            if (inseck > 1) {
                if (mlister.size() != 0) {
                    for (int i = 0; i < mlister.size() - 1; i++) {
                        for (int j = i + 1; j < mlister.size(); j++) {
                            if (mlister.get(i).equals(mlister.get(j))) {
                                mlister.remove(j);
                                j--;
                            }
                        }
                    }
                    for (int k = 0; k < mlister.size(); k++) {
                        if (k == mlister.size() - 1) {
                            urls += mlister.get(k);
                        } else {
                            urls += mlister.get(k) + "|";
                        }
                    }
                    String url = wanString + qianString + baiString + shiString + geString + ":" + urls;
                    if (mlister.get(0).length() == 2) {
                        if (inseck == 2) {
                            jilu = 1;
                        } else if (inseck == 3) {
                            jilu = 3;
                        } else if (inseck == 4) {
                            jilu = 6;
                        } else if (inseck == 5) {
                            jilu = 10;
                        }
                    } else if (mlister.get(0).length() == 3) {
                        if (inseck == 3) {
                            jilu = 1;
                        } else if (inseck == 4) {
                            jilu = 4;
                        } else if (inseck == 5) {
                            jilu = 10;
                        }
                    } else if (mlister.get(0).length() == 4) {
                        if (inseck == 4) {
                            jilu = 1;
                        } else if (inseck == 5) {
                            jilu = 5;
                        }
                    }
                    ds_one_zhu.setText((urls.split("\\|").length) * jilu + "");
                    ds_num.setText(url);
                } else {
                    ds_one_zhu.setText(0 + "");
                    ds_one_money.setText(ds_one_zhu.getText().toString());
                    ds_num.setText("");
                }
                initDmoney();
            } else {
                ds_one_zhu.setText(0 + "");
                ds_one_money.setText(ds_one_zhu.getText().toString());
                ds_num.setText("");
            }
        } else {
            String ydurl = "";
            String str = ds_remark.getText().toString();
            String[] strings = str.split("；");
            String[] str1 = strings[0].split("：");
            String string = str1[1];
            if (string.indexOf("*") != -1) {
                string = string.replace("*", "");
            }
            List<String> mlistD = new ArrayList<>();
            for (int i = 0; i < dp.length; i++) {
                if (dp[i].length() == string.length()) {
                    mlistD.add(dp[i]);
                }
            }
            if (mlistD.size() != 0) {
                for (int i = 0; i < mlistD.size() - 1; i++) {
                    for (int j = i + 1; j < mlistD.size(); j++) {
                        if (mlistD.get(i).equals(mlistD.get(j))) {
                            mlistD.remove(j);
                            j--;
                        }
                    }
                }
            }
            if (mlistD.size() != 0) {
                for (int k = 0; k < mlistD.size(); k++) {
                    if (k == mlistD.size() - 1) {
                        ydurl += mlistD.get(k);
                    } else {
                        ydurl += mlistD.get(k) + "|";
                    }
                }
                ds_one_zhu.setText((ydurl.split("\\|").length) + "");
                ds_num.setText(ydurl);
                initDmoney();
            }else {
                ds_one_zhu.setText(0 + "");
                ds_one_money.setText(ds_one_zhu.getText().toString());
                ds_num.setText("");
            }
            getIssuesUrl(caiPiaoNewTopBean.getH_g_p_nid());
        }
    }

    /**
     * 单式初始化钱数
     */
    public void initDmoney() {

        if (userInfo.getCorner().equals("元")) {
            DecimalFormat df = new DecimalFormat("0.00000");// 格式化小数
            ds_one_money.setText(df.format(Double.parseDouble(ds_code_txt.getText().toString()) * Double.parseDouble(ds_one_zhu.getText().toString())));
        } else if (userInfo.getCorner().equals("角")) {
            DecimalFormat df = new DecimalFormat("0.00000");// 格式化小数
            ds_one_money.setText(df.format(Double.parseDouble(ds_code_txt.getText().toString()) * Double.parseDouble(ds_one_zhu.getText().toString()) / 10));

        } else if (userInfo.getCorner().equals("分")) {
            DecimalFormat df = new DecimalFormat("0.00000");// 格式化小数
            ds_one_money.setText(df.format(Double.parseDouble(ds_code_txt.getText().toString()) * Double.parseDouble(ds_one_zhu.getText().toString()) / 100));

        } else {
            DecimalFormat df = new DecimalFormat("0.000000");// 格式化小数
            ds_one_money.setText(df.format(Double.parseDouble(ds_code_txt.getText().toString()) * Double.parseDouble(ds_one_zhu.getText().toString()) / 1000));

        }
    }

    /**
     * 单式进度条监听
     */
    private SeekBar.OnSeekBarChangeListener dchange = new SeekBar.OnSeekBarChangeListener() {
        //当进度条停止的时候触发
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            db = 1;
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // 当进度条停止修改的时候触发
            db = 1;
            dcount = ds11;
            userInfo.setU_RebateB(ds_zero.getText().toString());
        }

        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (db == 0) {
                /**
                 * 第一次进入算当前奖金
                 */
                double num = (float) Double
                        .parseDouble(userInfo.getU_RebateB());
                DecimalFormat df11 = new DecimalFormat("#0.0000");// 格式化小数
                double du = Utils.sub(
                        Double.parseDouble(userInfo.getU_RebateA()), num);
                double dp = Utils.mul(du, amdString);
                double db = Utils.add(dp, dcc) * dz;
                ds11 = df11.format((float) db);// 返回的是String类型
                dcount = ds11;
                ds_today_money.setText(ds11);
                db = 2;
            }
            if (db == 1) {
                float num = (float) Double.parseDouble(userInfo.getU_RebateA())
                        - (float) progress / 10;
                float inum = (float) Double
                        .parseDouble(userInfo.getU_RebateA()) - num;
                DecimalFormat df = new DecimalFormat("0.00");// 格式化小数
                String s = df.format(num);// 返回的是String类型
                ds_zero.setText(s);
                DecimalFormat df1 = new DecimalFormat("0");// 格式化小数
                String s1 = df1.format(1700 + inum * 10 * 2);// 返回的是String类型
                ds_seventen.setText(s1);
                DecimalFormat df11 = new DecimalFormat("0.0000");// 格式化小数
                float gp;
                if (dz == 1) {
                    double du = Utils.sub(
                            Double.parseDouble(userInfo.getU_RebateA()), num);
                    double dp = Utils.mul(du, amdString);
                    double db = Utils.add(dp, dcc);
                    gp = (float) db;
                } else {
                    double du = Utils.sub(
                            Double.parseDouble(userInfo.getU_RebateA()), num);
                    double dp = Utils.mul(du, amdString);
                    double db = Utils.add(dp, dcc) * dz;
                    gp = (float) db;
                }
                ds11 = df11.format(gp);// 返回的是String类型
                dcount = ds11;
                ds_today_money.setText(ds11);
            }
        }
    };
    /**
     * 单式倍数监听
     */
    private TextWatcher mdTextWatcher = new TextWatcher() {
        //   private CharSequence temp;

        public void afterTextChanged(Editable s) {
            if (!"".equals(s.toString())) {// 输入框不为空

                if (!ds_one_zhu.getText().toString().equals("0")) {
                    if (!s.toString().equals(String.valueOf(z))) {//倍数不等于注数
                        DecimalFormat df = new DecimalFormat("0.0000");// 格式化小数
//                        DecimalFormat df = new DecimalFormat("#0.####");// 格式化小数
                        int tz = Integer.valueOf(s.toString());
                        String s1 = df.format((float) Double.parseDouble(dcount) / dz
                                * tz);// 返回的是String类型
                        ds_today_money.setText(s1);
                        dcount = s1;
                        dz = tz;
                    }
                    DecimalFormat dff = new DecimalFormat("0.0000");
                    if (userInfo.getCorner().equals("元")) {
                        ds_one_money.setText(dff.format(Double.parseDouble(ds_code_txt.getText().toString()) * Double.parseDouble(ds_one_zhu.getText().toString())));
                    } else if (userInfo.getCorner().equals("角")) {
                        ds_one_money.setText(dff.format(Double.parseDouble(ds_code_txt.getText().toString()) * Double.parseDouble(ds_one_zhu.getText().toString()) / 10));
                    } else if (userInfo.getCorner().equals("分")) {
                        ds_one_money.setText(dff.format(Double.parseDouble(ds_code_txt.getText().toString()) * Double.parseDouble(ds_one_zhu.getText().toString()) / 100));
                    } else if (userInfo.getCorner().equals("厘")) {
                        ds_one_money.setText(dff.format(Double.parseDouble(ds_code_txt.getText().toString()) * Double.parseDouble(ds_one_zhu.getText().toString()) / 1000));
                    }
                } else {
                    ds_one_money.setText("0.0000");
                }
                //ds_code_txt.setText(s.toString());
            } else {// 输入框内容为空
                ds_one_money.setText("0.0000");
            }
        }

        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // temp = s;
        }

        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            if (s.toString().startsWith("0")
                    || s.toString().trim().length() == 0) {
                dz = 1;
                double dnum = (float) Double.parseDouble(userInfo.getU_RebateB());
                DecimalFormat df11 = new DecimalFormat("#0.0000");// 格式化小数
                double ddu = Utils.sub(Double.parseDouble(userInfo.getU_RebateA()), dnum);
                double ddp = Utils.mul(ddu, amdString);
                double ddb = Utils.add(ddp, dcc) * dz;
                ds11 = df11.format((float) ddb);// 返回的是String类型
                dcount = ds11;
                ds_today_money.setText("0.0000");

            }

        }

    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ds_del:
                //      ds_code_txt.getText().clear();
                ds_et.setText("");
                ds_today_money.setText(ds11);
                initDData();
                break;
            case R.id.ds_buy_car:
                caipiaoBeanList = caipiaoDao.findgamenewAll();
                if (!caipiaoBeanList.isEmpty()) {
                    for (int i = 0; i < caipiaoBeanList.size(); i++) {
                        if (caipiaoBeanList.get(i).getHx_name().equals(title)) {
                            Intent intent1 = new Intent(getActivity(), BuyCar.class);
                            Bundle bundle = new Bundle();
                            //  bundle.putSerializable("model", gtit1);传过去期号
                            intent1.putExtras(bundle);
                            intent1.putExtra("dtitle", title);
                            intent1.putExtra("beishu", ds_code_txt.getText().toString());
                            intent1.putExtra("gameId", caipiaoBeanList.get(i).getNameId());
                            intent1.putExtra("gameIssue", dqIssuseBean.getData().getLast_issue());
                            startActivity(intent1);
                            ds_et.setText("");
                            ds_today_money.setText(ds11);
                            initDData();
                            break;
                        } else {
                            Toast.makeText(getActivity(), "当前购物车为空", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(getActivity(), "当前购物车为空", Toast.LENGTH_SHORT).show();
                }
                break;
            //单式添加号码
            case R.id.ds_compound_btn:
                // 点击查查的时候获取点击的条目的时间信息,然后根据时间信息删除
                if (ds_one_zhu.getText().equals("0")) {
                    Toast.makeText(getActivity(), "请正确选择号码", Toast.LENGTH_SHORT).show();
                } else {
                    showUpdataTime();
                    // String a=
                    boolean result = caipiaoDao.addnewnumber(caiPiaoNewTopBean.getH_g_p_nid(), ds_zero.getText().toString(), ds_today_money.getText().toString(), title, caiPiaoNewTopBean.getH_g_p_name(), ds_num.getText().toString(),
                            ds_one_zhu.getText().toString(), ds_code_txt.getText().toString(), userInfo.getCorner(),
                            ds_one_money.getText().toString(), caiPiaoNewTopBean.getH_g_p_id(), caiPiaoNewTopBean.getH_g_p_one_amount(), caiPiaoNewTopBean.getH_g_p_tid(), "0", dtime + "");
                    ds_et.setText("");
                    ds_today_money.setText(ds11);
                    initDData();
                    if (result) {

                    } else {
                        Toast.makeText(getActivity(), "添加失败", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.ds_ib_yuan:
                String monedString = dcount;
                if (!ds_code_txt.getText().toString().equals("")) {
                    ds_ib_yuan.setBackgroundResource(R.drawable.yuanzhong);
                    ds_ib_jiao.setBackgroundResource(R.drawable.jiano);
                    ds_ib_fen.setBackgroundResource(R.drawable.fenno);
                    ds_ib_li.setBackgroundResource(R.drawable.lino);
                    if (userInfo.getCorner().equals("元")) {//获取单位也是元
                        monedString = dcount;
                        DecimalFormat df = new DecimalFormat("0.0000");
                        ds_one_money.setText(df.format(Double.parseDouble(ds_code_txt.getText().toString()) * Double.parseDouble(ds_one_zhu.getText().toString())));
                    } else if (userInfo.getCorner().equals("角")) {//获取的角
                        DecimalFormat df = new DecimalFormat(
                                "0.0000");// 格式化小数
                        String s = df.format((float) Double
                                .parseDouble(dcount) * 10);// 返回的是String类型
                        monedString = s;
                        amdString = amdString * 10;
                        dcc = dcc * 10;
                        ds_one_money.setText(df.format(Double.parseDouble(ds_code_txt.getText().toString()) * Double.parseDouble(ds_one_zhu.getText().toString())));

                    } else if (userInfo.getCorner().equals("分")) {
                        DecimalFormat df = new DecimalFormat(
                                "0.0000");// 格式化小数
                        String s = df.format((float) Double
                                .parseDouble(dcount) * 100);// 返回的是String类型
                        monedString = s;
                        amdString = amdString * 100;
                        dcc = dcc * 100;
                        ds_one_money.setText(df.format((float) Double
                                .parseDouble(Integer.valueOf(ds_code_txt.getText().toString()) * Integer.valueOf(ds_one_zhu.getText().toString()) + "")));

                    } else {
                        DecimalFormat df = new DecimalFormat(
                                "0.0000");// 格式化小数
                        String s = df.format((float) Double
                                .parseDouble(dcount) * 1000);// 返回的是String类型
                        monedString = s;
                        amdString = amdString * 1000;
                        dcc = dcc * 1000;
                        ds_one_money.setText(df.format(Double.parseDouble(ds_code_txt.getText().toString()) * Double.parseDouble(ds_one_zhu.getText().toString())));
                    }
                    ds_today_money.setText(monedString);
                    userInfo.setCorner("元");
                    ds_conner.setText("元");
                    dcount = monedString;
                } else {
                    Toast.makeText(getActivity(), "请选择倍数", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ds_ib_jiao:
                if (!ds_code_txt.getText().toString().equals("")) {
                    ds_ib_yuan.setBackgroundResource(R.drawable.yuanno);
                    ds_ib_jiao.setBackgroundResource(R.drawable.jiazhong);
                    ds_ib_fen.setBackgroundResource(R.drawable.fenno);
                    ds_ib_li.setBackgroundResource(R.drawable.lino);
                    monedString = dcount;
                    if (userInfo.getCorner().equals("元")) {
                        DecimalFormat df = new DecimalFormat(
                                "0.0000");// 格式化小数
                        String s = df.format((float) Double
                                .parseDouble(dcount) / 10);// 返回的是String类型
                        monedString = s;
                        amdString = amdString / 10;
                        dcc = dcc / 10;
                        ds_one_money.setText(df.format(Double.parseDouble(ds_code_txt.getText().toString()) * Double.parseDouble(ds_one_zhu.getText().toString()) / 10));

                    } else if (userInfo.getCorner().equals("角")) {
                        monedString = dcount;
                        DecimalFormat df = new DecimalFormat("0.0000");
                        ds_one_money.setText(df.format(Double.parseDouble(ds_code_txt.getText().toString()) * Double.parseDouble(ds_one_zhu.getText().toString()) / 10));
                    } else if (userInfo.getCorner().equals("分")) {
                        DecimalFormat df = new DecimalFormat(
                                "0.0000");// 格式化小数
                        String s = df.format((float) Double
                                .parseDouble(dcount) * 10);// 返回的是String类型
                        monedString = s;
                        amdString = amdString * 10;
                        dcc = dcc * 10;
                        ds_one_money.setText(df.format(Double.parseDouble(ds_code_txt.getText().toString()) * Double.parseDouble(ds_one_zhu.getText().toString()) / 10));
                    } else {
                        DecimalFormat df = new DecimalFormat(
                                "0.0000");// 格式化小数
                        String s = df.format((float) Double
                                .parseDouble(dcount) * 100);// 返回的是String类型
                        monedString = s;
                        amdString = amdString * 100;
                        dcc = dcc * 100;
                        ds_one_money.setText(df.format(Double.parseDouble(ds_code_txt.getText().toString()) * Double.parseDouble(ds_one_zhu.getText().toString()) / 10));
                    }
                    ds_today_money.setText(monedString);
                    userInfo.setCorner("角");
                    ds_conner.setText("角");
                    dcount = monedString;
                } else {
                    Toast.makeText(getActivity(), "请选择倍数", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ds_ib_fen:

                if (!ds_code_txt.getText().toString().equals("")) {
                    ds_ib_yuan.setBackgroundResource(R.drawable.yuanno);
                    ds_ib_jiao.setBackgroundResource(R.drawable.jiano);
                    ds_ib_fen.setBackgroundResource(R.drawable.fenzhong);
                    ds_ib_li.setBackgroundResource(R.drawable.lino);
                    monedString = dcount;
                    if (userInfo.getCorner().equals("元")) {
                        DecimalFormat df = new DecimalFormat(
                                "0.0000");// 格式化小数
                        String s = df.format((float) Double
                                .parseDouble(dcount) / 100);// 返回的是String类型
                        monedString = s;
                        amdString = amdString / 100;
                        dcc = dcc / 100;
                        ds_one_money.setText(df.format(Double.parseDouble(ds_code_txt.getText().toString()) * Double.parseDouble(ds_one_zhu.getText().toString()) / 100));
                    } else if (userInfo.getCorner().equals("角")) {
                        DecimalFormat df = new DecimalFormat(
                                "0.0000");// 格式化小数
                        String s = df.format((float) Double
                                .parseDouble(dcount) / 10);// 返回的是String类型
                        monedString = s;
                        amdString = amdString / 10;
                        dcc = dcc / 10;
                        ds_one_money.setText(df.format(Double.parseDouble(ds_code_txt.getText().toString()) * Double.parseDouble(ds_one_zhu.getText().toString()) / 100));
                    } else if (userInfo.getCorner().equals("分")) {
                        monedString = dcount;
                        DecimalFormat df = new DecimalFormat(
                                "0.0000");// 格式化小数
                        ds_one_money.setText(df.format(Double.parseDouble(ds_code_txt.getText().toString()) * Double.parseDouble(ds_one_zhu.getText().toString()) / 100));
                    } else {
                        DecimalFormat df = new DecimalFormat(
                                "0.0000");// 格式化小数
                        String s = df.format((float) Double
                                .parseDouble(dcount) * 10);// 返回的是String类型
                        monedString = s;
                        amdString = amdString * 10;
                        dcc = dcc * 10;
                        ds_one_money.setText(df.format(Double.parseDouble(ds_code_txt.getText().toString()) * Double.parseDouble(ds_one_zhu.getText().toString()) / 100));
                    }
                    ds_today_money.setText(monedString);
                    userInfo.setCorner("分");
                    ds_conner.setText("分");
                    dcount = monedString;
                } else {
                    Toast.makeText(getActivity(), "请选择倍数", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ds_ib_li:

                if (!ds_code_txt.getText().toString().equals("")) {
                    ds_ib_yuan.setBackgroundResource(R.drawable.yuanno);
                    ds_ib_jiao.setBackgroundResource(R.drawable.jiano);
                    ds_ib_fen.setBackgroundResource(R.drawable.fenno);
                    ds_ib_li.setBackgroundResource(R.drawable.lizhong);
                    monedString = dcount;
                    if (userInfo.getCorner().equals("元")) {
                        DecimalFormat df = new DecimalFormat(
                                "0.0000");// 格式化小数
                        String s = df.format((float) Double
                                .parseDouble(dcount) / 1000);// 返回的是String类型
                        monedString = s;
                        amdString = amdString / 1000;
                        dcc = dcc / 1000;
                        ds_one_money.setText(df.format(Double.parseDouble(ds_code_txt.getText().toString()) * Double.parseDouble(ds_one_zhu.getText().toString()) / 1000));

                    } else if (userInfo.getCorner().equals("角")) {
                        DecimalFormat df = new DecimalFormat(
                                "0.0000");// 格式化小数
                        String s = df.format((float) Double
                                .parseDouble(dcount) / 100);// 返回的是String类型
                        monedString = s;
                        amdString = amdString / 100;
                        dcc = dcc / 100;
                        ds_one_money.setText(df.format(Double.parseDouble(ds_code_txt.getText().toString()) * Double.parseDouble(ds_one_zhu.getText().toString()) / 1000));
                    } else if (userInfo.getCorner().equals("分")) {
                        DecimalFormat df = new DecimalFormat(
                                "0.0000");// 格式化小数
                        String s = df.format((float) Double
                                .parseDouble(dcount) / 10);// 返回的是String类型
                        monedString = s;
                        amdString = amdString / 10;
                        dcc = dcc / 10;
                        ds_one_money.setText(df.format(Double.parseDouble(ds_code_txt.getText().toString()) * Double.parseDouble(ds_one_zhu.getText().toString()) / 1000));
                    } else {
                        monedString = dcount;
                        DecimalFormat df = new DecimalFormat(
                                "0.0000");// 格式化小数
                        ds_one_money.setText(df.format(Double.parseDouble(ds_code_txt.getText().toString()) * Double.parseDouble(ds_one_zhu.getText().toString()) / 1000));
                    }
                    ds_today_money.setText(monedString);
                    userInfo.setCorner("厘");
                    ds_conner.setText("厘");
                    dcount = monedString;
                } else {
                    Toast.makeText(getActivity(), "请选择倍数", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.ds_jia:
                if (Integer.parseInt(ds_one_zhu.getText().toString()) != 0) {
                    DecimalFormat df = new DecimalFormat("0.0000");
                    if (ds_code_txt.length() == 0) {
                        ds_code_txt.setText(dz + "");
                        initDMultiple();
                        double dnum = (float) Double.parseDouble(userInfo.getU_RebateB());
                        DecimalFormat df11 = new DecimalFormat("#0.0000");// 格式化小数
                        double ddu = Utils.sub(Double.parseDouble(userInfo.getU_RebateA()), dnum);
                        double ddp = Utils.mul(ddu, amdString);
                        double ddb = Utils.add(ddp, dcc) * dz;
                        ds11 = df11.format((float) ddb);// 返回的是String类型
                        dcount = ds11;
                        ds_today_money.setText(ds11);

                    } else {
                        dz = dz + 1;
                        ds_code_txt.setText(dz + "");
                        initDMultiple();
                        String ds = df.format((float) Double.parseDouble(dcount) / (dz - 1) * dz);
                        ds_today_money.setText(ds);
                        dcount = ds;
                    }
                } else {
                    Toast.makeText(getActivity(), "投注注数最低一注,请选择注数", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ds_jian:
                if (dz == 1) {
                    ds_code_txt.setText(dz + "");
                    initDMultiple();
                    ds_today_money.setText(dcount);
                } else {
                    dz = dz - 1;
                    ds_code_txt.setText(dz + "");
                    initDMultiple();
                    DecimalFormat df = new DecimalFormat("0.0000");
                    String ds6 = df.format((float) Double.parseDouble(dcount) / (dz + 1) * dz);
                    dcount = ds6;
                    ds_today_money.setText(ds6);
                }
                break;
        }
    }

    /**
     * 请当前期号接口
     *
     * @param h_g_p_nid
     */
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

    /**
     * 单式加减
     */
    private void initDMultiple() {
        if (userInfo.getCorner().equals("元")) {
            DecimalFormat df = new DecimalFormat("0.0000");
            ds_one_money.setText(df.format(Double.parseDouble(ds_one_zhu.getText().toString()) * Double.parseDouble(ds_code_txt.getText().toString())));
        } else if (userInfo.getCorner().equals("角")) {
            DecimalFormat df = new DecimalFormat("0.0000");
            ds_one_money.setText(df.format(Double.parseDouble(ds_one_zhu.getText().toString()) * Double.parseDouble(ds_code_txt.getText().toString()) / 10));
        } else if (userInfo.getCorner().equals("分")) {
            DecimalFormat df = new DecimalFormat("0.0000");
            ds_one_money.setText(df.format(Double.parseDouble(ds_one_zhu.getText().toString()) * Double.parseDouble(ds_code_txt.getText().toString()) / 100));
        } else {
            DecimalFormat df = new DecimalFormat("0.0000");
            ds_one_money.setText(df.format(Double.parseDouble(ds_one_zhu.getText().toString()) * Double.parseDouble(ds_code_txt.getText().toString()) / 1000));
        }
    }

    /**
     * 获取毫秒值
     */
    public void showUpdataTime() {
        Date date = new Date();
        dtime = date.getTime();

    }

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
                    break;
            }
        }
    };
}
