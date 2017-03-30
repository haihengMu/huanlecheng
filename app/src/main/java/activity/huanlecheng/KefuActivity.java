package activity.huanlecheng;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

/**
 * 在线客服
 * Created by Administrator on 2016/12/14.
 */

public class KefuActivity extends BaseActivity {

    private WebView webview;
    private WebView wv;
    private Button title_left_btn;
    private TextView title_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kefu);
        title_left_btn = (Button) findViewById(R.id.title_left_btn);
        title_textview = (TextView) findViewById(R.id.title_textview);
        title_textview.setText("在线客服");
        title_left_btn.setBackgroundResource(R.drawable.aar);
        title_left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        wv = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = wv.getSettings();
        // 设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        // 设置可以访问文件
        webSettings.setAllowFileAccess(true);
        // 设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        // 加载需要显示的网页
        wv.loadUrl("http://www20.53kf.com/webCompany.php?arg=10130909&style=1");
        // 设置Web视图
        // wv.setWebViewClient(new webViewClient ());
        wv.setWebViewClient(new webViewClient());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    // 设置回退
    // 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack()) {
            wv.goBack(); // goBack()表示返回WebView的上一页面
            return true;
        }
        finish();// 结束退出程序
        return false;
    }

    // Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
