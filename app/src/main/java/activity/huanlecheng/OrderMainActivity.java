package activity.huanlecheng;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import fragment.MyOrderFragment;
import fragment.ZhangHuFragment;
import view.NestRadioGroup;


public class OrderMainActivity extends FragmentActivity implements NestRadioGroup.OnCheckedChangeListener {
	private FragmentManager mFragmentManager;
	private Fragment[] fragments = new Fragment[1];
	private Button title_left_btn;
	private TextView title_textview;
	private String change_id;
	public static OrderMainActivity orm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		orm = this;
		change_id = getIntent().getStringExtra("change_id");
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
		if (change_id.equals("2")) {
			title_textview.setText("账户明细");
		}else if (change_id.equals("1")) {
			title_textview.setText("追号记录");
		}else {
			title_textview.setText("投注记录");
		}
		
		NestRadioGroup bottom = (NestRadioGroup) findViewById(R.id.bottom);
		bottom.setOnCheckedChangeListener(this);
		mFragmentManager = getSupportFragmentManager();
		changeFragment(0);
	}

	private void changeFragment(int position) {
		FragmentTransaction t = mFragmentManager.beginTransaction();
		if (fragments[position] == null) {
			switch (position) {
			case 0:
				if (change_id.equals("2")) {
					fragments[position] = new ZhangHuFragment();
					Bundle data = new Bundle();
					data.putString("change_id", change_id);
					fragments[position].setArguments(data);
				}else {
					fragments[position] = new MyOrderFragment();
					Bundle data = new Bundle();
					data.putString("change_id", change_id);
					fragments[position].setArguments(data);
				}
				break;
			}
			t.add(R.id.continer, fragments[position]);
		}
		t.show(fragments[0]);
		t.commit();
	}

	@Override
	public void onCheckedChanged(NestRadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.daily_check: // 热门
			changeFragment(0);
			break;
		}
	}
}


