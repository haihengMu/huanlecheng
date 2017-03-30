/**
 * 异步图片适配器  对应BatmipDemo
 */
package adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.Random;

import activity.huanlecheng.LotteryDetailActivity;
import activity.huanlecheng.R;
import bean.GetRecordsListResponseModel;
import bean.HxGame;
import bean.HxPlay;
import bean.RecordsResponseModel;
import view.ViewHolder;

public class RecordsAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	private ImageLoader mImageLoader;
	private DisplayImageOptions op;
	public RecordsResponseModel mList;
	private HxGame hg;
	private HxPlay hp;

	public RecordsAdapter(Context pContext) {
		this.mInflater = LayoutInflater.from(pContext);
		this.mContext = pContext;
		mImageLoader = ImageLoader.getInstance();
		op = getListOptions();

	}

	public void setData(RecordsResponseModel models, HxGame hg, HxPlay hp) {
		this.mList = models;
		this.hg = hg;
		this.hp = hp;
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
					R.layout.records_item, null);
		}

		final TextView bottom_tt = ViewHolder.get(convertView, R.id.kaijiangText);
		TextView qs = ViewHolder.get(convertView, R.id.qs);
		TextView sj = ViewHolder.get(convertView, R.id.sj);
		TextView mo = ViewHolder.get(convertView, R.id.mo);
		TextView status = ViewHolder.get(convertView, R.id.status);
		//TextView tv_ring_name=ViewHolder.get(convertView,R.id.tv_ring_name);
		final TextView tv_bigname= ViewHolder.get(convertView,R.id.tv_bigname);

		final GetRecordsListResponseModel gm = mList.getMsg().get(position);
		qs.setText(gm.getB_L_Issue() + "期");
		sj.setText(gm.getB_L_AddTime());
		mo.setText(gm.getB_L_Money());
		if (gm.getB_L_State().equals("1")) {
			status.setText("未开奖");
		} else if (gm.getB_L_State().equals("2")) {
			status.setText("已中奖");
		} else if (gm.getB_L_State().equals("3")) {
			status.setText("未中奖");
		} else if (gm.getB_L_State().equals("4")) {
			status.setText("派奖中");
		} else if (gm.getB_L_State().equals("5")) {
			status.setText("已撤单");
		} else if (gm.getB_L_State().equals("6")) {
			status.setText("已完成");
		}
		//设置字体的随机颜色
		Random random=new Random();
		int red= random.nextInt(150);
		int green= random.nextInt(150);
		int blue= random.nextInt(150);
		int color= Color.rgb(red,green,blue);
		for (int i = 0; i < hp.getHX_Game_Play().size(); i++) {
			if (gm.getB_L_Type()
					.equals(hp.getHX_Game_Play().get(i).getG_P_Id())) {
				for (int j = 0; j < hg.getHX_Game_Name().size(); j++) {
					if (hg.getHX_Game_Name().get(j).getG_N_Id().equals(hp.getHX_Game_Play().get(i).getG_P_NameId())) {
						bottom_tt.setText(hg.getHX_Game_Name().get(j).getG_N_Title() + "-" + hp.getHX_Game_Play().get(i).getG_P_Name());
						tv_bigname.setText(hg.getHX_Game_Name().get(j).getG_N_Title().substring(0,1));
						tv_bigname.setTextColor(color);
						/*//截取开头字母设置给圆圈

						tv_ring_name.setTextColor(color);*/
					}
				}
			}

		}
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mContext,
						LotteryDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("model", gm);
				intent.putExtras(bundle);
				intent.putExtra("name", bottom_tt.getText().toString());
				mContext.startActivity(intent);
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
