package activity.huanlecheng;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import adapter.ProivceAdapter;
import bean.ProvinceCityBean;
import butterknife.ButterKnife;
import butterknife.InjectView;
import constants.Constants;
import util.CacheUtils;
import util.JsonUtil;

public class ProvinceActivity extends BaseActivity {
    private static final String TAG = "ProvinceActivity";
    @InjectView(R.id.title_left_btn)
    Button titleLeftBtn;
    @InjectView(R.id.title_left_r)
    RelativeLayout titleLeftR;
    @InjectView(R.id.title_textview)
    TextView titleTextview;
    @InjectView(R.id.title_relavite)
    RelativeLayout titleRelavite;
    @InjectView(R.id.title_right_txt)
    TextView titleRightTxt;
    @InjectView(R.id.title_right_click)
    RelativeLayout titleRightClick;
    @InjectView(R.id.progressBar1)
    ProgressBar progressBar1;
    @InjectView(R.id.test)
    RelativeLayout test;
    @InjectView(R.id.list_view)
    ListView listView;
    private List<ProvinceCityBean.DataBean> data;
    private List<ProvinceCityBean.DataBean> provinceData;
    private List<ProvinceCityBean.DataBean> cityData;
    private ProivceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_province);
        ButterKnife.inject(this);
        titleLeftBtn.setBackgroundResource(R.drawable.aar);
        titleTextview.setText("开户银行地址");
        cityData = new ArrayList<>();
        EventBus.getDefault().register(this);
        getCache();
        distinguish();
        adapter = new ProivceAdapter(this);
        adapter.setData(provinceData);
        listView.setAdapter(adapter);
        titleLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProvinceCityBean.DataBean dataBean = (ProvinceCityBean.DataBean) adapter.getItem(position);
                String proid = dataBean.getId();
                for (ProvinceCityBean.DataBean pData : data) {
                    if (proid.equals(pData.getPid())) {
                        cityData.add(pData);
                    }
                }
                Intent intent = new Intent(ProvinceActivity.this, cityActivity.class);
                intent.putExtra("cityData", (Serializable) cityData);
                startActivity(intent);
                cityData.clear();
            }
        });
    }

    public void iteratorRemove(List<ProvinceCityBean.DataBean> list, String obj) {

        Iterator<ProvinceCityBean.DataBean> it = list.iterator();
        while (it.hasNext()) {
            ProvinceCityBean.DataBean item = it.next();
            if (obj.equals(item.getPid())) {
                provinceData.add(item);
                it.remove();//remove the current item
            }
        }
    }

    @Subscribe
    public void onEventMainThread(ProvinceCityBean.DataBean dataBean) {
        finish();
    }

    private void distinguish() {
        provinceData = new ArrayList<>();
        iteratorRemove(data, "0");
        Log.e(TAG, provinceData.size() + "");
    }

    private void getCache() {
        String cache = CacheUtils.getCache(this, Constants.address);
        Log.d(TAG, cache);
        ProvinceCityBean provinceCityBean = JsonUtil.parseJsonToBean(cache, ProvinceCityBean.class);
        data = provinceCityBean.getData();
    }
}
