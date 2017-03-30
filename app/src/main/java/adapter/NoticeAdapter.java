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
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import activity.huanlecheng.BroswerActivity;
import activity.huanlecheng.R;
import bean.GgBean;
import view.ViewHolder;

public class NoticeAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	private ImageLoader mImageLoader;
	private DisplayImageOptions op;
	public GgBean mList;


	public NoticeAdapter(Context pContext) {
		this.mInflater = LayoutInflater.from(pContext);
		this.mContext = pContext;
		mImageLoader = ImageLoader.getInstance();
		op = getListOptions();

	}
	public void setData(GgBean nrm) {
		this.mList=nrm;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mList== null ? 0 : mList.getData().size();
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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.notice_item, null);
		}

		TextView bottom_tt = ViewHolder.get(convertView, R.id.kaijiangText);
		TextView time= ViewHolder.get(convertView, R.id.time);

		//final MsgBeanGg gm=mList.getData().getList().get(position);
		bottom_tt.setText(mList.getData().get(position).getTitle());
		time.setText(mList.getData().get(position).getText());
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mContext, BroswerActivity.class);
				/*Bundle bundle=new Bundle();
				bundle.putSerializable("model", mList.getData().get(position).getCount());
				intent.putExtras(bundle);*/
				intent.putExtra("model",mList.getData().get(position).getCount());
				mContext.startActivity(intent);
			}
		});
		return convertView;

	}

	public DisplayImageOptions getListOptions() {
		DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(false) // 设置图片不缓存于内存中
				.cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565) // 设置图片的质量
				.imageScaleType(ImageScaleType.IN_SAMPLE_INT) // 设置图片的缩放类型，该方
				.build();
		return options;
	}


}
