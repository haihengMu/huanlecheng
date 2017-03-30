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

import java.util.ArrayList;
import java.util.List;

import adapter.ProivceAdapter;
import bean.ProvinceCityBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mhc on 2017/3/23.
 */

public class cityActivity extends BaseActivity {
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
    private List<ProvinceCityBean.DataBean> cityData;
    private ProivceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_province);
        ButterKnife.inject(this);
        titleLeftBtn.setBackgroundResource(R.drawable.aar);
        titleTextview.setText("开户银行地址");
        cityData = (ArrayList<ProvinceCityBean.DataBean>) getIntent().getSerializableExtra("cityData");
        adapter = new ProivceAdapter(this);
        adapter.setData(cityData);
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
                ProvinceCityBean.DataBean cData = cityData.get(position);
                ProvinceCityBean.DataBean dataBean = new ProvinceCityBean.DataBean();
                dataBean.setId(cData.getId());
                dataBean.setName(cData.getName());
                dataBean.setPid(cData.getPid());
                EventBus.getDefault().post(dataBean);
                finish();
            }
        });
    }
}
