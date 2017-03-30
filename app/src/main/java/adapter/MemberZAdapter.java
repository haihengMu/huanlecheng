package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import activity.huanlecheng.R;


/**
 * 转账页的adapter
 * Created by Administrator on 2016/12/12.
 */

public class MemberZAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;
    private TextView tv_name;

    public MemberZAdapter(Context applicationContext) {
        this.context=applicationContext;
    }

    public void setData(List<String> data) {
        this.list = data;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.state_item,null);
            tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        }
        tv_name.setText(list.get(position));

        return convertView;
    }
    @Override
    public int getCount() {
        return 3;
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
