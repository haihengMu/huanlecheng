/**
 * @file XListView.java
 * @package me.maxwin.view
 * @create Mar 18, 2012 6:28:41 PM
 * @author Maxwin
 * @description An ListView support (a) Pull down to refresh, (b) Pull up to load more.
 * 		Implement IXListViewListener, and see stopRefresh() / stopLoadMore().
 */
package view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import activity.huanlecheng.R;
import constants.RUser;

public class XListView extends ListView implements OnScrollListener {

	private final static int SCROLLBACK_HEADER = 0;
	private final static int SCROLLBACK_FOOTER = 1;
	private final static int SCROLL_DURATION = 400; // scroll back duration
	private final static int PULL_LOAD_MORE_DELTA = 50; // when pull up >= 50px
	// at bottom, trigger
	// load more.
	private final static float OFFSET_RADIO = 1.8f; // support iOS like pull
	private float mLastY = -1; // save event y
	private Scroller mScroller; // used for scroll back
	private OnScrollListener mScrollListener; // user's scroll listener
	// the interface to trigger refresh and load more.
	private IXListViewListener mListViewListener;
	// -- header view
	private XListViewHeader mHeaderView;
	// header view content, use it to calculate the Header's height. And hide it
	// when disable pull refresh.
	private RelativeLayout mHeaderViewContent;
	private TextView mHeaderSuccess;
	private ProgressBar pBar;
	private ImageView enm;
	// private TextView mHeaderTimeView;
	private int mHeaderViewHeight; // header view's height
	private boolean mEnablePullRefresh = true;
	private boolean mPullRefreshing = false; // is refreashing.
	// -- footer view
	private XListViewFooter mFooterView;
	private boolean mEnablePullLoad;
	private boolean mPullLoading;
	private boolean mIsFooterReady = false;
	// total list items, used to detect is at the bottom of listview.
	private int mTotalItemCount;
	// for mScroller, scroll back from header or footer.
	private int mScrollBack;
	// feature.
	// ================================= 功能设置Flag
	// ================================

	/** 可以加载更多？ */
	public boolean mCanLoadMore = true;
	/** 可以下拉刷新？ */
	public boolean mCanRefresh = true;
	/** 可以自动加载更多吗？（注意，先判断是否有加载更多，如果没有，这个flag也没有意义） */
	public boolean mIsAutoLoadMore = true;
	private int mLastItemIndex;
	private int mCount;
	/** 加载中 */
	private boolean ENDINT_LOADING = true;
	private int mPosition;
	public boolean isCanRefresh() {
		return mCanRefresh;
	}

	public void setCanRefresh(boolean pCanRefresh) {
		mCanRefresh = pCanRefresh;
	}

	public boolean isCanLoadMore() {
		return mCanLoadMore;
	}
	//弃用此方法
	public void setCanLoadMore(boolean pCanLoadMore) {
		mCanLoadMore = pCanLoadMore;
		if (mCanLoadMore) {
			// if (mFooterView != null
			// && mFooterView.getVisibility() == VISIBLE) {
			//
			// }else {
			// addFooterView(mFooterView);
			// }
			// mPullLoading = true;
			mFooterView.show();
			// mFooterView.setState(XListViewFooter.STATE_NORMAL);
		} else {
			// if (!pCanLoadMore) {
			// if (mFooterView != null
			// && mFooterView.getVisibility() == VISIBLE) {
			// // 突然关闭加载更多功能之后，我们要移除FootView。
			// System.out
			// .println("this.removeFooterView(endRootView);...");
			// mFooterView.hide();
			// // this.removeFooterView(mFooterView);
			// }
			// } else {
			// mFooterView.hide();
			// }
			mFooterView.hide();
		}
	}

	public boolean isAutoLoadMore() {
		return mIsAutoLoadMore;
	}

	public void setAutoLoadMore(boolean pIsAutoLoadMore) {
		mIsAutoLoadMore = pIsAutoLoadMore;
	}

	/**
	 * @param context
	 */
	public XListView(Context context) {
		super(context);
		initWithContext(context);
	}

