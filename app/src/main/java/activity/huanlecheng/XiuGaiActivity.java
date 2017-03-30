package activity.huanlecheng;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import bean.MibaoWentiBean;
import bean.XGmiBaoBean;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import http.AjaxParams;
import util.Utils;

/**
 * Created by Administrator on 2016/12/20.
 */
public class XiuGaiActivity extends BaseActivity{

    private Button title_left_btn;
    private TextView title_textview;
    private TextView et_pw_num;
    private EditText et_pw_da;
    private EditText et_pw;
    private Spinner spinner;
    private EditText et_mm;
    private String wenti;
    private Button bt_set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiugai);
        wenti = getIntent().getStringExtra("wenti");
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
        title_textview.setText("修改密保");
    }

    private void initView() {
        et_pw_num = (TextView) findViewById(R.id.et_pw_num);//原问题
        //原问题答案
        et_pw_da = (EditText) findViewById(R.id.et_pw_da);
        et_pw = (EditText) findViewById(R.id.et_pw);//资金密码
        spinner = (Spinner) findViewById(R.id.spin);//现在的问题
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv=(TextView)view;
                tv.setTextColor(XiuGaiActivity.this.getResources().getColor(R.color.blue));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        et_mm = (EditText) findViewById(R.id.et_mm);//现在问题答案
        bt_set = (Button) findViewById(R.id.bt_set);//确定
        et_pw.setTextColor(Color.parseColor(String.valueOf("#ffffff")));
        et_mm.setTextColor(Color.parseColor(String.valueOf("#ffffff")));
        et_pw_da.setTextColor(Color.parseColor(String.valueOf("#ffffff")));
    //    et_pw_num.setText(wenti);
        getwentiUrl();
        bt_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingWindow.showDialog(Constants.tjing);
                initUrl();
            }
        });
    }

    private void initUrl() {
        if (Utils.isEmpty(et_pw_da.getText().toString())){
            loadingWindow.cancel();
            showToast("请输入问题答案");
        }else if (Utils.isEmpty(et_pw.getText().toString())){
            loadingWindow.cancel();

            showToast("请输入资金密码");
        }else if (spinner.getSelectedItem().toString().equals("请选择问题")){
            loadingWindow.cancel();
            showToast("请选择密保问题");
        }else if (Utils.isEmpty(et_mm.getText().toString())){
            loadingWindow.cancel();

            showToast("请输入问题答案");
        }else {
            getUrl(wenti,et_pw_da.getText().toString(),spinner.getSelectedItemPosition()+"",et_mm.getText().toString(),et_pw.getText().toString());
        }
    }

    /**
     * 设置密保网络请求
     */
    public void getUrl(String wenti, String et_daan, String spinner, String et_mm, String et_pw) {
  /*      U_SecurityQuestion_1=原问题
        U_SecurityAnswer_1=原答案
        U_SecurityQuestion2=新问题
        U_SecurityAnswer2=新答案
        U_BankPassWord=取款密码*/
   /*     String a= URLEncoder.encode(wenti);
        String b= URLEncoder.encode(et_daan);
        String c= URLEncoder.encode(spinner);
        String d= URLEncoder.encode(et_mm);
        String e= URLEncoder.encode(et_pw);
        String url="qid_old="+a+"&answer_old="+b+"&qid_new="+c+"&answer_new="+d+"&pass="+e;
        String f= URLEncoder.encode(url);*/
        AjaxParams params=new AjaxParams();
        params.put("qid_old",wenti);
        params.put("answer_old",et_daan);
        params.put("qid_new",spinner);
        params.put("answer_new",et_mm);
        params.put("pass",et_pw);
        wh.configCookieStore(RUser.cookieStore);
        wh.post(Constants.getUrl()+Constants.xiugsafe,params, new AjaxCallBack<String>() {
       // wh.post(Constants.getUrl(),params, new AjaxCallBack<String>() {
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
                Message msg=new Message();
                msg.what=0;
                msg.obj=s;
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                loadingWindow.cancel();
            }
        });
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    loadingWindow.cancel();
                    String str= (String) msg.obj;
                   // 123q1213w
                    java.lang.reflect.Type type=new TypeToken<XGmiBaoBean>(){}.getType();
                    XGmiBaoBean xGmiBaoBean=gson.fromJson(str,type);
                if (str.indexOf("修改成功")!=-1){
                    showToast(xGmiBaoBean.getdata());
                }else {
                    showToast(xGmiBaoBean.getError());
                }

                    //finish();
                    break;
                case 1:
                    String string= (String) msg.obj;
                    java.lang.reflect.Type type1=new TypeToken<MibaoWentiBean>(){}.getType();
                    MibaoWentiBean mibaoWentiBean=gson.fromJson(string,type1);
                    if (!mibaoWentiBean.getData().equals("")){
                        for (int i=0;i<mibaoWentiBean.getData().size();i++){
                            if (mibaoWentiBean.getData().get(i).getId().equals(wenti)){
                                et_pw_num.setText(mibaoWentiBean.getData().get(i).getName());
                                break;
                            }
                        }
                    }
                    break;
            }
        }
    };

    public void getwentiUrl() {
        wh.configCookieStore(RUser.cookieStore);
        wh.get(Constants.getUrl() + "/static/model/public/json/security_question.json?", new AjaxCallBack<String>() {
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
                Message msg=new Message();
                msg.what=1;
                msg.obj=s;
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }
}
