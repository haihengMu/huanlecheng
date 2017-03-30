package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import activity.huanlecheng.R;
import bean.NewPlayGameNameChildMode;
import util.FEventBus;
import util.JsonUtil;
import view.ClassGridView;
import view.ViewHolder;

/**
 * Created by Administrator on 2017/3/27.
 */

public class NewPlayAdapter extends BaseAdapter {
    private static final String TAG = "-----------------------";
    private Context context;
    private List<NewPlayGameNameChildMode.ListBean> date;
    private String name;
    private String key;
    private String value;
    private String pid;


    public NewPlayAdapter(FragmentActivity activity) {
        this.context = activity;
    }

    public void setDate(List<NewPlayGameNameChildMode.ListBean> date, String name, String pid) {
        this.date = date;
        this.pid=pid;
        this.name = name;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return date == null ? 0 : date.size();
    }

    @Override
    public Object getItem(int position) {
        return date.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.th_item, null);
        }
        final TextView bottom_tt = ViewHolder.get(convertView, R.id.th_text);
        Button da = ViewHolder.get(convertView, R.id.da);
        Button xiao = ViewHolder.get(convertView, R.id.xiao);
        Button quan = ViewHolder.get(convertView, R.id.quan);
        Button ji = ViewHolder.get(convertView, R.id.ji);
        Button ou = ViewHolder.get(convertView, R.id.ou);
        Button qing = ViewHolder.get(convertView, R.id.qing);
        LinearLayout kuanglaLayout = ViewHolder.get(convertView, R.id.kuang);
        if (name.equals("前二大小") || name.equals("后二大小") ||
                name.equals("一帆风顺") || name.equals("好事成双") ||
                name.equals("三星报喜") || name.equals("四季发财")) {
            kuanglaLayout.setVisibility(View.GONE);
        }else {
            kuanglaLayout.setVisibility(View.VISIBLE);
        }
        ClassGridView gridView = ViewHolder.get(convertView, R.id.gridview);
        final NewPlayGameNameChildMode.ListBean gm = date.get(position);
        String s = JsonUtil.toJson(gm);
        HashMap<String, Object> stringObjectHashMap = JsonUtil.parseJsonToMap(s);
        Iterator<Map.Entry<String, Object>> iterator = stringObjectHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> next = iterator.next();
            key = next.getKey();
            Object v = next.getValue();
            value = v + "";
            Log.e(TAG, "key = " + key + "; value = " + value);
        }
        if (value.indexOf("\\[")==-1){

        String[] arr = value.split("\\[");
        String[] arr1 = arr[1].split("\\]");
        String a=arr1[0].replace(" ","");
        String[] arr2 = a.split(",");
        final List<String> mmlist = new ArrayList<>();
        for (int i = 0; i < arr2.length; i++) {
            mmlist.add(arr2[i]);
        }
        final NewPlayChildAdapter nca = new NewPlayChildAdapter(context);
        gridView.setAdapter(nca);
        bottom_tt.setText(key);
        nca.setData(mmlist,"");
        da.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  EventBus.getDefault().post(new FEventBus("da",position+""));
                String d="da";
                nca.setData(mmlist,d);
                // TODO Auto-generated method stub
                String numberpostiton="";
                for (int i=0;i<mmlist.size();i++){
                    numberpostiton+=mmlist.get(i);
                }
                Intent intent = new Intent();
                intent.setAction("action.big");
                intent.putExtra("b", "大");
                intent.putExtra("position", position + "");
                intent.putExtra("positionnumber", numberpostiton);
                intent.putExtra("gpid",pid);
                context.sendBroadcast(intent);
            }
        });
        xiao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                EventBus.getDefault().post(new FEventBus("xiao",position+""));
                String d="xiao";
                nca.setData(mmlist,d);
                String numberpostiton="";
                for (int i=0;i<mmlist.size();i++){
                    numberpostiton+=mmlist.get(i);
                }
                Intent intent = new Intent();
                intent.setAction("action.big");
                intent.putExtra("b", "小");
                intent.putExtra("position", position+"");
                intent.putExtra("positionnumber", numberpostiton);
                intent.putExtra("gpid",pid);
                context.sendBroadcast(intent);
            }
        });
        quan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                EventBus.getDefault().post(new FEventBus("quan",position+""));
                String d="quan";
                nca.setData(mmlist,d);
                String numberpostiton="";
                for (int i=0;i<mmlist.size();i++){
                    numberpostiton+=mmlist.get(i);
                }
                Intent intent = new Intent();
                intent.setAction("action.big");
                intent.putExtra("b", "全");
                intent.putExtra("position", position+"");
                intent.putExtra("positionnumber", numberpostiton);
                intent.putExtra("gpid",pid);
                context.sendBroadcast(intent);
            }
        });
        ji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new FEventBus("ji",position+""));
                String d="ji";
                nca.setData(mmlist,d);
                String numberpostiton="";
                for (int i=0;i<mmlist.size();i++){
                    numberpostiton+=mmlist.get(i);
                }
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setAction("action.big");
                intent.putExtra("b", "奇");
                intent.putExtra("position", position+"");
                intent.putExtra("positionnumber", numberpostiton);
                intent.putExtra("gpid",pid);
                context.sendBroadcast(intent);
            }
        });
        ou.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
               // EventBus.getDefault().post(new FEventBus("ou",position+""));
                String d="ou";
                nca.setData(mmlist,d);
                String numberpostiton="";
                for (int i=0;i<mmlist.size();i++){
                    numberpostiton+=mmlist.get(i);
                }
                Intent intent = new Intent();
                intent.setAction("action.big");
                intent.putExtra("b", "偶");
                intent.putExtra("positionnumber", numberpostiton);
                intent.putExtra("position", position+"");
                intent.putExtra("gpid",pid);
                context.sendBroadcast(intent);
            }
        });
        qing.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String d="qing";
                nca.setData(mmlist,d);
                String numberpostiton="";
                for (int i=0;i<mmlist.size();i++){
                    numberpostiton+=mmlist.get(i);
                }
                Intent intent = new Intent();
                intent.setAction("action.big");
                intent.putExtra("b", "清");
                intent.putExtra("position", position+"");
                intent.putExtra("gpid",pid);
                context.sendBroadcast(intent);
            }
        });
        }
        return convertView;
    }


}
