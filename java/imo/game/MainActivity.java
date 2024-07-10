package imo.game;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;

public class MainActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        View root = findViewById(R.id.root);
        root.setBackgroundResource(R.drawable.card_transparent);
        root.setOnTouchListener(new OnTouchListener(){
            final int TOUCH_SAFE_PADDING = 10;
            @Override
            public boolean onTouch(View v, MotionEvent motion){
                if(MotionEvent.ACTION_MOVE == motion.getAction()){
                    int viewCenter = v.getWidth() / 2;
                    float x = motion.getX();
                    int resId = -1;
                    
                    x -= viewCenter;
                    if(x < -TOUCH_SAFE_PADDING) resId = R.drawable.card_left;
                    if(x > TOUCH_SAFE_PADDING) resId = R.drawable.card_right;
                    if(resId == -1) resId = R.drawable.card_transparent;
                    v.setBackgroundResource(resId);
                }
                if(MotionEvent.ACTION_UP == motion.getAction()){
                    v.setBackgroundResource(R.drawable.card_transparent);
                }
                return true;
            }
        });
    }
}
