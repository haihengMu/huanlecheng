package activity.huanlecheng;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.BuyCarAdapter;
import bean.AddBuyResponse;
import bean.CaipiaoBean;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import http.AjaxParams;
import util.CaipiaoDao;
import util.ShowToast;
import view.XListView;

/**
 * 购物车页
 * 还有一个按钮删除根据id删除数据库中的某个数据,还没写别忘了
 * Created by Administrator on 2016/11/4.
 */

public class BuyCar extends BaseActivity implements XListView.IXListViewListener, View.OnClickListener {


    private XListView xlistview;
    private Button title_left_btn;
    private TextView title_textview;
    private ImageView iv_del;
    private CaipiaoDao caipiaoDao;
    private ImageView iv_zhuihao;
    private List<CaipiaoBean> caipiaolist;
    private BuyCarAdapter buyCarAdapter;
    private ImageView y_touzhu;
    private String ds_titile;
    private List<CaipiaoBean> dTitleList;
    private List<CaipiaoBean> mlist;
    private TextView tv_money;
    private String model;
    private String beishu;
    private String money;
    private List<String> moneylist;
    private String s_title;
    private LinearLayout view_loading;
    private LinearLayout view_load_fail;
    private TextView txt_neterr;
    private List<CaipiaoBean> newlist;
    private String gameId;
    private String gt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_car);
        tv_money = (TextView) findViewById(R.id.tv_money);
        initHead();
        initView();
    }

    private void initHead() {
        title_left_btn = (Button) findViewById(R.id.title_left_btn);
        title_left_btn.setBackgroundResource(R.drawable.aar);
        title_left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title_textview = (TextView) findViewById(R.id.title_textview);
        title_textview.setText("确认购买");
        view_loading = (LinearLayout) findViewById(R.id.view_loading);
        view_load_fail = (LinearLayout) findViewById(R.id.view_load_fail);
        txt_neterr = (TextView) findViewById(R.id.txt_neterr);
    }

    private void initView() {
        ds_titile = getIntent().getStringExtra("dtitle");
        gameId = getIntent().getStringExtra("gameId");
        gt = getIntent().getStringExtra(
                "model");
        xlistview = (XListView) findViewById(R.id.xlistview);
        iv_zhuihao = (ImageView) findViewById(R.id.iv_zhuihao);
        y_touzhu = (ImageView) findViewById(R.id.y_touzhu);
        y_touzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Touzhu(gameId, gt);
            }
        });
        iv_zhuihao.setOnClickListener(this);
        iv_del = (ImageView) findViewById(R.id.iv_del);
        iv_del.setOnClickListener(this);
        buyCarAdapter = new BuyCarAdapter(getApplication());
        xlistview.setAdapter(buyCarAdapter);
        xlistview.setPullLoadEnable(false);
        xlistview.setPullRefreshEnable(false);
        caipiaoDao = new CaipiaoDao(getBaseContext());
        initSQDao();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action.delete");
        //接的成功页的广播
     /*   intentFilter.addAction("action.deleteall");
        s_title = getIntent().getStringExtra("s_title");*/
        registerReceiver(bdr, intentFilter);

    }

    /**
     * 将集合中的数据全部修改存到数据库
     */
    private void initSQDao() {
        dTitleList = caipiaoDao.findName(ds_titile);
        // String mode = userInfo.getCorner();
        for (int i = 0; i < dTitleList.size(); i++) {
            if (dTitleList.size() - 1 == i) {
                //拿到最后一个存入数据的模式和倍数
                model = dTitleList.get(i).getMode();
                beishu = dTitleList.get(i).getMultiple();
            }
        }
        caipiaoDao.updateMode(model, ds_titile);
        caipiaoDao.updatebei(beishu, ds_titile);
        newlist = caipiaoDao.findName(ds_titile);
        moneylist = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("0.0000");
        for (int j = 0; j < newlist.size(); j++) {
            if (model.equals("元")) {
                money = df.format(Double.parseDouble(newlist.get(j).getCode_text()) * Double.parseDouble(newlist.get(j).getMultiple()));
            } else if (model.equals("角")) {
                money = df.format(Double.parseDouble(newlist.get(j).getCode_text()) * Double.parseDouble(newlist.get(j).getMultiple()) / 10);
            } else if (model.equals("分")) {
                money = df.format(Double.parseDouble(newlist.get(j).getCode_text()) * Double.parseDouble(newlist.get(j).getMultiple()) / 100);
            } else if (model.equals("厘")) {
                money = df.format(Double.parseDouble(newlist.get(j).getCode_text()) * Double.parseDouble(newlist.get(j).getMultiple()) / 1000);
            }
            moneylist.add(money);
        }
        buyCarAdapter.setData(newlist, moneylist);
    }

    BroadcastReceiver bdr = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("action.delete")) {
                initSQDao();
            }
        }
    };

    public void Touzhu(String gameId, String gt) {
       Map<String,String> mmap;
        AjaxParams params = new AjaxParams();
        params.put("gid", gameId);
        params.put("issue", gt);
        params.put("rebate", "12.60");
        for (int i = 0; i < newlist.size(); i++) {
            mmap=new HashMap<>();
            mmap.put("pid",newlist.get(i).getTipId());
            mmap.put("code",newlist.get(i).getNumber());
            mmap.put("multiple",newlist.get(i).getMultiple());
            mmap.put("bet_num",newlist.get(i).getCode_text());
            if (newlist.get(i).getMode().equals("元")) {
                mmap.put("amount_mode",newlist.get(i).getTipId());
            } else if (newlist.get(i).getMode().equals("角")) {
                mmap.put("amount_mode",newlist.get(i).getTipId());
            } else if (newlist.get(i).getMode().equals("分")) {
                mmap.put("amount_mode",newlist.get(i).getTipId());
            } else {
                mmap.put("amount_mode",newlist.get(i).getTipId());
            }
        }
      //  params.put("codes", mmap);
        wh.configCookieStore(RUser.cookieStore);
        wh.post(Constants.getUrl() + Constants.touzhu, params, new AjaxCallBack<String>() {
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
                handle.sendMessage(msg);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_del:
                List<CaipiaoBean> mlist2 = caipiaoDao.findgamenewAll();
                for (int i = 0; i < mlist2.size(); i++) {
                    if (mlist2.get(i).getHx_name().equals(ds_titile)) {
                        caipiaoDao.delByTitle(mlist2.get(i).getHx_name());
                    }
                }
                xlistview.setAdapter(null);
                Toast.makeText(getApplicationContext(), "清除成功", Toast.LENGTH_SHORT).show();
                break;
       /*     case R.id.iv_zhuihao:
                *//**4
             * 需要在数据库中存nameid
             *id+投注号+注数
             * 百分数
             * 钱数
             * 当前总奖金
             * 倍数
             * 总注数
             * 0单式1复试
             *//*

                String url = "";
                String dnumber = "";
                String tipid = "";
                double dnum = 0;
                String dcode_txt = "";
                String fnumber = "";
                String fcode_txt = "";
                String ftipid = "";
                double z_money = 0;
                String dnameID = "";
                String dzero = "";
                String mount = "";
                int code_txt = 0;
                String today = "";
                int dcode=0;
                caipiaolist = caipiaoDao.findAll();
                mlist = new ArrayList<>();
                for (int i = 0; i < caipiaolist.size(); i++) {
                    if (caipiaolist.get(i).getHx_name().equals(ds_titile)) {
                        mlist.add(caipiaolist.get(i));
                    }
                }
                if (mlist.size() == 0) {
                    // Toast.makeText(getApplicationContext(), "0++", Toast.LENGTH_SHORT).show();
                } else {
                    for (int i = 0; i < mlist.size(); i++) {
                        if (model.equals("元")) {
                            z_money = Double.parseDouble(mlist.get(i).getCode_text()) * Double.parseDouble(mlist.get(i).getMultiple());
                        } else if (model.equals("角")) {
                            z_money = Double.parseDouble(mlist.get(i).getCode_text()) * Double.parseDouble(mlist.get(i).getMultiple()) / 10;
                        } else if (model.equals("分")) {
                            z_money = Double.parseDouble(mlist.get(i).getCode_text()) * Double.parseDouble(mlist.get(i).getMultiple()) / 100;
                        } else if (model.equals("厘")) {
                            z_money = Double.parseDouble(mlist.get(i).getCode_text()) * Double.parseDouble(mlist.get(i).getMultiple()) / 1000;
                        }
                        dnum += z_money;
                        dcode = Integer.valueOf(mlist.get(i).getCode_text());
                        code_txt +=dcode;
                        if (mlist.size() == 1) {//只有一个的时候
                            dnameID = mlist.get(i).getNameId();
                            dzero = mlist.get(i).getZero();
                            mount = mlist.get(i).getMount();
                            today = mlist.get(i).getToday_money();
                            if (mlist.get(i).getIdea().equals("0")) {//单式
                                dnumber = mlist.get(i).getNumber();
                                tipid = mlist.get(i).getTid();
                                dcode_txt = mlist.get(i).getCode_text();
                                url = tipid + "+" + dnumber + "+" + dcode_txt;
                            } else if (mlist.get(i).getIdea().equals("1")) {//复试
                                fnumber = mlist.get(i).getNumber();
                                ftipid = mlist.get(i).getTid();
                                fcode_txt = mlist.get(i).getCode_text();
                                url = ftipid + "+" + fnumber + "+" + fcode_txt;
                            }
                        } else {//购物车里有多个的时候
                            if (mlist.size() - 1 == i) {//最后一个
                                dnameID = mlist.get(i).getNameId();
                                dzero = mlist.get(i).getZero();//百分数
                                mount = mlist.get(i).getMount();
                                today = mlist.get(i).getToday_money();

                                if (mlist.get(i).getIdea().equals("0")) {//单式
                                    dnumber = mlist.get(i).getNumber();
                                    tipid = mlist.get(i).getTid();
                                    dcode_txt = mlist.get(i).getCode_text();
                                    url = url + tipid + "+" + dnumber + "+" + dcode_txt;
                                } else if (mlist.get(i).getIdea().equals("1")) {//复试
                                    fnumber = mlist.get(i).getNumber();
                                    ftipid = mlist.get(i).getTid();
                                    fcode_txt = mlist.get(i).getCode_text();
                                    url = url + ftipid + "+" + fnumber + "+" + fcode_txt;
                                }
                            } else {
                                if (mlist.get(i).getIdea().equals("0")) {//单式
                                    dnumber = mlist.get(i).getNumber();
                                    tipid = mlist.get(i).getTid();
                                    dcode_txt = mlist.get(i).getCode_text();
                                    url = url + tipid + "+" + dnumber + "+" + dcode_txt + "#";
                                } else if (mlist.get(i).getIdea().equals("1")) {//复试
                                    fnumber = mlist.get(i).getNumber();
                                    ftipid = mlist.get(i).getTid();
                                    fcode_txt = mlist.get(i).getCode_text();
                                    url = url + ftipid + "+" + fnumber + "+" + fcode_txt + "#";
                                }
                            }
                        }
                    }
                    Intent intent = new Intent(this, ChaseMainActivity.class);
                    intent.putExtra("nameid", dnameID);
                    intent.putExtra("url", url);
                    intent.putExtra("zero", dzero);
                    intent.putExtra("num", dnum + "");
                    intent.putExtra("mode",model);
                    intent.putExtra("qh", gt.getThen_Issue());//传期号
                    intent.putExtra("mount", mount);
                    intent.putExtra("zhucount", code_txt+"");//全部的注数
                    intent.putExtra("today", today);//当前奖金
                    intent.putExtra("bei", beishu);//倍数
                    intent.putExtra("title",ds_titile);//标题
                    startActivity(intent);
                    userInfo.setCorner(model);
                    finish();
                }*/
            //break;
          /*  case R.id.y_touzhu:
                url = "";
                dnum = 0;
                z_money = 0;
                dnameID = "";
                dzero = "";

                caipiaolist = caipiaoDao.findAll();
                mlist = new ArrayList<>();
                for (int i = 0; i < caipiaolist.size(); i++) {
                    if (caipiaolist.get(i).getHx_name().equals(ds_titile)) {
                        mlist.add(caipiaolist.get(i));
                    }
                }
                DecimalFormat df = new DecimalFormat("0.0000");
                if (mlist.size() == 0) {
                } else {
                    for (int i = 0; i < mlist.size(); i++) {
                        if (model.equals("元")) {
                            z_money = Double.parseDouble(mlist.get(i).getCode_text()) * Double.parseDouble(mlist.get(i).getMultiple());
                        } else if (model.equals("角")) {
                            z_money = Double.parseDouble(mlist.get(i).getCode_text()) * Double.parseDouble(mlist.get(i).getMultiple()) / 10;
                        } else if (model.equals("分")) {
                            z_money = Double.parseDouble(mlist.get(i).getCode_text()) * Double.parseDouble(mlist.get(i).getMultiple()) / 100;
                        } else if (model.equals("厘")) {
                            z_money = Double.parseDouble(mlist.get(i).getCode_text()) * Double.parseDouble(mlist.get(i).getMultiple()) / 1000;

                        }
                        dnum += z_money;
                        if (mlist.size() == 1) {//只有一个的时候
                            dnameID = mlist.get(i).getNameId();
                            dzero = mlist.get(i).getZero();
                            if (mlist.get(i).getIdea().equals("0")) {//单式
                                dnumber = mlist.get(i).getNumber();
                                tipid = mlist.get(i).getTid();
                                dcode_txt = mlist.get(i).getCode_text();
                                url = tipid + "+" + dnumber + "+" + dcode_txt;
                            } else if (mlist.get(i).getIdea().equals("1")) {//复试
                                fnumber = mlist.get(i).getNumber();
                                ftipid = mlist.get(i).getTid();
                                fcode_txt = mlist.get(i).getCode_text();
                                url = ftipid + "+" + fnumber + "+" + fcode_txt;
                            }
                        } else {//购物车里有多个的时候
                            if (mlist.size() - 1 == i) {//最后一个
                                dnameID = mlist.get(i).getNameId();
                                dzero = mlist.get(i).getZero();//百分数
                                if (mlist.get(i).getIdea().equals("0")) {//单式
                                    dnumber = mlist.get(i).getNumber();
                                    tipid = mlist.get(i).getTid();
                                    dcode_txt = mlist.get(i).getCode_text();
                                    url = url + tipid + "+" + dnumber + "+" + dcode_txt;
                                } else if (mlist.get(i).getIdea().equals("1")) {//复试
                                    fnumber = mlist.get(i).getNumber();
                                    ftipid = mlist.get(i).getTid();
                                    fcode_txt = mlist.get(i).getCode_text();
                                    url = url + ftipid + "+" + fnumber + "+" + fcode_txt;
                                }
                            } else {
                                if (mlist.get(i).getIdea().equals("0")) {//单式
                                    dnumber = mlist.get(i).getNumber();
                                    tipid = mlist.get(i).getTid();
                                    dcode_txt = mlist.get(i).getCode_text();
                                    url = url + tipid + "+" + dnumber + "+" + dcode_txt + "#";
                                } else if (mlist.get(i).getIdea().equals("1")) {//复试
                                    fnumber = mlist.get(i).getNumber();
                                    ftipid = mlist.get(i).getTid();
                                    fcode_txt = mlist.get(i).getCode_text();
                                    url = url + ftipid + "+" + fnumber + "+" + fcode_txt + "#";
                                }
                            }
                        }
                    }
                    if (gt.getThen_Issue() == null) {
                        Toast.makeText(getApplicationContext(), "以过期", Toast.LENGTH_SHORT).show();
                    } else {
                        if ((float) Double.parseDouble(userInfo.getU_Money()) < (float) Double.parseDouble(dnum + "")) {
                            showToast("金额不足，请充值");
                        } else {
                        String bsString = beishu;
                        DecimalFormat dd = new DecimalFormat("0.00000000");// 格式化小数
                        if (model.equals("元")) {
                            bsString = beishu;
                        } else if (model.equals("角")) {
                            bsString = dd.format((float) Double.parseDouble(bsString) / 10);
                        } else if (model.equals("分")) {
                            bsString = dd
                                    .format((float) Double.parseDouble(bsString) / 100);
                        } else {
                            bsString = dd
                                    .format((float) Double.parseDouble(bsString) / 1000);
                        }
                        loadingWindow.showDialog(Constants.tjing);
                        tv_money.setText(dnum + "");
                        requestUserinfo(dnameID, gt.getThen_Issue(), url, bsString, dzero, dnum + "");
                        userInfo.setCorner(model);
                           }
                    }
                }*/
        }
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
                    java.lang.reflect.Type type = new TypeToken<AddBuyResponse>() {
                    }.getType();
                    Gson gson = new Gson
                            ();
                    AddBuyResponse hp = gson.fromJson(str, type);
                    if (!hp.getError().equals("")) {
                        showToast(hp.getMsg());
                    } else {
                        intent = new Intent(BuyCar.this,
                                SuccessActivity.class);
                        intent.putExtra("money", tv_money.getText().toString());
                        intent.putExtra("id", "0");// 0投注1是追号记录
                        intent.putExtra("title", ds_titile);
                        startActivity(intent);
                        finish();
                    }
                    break;
                case 1:
                    String str1 = (String) msg.obj;
                    break;

                default:
                    break;
            }
        }
    };

    /**
     * 投注
     */
    public void requestUserinfo(String gameID, String qihao, String url,
                                String beishu, String fandian, String amout) {

        AjaxParams params = new AjaxParams();
        params.put("Model", "Game");
        params.put("Action", "AddBuy");
        params.put("Id", gameID);
        params.put("Issue", qihao);
        params.put("BuyCode", url);
        params.put("BuyChase", "");
        params.put("IsChaseCode", "0");
        params.put("Multiple", beishu);
        params.put("Rebate", fandian);
        params.put("TheTotalAmount", amout);
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
                loadingWindow.cancel();
                ShowToast.showMsg(BuyCar.this,
                        Constants.NETERROR);
            }

        });

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

}
