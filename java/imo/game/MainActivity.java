package imo.game;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import java.util.List;
import java.util.Arrays;

public class MainActivity extends Activity{
    TextView textview;
    int scriptIndex = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        textview = findViewById(R.id.textview);
        View root = findViewById(R.id.root);
        String story_script = getString(R.string.story_script);
        List<String> storyScript;
        
        story_script = story_script.trim();
        storyScript = Arrays.asList(story_script.split("\n"));
        
        final List<String> script = storyScript;
        
        root.setOnTouchListener(new OnTouchListener(){
                @Override
                public boolean onTouch(View v,MotionEvent motion){
                    if(MotionEvent.ACTION_DOWN != motion.getAction()) return true;
                    if(scriptIndex > script.size() - 1) return true;
                    
                    textview.setText(script.get(scriptIndex));
                    scriptIndex++;
                    return true;
                }
            });
        
        root.setBackgroundResource(R.drawable.card_transparent);
        
        if(true) return;
        
        root.setOnTouchListener(new OnTouchListener(){
                final int CENTER_NO_TOUCH_AREA = 75;
                @Override
                public boolean onTouch(View v,MotionEvent motion){
                    if(MotionEvent.ACTION_MOVE == motion.getAction()){
                        int viewCenter = v.getWidth() / 2;
                        float x = motion.getX();
                        int resId = -1;

                        x -= viewCenter;

                        if(x < -CENTER_NO_TOUCH_AREA){
                            resId = R.drawable.card_left;
                            triggerNo();
                        } 
                        if(x > CENTER_NO_TOUCH_AREA){
                            resId = R.drawable.card_right;
                            triggerYes();
                        }
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
    
    void triggerYes(){
        textview.setText("Yes");
    }
    
    void triggerNo(){
        textview.setText("No");
    }
}
