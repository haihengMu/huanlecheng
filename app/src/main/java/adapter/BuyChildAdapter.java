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

import java.util.ArrayList;
import java.util.List;

import activity.huanlecheng.R;
import bean.CaiPiaoNewTopBean;
import bean.NewPlayGameNameChild;
import bean.NewPlayGameNameChildMode;
import util.CaipiaoDao;
import view.ViewHolder;

public class BuyChildAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ImageLoader mImageLoader;
    private List<NewPlayGameNameChild> mList;
    private String title;
    private CaipiaoDao caipiaoDao;
    private RelativeLayout rl_tou;
    private String da_title;
    List<NewPlayGameNameChildMode.ListBean> mmllist = new ArrayList<>();

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
                                          List<CaiPiaoNewTopBean> list = caipiaoDao.findAllnewTop();
                                          boolean r = false;
                                          if (list.size() <= 0) {
                                              String a = bottom_tt.getText().toString();
                                              caipiaoDao.addnewtop(bottom_tt.getText().toString(), mList.get(position).getH_g_p_id(), mList.get(position).getH_g_p_cid(), mList.get(position).getH_g_p_nid(), mList.get(position).getH_g_p_tid(), mList.get(position).getH_g_p_gid(), mList.get(position).getH_g_p_rid(), mList.get(position).getH_g_p_one_amount(),
                                                      mList.get(position).getH_g_p_max_bet_mum(), mList.get(position).getH_g_p_bonus(), mList.get(position).getH_g_p_amount_step(), mList.get(position).getH_g_p_rebate_step(),
                                                      mList.get(position).getH_g_p_decimal(), mList.get(position).getH_g_p_return_off(), mList.get(position).getH_g_p_introduction(), mList.get(position).getH_g_p_example(),
                                                      mList.get(position).getH_g_p_max_imumbonus_rebate(), mList.get(position).getH_g_p_mini_mumbonus_rebate(), mList.get(position).getH_g_p_mini_bet_money(), mList.get(position).getH_g_p_max_bet_money(), mList.get(position).getH_g_p_max_bonus(),
                                                      mList.get(position).getH_g_p_max_bonus_mode(), mList.get(position).getH_g_p_not_bet_code(), mList.get(position).getH_g_p_singled_num(), mList.get(position).getH_g_p_max_bonus(), title);
                                          } else {
                                              for (int i = 0; i < list.size(); i++) {
                                                  if (!list.get(i).getH_g_p_name().equals(bottom_tt.getText().toString())) {
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

                                              caipiaoDao.addnewtop(bottom_tt.getText().toString(), mList.get(position).getH_g_p_id(), mList.get(position).getH_g_p_cid(), mList.get(position).getH_g_p_nid(),
                                                      mList.get(position).getH_g_p_tid(), mList.get(position).getH_g_p_gid(), mList.get(position).getH_g_p_rid(), mList.get(position).getH_g_p_one_amount(),
                                                      mList.get(position).getH_g_p_max_bet_mum(), mList.get(position).getH_g_p_bonus(), mList.get(position).getH_g_p_amount_step(), mList.get(position).getH_g_p_rebate_step(),
                                                      mList.get(position).getH_g_p_decimal(), mList.get(position).getH_g_p_return_off(), mList.get(position).getH_g_p_introduction(), mList.get(position).getH_g_p_example(),
                                                      mList.get(position).getH_g_p_max_imumbonus_rebate(), mList.get(position).getH_g_p_mini_mumbonus_rebate(), mList.get(position).getH_g_p_mini_bet_money(), mList.get(position).getH_g_p_max_bet_money(), mList.get(position).getH_g_p_max_bonus(),
                                                      mList.get(position).getH_g_p_max_bonus_mode(), mList.get(position).getH_g_p_not_bet_code(), mList.get(position).getH_g_p_singled_num(), mList.get(position).getH_g_p_max_bonus(), title);
                                          }
                                          Intent intent = new Intent();
                                          intent.setAction("action.topview");
                                          intent.putExtra("position", position + "");
                                          mContext.sendBroadcast(intent);
                                          notifyDataSetChanged();
                                      }
                                  }
        );
        final NewPlayGameNameChild gm = mList.get(position);
        if (da_title.equals("二星")) {
            if (position < 4) {
                bottom_tt.setText(gm.getH_g_p_name() + "直选");
            } else {
                bottom_tt.setText(gm.getH_g_p_name() + "组选");
            }
        } else if (da_title.equals("三星")) {
            if (position < 6) {
                bottom_tt.setText(gm.getH_g_p_name() + "直选");
            } else {
                bottom_tt.setText(gm.getH_g_p_name() + "组选");
            }

        } else if (title.equals("福彩3D") || title.equals("体彩排列3")) {
            if (da_title.equals("直选")) {
                bottom_tt.setText(gm.getH_g_p_name()+"直选");
            } else if (da_title.equals("组选")){
                bottom_tt.setText(gm.getH_g_p_name()+"组选");
            } else if (da_title.equals("定位胆")){
                bottom_tt.setText(gm.getH_g_p_name()+"定位胆");
            }else if (da_title.equals("和值")){
                bottom_tt.setText(gm.getH_g_p_name()+"和值");
            }
        } else if (da_title.equals("跨度") || da_title.equals("趣味")) {
            bottom_tt.setText(gm.getH_g_p_name());
        } else if (da_title.equals("任选")) {
            if (position < 2) {
                bottom_tt.setText("任四" + gm.getH_g_p_name());
            } else if (position > 6) {
                bottom_tt.setText("任二" + gm.getH_g_p_name());
            } else {
                bottom_tt.setText("任三" + gm.getH_g_p_name());
            }
        } else if (da_title.equals("大小单双")) {
            bottom_tt.setText(gm.getH_g_p_name() + "大小");
        } else if (da_title.equals("和值")) {
            if (position < 5) {
                bottom_tt.setText(gm.getH_g_p_name() + "直选" + da_title);
            } else if (position > 7) {
                bottom_tt.setText(gm.getH_g_p_name() + "组选" + da_title);
            } else {
                bottom_tt.setText(gm.getH_g_p_name() + "尾数" + da_title);
            }
        } else if (da_title.equals("胆拖")) {
            bottom_tt.setText(da_title);
        } else if (da_title.equals("前四") || da_title.equals("前三") || da_title.equals("中三") || da_title.equals("后三") || da_title.equals("后四")) {
            bottom_tt.setText(da_title + gm.getH_g_p_name());
        } else {
            bottom_tt.setText(gm.getH_g_p_name());
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