	public XListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initWithContext(context);
	}

	public XListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initWithContext(context);
	}

	private void initWithContext(Context context) {
		mScroller = new Scroller(context, new DecelerateInterpolator());
		// XListView need the scroll event, and it will dispatch the event to
		// user's listener (as a proxy).
		super.setOnScrollListener(this);

		// init header view
		mHeaderView = new XListViewHeader(context);
		mHeaderViewContent = (RelativeLayout) mHeaderView
				.findViewById(R.id.xlistview_header_content);
		// mHeaderTimeView = (TextView) mHeaderView
		// .findViewById(R.id.xlistview_header_time);
		mHeaderSuccess = (TextView) mHeaderView
				.findViewById(R.id.xlistview_header_hint_textview);
		pBar = (ProgressBar) mHeaderView
				.findViewById(R.id.xlistview_header_progressbar);
		enm = (ImageView) mHeaderView.findViewById(R.id.enm);
		addHeaderView(mHeaderView);

		// init footer view
		mFooterView = new XListViewFooter(context);

		// init header height
		mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {
					@Override
					public void onGlobalLayout() {
						mHeaderViewHeight = mHeaderViewContent.getHeight();
						getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);
					}
				});
	}

	@Override
	public void setAdapter(ListAdapter adapter) {
		// make sure XListViewFooter is the last footer view, and only add once.
		if (mIsFooterReady == false) {
			mIsFooterReady = true;
			addFooterView(mFooterView);
		}
		super.setAdapter(adapter);
	}

	/**
	 * enable or disable pull down refresh feature.
	 * 
	 * @param enable
	 */
	public void setPullRefreshEnable(boolean enable) {
		mEnablePullRefresh = enable;
		if (!mEnablePullRefresh) { // disable, hide the content
			mHeaderViewContent.setVisibility(View.INVISIBLE);
		} else {
			mHeaderViewContent.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * enable or disable pull up load more feature.
	 * 
	 * @param enable
	 */
	public void setPullLoadEnable(boolean enable) {
		mEnablePullLoad = enable;
		if (!mEnablePullLoad) {
			mFooterView.hide();
			ENDINT_LOADING = false;
			mFooterView.setOnClickListener(null);
		} else {
			ENDINT_LOADING = true;
			mPullLoading = false;
			mFooterView.show();
			mFooterView.setState(XListViewFooter.STATE_NORMAL);
			// both "pull up" and "click" will invoke load more.
			mFooterView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					startLoadMore();
				}
			});
		}
	}

	public void stopFailRefresh() {
		if (mPullRefreshing == true) {
			mPullRefreshing = false;
			resetHeaderHeight();
		}
	}

	/**
	 * stop refresh, reset header view.
	 */
	public void stopRefresh() {
		RUser.complete_time = 2;
		new myThread().start();

	}

	public class myThread extends Thread {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			while (RUser.complete_time > 0) {
				try {
					Thread.sleep(500);
					RUser.complete_time--;
					Message msg = new Message();
					msg.what = 0;
					handle.sendMessage(msg);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	Handler handle = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				mHeaderSuccess.setText("刷新成功");
				pBar.setVisibility(View.GONE);
				enm.setVisibility(View.VISIBLE);
				if (RUser.complete_time == 0) {
					if (mPullRefreshing == true) {
						mPullRefreshing = false;
						resetHeaderHeight();
					}
				}
				break;

			default:
				break;
			}
		}
	};

	/**
	 * stop load more, reset footer view.
	 */
	public void stopLoadMore() {

		ENDINT_LOADING = true;
		if (mPullLoading == true) {
			mPullLoading = false;
			mFooterView.setState(XListViewFooter.STATE_NORMAL);
		}
	}

	/**
	 * set last refresh time
	 * 
	 * @param time
	 */
	public void setRefreshTime(String time) {
		// mHeaderTimeView.setText(time);
	}

	private void invokeOnScrolling() {
		if (mScrollListener instanceof OnXScrollListener) {
			OnXScrollListener l = (OnXScrollListener) mScrollListener;
			l.onXScrolling(this);
		}
	}

	private void updateHeaderHeight(float delta) {
		mHeaderView.setVisiableHeight((int) delta
				+ mHeaderView.getVisiableHeight());
		if (mEnablePullRefresh && !mPullRefreshing) { // 未处于刷新状态，更新箭头
			if (mHeaderView.getVisiableHeight() > mHeaderViewHeight) {
				mHeaderView.setState(XListViewHeader.STATE_READY);
			} else {
				mHeaderView.setState(XListViewHeader.STATE_NORMAL);
			}
		}
		setSelection(0); // scroll to top each time
	}

	/**
	 * reset header view's height.
	 */
	private void resetHeaderHeight() {
		int height = mHeaderView.getVisiableHeight();
		if (height == 0) // not visible.
			return;
		// refreshing and header isn't shown fully. do nothing.
		if (mPullRefreshing && height <= mHeaderViewHeight) {
			return;
		}
		int finalHeight = 0; // default: scroll back to dismiss header.
		// is refreshing, just scroll back to show all the header.
		if (mPullRefreshing && height > mHeaderViewHeight) {
			finalHeight = mHeaderViewHeight;
		}
		mScrollBack = SCROLLBACK_HEADER;

		mScroller.startScroll(0, height, 0, finalHeight - height,
				SCROLL_DURATION);

		// trigger computeScroll
		invalidate();
	}

	private void updateFooterHeight(float delta) {
		int height = mFooterView.getBottomMargin() + (int) delta;
		if (mEnablePullLoad && !mPullLoading) {
			if (height > PULL_LOAD_MORE_DELTA) { // height enough to invoke load
				// more.
				mFooterView.setState(XListViewFooter.STATE_READY);
			} else {
				mFooterView.setState(XListViewFooter.STATE_NORMAL);
			}
		}
		mFooterView.setBottomMargin(height);

		// setSelection(mTotalItemCount - 1); // scroll to bottom
	}

	private void resetFooterHeight() {
		int bottomMargin = mFooterView.getBottomMargin();
		if (bottomMargin > 0) {
			mScrollBack = SCROLLBACK_FOOTER;
			mScroller.startScroll(0, bottomMargin, 0, -bottomMargin,
					SCROLL_DURATION);
			invalidate();
		}
	}

	public void startLoadMore() {
		mPullLoading = true;
		ENDINT_LOADING = false;
		mFooterView.setState(XListViewFooter.STATE_LOADING);
		if (mListViewListener != null) {
			mListViewListener.onLoadMore();
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (mCanRefresh) {
			if (mLastY == -1) {
				mLastY = ev.getRawY();
			}
			switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				mLastY = ev.getRawY();
				mHeaderSuccess.setText(R.string.xlistview_header_hint_normal);
				break;
			case MotionEvent.ACTION_MOVE:
				final float deltaY = ev.getRawY() - mLastY;
				mLastY = ev.getRawY();
//				System.out.println("数据监测：" + getFirstVisiblePosition()
//						+ "---->" + getLastVisiblePosition());
				if (getFirstVisiblePosition() == 0
						&& (mHeaderView.getVisiableHeight() > 0 || deltaY > 0)) {
					// the first item is showing, header has shown or pull down.
					updateHeaderHeight(deltaY / OFFSET_RADIO);
					invokeOnScrolling();
				} else if (getLastVisiblePosition() == mTotalItemCount - 1
						&& (mFooterView.getBottomMargin() > 0 || deltaY < 0)) {
					// last item, already pulled up or want to pull up.
					updateFooterHeight(-deltaY / OFFSET_RADIO);
				}
				break;
			default:
				mLastY = -1; // reset
				if (getFirstVisiblePosition() == 0) {
					// invoke refresh
					if (mEnablePullRefresh
							&& mHeaderView.getVisiableHeight() > mHeaderViewHeight) {
						mPullRefreshing = true;
						mHeaderView.setState(XListViewHeader.STATE_REFRESHING);
						if (mListViewListener != null) {
							mListViewListener.onRefresh();
						}
					}
					resetHeaderHeight();
				}
				if (getLastVisiblePosition() == mTotalItemCount - 1) {
					// invoke load more.
					// if (mEnablePullLoad
					// && mFooterView.getBottomMargin() > PULL_LOAD_MORE_DELTA)
					// {
					// startLoadMore();
					// }
					resetFooterHeight();
				}
				break;
			}

		}
		return super.onTouchEvent(ev);
	}

	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			if (mScrollBack == SCROLLBACK_HEADER) {
				mHeaderView.setVisiableHeight(mScroller.getCurrY());
			} else {
				mFooterView.setBottomMargin(mScroller.getCurrY());
			}
			postInvalidate();
			invokeOnScrolling();
		}
		super.computeScroll();
	}

	@Override
	public void setOnScrollListener(OnScrollListener l) {
		mScrollListener = l;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (mScrollListener != null) {
			mScrollListener.onScrollStateChanged(view, scrollState);
		}
		if (mCanLoadMore) {// 存在加载更多功能
			if (mLastItemIndex == mCount && scrollState == SCROLL_STATE_IDLE) {
				if (mIsAutoLoadMore) {// 自动加载更多，我们让FootView显示 “更 多”
					if (ENDINT_LOADING == true) {
						mFooterView.show();
						startLoadMore();
					}
				}
			}
		} else if (mFooterView != null
				&& mFooterView.getVisibility() == VISIBLE) {
			// 突然关闭加载更多功能之后，我们要移除FootView。
			System.out.println("this.removeFooterView(endRootView);...");
			mFooterView.setVisibility(View.GONE);
			this.removeFooterView(mFooterView);
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
						 int visibleItemCount, int totalItemCount) {
		// send to user's listener
		mLastItemIndex = firstVisibleItem + visibleItemCount - 2;
		mCount = totalItemCount - 2;
		mTotalItemCount = totalItemCount;
		if (mScrollListener != null) {
			mScrollListener.onScroll(view, firstVisibleItem, visibleItemCount,
					totalItemCount);
		}
	}

	public void setXListViewListener(IXListViewListener l) {
		mListViewListener = l;
	}

	/**
	 * you can listen ListView.OnScrollListener or this one. it will invoke
	 * onXScrolling when header/footer scroll back.
	 */
	public interface OnXScrollListener extends OnScrollListener {
		public void onXScrolling(View view);
	}

	/**
	 * implements this interface to get refresh/load more event.
	 */
	public interface IXListViewListener {
		public void onRefresh();

		public void onLoadMore();
	}
	  	
}
