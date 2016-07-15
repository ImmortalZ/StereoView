package mr_immortalz.com.stereoview.custom;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import mr_immortalz.com.stereoview.utils.LogUtil;

/**
 * Created by Mr_immortalZ on 2016/7/13.
 * email : mr_immortalz@qq.com
 */
public class CustomEdittext extends EditText {

    private int width;
    private int height;
    private int lineCount;
    private float textSize;
    private boolean hasFocus;
    private Region region;
    private Rect rect;

    public CustomEdittext(Context context) {
        super(context);
        init();
    }

    public CustomEdittext(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomEdittext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        textSize = getTextSize();
        region = new Region();
        rect = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogUtil.m();
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        rect.set(0, 0, width / 2, height);
    }


    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        hasFocus = focused;
        LogUtil.m(focused + "");
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        LogUtil.m();
        if (hasFocus) {
            Rect bounds = new Rect();
            getFocusedRect(bounds);
            rect.set(0, bounds.top, bounds.right + 30, bounds.bottom);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                if (!isContain(event)) {
                    //子类不需要，交给容器自己处理
                    getParent().requestDisallowInterceptTouchEvent(false);
                    setFocusable(false);
                } else {
                    //子类自己做操作
                    setFocusableInTouchMode(true);
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.dispatchTouchEvent(event);
    }

    private boolean isContain(MotionEvent event) {
        region.set(rect);
        if (region.contains((int) event.getX(), (int) event.getY())) {
            return true;
        }
        return false;
    }
}
