package adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import activity.huanlecheng.BuyChildActivty1;
import activity.huanlecheng.R;
import bean.GetHistoryNewWinCodeBean;

/**
 * 购彩大厅中每种玩法上面的listview的
 * Created by Administrator on 2016/10/19.
 */

public class BuyChildListAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater mInflater;
    private final List<String> list;
    private GetHistoryNewWinCodeBean myList;
    private String title;

    public BuyChildListAdapter(BuyChildActivty1 buyChildActivty1) {
        this.context = buyChildActivty1;
        this.mInflater = LayoutInflater.from(buyChildActivty1);
        list = new ArrayList<>();
    }

    public void setDate(GetHistoryNewWinCodeBean myList, String title) {
        this.myList = myList;
        this.title=title;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String[] listW_Code = myList.getData().get(position).getNative_code().split(",");
        if (listW_Code.length == 5) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.buy_list_item, null);
                viewHolder.ll_layout = (LinearLayout) convertView.findViewById(R.id.ll_layout);
                viewHolder.tv_issue = (TextView) convertView.findViewById(R.id.tv_issue);
                viewHolder.tv_num1 = (TextView) convertView.findViewById(R.id.tv_num1);
                viewHolder.tv_num2 = (TextView) convertView.findViewById(R.id.tv_num2);
                viewHolder.tv_num3 = (TextView) convertView.findViewById(R.id.tv_num3);
                viewHolder.tv_num4 = (TextView) convertView.findViewById(R.id.tv_num4);
                viewHolder.tv_num5 = (TextView) convertView.findViewById(R.id.tv_num5);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            if (position % 2 == 0) {
                viewHolder.ll_layout.setBackgroundResource(R.drawable.denglu);
            } else {
                viewHolder.ll_layout.setBackgroundResource(Color.alpha(0));
            }
            String a = myList.getData().get(position).getIssue();
            String b = a.substring(a.length() - 3, a.length());
            viewHolder.tv_issue.setText(b);
            if (position == 0) {
                viewHolder.tv_issue.setText(myList.getData().get(position).getIssue());
                for (int i = 0; i < listW_Code.length; i++) {
                    list.add(listW_Code[i]);
                }
                viewHolder.tv_num1.setText(list.get(0));
                viewHolder.tv_num2.setText(list.get(1));
                viewHolder.tv_num3.setText(list.get(2));
                viewHolder.tv_num4.setText(list.get(3));
                viewHolder.tv_num5.setText(list.get(4));
                viewHolder.tv_num1.setBackgroundResource(R.drawable.kj_back);
                viewHolder.tv_num2.setBackgroundResource(R.drawable.kj_back);
                viewHolder.tv_num3.setBackgroundResource(R.drawable.kj_back);
                viewHolder.tv_num4.setBackgroundResource(R.drawable.kj_back);
                viewHolder.tv_num5.setBackgroundResource(R.drawable.kj_back);
            } else {
                for (int i = 0; i < listW_Code.length; i++) {
                    list.add(listW_Code[i]);
                }
                viewHolder.tv_num1.setText(list.get(0));
                viewHolder.tv_num2.setText(list.get(1));
                viewHolder.tv_num3.setText(list.get(2));
                viewHolder.tv_num4.setText(list.get(3));
                viewHolder.tv_num5.setText(list.get(4));
                viewHolder.tv_num2.setPadding(30, 0, 0, 0);
                viewHolder.tv_num3.setPadding(30, 0, 0, 0);
                viewHolder.tv_num4.setPadding(30, 0, 0, 0);
                viewHolder.tv_num5.setPadding(30, 0, 0, 0);
                viewHolder.tv_num1.setBackgroundResource(Color.alpha(0));
                viewHolder.tv_num2.setBackgroundResource(Color.alpha(0));
                viewHolder.tv_num3.setBackgroundResource(Color.alpha(0));
                viewHolder.tv_num4.setBackgroundResource(Color.alpha(0));
                viewHolder.tv_num5.setBackgroundResource(Color.alpha(0));
            }

