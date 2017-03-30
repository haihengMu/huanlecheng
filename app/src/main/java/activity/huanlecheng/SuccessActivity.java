package activity.huanlecheng;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import bean.CaipiaoBean;
import util.CaipiaoDao;


public class SuccessActivity extends BaseActivity {
	private Button title_left_btn;
	private TextView title_textview;
	private String money;
	//private TextView b_money;
	private TextView y_money;
	private Button lottery_info;
	private Button lottery_keep;
	private String id;
	private String title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_success);
		money = getIntent().getStringExtra("money");
		id = getIntent().getStringExtra("id");
		title = getIntent().getStringExtra("title");
		title_left_btn = (Button) findViewById(R.id.title_left_btn);
		title_left_btn.setBackgroundResource(R.drawable.aar);
		title_left_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		title_textview = (TextView) findViewById(R.id.title_textview);

		lottery_info = (Button) findViewById(R.id.lottery_info);
		lottery_info.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (id.equals("0")) {
					Intent intent = new Intent(SuccessActivity.this,
							OrderMainActivity.class);
					intent.putExtra("change_id", "0");// 投注记录
					startActivity(intent);
				} else {
					Intent intent = new Intent(SuccessActivity.this,
							OrderMainActivity.class);
					intent.putExtra("change_id", "1");// 追号记录
					startActivity(intent);
				}

			}
		});
		lottery_keep = (Button) findViewById(R.id.lottery_keep);
		lottery_keep.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CaipiaoDao caipiaoDao=new CaipiaoDao(getApplicationContext());
				List<CaipiaoBean> mlist2=caipiaoDao.findAll();
				for (int i=0;i<mlist2.size();i++){
					if (mlist2.get(i).getHx_name().equals(title)){
						caipiaoDao.delByTitle(mlist2.get(i).getHx_name());
					}
				}
				finish();
			}
		});
		userInfo.setU_Money(((float) Double.parseDouble(userInfo.getU_Money()) - (float) Double
				.parseDouble(money)) + "");
		if (id.equals("0")) {
			title_textview.setText("投注成功");
			lottery_info.setText("投注记录");
		} else {
			title_textview.setText("追号成功");
			lottery_info.setText("追号记录");
		}

		Intent intent = new Intent();
		intent.setAction("action.main");
		sendBroadcast(intent);

		Intent intent1 = new Intent();
		intent1.setAction("action.center");
		sendBroadcast(intent1);
	}
}
