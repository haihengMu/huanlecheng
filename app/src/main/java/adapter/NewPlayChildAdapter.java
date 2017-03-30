package adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.List;

import activity.huanlecheng.R;
import view.ViewHolder;

/**
 * Created by Administrator on 2017/3/27.
 */

public class NewPlayChildAdapter extends BaseAdapter {
    private Context context;
    private List<String> data;
    private String d;

    public NewPlayChildAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<String> data, String d) {
        this.data = data;
        this.d = d;
        notifyDataSetChanged();
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.th_child_item, null);
        }
        final Button bottom_tt = ViewHolder.get(convertView, R.id.th_text);
        bottom_tt.setText(data.get(position));
        bottom_tt.setTextColor(Color.parseColor("#ffffff"));
        bottom_tt.setBackgroundResource(R.drawable.changfangs);
        bottom_tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottom_tt.setBackgroundResource(R.drawable.zhongback);
                notifyDataSetChanged();
            }
        });
        if (d.equals("da")){
            int a=data.size()/2;
            if (position>=a){
                bottom_tt.setBackgroundResource(R.drawable.zhongback);
            }else {
                bottom_tt.setBackgroundResource(R.drawable.changfangs);
            }
        }
        if (d.equals("xiao")){
            int a=data.size()/2;
            if (position<a){
                bottom_tt.setBackgroundResource(R.drawable.zhongback);
            }else {
                bottom_tt.setBackgroundResource(R.drawable.changfangs);
            }
        }
        if (d.equals("quan")){
            bottom_tt.setBackgroundResource(R.drawable.zhongback);
        }
        if (d.equals("ji")){
              if (Integer.parseInt(data.get(position))%2==0){
                  bottom_tt.setBackgroundResource(R.drawable.changfangs);
              }else {
                  bottom_tt.setBackgroundResource(R.drawable.zhongback);
          }
        }
        if (d.equals("ou")){
                if (Integer.parseInt(data.get(position))%2==0){
                    bottom_tt.setBackgroundResource(R.drawable.zhongback);
                }else {
                    bottom_tt.setBackgroundResource(R.drawable.changfangs);
            }
        }
    /*    bottom_tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (gm.isB()) {
                    gm.setB(false);
                    Intent intent = new Intent();
                    intent.setAction("action.compound");
                    intent.putExtra("position", position + "");
                    intent.putExtra("it", it);
                    intent.putExtra("tf", "0");// 0false 1true
                    intent.putExtra("gpid",gpid);
                    mContext.sendBroadcast(intent);
                } else {
                    gm.setB(true);
                    Intent intent = new Intent();
                    intent.setAction("action.compound");
                    intent.putExtra("position", position + "");
                    intent.putExtra("it", it);
                    intent.putExtra("tf", "1");// 0false 1true
                    intent.putExtra("gpid",gpid);
                    mContext.sendBroadcast(intent);
                }
            }
        });*/

        return convertView;

    }


}
