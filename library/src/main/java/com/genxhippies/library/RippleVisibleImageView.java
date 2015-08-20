package com.genxhippies.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by inkyfox on 15. 8. 20..
 */
public class RippleVisibleImageView extends ImageView {

    private Drawable mForeground = null;
    private int mForegroundResource;

    public RippleVisibleImageView(Context context) {
        this(context, null);
    }

    public RippleVisibleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RippleVisibleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        Drawable foreground = null;
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.genxhippies);
            if (array != null) {
                foreground = array.getDrawable(R.styleable.genxhippies_android_foreground);
                array.recycle();
            }
        }
        setForeground(foreground);
    }

    public void setForegroundResource(int resid) {
        if (resid != 0 && resid == mForegroundResource) {
            return;
        }

        Drawable d = null;
        if (resid != 0) {
            if (Build.VERSION.SDK_INT >= 21) {
                d = getContext().getDrawable(resid);
            } else {
                d = getContext().getResources().getDrawable(resid);
            }
        }
        setForeground(d);

        mForegroundResource = resid;
    }

    public void setForegroundColor(int color) {
        if (Build.VERSION.SDK_INT >= 11 && mForeground instanceof ColorDrawable) {
            ((ColorDrawable) mForeground.mutate()).setColor(color);
            mForegroundResource = 0;
        } else {
            setForeground(new ColorDrawable(color));
        }
    }

    public void setForeground(Drawable d) {
        if (mForeground != null) {
            mForeground.setCallback(null);
            unscheduleDrawable(mForeground);
        }

        if(d != null) {
            d.setCallback(this);
            if (d.isStateful()) {
                d.setState(getDrawableState());
            }
            d.setVisible(getVisibility() == VISIBLE, false);
            d.setBounds(0, 0, getWidth(), getHeight());
        }

        mForegroundResource = 0;
        mForeground = d;
    }

    @Override
    public void invalidateDrawable(Drawable drawable) {
        if (drawable == mForeground) {
            invalidate();
        } else {
            super.invalidateDrawable(drawable);
        }
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        final int[] state = getDrawableState();
        if (mForeground != null) {
            mForeground.setState(state);
            invalidate();
        }
    }

    @Override
    public void drawableHotspotChanged(float x, float y) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            super.drawableHotspotChanged(x, y);
            if (mForeground != null) {
                mForeground.setHotspot(x, y);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mForeground != null) mForeground.draw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (mForeground != null) mForeground.setBounds(0, 0, w, h);
    }
}
