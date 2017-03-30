/**
 * 异步图片适配器  对应BatmipDemo
 */
package adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import activity.huanlecheng.R;
import bean.GetHistoryNewModesWinCodeBean;
import bean.GetHistoryNewWinCodeBean;
import util.Utils;
import view.AutoNextLineView;
import view.ViewHolder;

public class InfoChildAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ImageLoader mImageLoader;
    private DisplayImageOptions op;
    public GetHistoryNewWinCodeBean mList;
    public String title;

    public InfoChildAdapter(Context pContext) {
        this.mInflater = LayoutInflater.from(pContext);
        this.mContext = pContext;
        mImageLoader = ImageLoader.getInstance();
        op = getListOptions();

    }

    public void setData(GetHistoryNewWinCodeBean mList, String title) {
        this.mList = mList;
        this.title=title;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return mList == null ? 0 : mList.getData().size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.kaijiang_item, null);
        }
        TextView time = ViewHolder.get(convertView, R.id.shijian);
        TextView date = ViewHolder.get(convertView, R.id.riqi);
        AutoNextLineView alAutoNextLineView = ViewHolder.get(convertView,
                R.id.AutoNextLineLayout);
        alAutoNextLineView.setmCellHeight(Utils.dip2px(mContext, 32));
        alAutoNextLineView.setmCellWidth(Utils.dip2px(mContext, 40));
        final GetHistoryNewModesWinCodeBean gm = mList.getData().get(position);
        String[] l = mList.getData().get(position).getCode().split(",");
  /*      List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        List<String> list4 = new ArrayList<>();*/
        alAutoNextLineView.removeAllViews();
        for (int i = 0; i < l.length; i++) {
            System.out.println(l[i]);
            Button button = new Button(mContext);
            button.setText(l[i] + "");
            button.setTextSize(13);
            button.setBackgroundResource(R.drawable.kj_ring);
            button.setTextColor(Color.parseColor("#ffc300"));
            alAutoNextLineView.addView(button);
        }
        time.setText(gm.getTime());
        date.setText(gm.getIssue());
        return convertView;
  /*      if (title.equals("韩国1.5分彩") || title.equals("东京1.5分彩") || title.equals("菲律宾1.5分彩")) {
            int temp = 0;
            int temp1 = 0;
            int temp2 = 0;
            int temp3 = 0;
            int temp4 = 0;
            for (int i = 0; i < l.length; i++) {
                System.out.println(l[i]);
                if (i >= 0 && i < 4) {
                    list.add(l[i]);
                } else if (i >= 4 && i < 8) {
                    list1.add(l[i]);
                } else if (i >= 8 && i < 12) {
                    list2.add(l[i]);
                } else if (i >= 12 && i < 16) {
                    list3.add(l[i]);
                } else if (i >= 16 && i < 20) {
                    list4.add(l[i]);
                }
            }

            for (int i = 0; i < list.size(); i++) {
                temp += Integer.valueOf(list.get(i));
            }
            for (int i = 0; i < list1.size(); i++) {
                temp1 += Integer.valueOf(list1.get(i));
            }
            for (int i = 0; i < list2.size(); i++) {
                temp2 += Integer.valueOf(list2.get(i));
            }
            for (int i = 0; i < list3.size(); i++) {
                temp3 += Integer.valueOf(list3.get(i));
            }
            for (int i = 0; i < list4.size(); i++) {
                temp4 += Integer.valueOf(list4.get(i));
            }
            List<String> llist = new ArrayList<>();
            llist.add(temp + "");
            llist.add(temp1 + "");
            llist.add(temp2 + "");
            llist.add(temp3 + "");
            llist.add(temp4 + "");
            List<String> lllist = new ArrayList<>();
            for (int i = 0; i < llist.size(); i++) {
                String str = llist.get(i).substring(llist.get(i).length() - 1, llist.get(i).length());
                lllist.add(str);
            }
            System.out.println(lllist + "qqqqq");
            for (int i = 0; i < llist.size(); i++) {
                System.out.println(l[i]);
                Button button = new Button(mContext);
                button.setText(lllist.get(i));
                button.setTextSize(13);
                button.setBackgroundResource(R.drawable.kj_ring);
                button.setTextColor(Color.parseColor("#ffc300"));
                alAutoNextLineView.addView(button);
            }
        } else if (title.equals("台湾5分彩")) {
            int temp = 0;
            int temp1 = 0;
            int temp2 = 0;
            int temp3 = 0;
            int temp4 = 0;
            for (int i = 0; i < l.length; i++) {
                //  System.out.println(l[i]);
                if (i >= 0 && i < 4) {
                    list.add(l[i]);
                } else if (i >= 4 && i < 8) {
                    list1.add(l[i]);
                } else if (i >= 8 && i < 12) {
                    list2.add(l[i]);
                } else if (i >= 12 && i < 16) {
                    list3.add(l[i]);
                } else if (i >= 16 && i < 20) {
                    if (i == 19) {
                        String[] arr1 = l[i].split(" ");
                        String a = arr1[0];
                        list4.add(a);
                    } else {
                        list4.add(l[i]);
                    }
                }
            }

            for (int i = 0; i < list.size(); i++) {
                temp += Integer.valueOf(list.get(i));
            }
            for (int i = 0; i < list1.size(); i++) {
                temp1 += Integer.valueOf(list1.get(i));
            }
            for (int i = 0; i < list2.size(); i++) {
                temp2 += Integer.valueOf(list2.get(i));
            }
            for (int i = 0; i < list3.size(); i++) {
                temp3 += Integer.valueOf(list3.get(i));
            }
            for (int i = 0; i < list4.size(); i++) {
                temp4 += Integer.valueOf(list4.get(i));
            }
            List<String> llist = new ArrayList<>();
            llist.add(temp + "");
            llist.add(temp1 + "");
            llist.add(temp2 + "");
            llist.add(temp3 + "");
            llist.add(temp4 + "");
            List<String> lllist = new ArrayList<>();
            for (int i = 0; i < llist.size(); i++) {
                String str = llist.get(i).substring(llist.get(i).length() - 1, llist.get(i).length());
                lllist.add(str);
            }
            System.out.println(lllist + "qqqqq");
            for (int i = 0; i < llist.size(); i++) {
                System.out.println(l[i]);
                Button button = new Button(mContext);
                button.setText(lllist.get(i));
                button.setTextSize(13);
                button.setBackgroundResource(R.drawable.kj_ring);
                button.setTextColor(Color.parseColor("#ffc300"));
                alAutoNextLineView.addView(button);
            }
        } else {

        }*/


    }

    public DisplayImageOptions getListOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(false) // 设置图片不缓存于内存中
                .cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565) // 设置图片的质量
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT) // 设置图片的缩放类型，该方
                .build();
        return options;
    }


}
