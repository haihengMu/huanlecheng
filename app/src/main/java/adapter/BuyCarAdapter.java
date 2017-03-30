package adapter;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import activity.huanlecheng.R;
import bean.CaipiaoBean;
import util.CaipiaoDao;

/**
 * Created by Administrator on 2016/11/7.
 */

public class BuyCarAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater mInflater;
    private List<CaipiaoBean> caipiaolist;
    private TextView tv_name;
    private TextView touzhu_number;
    private TextView tv_zhushu;
    private TextView tv_code;
    private TextView tv_mode;
    private TextView tv_money;
    private TextView tv_play_name;
    private TextView tv_name_one;
    private ImageView iv_chacha;
    private CaipiaoDao caipiaoDao;
    private List<String> money;

    public BuyCarAdapter(Application application) {
        this.context=application;
        this.mInflater= LayoutInflater.from(application);
    }
    public void setData(List<CaipiaoBean> dTitleList, List<String> money) {
        this.caipiaolist = dTitleList;
        this.money=money;
        notifyDataSetChanged();
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        caipiaoDao = new CaipiaoDao(context);
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.buy_car_item,null);
        }
        tv_name_one = (TextView) convertView.findViewById(R.id.tv_name_one);
        tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        tv_play_name = (TextView) convertView.findViewById(R.id.tv_play_name);
        touzhu_number = (TextView) convertView.findViewById(R.id.touzhu_number);
        tv_zhushu = (TextView) convertView.findViewById(R.id.tv_zhushu);
        tv_code = (TextView) convertView.findViewById(R.id.tv_code);
        tv_mode = (TextView) convertView.findViewById(R.id.tv_mode);
        tv_money = (TextView) convertView.findViewById(R.id.tv_money);
        iv_chacha = (ImageView) convertView.findViewById(R.id.iv_chacha);

        iv_chacha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caipiaoDao.delByTime(caipiaolist.get(position).getTime());
             //   Toast.makeText(context,caipiaolist.get(position).getTime()+"",Toast.LENGTH_SHORT).show();
                caipiaolist.remove(position);
                notifyDataSetChanged();
                Intent intent=new Intent();
                intent.setAction("action.delete");
                context.sendBroadcast(intent);

            }
        });
      /*  96269;
        12579;*/
        Random random=new Random();
        int red=random.nextInt(240);
        int green=random.nextInt(240);
        int blue=random.nextInt(240);
        int color= Color.rgb(red,green,blue);
        tv_name_one.setText(caipiaolist.get(position).getHx_name().substring(0,1));
        tv_name_one.setTextColor(color);
        tv_name.setText(caipiaolist.get(position).getHx_name());
        tv_play_name.setText(caipiaolist.get(position).getName());
        touzhu_number.setText(caipiaolist.get(position).getNumber());
        tv_zhushu.setText(caipiaolist.get(position).getCode_text());
        tv_code.setText(caipiaolist.get(position).getMultiple());
        tv_money.setText(money.get(position));
        tv_mode.setText(caipiaolist.get(position).getMode());


        return convertView;
    }
    @Override
    public int getCount() {
        return caipiaolist==null?0:caipiaolist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



}
