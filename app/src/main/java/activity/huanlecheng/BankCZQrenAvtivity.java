package activity.huanlecheng;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import bean.BankCZQrenBean;
import butterknife.ButterKnife;
import butterknife.InjectView;


public class BankCZQrenAvtivity extends BaseActivity {

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
    @InjectView(R.id.tv_payee)// 收款人
            TextView tvPayee;
    @InjectView(R.id.tv_payee_name)// 收款人姓名
            TextView tvPayeeName;
    @InjectView(R.id.tv_topup_amount) // 充值金额
            TextView tvTopupAmount;
    @InjectView(R.id.tv_ps)// 附言
            TextView tvPs;
    @InjectView(R.id.tv_wancheng)
    TextView tvWancheng;

    private BankCZQrenBean data;
    private String et_name;
    private String ed_money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_qren);
        initValue();
        initView();
    }

    private void initView() {
        ButterKnife.inject(this);
        titleLeftBtn.setBackgroundResource(R.drawable.aar);
        tvPayee.setText(data.getData().getManual().getAccountNumber());
        tvPayeeName.setText(data.getData().getManual().getAccountName());
        tvTopupAmount.setText(ed_money);
        tvPs.setText(et_name);
        tvWancheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initValue() {
        Intent intent = getIntent();
        data = (BankCZQrenBean) intent.getSerializableExtra("data");
        et_name = intent.getStringExtra("et_name");
        ed_money = intent.getStringExtra("ed_money");
    }
}
