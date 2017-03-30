package fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import activity.huanlecheng.R;
import adapter.RecordsAdapter;
import bean.HxGame;
import bean.HxPlay;
import bean.UserBettingInfo;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import http.AjaxParams;
import util.JsonUtil;
import util.ToastUtil;
import view.XListView;

public class AllOrderFragment extends BaseFragment implements OnClickListener,
        XListView.IXListViewListener {
    private static final String TAG = "AllOrderFragment";
    private String channel_id;
    private String lo_id;
    private LinearLayout loading_layout;// 载入中
    private LinearLayout load_layout_fail;// 载入失败
    private TextView txt_neterr;// 载入失败
    private ImageView fail_btn;// 失败按钮 点击重新加载
    private View parentView;
    private XListView lv;
    private RecordsAdapter gAdapter;
    private int n = 1;
    private List<HxGame.DataBean> data;
    private UserBettingInfo bettingInfo;
    private HxPlay hxPlay;
    private List<UserBettingInfo.DataBean.ListBean> listData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        channel_id = args != null ? args.getString("id") : "";// 0 投注, 1 追号, 2账户明细
        lo_id = args != null ? args.getString("lo_id") : "";//0=全部 1=未开奖 2=已中奖 3=未中奖
        Log.e(TAG, "channel_id = " + channel_id + "; lo_id = " + lo_id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        parentView = inflater
                .inflate(R.layout.fragment_order, container, false);
        loading_layout = (LinearLayout) parentView.findViewById(R.id.view_loading);
        load_layout_fail = (LinearLayout) parentView
                .findViewById(R.id.view_load_fail);
        load_layout_fail.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                loading_layout.setVisibility(View.VISIBLE);
                load_layout_fail.setVisibility(View.GONE);
                try {
                    n = 1;
                    requestName();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        txt_neterr = (TextView) parentView.findViewById(R.id.txt_neterr);
        fail_btn = (ImageView) parentView.findViewById(R.id.fail_btn);
        fail_btn.setOnClickListener(this);
        listData = new ArrayList<>();
        gAdapter = new RecordsAdapter(getActivity());
        lv = (XListView) parentView.findViewById(R.id.main_lv_list);
        lv.setXListViewListener(this);
        lv.setPullLoadEnable(true);
        lv.setAdapter(gAdapter);
        try {
            n = 1;
            requestName();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return parentView;
    }

    /**
     * 获取数据
     *
     * @throws Exception
     */
    private void GetData() {
        AjaxParams params = new AjaxParams();
        String url = Constants.getBaseUrl() + "/static/model/public/json/game_play.json";
        wh.get(url, params, new AjaxCallBack<String>() {
            @Override
            public void onStart() {// 开始http请求的时候回调
                // TODO Auto-generated method stub
                super.onStart();

            }

            @Override
            public void onLoading(long count, long current) {// 每1秒钟自动被回调一次
                // TODO Auto-generated method stub
                super.onLoading(count, current);

            }

            @Override
            public void onSuccess(String t) {// 加载成功的时候回调
                // TODO Auto-generated method stub
                super.onSuccess(t);
                Message msg = new Message();
                msg.what = 0;// 第一次
//                if (n == 1) {
//                    msg.what = 0;// 第一次
//                } else {
//                    msg.what = 1;// loadmore
//                }
                msg.obj = t;
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) { // 加载失败的时候回调
                // TODO Auto-generated method stub
                super.onFailure(t, errorNo, strMsg);
                if (n == 1) {
                    if (lv.getVisibility() == 0) {
                        lv.stopFailRefresh();
                        ToastUtil.showToast(getActivity(), Constants.NETERROR);
                    } else {
                        loading_layout.setVisibility(View.GONE);
                        lv.setVisibility(View.GONE);
                        lv.stopFailRefresh();
                        load_layout_fail.setVisibility(View.VISIBLE);
                        txt_neterr.setText(Constants.NETERROR + "  点击上方按钮重新加载");
                    }
                } else {
                    n = n - 1;
                    lv.stopLoadMore();
                    ToastUtil.showToast(getActivity(), Constants.NETERROR);
                }

            }

        });
    }

    /**
     * 彩票名称
     */
    public void requestName() {
        AjaxParams params = new AjaxParams();
        wh.configCookieStore(RUser.cookieStore);
        wh.get(Constants.getUrl() + "/static/model/public/json/game_name.json", params, new AjaxCallBack<String>() {
            @Override
            public void onStart() {// 开始http请求的时候回调
                // TODO Auto-generated method stub
                super.onStart();
            }

            @Override
            public void onLoading(long count, long current) {// 每1秒钟自动被回调一次
                // TODO Auto-generated method stub
                super.onLoading(count, current);

            }

            @Override
            public void onSuccess(String t) {// 加载成功的时候回调
                // TODO Auto-generated method stub
                super.onSuccess(t);
                Message msg = new Message();
                msg.what = 2;
                msg.obj = t;
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) { // 加载失败的时候回调
                // TODO Auto-generated method stub
                super.onFailure(t, errorNo, strMsg);
                ToastUtil.showToast(getActivity(), Constants.NETERROR);
                loading_layout.setVisibility(View.GONE);
                lv.setVisibility(View.GONE);
                lv.stopFailRefresh();
                load_layout_fail.setVisibility(View.VISIBLE);
                txt_neterr.setText(Constants.NETERROR + " 点击屏幕重新加载");
            }
        });

    }

    /**
     * 获取投注信息
     */
    public void requestLoName() {
        AjaxParams params = new AjaxParams();
        params.put("key", "");
        params.put("type1", "0");
        params.put("type2", "");
        params.put("showpagenum", n + "");
        if (lo_id.equals("0")) {
            params.put("type3", "0");
        } else if (lo_id.equals("1")) {
            params.put("type3", "1");
        } else if (lo_id.equals("2")) {
            params.put("type3", "2");
        } else if (lo_id.equals("3")) {
            params.put("type3", "3");
        }
        wh.configCookieStore(RUser.cookieStore);
        String url = Constants.getUrl();
        if ("0".equals(channel_id)) {
            // 投注
            url = url + Constants.getbetLog;
        } else {
            // 追号
            url = url + Constants.getchaselog;
        }
        wh.post(url, params, new AjaxCallBack<String>() {
            @Override
            public void onStart() {// 开始http请求的时候回调
                super.onStart();

            }

            @Override
            public void onLoading(long count, long current) {// 每1秒钟自动被回调一次
                super.onLoading(count, current);

            }

            @Override
            public void onSuccess(String t) {// 加载成功的时候回调
                // TODO Auto-generated method stub
                super.onSuccess(t);
                Message msg = new Message();
                msg.what = 3;
                msg.obj = t;
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) { // 加载失败的时候回调
                // TODO Auto-generated method stub
                super.onFailure(t, errorNo, strMsg);
                ToastUtil.showToast(getActivity(), Constants.NETERROR);
                loading_layout.setVisibility(View.GONE);
                lv.setVisibility(View.GONE);
                lv.stopFailRefresh();
                load_layout_fail.setVisibility(View.VISIBLE);
                txt_neterr.setText(Constants.NETERROR + " 点击屏幕重新加载");
            }

        });

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    String str3 = (String) msg.obj;
                    Log.d(TAG, str3);
                    hxPlay = JsonUtil.parseJsonToBean(str3, HxPlay.class);
                    if ("0".equals(hxPlay.getData().size())) {
                        loading_layout.setVisibility(View.GONE);
                        lv.setVisibility(View.GONE);
                        load_layout_fail.setVisibility(View.VISIBLE);
                        txt_neterr.setText("获取游戏玩法列表失败, 请点击重试");
                    } else {
                        requestLoName();
                    }
                    break;
                case 2:
                    String str2 = (String) msg.obj;
                    Log.i(TAG, str2);
                    HxGame hxGame = JsonUtil.parseJsonToBean(str2, HxGame.class);
                    if (hxGame.getData().size() == 0) {
                        ToastUtil.showToast(getActivity(), "获取彩种列表失败");
                    } else {
                        data = hxGame.getData();
                        Log.e(TAG, data.size() + "");
                        GetData();
                    }
                    break;
                case 3:
                    lv.stopRefresh();
                    String str = (String) msg.obj;
                    bettingInfo = JsonUtil.parseJsonToBean(str, UserBettingInfo.class);
                    int total_pages = bettingInfo.getData().getTotal_pages();
                    Log.d(TAG, str);
                    if (AllOrderFragment.this.bettingInfo.getData().getList().size() == 0) {
                        loading_layout.setVisibility(View.GONE);
                        lv.setVisibility(View.GONE);
                        load_layout_fail.setVisibility(View.VISIBLE);
                        txt_neterr.setText("暂无数据");
                    } else {
                        if (n == 1) {
                            listData.clear();
                        }
                        listData.addAll(bettingInfo.getData().getList());
                        gAdapter.setData(listData, data, hxPlay.getData());
                        lv.stopLoadMore();
                        if (n == total_pages) {
                            lv.setPullLoadEnable(false);
                        } else {
                            lv.setPullLoadEnable(true);
                        }
                        lv.setVisibility(View.VISIBLE);
                        loading_layout.setVisibility(View.GONE);
                        load_layout_fail.setVisibility(View.GONE);
                    }
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void onRefresh() {
        n = 1;
        requestLoName();
    }

    @Override
    public void onLoadMore() {
        n = n + 1;
        requestLoName();
    }

    @Override
    public void onClick(View v) {
    }
}
