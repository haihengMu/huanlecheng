/**
 * 异步图片适配器  对应BatmipDemo
 */
package adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.List;

import activity.huanlecheng.R;
import bean.ThousandsChildBean;
import view.ViewHolder;

public class CompoundGridAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	private ImageLoader mImageLoader;
	private DisplayImageOptions op;
	private List<ThousandsChildBean> mList;
	private String it;
	private String gpid;
	public CompoundGridAdapter(Context pContext) {
		this.mInflater = LayoutInflater.from(pContext);
		this.mContext = pContext;
		mImageLoader = ImageLoader.getInstance();
		op = getListOptions();

	}

	public void setData(List<ThousandsChildBean> models, String it, String gpid) {
		this.mList = models;
		this.it=it;
		this.gpid=gpid;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mList == null ? 0 : mList.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.th_child_item, null);
		}

		Button bottom_tt = ViewHolder.get(convertView, R.id.th_text);
		final String string = bottom_tt.getText().toString();
		final ThousandsChildBean gm = mList.get(position);
		if (gm.isB()) {
			bottom_tt.setBackgroundResource(R.drawable.changzhong);
			bottom_tt.setTextColor(Color.parseColor("#0084FF"));
		} else {
			bottom_tt.setBackgroundResource(R.drawable.changfangs);
		}
		bottom_tt.setText(gm.getId());
		bottom_tt.setTextColor(Color.parseColor("#ffffff"));
		bottom_tt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (gm.isB()) {
					gm.setB(false);
					Intent intent = new Intent();
					intent.setAction("action.compound");
					intent.putExtra("position", position + "");
					intent.putExtra("it", it);
					intent.putExtra("tf", "0");// 0false 1true
					intent.putExtra("gpid",gpid);
					mContext.sendBroadcast(intent);
				} else {
					gm.setB(true);
					Intent intent = new Intent();
					intent.setAction("action.compound");
					intent.putExtra("position", position + "");
					intent.putExtra("it", it);
					intent.putExtra("tf", "1");// 0false 1true
					intent.putExtra("gpid",gpid);
					mContext.sendBroadcast(intent);
				}
			}
		});

		return convertView;

	}

	public DisplayImageOptions getListOptions() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.cacheInMemory(false) // 设置图片不缓存于内存中
				.cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565) // 设置图片的质量
				.imageScaleType(ImageScaleType.IN_SAMPLE_INT) // 设置图片的缩放类型，该方
				.build();
		return options;
	}
}
