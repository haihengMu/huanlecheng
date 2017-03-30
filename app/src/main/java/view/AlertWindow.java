package view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import activity.huanlecheng.R;


public class AlertWindow extends Dialog {

	public View view;
	private Context c;
	private Button sureBtn;// 保存
	private Button cancelBtn;// 叉
	private EditText editText;// 输入框

	public AlertWindow(Context context) {
		super(context);
	}

	public AlertWindow(Context paramContext, int paramInt) {
		super(paramContext, paramInt);
		view = View.inflate(paramContext, R.layout.alert_window, null);
		this.setContentView(view);
		this.c = paramContext;

	}

	public void cancel() {
		if (!isShowing()) {
			return;
		}
		this.editText.setText("");
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

		TextView txtTitle = (TextView) view.findViewById(R.id.put_text);// 标签
		this.editText = (EditText) view.findViewById(R.id.put_edit_text);// 输入框
		this.sureBtn = (Button) view.findViewById(R.id.save);
		this.cancelBtn = (Button) view.findViewById(R.id.del);

		txtTitle.setText(title);
		this.show();

		// 设置软键盘
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		InputMethodManager imm = (InputMethodManager) c.getSystemService(c.INPUT_METHOD_SERVICE);
		imm.showSoftInput(editText, 0);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

	}

	public Button getSureBtn() {
		return this.sureBtn;
	}

	public Button getCancelBtn() {
		return this.cancelBtn;
	}

	public EditText getEditText() {
		return this.editText;
	}

}
