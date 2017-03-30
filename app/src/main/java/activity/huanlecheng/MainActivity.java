package activity.huanlecheng;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.Toast;

import constants.UserInfo;
import fragment.BuyFragment;
import fragment.HomeFragment;
import fragment.InfoFragment;
import fragment.MyFragment;
import view.NestRadioGroup;


public class MainActivity extends FragmentActivity implements
        NestRadioGroup.OnCheckedChangeListener {
    private static FragmentManager mFragmentManager;
    private static Fragment[] fragments = new Fragment[4];
    UserInfo userinfo;
    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;
    private static RadioButton home;
    private static RadioButton buy;
    private static RadioButton info;
    private static RadioButton center;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyApplication.activityList.add(this);
        userinfo = new UserInfo(getApplicationContext());
        NestRadioGroup bottom = (NestRadioGroup) findViewById(R.id.bottom);
        int red = 63;
        int green = 65;
        int blue = 78;
        int color = Color.rgb(red, green, blue);
        bottom.setBackgroundColor(color);
        bottom.setOnCheckedChangeListener(this);
        mFragmentManager = getSupportFragmentManager();
        home = (RadioButton) findViewById(R.id.home);
        buy = (RadioButton) findViewById(R.id.buy_lottery);
        info = (RadioButton) findViewById(R.id.info_lottery);
        center = (RadioButton) findViewById(R.id.personnel_center);
        changeFragment(0);
  /*      IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("action.close");
        registerReceiver(mre,intentFilter);*/
    }
   /* private BroadcastReceiver mre = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("action.close")) {
                finish();
            }
        }
    };*/

    /**
     * @date：2015-3-30 上午9:59:17
     * @Package:cn.com.tanghuzhao.activity
     * @Description: 切换fragment
     * @Author: bo.wang
     */
    private static void changeFragment(int position) {
        FragmentTransaction t = mFragmentManager.beginTransaction();
        if (fragments[position] == null) {
            switch (position) {
                case 0:
                    fragments[position] = new HomeFragment();
                    break;
                case 1:
                    fragments[position] = new BuyFragment();
                    break;
                case 2:
                    fragments[position] = new InfoFragment();
                    break;
                case 3:
                    fragments[position] = new MyFragment();

                    break;
            }
            t.add(R.id.continer, fragments[position]);
        }
        for (int i = 0; i < fragments.length; i++) {
            if (i == position) {
                t.show(fragments[position]);
            } else {
                if (fragments[i] != null) {
                    t.hide(fragments[i]);
                }
            }
        }
        t.commitAllowingStateLoss();
    }

    @Override
    public void onCheckedChanged(NestRadioGroup group, int checkedId) {
        Intent intent;
        switch (checkedId) {
            case R.id.home:
                changeFragment(0);
                break;
            case R.id.buy_lottery:
                changeFragment(1);
                break;
            case R.id.info_lottery:
                changeFragment(2);
                break;
            case R.id.personnel_center:
                changeFragment(3);
                break;
        }
    }

    public static void choose() {
        mFragmentManager.getFragments().clear();
//		fragments = new Fragment[3];
//		changeFragment(0);
//		home.setChecked(true);
    }

    public static void closeAll(){
        mFragmentManager.getFragments().clear();
//		fragments = new Fragment[3];
        changeFragment(0);
        home.setChecked(true);
    }
    @Override
    protected void onDestroy() {
        MyApplication.activityList.remove(this);
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            MyApplication.finishAllActivity();
            System.exit(0);
        }
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
}
