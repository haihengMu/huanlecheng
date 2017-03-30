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

import fragment.ChaseFragment;
import view.NestRadioGroup;


public class ChaseMainActivity extends FragmentActivity implements
		NestRadioGroup.OnCheckedChangeListener {
	private FragmentManager mFragmentManager;
	private Fragment[] fragments = new Fragment[1];
	private Button title_left_btn;
	private TextView title_textview;
	public static ChaseMainActivity orm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		orm = this;
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
		title_textview.setText("我要追号");
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
				fragments[position] = new ChaseFragment();
				Bundle data = new Bundle();
				data.putString("nameid", getIntent().getStringExtra("nameid"));
				data.putString("url", getIntent().getStringExtra("url"));
				data.putString("zero", getIntent().getStringExtra("zero"));
				data.putString("num", getIntent().getStringExtra("num"));
				data.putString("mode",getIntent().getStringExtra("mode"));
				data.putString("qh", getIntent().getStringExtra("qh"));
				data.putString("mount", getIntent().getStringExtra("mount"));
				data.putString("zhucount", getIntent().getStringExtra("zhucount"));
				data.putString("today", getIntent().getStringExtra("today"));
				data.putString("bei", getIntent().getStringExtra("bei"));
				data.putString("title",getIntent().getStringExtra("title"));
				fragments[position].setArguments(data);
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
