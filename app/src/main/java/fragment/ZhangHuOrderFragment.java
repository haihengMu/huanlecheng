package fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import activity.huanlecheng.R;
import adapter.MLAdapter;
import bean.GetMLResponseModel;
import bean.LoginBean;
import bean.MLbean;
import constants.Constants;
import constants.RUser;
import http.AjaxCallBack;
import http.AjaxParams;
import util.JsonUtil;
import util.ShowToast;
import view.XListView;


public class ZhangHuOrderFragment extends BaseFragment implements
        View.OnClickListener, XListView.IXListViewListener {
    private static final String TAG = "ZhangHuOrderFragment";
    private String channel_id;
    private LinearLayout load_layout;// 载入中
    private LinearLayout load_layout_fail;// 载入失败
    private TextView txt_neterr;// 载入失败
    private ImageView fail_btn;// 失败按钮 点击重新加载
    private View parentView;
    private XListView lv;
    private MLAdapter gAdapter;
    private int n = 1;
    private GetMLResponseModel rrm;
    private String lo_id;
    private List<GetMLResponseModel.DataBean.ListBean> listData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        channel_id = args != null ? args.getString("id") : "";
        lo_id = args != null ? args.getString("lo_id") : "";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        parentView = inflater
                .inflate(R.layout.fragment_order, container, false);
        load_layout = (LinearLayout) parentView.findViewById(R.id.view_loading);
        load_layout_fail = (LinearLayout) parentView
                .findViewById(R.id.view_load_fail);
        load_layout_fail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                load_layout.setVisibility(View.VISIBLE);
                load_layout_fail.setVisibility(View.GONE);
                try {
                    n = 1;
                    GetData();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        txt_neterr = (TextView) parentView.findViewById(R.id.txt_neterr);
        fail_btn = (ImageView) parentView.findViewById(R.id.fail_btn);
        fail_btn.setOnClickListener(this);
        rrm = new GetMLResponseModel();
        listData = new ArrayList<>();
        gAdapter = new MLAdapter(getActivity());
        lv = (XListView) parentView.findViewById(R.id.main_lv_list);
        lv.setXListViewListener(this);
        lv.setPullLoadEnable(false);
        lv.setAdapter(gAdapter);
        try {
            n = 1;
            GetData();
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
    private void GetData() throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("type1", lo_id);
        params.put("pagenum", "10");
        params.put("showpagenum", n + "");
        wh.configCookieStore(RUser.cookieStore);
        wh.get(Constants.getUrl() + "/users/change_account", params, new AjaxCallBack<String>() {
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
                msg.what = 0;
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
                        ShowToast.showMsg(getActivity(), Constants.NETERROR);
                    } else {
                        load_layout.setVisibility(View.GONE);
                        lv.setVisibility(View.GONE);
                        lv.stopFailRefresh();
                        load_layout_fail.setVisibility(View.VISIBLE);
                        txt_neterr.setText(Constants.NETERROR + "  点击上方按钮重新加载");
                    }
                } else {
                    n = n - 1;
                    lv.stopLoadMore();
                    ShowToast.showMsg(getActivity(), Constants.NETERROR);
                }

            }

        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    lv.stopRefresh();
                    String str = (String) msg.obj;
                    Log.e(TAG, str);
                    GetMLResponseModel getMLResponseModel = JsonUtil.parseJsonToBean(str, GetMLResponseModel.class);
                    int total_pages = getMLResponseModel.getData().getTotal_pages();
                    if (getMLResponseModel.getData().getList().size() == 0) {
                        load_layout.setVisibility(View.GONE);
                        lv.setVisibility(View.GONE);
                        load_layout_fail.setVisibility(View.VISIBLE);
                        txt_neterr.setText("暂无数据");
                    } else {
                        if (n == 1) {
                            listData.clear();
                        }
                        listData.addAll(getMLResponseModel.getData().getList());
                        gAdapter.setData(listData);
                        lv.stopLoadMore();
                        if (n == total_pages) {
                            lv.setPullLoadEnable(false);
                        } else {
                            lv.setPullLoadEnable(true);
                        }
                        lv.setVisibility(View.VISIBLE);
                        load_layout.setVisibility(View.GONE);
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
        // TODO Auto-generated method stub
        try {
            n = 1;
            GetData();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onLoadMore() {
        // TODO Auto-generated method stub
        n = n + 1;
        try {
            GetData();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }

}
