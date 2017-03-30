package view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.OverScroller;

import activity.huanlecheng.R;


/**
 * d导入的一个帮助 标签和下面viewpager滑动的方法
 */
public class HelpHorizontalScrollView extends View implements OnPageChangeListener {

    private Context context;
    private int width;
    private int padingLeft = 5;
    private int padingRight;
    private float lineStartX = 0;
    private float lineEndX = 0;
    private Paint textPaint;
    private Paint textPaint1;
    private int textWidth;
    private int lastPosition = 0;
    private float lineScale = 0;

    private ViewPager mViewPager;


    /**
     * 这些事默认设置的
     */
    private static final int DEFULT_TEXT_COLOR = Color.BLACK;
    private static final int DEFULT_TEXT_SIZE = 40;//标题栏字体大小
    //	private  static final int DEFULT_LINE_COLOR = Color.RED;
    private static final int LINE_COLOR = Color.parseColor("#44f0f0f0");
    private static final int DEFULT_LINE_COLOR = R.drawable.change;
    private static final int DEFULT_LINE_STRO = 5000;//下划线的高度
    private String[] str; // 存储 题要显示的字符
    private float[] strX; // 存储 每个字符  开始的X位置
    private float[] strWidth;  // 存储每个字符的长度
    private int startX; // 与手势相关    记录按下的X的坐标

    private int recordDownX = 0;

    private OnClickOneListener mOnClickOneListener;
    private OverScroller mScroller;
    private int position;

    public HelpHorizontalScrollView(Context context, AttributeSet attrs,
                                    int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public HelpHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HelpHorizontalScrollView(Context context) {
        super(context);
        init(context);
    }


    /**
     * 初始化的时候需要new出来的一些类，和必要的设置
     *
     * @param context
     */
    private void init(Context context) {
        this.context = context;
        mScroller = new OverScroller(context, new LinearInterpolator());
        textPaint = new Paint();
        textPaint1 = new Paint();
        linePaint = new Paint();

        textPaint.setColor(Color.parseColor("#ffc300"));
        textPaint1.setColor(Color.WHITE);

        textPaint.setTextSize(DEFULT_TEXT_SIZE);
        textPaint1.setTextSize(DEFULT_TEXT_SIZE);

        textPaint.setTypeface(Typeface.SANS_SERIF);
        textPaint1.setTypeface(Typeface.SANS_SERIF);

        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint1.setTextAlign(Paint.Align.LEFT);


        linePaint.setColor(LINE_COLOR);
        linePaint.setStrokeWidth(DEFULT_LINE_STRO);
    }


    /**
     * 画图
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (str == null) {
            return;
        }
        measureSize();
        measureLineSize();//测量下划线的位置
        drawLine(canvas);//画下划线
        drawText(canvas); //画字

    }

    /**
     * 这个是用来通过Scroller来控制缓冲变化的   后来不需要了
     */
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
        }
        super.computeScroll();
        postInvalidate();
    }


    /**
     * 画字的具体代码
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        int totel = 0;
        for (int i = 0; i < str.length; i++) {
            if (i == 0) {
                totel += padingLeft;
            }
            strX[i] = totel;
            if (i == position) {
                canvas.drawText(str[i], strX[i], textHeight, textPaint);
            } else {
                canvas.drawText(str[i], strX[i], textHeight, textPaint1);
            }
            float oneTextWidth = textPaint.measureText(str[i]);
            totel = (int) (totel + oneTextWidth + space);
            strWidth[i] = oneTextWidth;
        }
        textWidth = totel - space + padingLeft;
    }


    private void drawLine(Canvas canvas) {
        canvas.drawLine(lineStartX, lineHeight, lineEndX, lineHeight, linePaint);
    }


    private void measureSize() {
        width = getWidth();
        height = getHeight();
        padingLeft = getPaddingLeft();
        padingRight = getPaddingRight();
        textHeight = (int) (height / 2 - (textPaint.descent() + textPaint.ascent()) / 2);
        lineHeight = (int) (textHeight - (textPaint.descent() + textPaint.ascent()));
    }


    private void measureLineSize() {
        lineStartX = strX[lastPosition] - space / 2 + (strWidth[lastPosition] + space) * lineScale;
        if ((lastPosition + 1) == mViewPager.getAdapter().getCount()) {
            lineEndX = strX[lastPosition] + strWidth[lastPosition] + space / 2;
        } else {
            lineEndX = strX[lastPosition] + strWidth[lastPosition] + space / 2 + (strWidth[lastPosition + 1] + space) * lineScale;
        }
    }


    public void setTitle(String... str) {
        this.str = str;
        strX = new float[str.length];
        strWidth = new float[str.length];
    }


    private int space = 0;
    private Paint linePaint;
    private int lineHeight;
    private int height;
    private int textHeight;

    public void setspace(int space) {
        this.space = space;
    }

    public void setTextColorResourceId(int colorid) {
        int color = context.getResources().getColor(colorid);
        textPaint.setColor(color);
    }

    public void setTextColor(int color) {
        textPaint.setColor(color);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = recordDownX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) event.getX();
                int scrollX = getScrollX();
                if (Math.abs(moveX - recordDownX) > 32) {
                    smootMove(scrollX, startX - moveX);
                }
                startX = (int) event.getX();
                break;
            case MotionEvent.ACTION_UP:
                if (strX != null) {
                    int upX = (int) event.getX();
                    if (Math.abs(upX - recordDownX) < 32) {
                        int position = decidePosition(upX);
                        setPosition(position);
                        if (mOnClickOneListener != null) {
                            mOnClickOneListener.onClick(position);
                        }
                    }
                    break;
                }
        }
        return true;
    }


    private int decidePosition(int upX) {
        if (strX != null) {
            for (int i = 0; i < strX.length; i++) {
                if ((upX + getScrollX()) < (strX[i] + strWidth[i]) + space / 2) {
                    return i;
                }
            }
        }

        return 0;
    }

    public void setPosition(int position) {
        //this.position = position;
        mViewPager.setCurrentItem(position);
    }


    private void smootMove(int scrollX, int dy) {
        if ((scrollX <= 0 && dy > 0 && textWidth > width) || (scrollX > 0)) {
            if ((scrollX + dy) < 0) {
                scrollTo(0, 0);
            } else {
                if ((scrollX + dy + getWidth()) > (textWidth + padingRight)) {
                    scrollTo(textWidth + padingRight - getWidth(), 0);
                } else {
                    scrollTo(scrollX + dy, 0);
                }
            }
        }
    }

    public void setViewPager(ViewPager mViewPager) {
        this.mViewPager = mViewPager;
        if (this.mViewPager != null) {
            this.mViewPager.setOnPageChangeListener(this);
        }
    }

    /**
     * dip转为 px
     */
    public int dip2px(float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int lastPosition, float scale, int location) {
        this.position = lastPosition;
        this.lastPosition = lastPosition;
        lineScale = scale;
        if (strX != null && strWidth != null) {
            if (textWidth > getWidth()) {
                smootMove((int) (strX[lastPosition]), (int) ((strWidth[lastPosition] + space) * lineScale));
            }
        }
        invalidate();
        //  smootMove((int) (strX[lastPosition] - space ), (int) ((strWidth[lastPosition] + space) * lineScale));
    }

    @Override
    public void onPageSelected(int arg0) {
    }

    public void setOnClickOneListener(OnClickOneListener mOnClickOneListener) {
        this.mOnClickOneListener = mOnClickOneListener;
    }

    /**
     * 内部接口    是点击事件的接口
     *
     * @author lenovo
     */
    public interface OnClickOneListener {
        public void onClick(int position);
    }
}
