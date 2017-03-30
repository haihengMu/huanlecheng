package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import activity.huanlecheng.MoreActivity;
import activity.huanlecheng.R;
import bean.NewPlayGameNameChild;
import bean.PlayGameNewChildBean;

/**
 * Created by Administrator on 2016/11/22.
 */

public class MoreDownListAdapter extends BaseAdapter {
    private Context context;
    private List<PlayGameNewChildBean> date;
    private List<NewPlayGameNameChild> mlist;
    private String title;

    public MoreDownListAdapter(MoreActivity moreActivity) {
        this.context = moreActivity;
    }
    /**
     * 分类名称传过来的值
     */
    public void setData(List<NewPlayGameNameChild> mList, String title) {
        this.mlist=mList;
        this.title=title;
        notifyDataSetChanged();
    }
    public void setDate(List<PlayGameNewChildBean> date) {
        this.date = date;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_more_item, null, false);
            viewHolder.gridView = (GridView) convertView.findViewById(R.id.listview_item_gridview);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(date.get(position).getH_g_t_title());
       if (mlist !=null){
           if (viewHolder.gridView !=null){
               List<NewPlayGameNameChild> list=new ArrayList<>();
               for ( int i=0;i<mlist.size();i++){
                   if (mlist.get(i).getH_g_p_tid().equals(date.get(position).getH_g_t_id())){
                       list.add(mlist.get(i));
                   }
               }
               BuyChildAdapter buyChildAdapter=new BuyChildAdapter(context,list,title,viewHolder.textView.getText().toString());
               viewHolder.gridView.setAdapter(buyChildAdapter);

           }
       }

        return convertView;
    }

    @Override
    public int getCount() {
        return date == null ? 0 : date.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder {
        GridView gridView;
        TextView textView;
    }
}
