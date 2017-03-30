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
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import activity.huanlecheng.R;
import bean.GetMLResponseModel;
import bean.MLbean;
import view.ViewHolder;

public class MLAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	private ImageLoader mImageLoader;
	private DisplayImageOptions op;
	public GetMLResponseModel mList;
	private TextView qs_state;

	public MLAdapter(Context pContext) {
		this.mInflater = LayoutInflater.from(pContext);
		this.mContext = pContext;
		mImageLoader = ImageLoader.getInstance();
		op = getListOptions();

	}

	public void setData(GetMLResponseModel models) {
		this.mList = models;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mList == null ? 0 : mList.getMsg().size();
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
					R.layout.ml_item, null);
		}

		TextView jysj = ViewHolder.get(convertView, R.id.jysj);
		TextView jylx = ViewHolder.get(convertView, R.id.jylx);
		TextView qs = ViewHolder.get(convertView, R.id.qs);
		TextView mon = ViewHolder.get(convertView, R.id.mon);
		TextView qs_state = ViewHolder.get(convertView, R.id.qs_state);

		final MLbean gm = mList.getMsg().get(position);
		jysj.setText("交易时间：" + gm.getM_L_AddTime());
		jylx.setText("交易类型：" + gm.getM_L_Content());
		String number = gm.getM_L_Type();
		if (number.equals("1")) {
			qs_state.setText("交易状态:"+"存款");
		} else if (number.equals("2")) {
			qs_state.setText("交易状态:"+"取款");
		} else if (number.equals("3")) {
			qs_state.setText("交易状态:"+"投注");
		} else if (number.equals("4")) {
			qs_state.setText("交易状态:"+"派奖");
		} else if (number.equals("5")) {
			qs_state.setText("交易状态:"+"返点");
		} else if (number.equals("6")) {
			qs_state.setText("交易状态:"+"活动");
		} else if (number.equals("7")) {
			qs_state.setText("交易状态:"+"撤单");
		} else if (number.equals("8")) {
			qs_state.setText("交易状态:"+"分红");
		} else if (number.equals("9")) {
			qs_state.setText("交易状态:"+"转账");
		} else if (number.equals("10")) {
			qs_state.setText("交易状态:"+"其他");
		}
		qs.setText("花费金额:"+gm.getM_L_Money());
		mon.setText(gm.getM_L_AfterMoney());
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
