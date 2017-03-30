package view;

import android.view.MotionEvent;
import android.widget.GridView;


public class ClassGridView extends GridView
{  
    public ClassGridView(android.content.Context context,  
            android.util.AttributeSet attrs)  
    {  
        super(context, attrs);  
    }
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //请求所有父控件及祖宗控件不要拦截事件
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
 /*
    *//**
     * 设置不滚动 
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)  
    {  
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);  
  
    }
  
}  
