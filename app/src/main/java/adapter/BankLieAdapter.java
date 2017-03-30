package adapter;

import android.app.Application;
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
import bean.BankLieBean;

/**
 * Created by Administrator on 2016/11/13.
 */

public class BankLieAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater mInflater;
    private List<BankLieBean> mlist;


    public BankLieAdapter(Application application) {
        this.context = application;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setData(List<BankLieBean> mlist) {
        this.mlist = mlist;
    }

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
        viewHolder.ll_layot.setBackgroundColor(Color.rgb(63, 65, 78));
        viewHolder.bankname.setText(mlist.get(position).getH_U_B_L_Bank_Account());

        return convertView;
    }

    @Override
    public int getCount() {
        return mlist == null ? 0 : mlist.size();
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
