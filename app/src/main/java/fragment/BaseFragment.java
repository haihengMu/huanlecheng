package fragment;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import bean.CaiPiaoNewTopBean;
import constants.UserInfo;
import http.WiseHttp;
import view.AlertWindow;
import view.LoadingWindow;


/**
 * BaseFragment
 * 
 * @author bingchuan
 * @date 2015-3-27 下午3:36:12
 */
public abstract class BaseFragment extends Fragment {
	public Bundle bundle;
	public static Gson gson;
	public static String ONLINE = "online";
	public static String curVersionName = "";
	public static int curVersionCode;
	public static WiseHttp wh;
	public LoadingWindow loadingWindow;
	PackageInfo info;
	public static UserInfo userInfo;
	public static Toast toast;
	public static ImageLoader il;
	public AlertWindow alertWindow;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);
		try {
			info = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		curVersionName = info.versionName;
		curVersionCode = info.versionCode;
		//Log.d("curVersionName--------------", "" + curVersionName);// 1.0
	//	Log.d("curVersionCode--------------", "" + curVersionCode);// 1
		bundle = new Bundle();
		gson = new Gson();
		wh = new WiseHttp();
		userInfo = new UserInfo(getActivity());
		alertWindow = new AlertWindow(getActivity(), getResources().getIdentifier(
				"MyDialog", "style", getActivity().getPackageName()));// 对话框对象
		loadingWindow = new LoadingWindow(getActivity(),
				getResources().getIdentifier("MyDialog", "style", getActivity().getPackageName()));
		il = ImageLoader.getInstance();
		super.onCreate(savedInstanceState);
	}

	public void showToast(String str) {
		if (null == toast) {
			toast = Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT);
		} else {
			toast.setText(str);
		}

		toast.show();
	}

	public void showToast(int strId) {
		showToast(getString(strId));
	}


}
