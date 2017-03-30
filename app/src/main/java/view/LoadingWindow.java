package view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import activity.huanlecheng.R;


public class LoadingWindow extends Dialog {

	public View view;
	public LoadingWindow(Context context) {
		super(context);
	}

	public LoadingWindow(Context paramContext, int paramInt) {
		super(paramContext, paramInt);
		view = View.inflate(paramContext, R.layout.loading, null);
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
		TextView txtTitle = (TextView) view.findViewById(R.id.load_txt);// 标签
		txtTitle.setText(title);
		this.show();
	}

}
