package adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import activity.huanlecheng.R;
import bean.BankCZBean;
import bean.GetPayListBean;

/**
 * Created by Administrator on 2016/12/7.
 */

public class BankCZAdapter extends BaseAdapter {
    private List<GetPayListBean.DataBean> data;
    private Context context;

    public BankCZAdapter(Context applicationContext) {
        this.context = applicationContext;
    }

    public void setData(List<GetPayListBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.banlieitem, null);
            viewHolder.bankname = (TextView) convertView.findViewById(R.id.tv_banke_name);
            viewHolder.ll_layot = (LinearLayout) convertView.findViewById(R.id.ll_layot);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.bankname.setText(data.get(position).get_$1().getList().get(0).getCode());
        viewHolder.ll_layot.setBackgroundColor(Color.rgb(63, 65, 78));
        return convertView;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder {
        TextView bankname;
        LinearLayout ll_layot;
    }

}
