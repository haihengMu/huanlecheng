package view;


import android.app.Dialog;
import android.content.Context;
import android.view.View;

import activity.huanlecheng.R;


public class LoadingPro extends Dialog {

	public View view;
	public CircleProgressBar progress2;
	public LoadingPro(Context context) {
		super(context);
	}

	public LoadingPro(Context paramContext, int paramInt) {
		super(paramContext, paramInt);
		view = View.inflate(paramContext, R.layout.loading_pro, null);
		progress2 = (CircleProgressBar)view.findViewById(R.id.progress2);
		this.setContentView(view);

	}

	public void cancel() {
		if (!isShowing()) {
			return;
		}
		super.cancel();

	}

	public void show() {
		if (isShowing()) {

			return;
		}
		super.show();
	}

	public void setView(View view) {
		this.view = view;
		this.setContentView(view);
	}

	public View getView() {
		return view;
	}

	public void showDialog(String title) {
		
		this.show();
	}
	public void hand(int finalI ){
		progress2.setProgress(finalI);
	}
}
