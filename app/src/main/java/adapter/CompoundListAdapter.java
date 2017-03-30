/**
 * 异步图片适配器  对应BatmipDemo
 */
package adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.List;

import activity.huanlecheng.R;
import bean.ThousandsBean;
import view.ClassGridView;
import view.ViewHolder;

public class CompoundListAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	private ImageLoader mImageLoader;
	private DisplayImageOptions op;
	private List<ThousandsBean> mList;
	private CompoundGridAdapter cma;
	private ClassGridView gridView;
	private String kuang;
	private String gpid;
	
	public CompoundListAdapter(Context pContext) {
		this.mInflater = LayoutInflater.from(pContext);
		this.mContext = pContext;
		mImageLoader = ImageLoader.getInstance();
		op = getListOptions();

	}

	public void setData(List<ThousandsBean> models, String kuang, String gpid) {
		this.mList = models;
		this.kuang=kuang;
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
					R.layout.th_item, null);
		}

		TextView bottom_tt = ViewHolder.get(convertView, R.id.th_text);
		Button da = ViewHolder.get(convertView, R.id.da);
		Button xiao = ViewHolder.get(convertView, R.id.xiao);
		Button quan = ViewHolder.get(convertView, R.id.quan);
		Button ji = ViewHolder.get(convertView, R.id.ji);
		Button ou = ViewHolder.get(convertView, R.id.ou);
		Button qing = ViewHolder.get(convertView, R.id.qing);
		LinearLayout kuanglaLayout=ViewHolder.get(convertView, R.id.kuang);
		ClassGridView gridView = ViewHolder.get(convertView, R.id.gridview);
		final ThousandsBean gm = mList.get(position);
		bottom_tt.setText(gm.getThouString());
		if (kuang.equals("1")) {
			kuanglaLayout.setVisibility(View.VISIBLE);
		}else {
			kuanglaLayout.setVisibility(View.GONE);
		}
		cma = new CompoundGridAdapter(mContext);
		gridView.setAdapter(cma);
		cma.setData(mList.get(position).getThousandsChildBeans(),
				String.valueOf(position),gpid);
		
		da.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction("action.big");
				intent.putExtra("b", "大");
				intent.putExtra("position", position+"");
				intent.putExtra("gpid",gpid);
				mContext.sendBroadcast(intent);
			}
		});
		xiao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction("action.big");
				intent.putExtra("b", "小");
				intent.putExtra("position", position+"");
				intent.putExtra("gpid",gpid);
				mContext.sendBroadcast(intent);
			}
		});
		quan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction("action.big");
				intent.putExtra("b", "全");
				intent.putExtra("position", position+"");
				intent.putExtra("gpid",gpid);
				mContext.sendBroadcast(intent);
			}
		});
		ji.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction("action.big");
				intent.putExtra("b", "奇");
				intent.putExtra("position", position+"");
				intent.putExtra("gpid",gpid);
				mContext.sendBroadcast(intent);
			}
		});
		ou.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction("action.big");
				intent.putExtra("b", "偶");
				intent.putExtra("position", position+"");
				intent.putExtra("gpid",gpid);
				mContext.sendBroadcast(intent);
			}
		});
		qing.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction("action.big");
				intent.putExtra("b", "清");
				intent.putExtra("position", position+"");
				intent.putExtra("gpid",gpid);
				mContext.sendBroadcast(intent);
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
