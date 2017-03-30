package activity.huanlecheng;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class MyApplication extends Application {
	public static List<Activity> activityList = new LinkedList<Activity>();
	private boolean isAutoLogin = true;
	private static MyApplication instance;
	public static Context applicationContext;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		applicationContext  =this;
		instance = this;
		initImageLoader(getApplicationContext());

	}

	public static void finishAllActivity() {
		for (Activity activity : activityList) {
			activity.finish();
		}
	}
	public static MyApplication getInstance() {
		return instance;
	}
	/** 初始化ImageLoader */
	public static void initImageLoader(Context context) {
		/**
		 * "http://site.com/image.png" // from Web
		 * "file:///mnt/sdcard/image.png" // from SD card
		 * "file:///mnt/sdcard/video.mp4" // from SD card (video thumbnail)
		 * "content://media/external/images/media/13" // from content provider
		 * "content://media/external/video/media/13" // from content provider
		 * (video thumbnail) "assets://image.png" // from assets "drawable://" +
		 * R.drawable.img // from drawables (non-9patch images)
		 */
		File cacheDir = StorageUtils.getOwnCacheDirectory(context,
				"lottery/Cache");// 获取到缓存的目录地址
		Log.d("cacheDir", cacheDir.getPath());
		// 创建配置ImageLoader(所有的选项都是可选的,只使用那些你真的想定制)，这个可以设定在APPLACATION里面，设置为全局的配置参数
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context)
				.memoryCacheExtraOptions(480, 800)
				// max width, max height，即保存的每个缓存文件的最大长宽
				// .discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75,
				// null)
				// Can slow ImageLoader, use it carefully (Better don't use
				// it)/设置缓存的详细信息，最好不要设置这个
				.threadPoolSize(3)
				// 线程池内加载的数量
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
				// You can pass your own memory cache
				// implementation/你可以通过自己的内存缓存实现
				.memoryCacheSize(2 * 1024 * 1024)
				.discCacheSize(50 * 1024 * 1024)
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				// 将保存的时候的URI名称用MD5 加密
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.discCacheFileCount(100)
				// 缓存的文件数量
				.discCache(new UnlimitedDiscCache(cacheDir))
				// 自定义缓存路径
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
				.imageDownloader(
						new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout
																				// (5
																				// s),
																				// readTimeout
																				// (30
																				// s)超时时间
				.writeDebugLogs() // Remove for release app
				.build();// 开始构建
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);// 全局初始化此配置
	}

	public boolean autoLogin() {
		if (isAutoLogin) {
			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
			isAutoLogin = preferences.getBoolean("isautologin", false);
		}
		return isAutoLogin;
	}

	/**
	 * 设置自动登录
	 * @param isAutoLogin
	 * @description
	 */
	public void isAutoLogin(Boolean isAutoLogin) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
		SharedPreferences.Editor editor = preferences.edit();
		if (editor.putBoolean("isautologin", isAutoLogin).commit()) {
			this.isAutoLogin = isAutoLogin;
		}
	}
}
