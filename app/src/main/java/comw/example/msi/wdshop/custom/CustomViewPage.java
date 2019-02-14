package comw.example.msi.wdshop.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * <p>文件描述：<p>
 * <p>作者：${Ln}<p>
 * <p>创建时间：2019/1/5  8:33<p>
 * <p>更改时间：2019/1/5  8:33<p>
 * <p>版本号：1<p>
 */
public class CustomViewPage extends ViewPager {
    private boolean isCanScroll = false;//禁止滑动
    public CustomViewPage(@NonNull Context context) {
        super(context);
    }

    public CustomViewPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScanScroll(boolean isCanScroll){
        this.isCanScroll = isCanScroll;
    }

    @Override
    public void scrollTo(int x, int y){
        super.scrollTo(x, y);
    }

    @Override
    public void setCurrentItem(int item) {
        //是否有滑动效果
        super.setCurrentItem(item,false);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {

        if (isCanScroll) {//触摸时禁止事件
            return super.onTouchEvent(arg0);
        } else {
            return false;
        }
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {

        if (isCanScroll) {
            return super.onInterceptTouchEvent(arg0);
        } else {
            return false;
        }
    }

}