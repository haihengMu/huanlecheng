package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import activity.huanlecheng.R;
import adapter.NewsFragmentPagerAdapter;
import bean.MyOrderTitleModel;
import util.Utils;

public class ChaseFragment extends BaseFragment {
	private View parentView;
	/** view scroll */
	// private ColumnHorizontalScrollView mColumnHorizontalScrollView;
	LinearLayout mRadioGroup_content;
	LinearLayout ll_more_columns;
	RelativeLayout rl_column;
	private ViewPager mViewPager;
	/** 左阴影部分 */
	public ImageView shade_left;
	/** 右阴影部分 */
	public ImageView shade_right;
	/** 屏幕宽度 */
	private int mScreenWidth = 0;
	/** Item宽度 */
	private int mItemWidth = 0;
	private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
	private List<MyOrderTitleModel> userChannelList;
	/** 当前选中的栏目 */
	private int columnSelectIndex = 0;
	private LinearLayout title_type;
	private String nameid,url,zero,num,qh,mount,zhucount,today,bei;
	private int a;
	private String mode;
	private String title;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mScreenWidth = Utils.getWindowsWidth(getActivity());
		mItemWidth = mScreenWidth / 3;// 一个Item宽度为屏幕的1/3
		Bundle args = getArguments();
		nameid = args.getString("nameid");
		url = args.getString("url");
		zero = args.getString("zero");
		num = args.getString("num");
		qh=args.getString("qh");
		mode = args.getString("mode");
		mount=args.getString("mount");
		zhucount=args.getString("zhucount");
		today=args.getString("today");
		title = args.getString("title");
		bei=args.getString("bei");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		parentView = inflater.inflate(R.layout.fragment_my_order, container, false);
		userChannelList = new ArrayList<MyOrderTitleModel>();
		// mColumnHorizontalScrollView = (ColumnHorizontalScrollView) parentView
		// .findViewById(R.id.mColumnHorizontalScrollView);
		mRadioGroup_content = (LinearLayout) parentView.findViewById(R.id.mRadioGroup_content);
		title_type = (LinearLayout) parentView.findViewById(R.id.title_type);
		rl_column = (RelativeLayout) parentView.findViewById(R.id.rl_column);
		mViewPager = (ViewPager) parentView.findViewById(R.id.mViewPager);
		shade_left = (ImageView) parentView.findViewById(R.id.shade_left);
		shade_right = (ImageView) parentView.findViewById(R.id.shade_right);
		for (int i = 0; i < 3; i++) {
			MyOrderTitleModel mm = new MyOrderTitleModel();
			mm.setId("" + i);
			if (i == 0) {
				mm.setName("利润率追号");
			} else if (i == 1) {
				mm.setName("同倍追号");
			} else if (i == 2) {
				mm.setName("翻倍追号");
			} 
			userChannelList.add(mm);
		}
		initTabColumn();
		initFragment();
		return parentView;
	}

	private void initTabColumn() {
		mRadioGroup_content.removeAllViews();
		int count = userChannelList.size();
		for (int i = 0; i < count; i++) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mItemWidth, LayoutParams.WRAP_CONTENT);
			// params.leftMargin = 5;
			// params.rightMargin = 5;
			mViewPager.setCurrentItem(0);
			final TextView columnTextView = new TextView(getActivity());
			columnTextView.setGravity(Gravity.CENTER);
			columnTextView.setPadding(5, 5, 5, 5);
			columnTextView.setId(i);
			columnTextView.setText(userChannelList.get(i).getName());
			columnTextView.setTextColor(getResources().getColorStateList(R.color.top_category_scroll_text_color_day));
			if (columnSelectIndex == i) {
				columnTextView.setSelected(true);
			}
			columnTextView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
						View localView = mRadioGroup_content.getChildAt(i);
						if (localView != v)
							localView.setSelected(false);
						else {
							localView.setSelected(true);
							mViewPager.setCurrentItem(i);
						}
					}
				}
			});

			mRadioGroup_content.addView(columnTextView, i, params);

		}
	}

	/**
	 * 初始化Fragment
	 */
	private void initFragment() {
		fragments.clear();// 清空
		int count = userChannelList.size();
		for (int i = 0; i < count; i++) {
			Bundle data = new Bundle();
			data.putString("id", userChannelList.get(i).getId());
			data.putString("nameid", nameid);
			data.putString("url", url);
			data.putString("zero", zero);
			data.putString("num", num);
			data.putString("qh", qh);
			data.putString("mode",mode);
			data.putString("mount", mount);
			data.putString("zhucount", zhucount);
			data.putString("today", today);
			data.putString("bei", bei);
			data.putString("title", title);
			ChaseOrderFragment newfragment = new ChaseOrderFragment();
			newfragment.setArguments(data);
			fragments.add(newfragment);
		}
		/**
		 * 此地注意 fragment嵌套时 获取manager要用 getChildFragmentManager！
		 */
		NewsFragmentPagerAdapter mAdapetr = new NewsFragmentPagerAdapter(getFragmentManager(), fragments);
		// mViewPager.setOffscreenPageLimit(0);
		mViewPager.setAdapter(mAdapetr);
		mViewPager.setOnPageChangeListener(pageListener);
	}

	/**
	 * ViewPager切换监听方法
	 */
	public OnPageChangeListener pageListener = new OnPageChangeListener() {

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int position) {
			// TODO Auto-generated method stub
			mViewPager.setCurrentItem(position);
			selectTab(position);
		}
	};

	/**
	 * 选择的Column里面的Tab
	 */
	private void selectTab(int tab_postion) {
		columnSelectIndex = tab_postion;
		for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
			View checkView = mRadioGroup_content.getChildAt(tab_postion);
			int k = checkView.getMeasuredWidth();
			int l = checkView.getLeft();
			int i2 = l + k / 2 - mScreenWidth / 2;
		}
		// 判断是否选中
		for (int j = 0; j < mRadioGroup_content.getChildCount(); j++) {
			View checkView = mRadioGroup_content.getChildAt(j);
			boolean ischeck;
			if (j == tab_postion) {
				ischeck = true;
			} else {
				ischeck = false;
			}
			checkView.setSelected(ischeck);
		}
	}
}
