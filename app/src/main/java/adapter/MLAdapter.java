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

import java.util.List;

import activity.huanlecheng.R;
import bean.GetMLResponseModel;
import view.ViewHolder;

public class MLAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ImageLoader mImageLoader;
    private DisplayImageOptions op;
    public List<GetMLResponseModel.DataBean.ListBean> mList;
    private TextView qs_state;

    public MLAdapter(Context pContext) {
        this.mInflater = LayoutInflater.from(pContext);
        this.mContext = pContext;
        mImageLoader = ImageLoader.getInstance();
        op = getListOptions();

    }

    public void setData(List<GetMLResponseModel.DataBean.ListBean> models) {
        this.mList = models;
        notifyDataSetChanged();
    }


    public void addAllList(List<GetMLResponseModel.DataBean.ListBean> models) {
        mList.addAll(models);
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
                    R.layout.ml_item, null);
        }

        TextView jysj = ViewHolder.get(convertView, R.id.jysj);
        TextView jylx = ViewHolder.get(convertView, R.id.jylx);
        TextView qs = ViewHolder.get(convertView, R.id.qs);
        TextView mon = ViewHolder.get(convertView, R.id.mon);
        TextView qs_state = ViewHolder.get(convertView, R.id.qs_state);

        GetMLResponseModel.DataBean.ListBean listBean = mList.get(position);
        jysj.setText("交易时间：" + listBean.getH_f_d_addtime());
        qs_state.setText("账户余额：" + listBean.getH_f_d_afterpt_balance());
        String number = listBean.getH_f_d_type();
        if (number.equals("1")) {
            jylx.setText("交易状态:" + "存款");
        } else if (number.equals("2")) {
            jylx.setText("交易状态:" + "取款");
        } else if (number.equals("3")) {
            jylx.setText("交易状态:" + "投注");
        } else if (number.equals("4")) {
            jylx.setText("交易状态:" + "中奖");
        } else if (number.equals("5")) {
            jylx.setText("交易状态:" + "返点");
        } else if (number.equals("6")) {
            jylx.setText("交易状态:" + "撤单");
        } else if (number.equals("7")) {
            jylx.setText("交易状态:" + "活动");
        } else if (number.equals("8")) {
            jylx.setText("交易状态:" + "分红");
        } else if (number.equals("9")) {
            jylx.setText("交易状态:" + "转账");
        } else if (number.equals("10")) {
            jylx.setText("交易状态:" + "其他");
        }
        qs.setText("花费金额:" + listBean.getH_f_d_money());
        String h_f_d_state = listBean.getH_f_d_state();
        if (h_f_d_state.equals("1")) {
            mon.setText("成功");
        } else if (h_f_d_state.equals("2")) {
            mon.setText("正常");
        } else if (h_f_d_state.equals("3")) {
            mon.setText("失败");
        }
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
