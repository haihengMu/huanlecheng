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
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import activity.huanlecheng.BuyChildActivty1;
import activity.huanlecheng.R;
import bean.NameBean;
import constants.RUser;


public class MainGridAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	private ImageLoader mImageLoader;
	private List<NameBean> list;
	private String[] str = new String[]{"中奖率高 玩法多样", "玩法简单 返奖率高", "高频彩种 好玩易中", "简单灵活 经济实惠", "赛车主题 乐趣无限", "想开就开 无需等待",
			"公益体彩 精彩纷呈", "种类多样 惊喜多多"};
	private ArrayList<String> mlist = new ArrayList<>();

	public MainGridAdapter(Context pContext) {
		this.mInflater = LayoutInflater.from(pContext);
		this.mContext = pContext;
		mImageLoader = ImageLoader.getInstance();
	}
	public void setData(List<NameBean> data) {
		this.list = data;
		notifyDataSetChanged();
	}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.main_grid_item, null);
			viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			viewHolder.tv_jiesao = (TextView) convertView.findViewById(R.id.tv_jiesao);
			viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_imageview);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.tv_name.setText(list.get(position).getName());
		for (int i = 0; i < str.length; i++) {
			mlist.add(str[i]);
		}
		Random random = new Random();
		if (list.get(position).getTid().equals("1") || list.get(position).getTid().equals("3") || list.get(position).getTid().equals("2")
				|| list.get(position).getTid().equals("10")) {
			viewHolder.tv_jiesao.setText(mlist.get(0));
			int red = random.nextInt(210);
			int green = random.nextInt(210);
			int blue = random.nextInt(210);
			int color = Color.rgb(red, green, blue);
			viewHolder.tv_jiesao.setTextColor(color);
			viewHolder.imageView.setBackgroundResource(R.drawable.shishi);
		} else if (list.get(position).getTid().equals("4") || list.get(position).getTid().equals("16") || list.get(position).getTid().equals("17")
				|| list.get(position).getTid().equals("19") || list.get(position).getTid().equals("18")|| list.get(position).getTid().equals("20")) {
			viewHolder.tv_jiesao.setText(mlist.get(2));
			int red = random.nextInt(210);
			int green = random.nextInt(210);
			int blue = random.nextInt(210);
			int color = Color.rgb(red, green, blue);
			viewHolder.tv_jiesao.setTextColor(color);
			if (list.get(position).getTid().equals("4") || list.get(position).getTid().equals("18")) {
				viewHolder.imageView.setBackgroundResource(R.drawable.fencai);
			} else if (list.get(position).getTid().equals("17")) {
				viewHolder.imageView.setBackgroundResource(R.drawable.han);
			} else {
				viewHolder.imageView.setBackgroundResource(R.drawable.logo_qtc_2_sm);
			}
		} else if (list.get(position).getTid().equals("5") || list.get(position).getTid().equals("6") || list.get(position).getTid().equals("8")
				|| list.get(position).getTid().equals("7")) {
			viewHolder.tv_jiesao.setText(mlist.get(1));
			int red = random.nextInt(210);
			int green = random.nextInt(210);
			int blue = random.nextInt(210);
			int color = Color.rgb(red, green, blue);
			viewHolder.tv_jiesao.setTextColor(color);
			viewHolder.imageView.setBackgroundResource(R.drawable.xuan);
		} else if (list.get(position).getTid().equals("9")) {
			viewHolder.tv_jiesao.setText(mlist.get(7));
			int red = random.nextInt(210);
			int green = random.nextInt(210);
			int blue = random.nextInt(210);
			int color = Color.rgb(red, green, blue);
			viewHolder.tv_jiesao.setTextColor(color);
			viewHolder.imageView.setBackgroundResource(R.drawable.kuai);
		} else if (list.get(position).getTid().equals("12")) {
			viewHolder.tv_jiesao.setText(mlist.get(3));
			int red = random.nextInt(210);
			int green = random.nextInt(210);
			int blue = random.nextInt(210);
			int color = Color.rgb(red, green, blue);
			viewHolder.tv_jiesao.setTextColor(color);
			viewHolder.imageView.setBackgroundResource(R.drawable.fucai);
		} else if (list.get(position).getTid().equals("11")) {
			viewHolder.tv_jiesao.setText(mlist.get(6));
			int red = random.nextInt(210);
			int green = random.nextInt(210);
			int blue = random.nextInt(210);
			int color = Color.rgb(red, green, blue);
			viewHolder.tv_jiesao.setTextColor(color);
			viewHolder.imageView.setBackgroundResource(R.drawable.pailie);
		} else if (list.get(position).getTid().equals("13")) {
			viewHolder.tv_jiesao.setText(mlist.get(4));
			int red = random.nextInt(210);
			int green = random.nextInt(210);
			int blue = random.nextInt(210);
			int color = Color.rgb(red, green, blue);
			viewHolder.tv_jiesao.setTextColor(color);
			viewHolder.imageView.setBackgroundResource(R.drawable.saiche);
		} else if (list.get(position).getTid().equals("14") || list.get(position).getTid().equals("15")) {
			viewHolder.tv_jiesao.setText(mlist.get(5));
			int red = random.nextInt(210);
			int green = random.nextInt(210);
			int blue = random.nextInt(210);
			int color = Color.rgb(red, green, blue);
			viewHolder.tv_jiesao.setTextColor(color);
			viewHolder.imageView.setBackgroundResource(R.drawable.han);
		}
        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(mContext, BuyChildActivty1.class);
                intent.putExtra("id", list.get(position).getTid());
                intent.putExtra("title", list.get(position).getName());
                RUser.id = list.get(position).getTid();
                mContext.startActivity(intent);
            }
        });
		return convertView;
	}
	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	public DisplayImageOptions getListOptions() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.cacheInMemory(false) // 设置图片不缓存于内存中
				.cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565) // 设置图片的质量
				.imageScaleType(ImageScaleType.IN_SAMPLE_INT) // 设置图片的缩放类型，该方
				.build();
		return options;
	}
	class ViewHolder {
		public TextView tv_name, tv_jiesao;
		public ImageView imageView;
	}

}
