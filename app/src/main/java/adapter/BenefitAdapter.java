/**
 * 异步图片适配器  对应BatmipDemo
 */
package adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import activity.huanlecheng.R;
import bean.BenefitnewBean;
import constants.Constants;
import util.ShowToast;
import view.ViewHolder;

public class BenefitAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	private ImageLoader mImageLoader;
	private DisplayImageOptions op;
	private BenefitnewBean data;

	public BenefitAdapter(Context pContext) {
		this.mInflater = LayoutInflater.from(pContext);
		this.mContext = pContext;
		mImageLoader = ImageLoader.getInstance();
		op = getListOptions();

	}
	public void setData(BenefitnewBean data) {
		this.data = data;
	}
	@Override
	public int getCount() {
		return  data.getData().getList().size();
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
					R.layout.benefit, null);
		}
		ImageView im= ViewHolder.get(convertView, R.id.im);
		TextView title = ViewHolder.get(convertView, R.id.title);
		TextView info = ViewHolder.get(convertView, R.id.info);
				

		if (data.getData().getList().get(position).getH_a_c_picture().equals("")){

		}else {
			mImageLoader.displayImage(Constants.img_url + data.getData().getList().get(position).getH_a_c_picture(), im, op);
		}
		title.setText(data.getData().getList().get(position).getH_a_c_title());
		info.setText(data.getData().getList().get(position).getH_a_c_info());
		convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Intent oIntent=new Intent(mContext,BenefitDetailActivity.class);
//				oIntent.putExtra("id", gm.getA_L_Id());
//				oIntent.putExtra("title", gm.getA_L_Title());
//				mContext.startActivity(oIntent);
				ShowToast.showMsg(mContext, "请移步到网站参加活动");
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
