package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import activity.huanlecheng.R;
import bean.MemberNowBean;
import bean.MsgNowBean;

/**
 * Created by Administrator on 2016/12/13.
 */

public class MemberNowAdapter extends BaseAdapter {
    private Context context;
    private List<MemberNowBean.DataBean.ListBean> mlist;

    public void setData(List<MemberNowBean.DataBean.ListBean> data) {
        this.mlist = data;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.member_list_item, null);
            viewHolder.tv_username = (TextView) convertView.findViewById(R.id.tv_username);
            viewHolder.tv_money = (TextView) convertView.findViewById(R.id.tv_money);
            viewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            viewHolder.tv_shangji = (TextView) convertView.findViewById(R.id.tv_shangji);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_username.setText(mlist.get(position).getH_u_name());
        viewHolder.tv_money.setText(mlist.get(position).getH_u_balance());
        viewHolder.tv_time.setText(mlist.get(position).getH_u_the_login_time());
        viewHolder.tv_shangji.setText(mlist.get(position).getH_u_proxy_list());
      /*  String a = mlist.get(position).getU_UpAgent();
        if (a.equals("[]")){

        }else {
            String b = a.substring(1, a.length() - 1);
            String[] arr = b.split(",");
            List<String> list = new ArrayList<>();
            for (int i = 0; i < arr.length; i++) {
                list.add(arr[i].substring(1, arr[i].length() - 1));
            }
            String c = "";
            for (int i = 0; i < list.size(); i++) {
                if (i == list.size() - 1) {
                    c += list.get(i);
                    break;
                }
                c += list.get(i) + "<";
            }
            viewHolder.tv_shangji.setText(c);
        }*/
        return convertView;
    }

    public MemberNowAdapter(Context applicationContext) {
        this.context = applicationContext;
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
        TextView tv_username, tv_money, tv_time, tv_shangji;
    }


}