//            viewHolder.tv_time.setText(mlist.get(position).getWinTime());
            list.clear();
        } else if (listW_Code.length == 3) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.buy_list_item_three, null);
                viewHolder.three_layout = (LinearLayout) convertView.findViewById(R.id.three_layout);
                viewHolder.three_issue = (TextView) convertView.findViewById(R.id.three_issue);
                viewHolder.three_num1 = (TextView) convertView.findViewById(R.id.three_num1);
                viewHolder.three_num2 = (TextView) convertView.findViewById(R.id.three_num2);
                viewHolder.three_num3 = (TextView) convertView.findViewById(R.id.three_num3);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            if (position % 2 == 0) {
                viewHolder.three_layout.setBackgroundResource(R.drawable.denglu);
            } else {
                viewHolder.three_layout.setBackgroundResource(Color.alpha(0));
            }
            String a = myList.getData().get(position).getIssue();
            String b = a.substring(a.length() - 3, a.length());
            if (position == 0) {
                viewHolder.three_issue.setText(myList.getData().get(position).getIssue());
                for (int i = 0; i < listW_Code.length; i++) {
                    list.add(listW_Code[i]);
                }
                viewHolder.three_num1.setText(list.get(0));
                viewHolder.three_num2.setText(list.get(1));
                viewHolder.three_num3.setText(list.get(2));
                viewHolder.three_num1.setPadding(1, 1, 1, 1);
                viewHolder.three_num2.setPadding(1, 1, 1, 1);
                viewHolder.three_num3.setPadding(1, 1, 1, 1);
                viewHolder.three_num1.setBackgroundResource(R.drawable.kj_back);
                viewHolder.three_num2.setBackgroundResource(R.drawable.kj_back);
                viewHolder.three_num3.setBackgroundResource(R.drawable.kj_back);
            } else {
                for (int i = 0; i < listW_Code.length; i++) {
                    list.add(listW_Code[i]);
                }
                viewHolder.three_issue.setText(b);
                viewHolder.three_num1.setText(list.get(0));
                viewHolder.three_num2.setText(list.get(1));
                viewHolder.three_num3.setText(list.get(2));
                viewHolder.three_num2.setPadding(40, 0, 0, 0);
                viewHolder.three_num3.setPadding(40, 0, 0, 0);
                viewHolder.three_num1.setBackgroundResource(Color.alpha(0));
                viewHolder.three_num2.setBackgroundResource(Color.alpha(0));
                viewHolder.three_num3.setBackgroundResource(Color.alpha(0));
            }

            list.clear();
        } else if (listW_Code.length == 10) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.buy_list_item_beijing, null);
                viewHolder.bother_layout = (LinearLayout) convertView.findViewById(R.id.bother_layout);
                viewHolder.btv_issue = (TextView) convertView.findViewById(R.id.bother_issue);
                viewHolder.btv_num1 = (TextView) convertView.findViewById(R.id.bother_num1);
                viewHolder.btv_num2 = (TextView) convertView.findViewById(R.id.bother_num2);
                viewHolder.btv_num3 = (TextView) convertView.findViewById(R.id.bother_num3);
                viewHolder.btv_num4 = (TextView) convertView.findViewById(R.id.bother_num4);
                viewHolder.btv_num5 = (TextView) convertView.findViewById(R.id.bother_num5);
                viewHolder.btv_num6 = (TextView) convertView.findViewById(R.id.bother_num6);
                viewHolder.btv_num7 = (TextView) convertView.findViewById(R.id.bother_num7);
                viewHolder.btv_num8 = (TextView) convertView.findViewById(R.id.bother_num8);
                viewHolder.btv_num9 = (TextView) convertView.findViewById(R.id.bother_num9);
                viewHolder.btv_num10 = (TextView) convertView.findViewById(R.id.bother_num10);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            if (position % 2 == 0) {
                viewHolder.bother_layout.setBackgroundResource(R.drawable.denglu);
            } else {
                viewHolder.bother_layout.setBackgroundResource(Color.alpha(0));
            }
            String a = myList.getData().get(position).getIssue();
            String b = a.substring(a.length() - 3, a.length());
            if (position == 0) {
                viewHolder.btv_issue.setText(myList.getData().get(position).getIssue());
                for (int i = 0; i < listW_Code.length; i++) {
                    list.add(listW_Code[i]);
                }
                viewHolder.btv_num1.setText(list.get(0));
                viewHolder.btv_num2.setText(list.get(1));
                viewHolder.btv_num3.setText(list.get(2));
                viewHolder.btv_num4.setText(list.get(3));
                viewHolder.btv_num5.setText(list.get(4));
                viewHolder.btv_num6.setText(list.get(5));
                viewHolder.btv_num7.setText(list.get(6));
                viewHolder.btv_num8.setText(list.get(7));
                viewHolder.btv_num9.setText(list.get(8));
                viewHolder.btv_num10.setText(list.get(9));

                viewHolder.btv_num1.setBackgroundResource(R.drawable.kj_back);
                viewHolder.btv_num2.setBackgroundResource(R.drawable.kj_back);
                viewHolder.btv_num3.setBackgroundResource(R.drawable.kj_back);
                viewHolder.btv_num4.setBackgroundResource(R.drawable.kj_back);
                viewHolder.btv_num5.setBackgroundResource(R.drawable.kj_back);
                viewHolder.btv_num6.setBackgroundResource(R.drawable.kj_back);
                viewHolder.btv_num7.setBackgroundResource(R.drawable.kj_back);
                viewHolder.btv_num8.setBackgroundResource(R.drawable.kj_back);
                viewHolder.btv_num9.setBackgroundResource(R.drawable.kj_back);
                viewHolder.btv_num10.setBackgroundResource(R.drawable.kj_back);
            } else {
                for (int i = 0; i < listW_Code.length; i++) {
                    list.add(listW_Code[i]);
                }
                viewHolder.btv_issue.setText(b);
                viewHolder.btv_num1.setText(list.get(0));
                viewHolder.btv_num2.setText(list.get(1));
                viewHolder.btv_num3.setText(list.get(2));
                viewHolder.btv_num4.setText(list.get(3));
                viewHolder.btv_num5.setText(list.get(4));
                viewHolder.btv_num6.setText(list.get(5));
                viewHolder.btv_num7.setText(list.get(6));
                viewHolder.btv_num8.setText(list.get(7));
                viewHolder.btv_num9.setText(list.get(8));
                viewHolder.btv_num10.setText(list.get(9));
                viewHolder.btv_num1.setBackgroundResource(Color.alpha(0));
                viewHolder.btv_num2.setBackgroundResource(Color.alpha(0));
                viewHolder.btv_num3.setBackgroundResource(Color.alpha(0));
                viewHolder.btv_num4.setBackgroundResource(Color.alpha(0));
                viewHolder.btv_num5.setBackgroundResource(Color.alpha(0));
                viewHolder.btv_num6.setBackgroundResource(Color.alpha(0));
                viewHolder.btv_num7.setBackgroundResource(Color.alpha(0));
                viewHolder.btv_num8.setBackgroundResource(Color.alpha(0));
                viewHolder.btv_num9.setBackgroundResource(Color.alpha(0));
                viewHolder.btv_num10.setBackgroundResource(Color.alpha(0));
            }

            list.clear();
        } else {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.buy_list_item_other, null);
                viewHolder.other_layout = (LinearLayout) convertView.findViewById(R.id.other_layout);
                viewHolder.other_issue = (TextView) convertView.findViewById(R.id.other_issue);
                viewHolder.other_num1 = (TextView) convertView.findViewById(R.id.other_num1);
                viewHolder.other_num2 = (TextView) convertView.findViewById(R.id.other_num2);
                viewHolder.other_num3 = (TextView) convertView.findViewById(R.id.other_num3);
                viewHolder.other_num4 = (TextView) convertView.findViewById(R.id.other_num4);
                viewHolder.other_num5 = (TextView) convertView.findViewById(R.id.other_num5);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            if (position % 2 == 0) {
                viewHolder.other_layout.setBackgroundResource(R.drawable.denglu);
            } else {
                viewHolder.other_layout.setBackgroundResource(Color.alpha(0));
            }
            String[] l = myList.getData().get(position).getNative_code().split(",");
            List<String> list = new ArrayList<>();
            List<String> list1 = new ArrayList<>();
            List<String> list2 = new ArrayList<>();
            List<String> list3 = new ArrayList<>();
            List<String> list4 = new ArrayList<>();

            if (title.equals("韩国1.5分彩") || title.equals("东京1.5分彩") || title.equals("菲律宾1.5分彩")) {
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
                String a = myList.getData().get(position).getIssue();
                String b = a.substring(a.length() - 3, a.length());
                viewHolder.other_issue.setText(b);
                if (position == 0) {
                    viewHolder.other_issue.setText(myList.getData().get(position).getIssue());
                    viewHolder.other_num1.setText(lllist.get(0));
                    viewHolder.other_num2.setText(lllist.get(1));
                    viewHolder.other_num3.setText(lllist.get(2));
                    viewHolder.other_num4.setText(lllist.get(3));
                    viewHolder.other_num5.setText(lllist.get(4));
                    viewHolder.other_num1.setBackgroundResource(R.drawable.kj_back);
                    viewHolder.other_num2.setBackgroundResource(R.drawable.kj_back);
                    viewHolder.other_num3.setBackgroundResource(R.drawable.kj_back);
                    viewHolder.other_num4.setBackgroundResource(R.drawable.kj_back);
                    viewHolder.other_num5.setBackgroundResource(R.drawable.kj_back);
                } else {
                    viewHolder.other_num1.setText(lllist.get(0));
                    viewHolder.other_num2.setText(lllist.get(1));
                    viewHolder.other_num3.setText(lllist.get(2));
                    viewHolder.other_num4.setText(lllist.get(3));
                    viewHolder.other_num5.setText(lllist.get(4));
                    viewHolder.other_num1.setBackgroundResource(Color.alpha(0));
                    viewHolder.other_num2.setBackgroundResource(Color.alpha(0));
                    viewHolder.other_num3.setBackgroundResource(Color.alpha(0));
                    viewHolder.other_num4.setBackgroundResource(Color.alpha(0));
                    viewHolder.other_num5.setBackgroundResource(Color.alpha(0));
                }
            } else if (title.equals("台湾5分彩")) {
                int temp = 0;
                int temp1 = 0;
                int temp2 = 0;
                int temp3 = 0;
                int temp4 = 0;
                for (int i = 0; i < l.length; i++) {
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
                String a = myList.getData().get(position).getIssue();
                String b = a.substring(a.length() - 3, a.length());
                viewHolder.other_issue.setText(b);
                if (position == 0) {
                    viewHolder.other_issue.setText(myList.getData().get(position).getIssue());
                    viewHolder.other_num1.setText(lllist.get(0));
                    viewHolder.other_num2.setText(lllist.get(1));
                    viewHolder.other_num3.setText(lllist.get(2));
                    viewHolder.other_num4.setText(lllist.get(3));
                    viewHolder.other_num5.setText(lllist.get(4));
                    viewHolder.other_num1.setBackgroundResource(R.drawable.kj_back);
                    viewHolder.other_num2.setBackgroundResource(R.drawable.kj_back);
                    viewHolder.other_num3.setBackgroundResource(R.drawable.kj_back);
                    viewHolder.other_num4.setBackgroundResource(R.drawable.kj_back);
                    viewHolder.other_num5.setBackgroundResource(R.drawable.kj_back);
                } else {
                    viewHolder.other_num1.setText(lllist.get(0));
                    viewHolder.other_num2.setText(lllist.get(1));
                    viewHolder.other_num3.setText(lllist.get(2));
                    viewHolder.other_num4.setText(lllist.get(3));
                    viewHolder.other_num5.setText(lllist.get(4));
                    viewHolder.other_num1.setBackgroundResource(Color.alpha(0));
                    viewHolder.other_num2.setBackgroundResource(Color.alpha(0));
                    viewHolder.other_num3.setBackgroundResource(Color.alpha(0));
                    viewHolder.other_num4.setBackgroundResource(Color.alpha(0));
                    viewHolder.other_num5.setBackgroundResource(Color.alpha(0));
                }
            }
            list.clear();
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return myList == null ? 0 : myList.getData().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //重写事件禁用点击
    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }


    class ViewHolder {
        LinearLayout ll_layout, three_layout, other_layout, bother_layout;
        //        RelativeLayout bother_layout;
        TextView tv_issue, tv_num1, tv_num2, tv_num3, tv_num4, tv_num5, three_issue, three_num1, three_num2, three_num3, other_issue, other_num1, other_num2, other_num3, other_num4, other_num5;
        TextView btv_issue, btv_num1, btv_num2, btv_num3, btv_num4, btv_num5, btv_num6, btv_num7, btv_num8, btv_num9, btv_num10;
    }
}
