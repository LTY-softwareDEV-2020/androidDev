package connectpc;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class touchableView extends View {
    mouseManager mouseManager = null;
    int lastx = 0;
    int lasty = 0;
    long downTime;
    public touchableView(Context context) {
        super(context);
    }

    public touchableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public touchableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public touchableView(Context context, @Nullable  AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setMouseManager(connectpc.mouseManager mouseManager) {
        this.mouseManager = mouseManager;
    }


    @Override

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
//                Log.e("zw","onTouchEvent down");
                lastx = (int)event.getX();
                lasty = (int)event.getY();
                downTime =  System.currentTimeMillis();
                break;
            case MotionEvent.ACTION_MOVE:
//                Log.e("zw","onTouchEvent move");
                int x = (int)event.getX();
                int y = (int)event.getY();
                int relativex = x - lastx;
                int relativey = y - lasty;
                lastx = x;
                lasty = y;
                if((relativex == x) && (relativey == y)){ //首次不发送
                    return true;
                }
                if((relativex == 0) && (relativey == 0)){//变化为0不发送
                    return true;
                }
                if (!this.mouseManager.isConnected()){//没有初始化
                    return true;
                }
                else {
                    mouseManager.sendMovement(relativex+"\t"+relativey);
                }
                break;
            case MotionEvent.ACTION_UP:
//                Log.e("zw","onTouchEvent up");
                if( System.currentTimeMillis() - downTime < 100 ){//按压不超过100ms 认为是点击
                    mouseManager.sendKey("L");
                }
                break;
        }

        return true;
    }
}
