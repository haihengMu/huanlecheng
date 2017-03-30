/**
 * 异步图片适配器  对应BatmipDemo
 */
package adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.List;

import activity.huanlecheng.R;
import bean.CaiPiaoNewTopBean;
import bean.NewPlayGameNameChild;
import util.CaipiaoDao;
import view.ViewHolder;

public class BuyChildAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ImageLoader mImageLoader;
    private DisplayImageOptions op;
    private List<NewPlayGameNameChild> mList;
    private String title;
    private CaipiaoDao caipiaoDao;
    private RelativeLayout rl_tou;
    private String da_title;

    public BuyChildAdapter(Context context, List<NewPlayGameNameChild> list, String title, String da_title) {
        this.title = title;
        this.mContext = context;
        this.mList = list;
        this.da_title = da_title;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
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
                    R.layout.down_lie_grid, null);
        }
        final TextView bottom_tt = ViewHolder.get(convertView, R.id.tv_name);
        ImageView iv_imageview = ViewHolder.get(convertView, R.id.iv_right);
        rl_tou = ViewHolder.get(convertView, R.id.rl_tou);
        caipiaoDao = new CaipiaoDao(mContext);
        rl_tou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CaiPiaoNewTopBean> list=caipiaoDao.findAllTop();
                int a=list.size();
            /*    List<CaiPiaoNewTopBean> list = caipiaoDao.findAllnewTop();
                boolean r = false;
                if (list.size() <= 0) {
                    String a=mList.get(position).getH_g_p_name();
                    String b=bottom_tt.getText().toString();
                    caipiaoDao.addnewtop(mList.get(position).getH_g_p_id(), mList.get(position).getH_g_p_name(), mList.get(position).getH_g_p_cid(), mList.get(position).getH_g_p_nid(),
                            mList.get(position).getH_g_p_tid(), mList.get(position).getH_g_p_gid(), mList.get(position).getH_g_p_rid(), mList.get(position).getH_g_p_one_amount(),
                            mList.get(position).getH_g_p_max_bet_mum(), mList.get(position).getH_g_p_bonus(), mList.get(position).getH_g_p_amount_step(), mList.get(position).getH_g_p_rebate_step(),
                            mList.get(position).getH_g_p_decimal(), mList.get(position).getH_g_p_return_off(), mList.get(position).getH_g_p_introduction(), mList.get(position).getH_g_p_example(),
                            mList.get(position).getH_g_p_max_imumbonus_rebate(), mList.get(position).getH_g_p_mini_mumbonus_rebate(), mList.get(position).getH_g_p_mini_bet_money(), mList.get(position).getH_g_p_max_bet_money(), mList.get(position).getH_g_p_max_bonus(),
                            mList.get(position).getH_g_p_max_bonus_mode(), mList.get(position).getH_g_p_not_bet_code(), mList.get(position).getH_g_p_singled_num(), mList.get(position).getH_g_p_max_bonus(), title);
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        if (!list.get(i).getH_g_p_name().equals(mList.get(position).getH_g_p_name())) {
                            if (!list.get(i).getTitle().equals(title)) {
                                r = true;
                            } else {
                                r = true;
                            }
                        } else {
                            if (!list.get(i).getTitle().equals(title)) {
                                r = true;
                            } else {
                                r = false;
                                break;
                            }
                        }
                    }
                }
                if (r) {
                    caipiaoDao.addnewtop(mList.get(position).getH_g_p_id(), mList.get(position).getH_g_p_name(), mList.get(position).getH_g_p_cid(), mList.get(position).getH_g_p_nid(),
                            mList.get(position).getH_g_p_tid(), mList.get(position).getH_g_p_gid(), mList.get(position).getH_g_p_rid(), mList.get(position).getH_g_p_one_amount(),
                            mList.get(position).getH_g_p_max_bet_mum(), mList.get(position).getH_g_p_bonus(), mList.get(position).getH_g_p_amount_step(), mList.get(position).getH_g_p_rebate_step(),
                            mList.get(position).getH_g_p_decimal(), mList.get(position).getH_g_p_return_off(), mList.get(position).getH_g_p_introduction(), mList.get(position).getH_g_p_example(),
                            mList.get(position).getH_g_p_max_imumbonus_rebate(), mList.get(position).getH_g_p_mini_mumbonus_rebate(), mList.get(position).getH_g_p_mini_bet_money(), mList.get(position).getH_g_p_max_bet_money(), mList.get(position).getH_g_p_max_bonus(),
                            mList.get(position).getH_g_p_max_bonus_mode(), mList.get(position).getH_g_p_not_bet_code(), mList.get(position).getH_g_p_singled_num(), mList.get(position).getH_g_p_max_bonus(), title);
                }*/
                Intent intent = new Intent();
                intent.setAction("action.topview");
                intent.putExtra("position", position + "");
                mContext.sendBroadcast(intent);
                notifyDataSetChanged();
            }
        }
        );
        final NewPlayGameNameChild gm = mList.get(position);
        if (title.indexOf("3D") != -1 || title.indexOf("时时乐") != -1) {
            if (da_title.equals("二星")) {
                if (position == 0 || position == 1) {
                    bottom_tt.setText(gm.getH_g_p_name() + "复式");
                } else if (position == 2 || position == 3) {
                    bottom_tt.setText(gm.getH_g_p_name() + "单式");
                }
            }
        } else if (title.indexOf("快3") != -1) {
            bottom_tt.setText(gm.getH_g_p_name());
        } else if (title.indexOf("赛车") != -1) {
            if (da_title.equals("大小") || da_title.equals("单双") || da_title.equals("龙虎")) {
                bottom_tt.setText(da_title + gm.getH_g_p_name());
            } else {
                bottom_tt.setText(gm.getH_g_p_name());
            }
        } else if (title.indexOf("11选5") == -1) {
            if (da_title.equals("任选")) {
                if (position == 1 || position == 0) {
                    bottom_tt.setText("任四" + gm.getH_g_p_name());
                } else if (position == 2 || position == 3 || position == 4 || position == 5 || position == 6) {
                    bottom_tt.setText("任三" + gm.getH_g_p_name());
                } else if (position == 7 || position == 8 || position == 9) {
                    bottom_tt.setText("任二" + gm.getH_g_p_name());
                }
            } else if (da_title.equals("和值")) {
                if (position == 1 || position == 0 || position == 2 || position == 3 || position == 4) {
                    bottom_tt.setText(gm.getH_g_p_name() + "直选");
                } else if (position == 5 || position == 6 || position == 7) {
                    bottom_tt.setText(gm.getH_g_p_name() + "组选");
                } else if (position == 8 || position == 9 || position == 10) {
                    bottom_tt.setText(gm.getH_g_p_name() + "尾数");
                }
            } else if (da_title.equals("二星")) {
                if (position == 0 || position == 1 || position == 2 || position == 3) {
                    bottom_tt.setText(gm.getH_g_p_name() + "直选");
                } else if (position == 4 || position == 5 || position == 6 || position == 7) {
                    bottom_tt.setText(gm.getH_g_p_name() + "组选");
                }
            } else if (position == 0 && gm.getH_g_p_name().equals("复式")) {
                bottom_tt.setText(da_title + gm.getH_g_p_name());
            } else if (position == 0 && gm.getH_g_p_name().equals("前三")) {
                bottom_tt.setText(gm.getH_g_p_name() + da_title);
            } else if (position == 1 && gm.getH_g_p_name().equals("中三")) {
                bottom_tt.setText(gm.getH_g_p_name() + da_title);
            } else if (position == 2 && gm.getH_g_p_name().equals("后三")) {
                bottom_tt.setText(gm.getH_g_p_name() + da_title);
            } else if (position == 1 && gm.getH_g_p_name().equals("单式")) {
                bottom_tt.setText(da_title + gm.getH_g_p_name());
            } else if (position == 3 && gm.getH_g_p_name().equals("组选24")) {
                bottom_tt.setText(da_title + gm.getH_g_p_name());
            } else if (position == 2 && gm.getH_g_p_name().equals("组三")) {
                bottom_tt.setText(da_title + gm.getH_g_p_name());
            } else if (position == 4 && gm.getH_g_p_name().equals("组六")) {
                bottom_tt.setText(da_title + gm.getH_g_p_name());
            } else if (position == 2 && gm.getH_g_p_name().equals("组合")) {
                bottom_tt.setText(da_title + gm.getH_g_p_name());
            } else if (position == 4 && gm.getH_g_p_name().equals("组选12")) {
                bottom_tt.setText(da_title + gm.getH_g_p_name());
            } else if (position == 5 && gm.getH_g_p_name().equals("组选6")) {
                bottom_tt.setText(da_title + gm.getH_g_p_name());
            } else if (position == 6 && gm.getH_g_p_name().equals("组选4")) {
                bottom_tt.setText(da_title + gm.getH_g_p_name());
            } else if (position == 6 && gm.getH_g_p_name().equals("混合组选")) {
                bottom_tt.setText(da_title + "混合");
            } else {
                bottom_tt.setText(gm.getH_g_p_name());
            }

        } else {
            if (da_title.equals("任选")) {
                if (position == 0 || position == 1 || position == 2 || position == 3 || position == 4 || position == 5 || position == 6 || position == 7) {
                    bottom_tt.setText(da_title + gm.getH_g_p_name() + "复式");
                } else if (position == 8 || position == 9 || position == 10 || position == 11 || position == 12 || position == 13 || position == 14 || position == 15) {
                    bottom_tt.setText(da_title + gm.getH_g_p_name() + "单式");
                }
            } else if (da_title.equals("二星")) {
                if (position == 0 || position == 1 || position == 2 || position == 3) {
                    bottom_tt.setText(gm.getH_g_p_name() + "直选");
                } else if (position == 4 || position == 5 || position == 6 || position == 7) {
                    bottom_tt.setText(gm.getH_g_p_name() + "组选");
                }
            } else if (da_title.equals("胆拖")) {
                bottom_tt.setText("任选" + gm.getH_g_p_name());
            } else if (position == 0 && gm.getH_g_p_name().equals("前三")) {
                bottom_tt.setText(gm.getH_g_p_name() + da_title);
            } else if (position == 1 && gm.getH_g_p_name().equals("中三")) {
                bottom_tt.setText(gm.getH_g_p_name() + da_title);
            } else if (position == 2 && gm.getH_g_p_name().equals("后三")) {
                bottom_tt.setText(gm.getH_g_p_name() + da_title);
            } else {
                bottom_tt.setText(gm.getH_g_p_name());
            }
        }
        return convertView;

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
