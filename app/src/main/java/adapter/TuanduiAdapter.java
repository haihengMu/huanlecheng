package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import activity.huanlecheng.R;
import bean.MsgTuanBean;

/**
 * Created by Administrator on 2016/12/14.
 */

public class TuanduiAdapter extends BaseAdapter {
    private Context context;
    private List<MsgTuanBean> list;

    public TuanduiAdapter(Context applicationContext) {
        this.context=applicationContext;
    }
    public void setData(List<MsgTuanBean> data) {
        this.list = data;
        notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.activity_tuan_money_item,null);
            viewHolder.tv_ding_num= (TextView) convertView.findViewById(R.id.tv_ding_num);
            viewHolder.tv_user= (TextView) convertView.findViewById(R.id.tv_user_num);
            viewHolder.tv_bank= (TextView) convertView.findViewById(R.id.tv_adress);
            viewHolder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_qu_money= (TextView) convertView.findViewById(R.id.tv_que_money);
            viewHolder.tv_fuwu_money= (TextView) convertView.findViewById(R.id.tv_fuwu_money);
            viewHolder.tv_state= (TextView) convertView.findViewById(R.id.tv_state);
            viewHolder.tv_time= (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_ding_num.setText(list.get(position).getT_L_OrderId());
        viewHolder.tv_user.setText(list.get(position).getT_L_UserName());
        viewHolder.tv_bank.setText(list.get(position).getT_L_Bank());
        viewHolder.tv_name.setText(list.get(position).getT_L_AccountName());
        viewHolder.tv_qu_money.setText(list.get(position).getT_L_Money()+"元");
        viewHolder.tv_fuwu_money.setText(list.get(position).getT_L_Servicefee());
        if (list.get(position).getT_L_State().equals("1")){
            viewHolder.tv_state.setText("已支付");
        }else  if (list.get(position).getT_L_State().equals("2")){
            viewHolder.tv_state.setText("处理中");
        }else  if (list.get(position).getT_L_State().equals("3")){
            viewHolder.tv_state.setText("已拒绝");
        }
        viewHolder.tv_time.setText(list.get(position).getT_L_AddTime());
        return convertView;
    }
    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



    class ViewHolder{
        TextView tv_ding_num,tv_user,tv_bank,tv_name,tv_qu_money,tv_fuwu_money,tv_state,tv_time;
    }
}
