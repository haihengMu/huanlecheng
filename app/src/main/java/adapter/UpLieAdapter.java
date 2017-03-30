package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import activity.huanlecheng.R;
import bean.CaipiaoTopBean;
import util.CaipiaoDao;

/**
 * Created by Administrator on 2016/11/12.
 */

public class UpLieAdapter extends BaseAdapter {
    private List<CaipiaoTopBean> liePlaynameList;
    private Context context;
    private LayoutInflater inflater;

    public void setData(List<CaipiaoTopBean> data) {
        this.liePlaynameList = data;
        notifyDataSetChanged();
    }

    public UpLieAdapter(Context applicationContext) {
        this.context = applicationContext;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.up_lie_grid, null);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.iv_right = (ImageView) convertView.findViewById(R.id.iv_right);
            viewHolder.rl_tou= (RelativeLayout) convertView.findViewById(R.id.rl_tou);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(liePlaynameList.get(position).getG_P_Name());
        viewHolder.rl_tou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CaipiaoDao caipiaoDao = new CaipiaoDao(context);
                caipiaoDao.delByname(liePlaynameList.get(position).getG_P_Name());
                liePlaynameList.remove(position);
                Intent intent = new Intent();
                intent.setAction("action.topviewdel");
                intent.putExtra("position", position + "");
                context.sendBroadcast(intent);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    @Override
    public int getCount() {
        return liePlaynameList == null ? 0 : liePlaynameList.size();
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
        public TextView tv_name;
        public ImageView iv_right;
        public RelativeLayout rl_tou;
    }

}
