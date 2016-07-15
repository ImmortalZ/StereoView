package mr_immortalz.com.stereoview.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import mr_immortalz.com.stereoview.R;

/**
 * Created by Mr_immortalZ on 2016/7/14.
 * email : mr_immortalz@qq.com
 */
public class RippleView extends View {
    private Paint maxPaint;
    private Paint minPaint;
    private Paint bgPaint;
    private Drawable bgNormal;
    private Drawable bgPressed;
    private Bitmap bgBitmapNormal;
    private Bitmap bgBitmapPressed;
    private int mWidth;
    private int mHeight;
    private int radius;
    private int maxRadius;
    private int minRadius;
    private RippleAnim rippleAnim;
    private float mInterpolatedTime;
    private Region region;
    private Rect rect;
    private boolean isPressed = false;
    private IRippleAnimListener iRippleAnimListener;

    public RippleView(Context context) {
        this(context, null);
    }

    public RippleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RippleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RippleView);
        bgPressed = ta.getDrawable(R.styleable.RippleView_pressed);
        bgNormal = ta.getDrawable(R.styleable.RippleView_normal);
        ta.recycle();
        init();
    }

    private void init() {
        maxPaint = new Paint();
        maxPaint.setColor(getResources().getColor(R.color.bg_gray));
        minPaint = new Paint();
        minPaint.setColor(getResources().getColor(R.color.bg_white));
        bgPaint = new Paint();
        bgPaint.setAntiAlias(true);
        if (bgNormal != null) {
            bgBitmapNormal = ((BitmapDrawable) bgNormal).getBitmap();
        }
        if (bgPressed != null) {
            bgBitmapPressed = ((BitmapDrawable) bgPressed).getBitmap();
        }
        region = new Region();
        rect = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        radius = Math.min(mWidth / 2, mHeight / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(getPaddingLeft(), 0);
        maxRadius = (int) (mInterpolatedTime * radius * 1.2f);
        if (maxRadius > radius * 0.8f) {
            minRadius = (int) ((mInterpolatedTime - 0.6) * radius * 3);
        }
        if (mInterpolatedTime != 1.0f) {
            canvas.drawCircle(mWidth / 2, mHeight / 2, maxRadius, maxPaint);
            canvas.drawCircle(mWidth / 2, mHeight / 2, minRadius, minPaint);
        } else {
            clearState();
        }
        if (bgBitmapNormal != null && !isPressed) {
            Rect rect = new Rect();
            rect.set(mWidth * 5 / 16, mHeight * 5 / 16, mWidth * 11 / 16, mHeight * 11 / 16);
            canvas.drawBitmap(bgBitmapNormal, null, rect, bgPaint);
        } else if (bgBitmapPressed != null && isPressed) {
            Rect rect = new Rect();
            rect.set(mWidth * 5 / 16, mHeight * 5 / 16, mWidth * 11 / 16, mHeight * 11 / 16);
            canvas.drawBitmap(bgBitmapPressed, null, rect, bgPaint);
        }


    }

    private void clearState() {
        minRadius = 0;
        maxRadius = 0;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                if (isContain(event)) {
                    isPressed = true;
                    startRipple();
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.dispatchTouchEvent(event);
    }

    private boolean isContain(MotionEvent event) {
        rect.set(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
        region.set(rect);
        if (region.contains((int) event.getX(), (int) event.getY())) {
            return true;
        }
        return false;
    }

    private void startRipple() {
        if (rippleAnim == null) {
            rippleAnim = new RippleAnim();
        }
        rippleAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (iRippleAnimListener != null) {
                    iRippleAnimListener.onComplete(RippleView.this);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        rippleAnim.cancel();
        rippleAnim.setDuration(600);
        startAnimation(rippleAnim);
    }

    private class RippleAnim extends Animation {
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            mInterpolatedTime = interpolatedTime;
            invalidate();
        }
    }

    public interface IRippleAnimListener{
        void onComplete(View view);
    }

    public void setiRippleAnimListener(IRippleAnimListener iRippleAnimListener) {
        this.iRippleAnimListener = iRippleAnimListener;
    }


}
