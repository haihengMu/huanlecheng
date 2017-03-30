package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import activity.huanlecheng.R;
import bean.ProvinceCityBean;

/**
 * Created by Mhc on 2017/3/22.
 */

public class ProivceAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<ProvinceCityBean.DataBean> mList;

    public ProivceAdapter(Context pContext) {
        this.mInflater = LayoutInflater.from(pContext);
        this.mContext = pContext;
    }

    public void setData(List<ProvinceCityBean.DataBean> models) {
        this.mList = models;
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
        ProvinceCityBean.DataBean gm = mList.get(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_province, null);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.proivce);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(gm.getName());
        return convertView;
    }

    class ViewHolder {
        TextView textView;
    }
}
