/**
 * 异步图片适配器  对应BatmipDemo
 */
package adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.List;
import java.util.Random;

import activity.huanlecheng.LotteryDetailActivity;
import activity.huanlecheng.R;
import bean.HxGame;
import bean.HxPlay;
import bean.UserBettingInfo;
import view.ViewHolder;

public class RecordsAdapter extends BaseAdapter {

    private static final String TAG = "RecordsAdapter";
    private Context mContext;
    private LayoutInflater mInflater;
    private ImageLoader mImageLoader;
    private DisplayImageOptions op;
    public List<UserBettingInfo.DataBean.ListBean> mList;
    private List<HxGame.DataBean> mHxGame;
    private List<HxPlay.DataBean> mHxPlay;

    public RecordsAdapter(Context pContext) {
        mInflater = LayoutInflater.from(pContext);
        mContext = pContext;
        mImageLoader = ImageLoader.getInstance();
        op = getListOptions();
    }

    public void setData(List<UserBettingInfo.DataBean.ListBean> models, List<HxGame.DataBean> hxGame, List<HxPlay.DataBean> hxPlay) {
        this.mList = models;
        mHxGame = hxGame;
        mHxPlay = hxPlay;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
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
//        TextView tv_ring_name = ViewHolder.get(convertView, R.id.tv_ring_name);
        final TextView tv_bigname = ViewHolder.get(convertView, R.id.tv_bigname);

        final UserBettingInfo.DataBean.ListBean listBean = mList.get(position);
        qs.setText(listBean.getH_b_d_issue() + "期");
        sj.setText(listBean.getH_b_d_addtime());
        mo.setText(listBean.getH_b_d_money() + "元");
        if (listBean.getH_b_d_state().equals("1")) {
            status.setText("未开奖");
        } else if (listBean.getH_b_d_state().equals("2")) {
            status.setText("已中奖");
        } else if (listBean.getH_b_d_state().equals("3")) {
            status.setText("未中奖");
        } else if (listBean.getH_b_d_state().equals("4")) {
            status.setText("派奖中");
        } else if (listBean.getH_b_d_state().equals("5")) {
            status.setText("已撤单");
        } else if (listBean.getH_b_d_state().equals("6")) {
            status.setText("已完成");
        }
        //设置字体的随机颜色
        Random random = new Random();
        int red = random.nextInt(150);
        int green = random.nextInt(150);
        int blue = random.nextInt(150);
        int color = Color.rgb(red, green, blue);
        Log.i(TAG, "mHxGame.size() = " + mHxGame.size());
        for (HxGame.DataBean dataBean : mHxGame) {
            if (listBean.getH_b_d_nid().equals(dataBean.getH_g_n_id())) {
                for (HxPlay.DataBean mHxPlayBean: mHxPlay) {
                    if (listBean.getH_b_d_pid().equals(mHxPlayBean.getH_g_p_id())) {
                        bottom_tt.setText(dataBean.getH_g_n_title() + "-" + mHxPlayBean.getH_g_p_name());
                        tv_bigname.setText(dataBean.getH_g_n_title().substring(0, 1));
                        tv_bigname.setTextColor(color);
                        //截取开头字母设置给圆圈
                        status.setTextColor(color);
                    }
                }
            }
        }

        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,
                        LotteryDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("model", listBean);
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
