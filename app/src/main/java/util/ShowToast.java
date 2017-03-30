package util;

import android.content.Context;
import android.widget.Toast;

public class ShowToast {
	  public static void showMsg(Context mContext, String msg) {
	        Toast toast=new Toast(mContext);
	        toast= Toast.makeText(mContext,msg, 300);
//	        toast.setGravity(Gravity.BOTTOM,0,0);//设置底部显示  
	        toast.show();//显示,(缺了这句不显示)  
	    }  
}
