package adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import activity.huanlecheng.R;
import bean.HXGameNameBean;
import bean.NameBean;
import util.CaipiaoDao;

/**
 * 自定义彩种的adapter
 * Created by Administrator on 2016/10/14.
 */

public class CustomAdapter extends BaseAdapter {
    //private CustomBean list;
    private Context context;
    private LayoutInflater inflater;
    private String[] str = new String[]{"中奖率高 玩法多样", "玩法简单 返奖率高", "高频彩种 好玩易中", "简单灵活 经济实惠", "赛车主题 乐趣无限", "想开就开 无需等待",
            "公益体彩 精彩纷呈", "种类多样 惊喜多多"};
    private ArrayList<HXGameNameBean> mlist;
    private ArrayList<String> list = new ArrayList<>();


    public CustomAdapter(Context applicationContext) {
        this.context = applicationContext;
        this.inflater = LayoutInflater.from(applicationContext);
    }

    public void setData(ArrayList<HXGameNameBean> hxList) {
        this.mlist = hxList;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_grid_item, null);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_jiesao = (TextView) convertView.findViewById(R.id.tv_jiesao);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_imageview);
            viewHolder.chacha= (ImageView) convertView.findViewById(R.id.iv_right);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(mlist.get(position).getH_g_n_title());
        for (int i = 0; i < str.length; i++) {
            list.add(str[i]);
        }
        //tv_name.setText(mlist.get(position).getG_N_Title());
        Random random = new Random();
        if (mlist.get(position).getH_g_n_id().equals("1") ||mlist.get(position).getH_g_n_id().equals("3") || mlist.get(position).getH_g_n_id().equals("2")
                || mlist.get(position).getH_g_n_id().equals("10")) {
            viewHolder.tv_jiesao.setText(list.get(0));
            int red = random.nextInt(210);
            int green = random.nextInt(210);
            int blue = random.nextInt(210);
            int color = Color.rgb(red, green, blue);
            viewHolder.tv_jiesao.setTextColor(color);
            viewHolder.imageView.setBackgroundResource(R.drawable.shishi);
        } else if (mlist.get(position).getH_g_n_id().equals("4") || mlist.get(position).getH_g_n_id().equals("16") || mlist.get(position).getH_g_n_id().equals("17")
                ||mlist.get(position).getH_g_n_id().equals("19") || mlist.get(position).getH_g_n_id().equals("18")) {
            viewHolder.tv_jiesao.setText(list.get(2));
            int red = random.nextInt(210);
            int green = random.nextInt(210);
            int blue = random.nextInt(210);
            int color = Color.rgb(red, green, blue);
            viewHolder.tv_jiesao.setTextColor(color);
            if (mlist.get(position).getH_g_n_id().equals("4") || mlist.get(position).getH_g_n_id().equals("18")) {
                viewHolder.imageView.setBackgroundResource(R.drawable.fencai);
            } else if (mlist.get(position).getH_g_n_id().equals("17")) {
                viewHolder.imageView.setBackgroundResource(R.drawable.han);
            } else {
                viewHolder.imageView.setBackgroundResource(R.drawable.logo_qtc_2_sm);
            }
        } else if (mlist.get(position).getH_g_n_id().equals("5") || mlist.get(position).getH_g_n_id().equals("6") || mlist.get(position).getH_g_n_id().equals("8")
                || mlist.get(position).getH_g_n_id().equals("7")) {
            viewHolder.tv_jiesao.setText(list.get(1));
            int red = random.nextInt(210);
            int green = random.nextInt(210);
            int blue = random.nextInt(210);
            int color = Color.rgb(red, green, blue);
            viewHolder.tv_jiesao.setTextColor(color);
            viewHolder.imageView.setBackgroundResource(R.drawable.xuan);
        } else if (mlist.get(position).getH_g_n_id().equals("9")) {
            viewHolder.tv_jiesao.setText(list.get(7));
            int red = random.nextInt(210);
            int green = random.nextInt(210);
            int blue = random.nextInt(210);
            int color = Color.rgb(red, green, blue);
            viewHolder.tv_jiesao.setTextColor(color);
            viewHolder.imageView.setBackgroundResource(R.drawable.kuai);
        } else if (mlist.get(position).getH_g_n_id().equals("12")) {
            viewHolder.tv_jiesao.setText(list.get(3));
            int red = random.nextInt(210);
            int green = random.nextInt(210);
            int blue = random.nextInt(210);
            int color = Color.rgb(red, green, blue);
            viewHolder.tv_jiesao.setTextColor(color);
            viewHolder.imageView.setBackgroundResource(R.drawable.fucai);
        } else if (mlist.get(position).getH_g_n_id().equals("11")) {
            viewHolder.tv_jiesao.setText(list.get(6));
            int red = random.nextInt(210);
            int green = random.nextInt(210);
            int blue = random.nextInt(210);
            int color = Color.rgb(red, green, blue);
            viewHolder.tv_jiesao.setTextColor(color);
            viewHolder.imageView.setBackgroundResource(R.drawable.pailie);
        } else if (mlist.get(position).getH_g_n_id().equals("13")) {
            viewHolder.tv_jiesao.setText(list.get(4));
            int red = random.nextInt(210);
            int green = random.nextInt(210);
            int blue = random.nextInt(210);
            int color = Color.rgb(red, green, blue);
            viewHolder.tv_jiesao.setTextColor(color);
            viewHolder.imageView.setBackgroundResource(R.drawable.saiche);
        } else if (mlist.get(position).getH_g_n_id().equals("14") || mlist.get(position).getH_g_n_id().equals("15")) {
            viewHolder.tv_jiesao.setText(list.get(5));
            int red = random.nextInt(210);
            int green = random.nextInt(210);
            int blue = random.nextInt(210);
            int color = Color.rgb(red, green, blue);
            viewHolder.tv_jiesao.setTextColor(color);
            viewHolder.imageView.setBackgroundResource(R.drawable.han);
        }
        viewHolder.chacha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean r=false;
                CaipiaoDao caipiaoDao = new CaipiaoDao(context);
                List<NameBean> nameBeanList = caipiaoDao.findAllname();
                if (nameBeanList.size()<=0) {
                    caipiaoDao.addname(mlist.get(position).getH_g_n_title(), mlist.get(position).getH_g_n_id());
                } else {
                    for (int i=0;i<nameBeanList.size();i++){
                        if (!nameBeanList.get(i).getName().equals(mlist.get(position).getH_g_n_title())){
                            r=true;
                        }else {
                            r=false;
                            break;
                        }
                    }
                }
                if (r){
                    caipiaoDao.addname(mlist.get(position).getH_g_n_title(), mlist.get(position).getH_g_n_id());
                }
                Intent intent = new Intent();
                intent.setAction("action.gradview");
                intent.putExtra("position", position + "");
                intent.putExtra("tf", "0");// 0false 1true
                context.sendBroadcast(intent);
            }

        });
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
        public TextView tv_name, tv_jiesao;
        public ImageView imageView,chacha;
    }

}
