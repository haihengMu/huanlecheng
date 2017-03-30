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
import android.widget.RadioButton;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import activity.huanlecheng.InfoChildActivity;
import activity.huanlecheng.R;
import bean.CustomBean;
import bean.HXGameNameBean;
import view.ViewHolder;


public class InfoFragmentAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	private ImageLoader mImageLoader;
	private DisplayImageOptions op;
	public CustomBean mList;
	private List<String> list = new ArrayList<String>() {
	};
	private String[] str = new String[]{"中奖率高 玩法多样", "玩法简单 返奖率高", "高频彩种 好玩易中", "简单灵活 经济实惠", "赛车主题 乐趣无限"
			, "公益体彩 精彩分呈", "好玩多样 经彩灵活"};
	private RadioButton jiesao;
	private RadioButton ring_name;
	public InfoFragmentAdapter(Context pContext) {
		this.mInflater = LayoutInflater.from(pContext);
		this.mContext = pContext;
		mImageLoader = ImageLoader.getInstance();
		op = getListOptions();

	}

	public void setData(CustomBean models) {
		this.mList = models;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mList == null ? 0 : mList.getData().size();
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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.kj_item, null);
		}
		TextView bottom_tt = ViewHolder.get(convertView, R.id.kaijiangText);

		jiesao = ViewHolder.get(convertView, R.id.jiesao);
		ring_name = ViewHolder.get(convertView, R.id.ring_name);

		final HXGameNameBean gm = mList.getData().get(position);
		bottom_tt.setText(gm.getH_g_n_title());
		bottom_tt.setText(gm.getH_g_n_title());
		ring_name.setText(gm.getH_g_n_title().substring(0, 1));
		//设置字体的随机颜色
		Random random = new Random();
		int red = random.nextInt(290);
		int green = random.nextInt(290);
		int blue = random.nextInt(290);
		int color = Color.rgb(red, green, blue);
		ring_name.setTextColor(color);
		ring_name.setText(gm.getH_g_n_title().substring(0, 1));


		for (int i = 0; i < str.length; i++) {
			list.add(str[i]);
		}
		if (mList.getData().get(position).getH_g_n_cid().equals("1") || mList.getData().get(position).getH_g_n_cid().equals("2") || mList.getData().get(position).getH_g_n_cid().equals("3")
				|| mList.getData().get(position).getH_g_n_cid().equals("10")) {
			jiesao.setText(list.get(0));
		} else if (mList.getData().get(position).getH_g_n_cid().equals("5") || mList.getData().get(position).getH_g_n_cid().equals("6")
				|| mList.getData().get(position).getH_g_n_cid().equals("7") || mList.getData().get(position).getH_g_n_cid().equals("8")) {
			jiesao.setText(list.get(1));
		} else if (mList.getData().get(position).getH_g_n_cid().equals("4") || mList.getData().get(position).getH_g_n_cid().equals("16")
				|| mList.getData().get(position).getH_g_n_cid().equals("17") || mList.getData().get(position).getH_g_n_cid().equals("15") || mList.getData().get(position).getH_g_n_cid().equals("14")
				|| mList.getData().get(position).getH_g_n_cid().equals("18")) {
			jiesao.setText(list.get(2));
		} else if (mList.getData().get(position).getH_g_n_cid().equals("12")) {
			jiesao.setText(list.get(3));
		} else if (mList.getData().get(position).getH_g_n_cid().equals("13")) {
			jiesao.setText(list.get(4));
		} else if (mList.getData().get(position).getH_g_n_cid().equals("11") || mList.getData().get(position).getH_g_n_cid().equals("9")) {
			jiesao.setText(list.get(5));
		} else {
			jiesao.setText(list.get(6));
		}

		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
		/*
					Intent intent=new Intent(mContext, InfoChildActivityOther.class);
					intent.putExtra("id",gm.getG_N_Id());
					intent.putExtra("title",gm.getG_N_Title());
					mContext.startActivity(intent);
				}else {*/
					// TODO Auto-generated method stub
					Intent intent = new Intent(mContext, InfoChildActivity.class);
					intent.putExtra("id", gm.getH_g_n_cid());
					intent.putExtra("title", gm.getH_g_n_title());
					mContext.startActivity(intent);
			//	}

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
