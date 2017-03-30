package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import activity.huanlecheng.ActivityZhuan;
import activity.huanlecheng.MemberActivity;
import activity.huanlecheng.MemberBJactivity;
import activity.huanlecheng.R;
import bean.MsgBean;

/**
 * Created by Administrator on 2016/12/7.
 */

public class MemberAdapter extends BaseAdapter {
    private List<MsgBean> list;
    private Context context;
    private String nowtime;//系统时间
    private FrameLayout fl_xiaxian;

    public MemberAdapter(Context applicationContext) {
        this.context = applicationContext;
    }

    public void setData(List<MsgBean> data, String time) {
        this.list = data;
        this.nowtime = time;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_huiyuan_guanli, null);
            viewHolder.member_number = (TextView) convertView.findViewById(R.id.member_number);
            viewHolder.menber_num = (TextView) convertView.findViewById(R.id.menber_num);
            viewHolder.member_money = (TextView) convertView.findViewById(R.id.member_money);
            viewHolder.member_state = (TextView) convertView.findViewById(R.id.member_state);
            viewHolder.ib_money = (ImageButton) convertView.findViewById(R.id.ib_money);
            viewHolder.ib_bianji = (ImageButton) convertView.findViewById(R.id.ib_bianji);
            viewHolder.ib_xiaxian = (ImageButton) convertView.findViewById(R.id.ib_xiaxian);
            viewHolder.tv_fandian = (TextView) convertView.findViewById(R.id.tv_fandian);
            viewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            fl_xiaxian = (FrameLayout) convertView.findViewById(R.id.fl_xiaxian);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.member_money.setText(list.get(position).getU_Money());
        viewHolder.tv_time.setText(list.get(position).getU_LastLoginTime());
        viewHolder.menber_num.setText(list.get(position).getU_UserName());
        viewHolder.tv_fandian.setText(list.get(position).getU_RebateA());
        viewHolder.member_number.setText(list.get(position).getU_Id());
        String time = list.get(position).getU_OnlineTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 此处会抛异常
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
      // 获取毫秒数
        long longDate =date.getTime();
        //long类型的 longdata,string类型的nowtime我想让他俩相减,但是我用Integer.parseInt(),    Integer.valueOf()
      //  Double.parseDouble()转换类型都不好使,报类型转换异常为什么
        long nowtime1= Long.parseLong(nowtime);

        long waite =nowtime1-longDate;
        long waite1=waite/1000;
        if (waite1<60){
            viewHolder.member_state.setText("在线");

        }else {
            viewHolder.member_state.setText("不在线");
        }
        viewHolder.ib_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ActivityZhuan.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name",list.get(position).getU_UserName());
                context.startActivity(intent);

            }
        });
        viewHolder.ib_bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MemberBJactivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("u_name",list.get(position).getU_Nickname());//用户昵称
                intent.putExtra("u_username",list.get(position).getU_UserName());//帐号名
                intent.putExtra("u_regtime",list.get(position).getU_RegTime());//注册时间
                intent.putExtra("u_lasttime",list.get(position).getU_LastLoginTime());//上次登录的时间
                intent.putExtra("u_id",list.get(position).getU_Id());//id
                context.startActivity(intent);
            }
        });

        if (list.get(position).getCountuid().equals("0")){
            fl_xiaxian.setVisibility(View.INVISIBLE);
        }else {//有下线
            fl_xiaxian.setVisibility(View.VISIBLE);
            viewHolder.ib_xiaxian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, MemberActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("a",list.get(position).getU_Id());
                    context.startActivity(intent);
                }
            });

        }

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


    class ViewHolder {
        TextView member_number, tv_fandian, tv_time, menber_num, member_money, member_state;
        ImageButton ib_money, ib_bianji, ib_xiaxian;
    }

}
