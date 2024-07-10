package imo.game;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class MainActivity extends Activity{
    TextView textview;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        textview = findViewById(R.id.textview);
        View root = findViewById(R.id.root);
        root.setBackgroundResource(R.drawable.card_transparent);
        root.setOnTouchListener(new OnTouchListener(){
                final int CENTER_NO_TOUCH_AREA = 75;
                @Override
                public boolean onTouch(View v,MotionEvent motion){
                    if(MotionEvent.ACTION_MOVE == motion.getAction()){
                        int viewCenter = v.getWidth() / 2;
                        float x = motion.getX();
                        int resId = -1;

                        x -= viewCenter;

                        if(x < -CENTER_NO_TOUCH_AREA) resId = R.drawable.card_left;
                        if(x > CENTER_NO_TOUCH_AREA) resId = R.drawable.card_right;
                        if(resId == -1) resId = R.drawable.card_solid;
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
