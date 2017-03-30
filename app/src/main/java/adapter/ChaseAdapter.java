/**
 * 异步图片适配器  对应BatmipDemo
 */
package adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import activity.huanlecheng.R;
import bean.ChaseBean;
import bean.ChaseResponseModel;
import view.ViewHolder;


public class ChaseAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	private ImageLoader mImageLoader;
	private DisplayImageOptions op;
	public ChaseResponseModel mList;

	public ChaseAdapter(Context pContext) {
		this.mInflater = LayoutInflater.from(pContext);
		this.mContext = pContext;
		mImageLoader = ImageLoader.getInstance();
		op = getListOptions();

	}

	public void setData(ChaseResponseModel models) {
		this.mList = models;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mList == null ? 0 : mList.getList().size();
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
					R.layout.chase_item, null);
		}
		CheckBox cBox = ViewHolder.get(convertView, R.id.cb);
		TextView vi_qh = ViewHolder.get(convertView, R.id.vi_qh);
		TextView vi_jz = ViewHolder.get(convertView, R.id.vi_jz);
		EditText et_zhqs = ViewHolder.get(convertView, R.id.et_zhqs);
		TextView vi_je = ViewHolder.get(convertView, R.id.vi_je);
		final ChaseBean gm = mList.getList().get(position);
		vi_qh.setText(gm.getG_T_Issue());
		vi_jz.setText(gm.getG_T_EndTime());
		if (gm.isB()) {
			cBox.setChecked(true);
		} else {
			cBox.setChecked(false);
		}
		et_zhqs.setText(gm.getBs());
		vi_je.setText(gm.getJz());
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
