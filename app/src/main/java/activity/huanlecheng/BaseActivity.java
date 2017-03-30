package activity.huanlecheng;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import constants.UserInfo;
import http.WiseHttp;
import view.AlertWindow;
import view.LoadingPro;
import view.LoadingWindow;


public class BaseActivity extends FragmentActivity {
	public Bundle bundle;
	public static Gson gson;
	public static String ONLINE = "online";
	public static String curVersionName = "";
	public static int curVersionCode;
	public static WiseHttp wh;
	public LoadingWindow loadingWindow;
	public LoadingPro loadingPro;
	PackageInfo info;
	public static UserInfo userInfo;
	public static Toast toast;
	public static ImageLoader il;
	public AlertWindow alertWindow;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		try {
			info = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		curVersionName = info.versionName;
		curVersionCode = info.versionCode;
		//Log.d("curVersionName--------------", "" + curVersionName);// 1.0
		//Log.d("curVersionCode--------------", "" + curVersionCode);// 1
		bundle = new Bundle();
		gson = new Gson();
		wh = new WiseHttp();
		userInfo = new UserInfo(getApplicationContext());
		alertWindow = new AlertWindow(this, getResources().getIdentifier(
				"MyDialog", "style", this.getPackageName()));// 对话框对象
		loadingWindow = new LoadingWindow(this,
				getResources().getIdentifier("MyDialog", "style", this.getPackageName()));
		loadingPro = new LoadingPro(this,
				getResources().getIdentifier("MyDialog", "style", this.getPackageName()));
		il = ImageLoader.getInstance();
		super.onCreate(savedInstanceState);
	}

	public void showToast(String str) {
		if (null == toast) {
			toast = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT);
		} else {
			toast.setText(str);
		}

		toast.show();
	}


}
