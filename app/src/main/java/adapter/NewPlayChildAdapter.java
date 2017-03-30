package adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import activity.huanlecheng.R;
import bean.FirstEvent;
import view.ViewHolder;

/**
 * Created by Administrator on 2017/3/27.
 */

public class NewPlayChildAdapter extends BaseAdapter {
    private Context context;
    private List<String> data;
    private String d;
    private int mlistViewPosition;
    private String mPid;
    private final EventBus aDefault;

    public NewPlayChildAdapter(Context context,int listViewPosition) {
        this.context = context;
        mlistViewPosition = listViewPosition;
        aDefault = EventBus.getDefault();
    }

    public void setData(List<String> data, String d, String pid) {
        this.data = data;
        this.d = d;
        mPid = pid;
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
        final CheckBox bottom_tt = ViewHolder.get(convertView, R.id.th_text);
        bottom_tt.setText(data.get(position));
        bottom_tt.setTextColor(Color.parseColor("#ffffff"));
        bottom_tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("checked = ", bottom_tt.isChecked() + "");
                FirstEvent firstEvent = new FirstEvent();
                firstEvent.setGameId(mPid);
                firstEvent.setMlistViewPosition(mlistViewPosition);
                firstEvent.setChecked(bottom_tt.isChecked());
                firstEvent.setmMsg(bottom_tt.getText().toString());
                aDefault.post(firstEvent);
            }
        });
        if (d.equals("da")) {
            int a = data.size() / 2;
            if (position >= a) {
                bottom_tt.setChecked(true);
            } else {
                bottom_tt.setChecked(false);
            }
        }
        if (d.equals("xiao")) {
            int a = data.size() / 2;
            if (position < a) {
                bottom_tt.setChecked(true);
            } else {
                bottom_tt.setChecked(false);
            }
        }
        if (d.equals("quan")) {
            bottom_tt.setChecked(true);
        }
        if (d.equals("ji")) {
            if (Integer.parseInt(data.get(position)) % 2 == 0) {
                bottom_tt.setChecked(false);
            } else {
                bottom_tt.setChecked(true);
            }
        }
        if (d.equals("ou")) {
            if (Integer.parseInt(data.get(position)) % 2 == 0) {
                bottom_tt.setChecked(true);
            } else {
                bottom_tt.setChecked(false);
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
