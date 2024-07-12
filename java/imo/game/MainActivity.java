package imo.game;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import java.util.List;
import java.util.Arrays;
import android.widget.ImageView;

public class MainActivity extends Activity{
    List<String> storyScript;
    int scriptIndex = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        View root = findViewById(R.id.root);
        final TextView textview = findViewById(R.id.textview);
        final ImageView imageview = findViewById(R.id.imageview);
        String story_script = getString(R.string.story_script);
        story_script = story_script.trim();
        storyScript = Arrays.asList(story_script.split("\n"));
        
        root.setOnTouchListener(new OnTouchListener(){
                @Override
                public boolean onTouch(View v,MotionEvent motion){
                    if(MotionEvent.ACTION_DOWN != motion.getAction()) return true;
                    if(scriptIndex > storyScript.size() - 1) return true;
                    
                    String string = storyScript.get(scriptIndex);
                    scriptIndex++;
                    
                    boolean isCommand = string.startsWith(Command.PREFIX);
                    if(!isCommand)
                        textview.setText(string);
                        
                    if(Command.isCommand(Command.CHOICES, string)){
                        Command.runChoices(string, textview);
                    }
                    return true;
                }
            });
        
        imageview.setImageResource(R.drawable.card_transparent);
        imageview.setOnTouchListener(new OnTouchListener(){
                final int CENTER_NO_TOUCH_AREA = 75;
                @Override
                public boolean onTouch(View view,MotionEvent motion){
                    ImageView v = (ImageView) view;
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
                        v.setImageResource(resId);
                    }
                    if(MotionEvent.ACTION_UP == motion.getAction()){
                        v.setImageResource(R.drawable.card_transparent);
                    }
                    return true;
                }
            });
    }
    
    void triggerYes(){
        //do something
    }
    
    void triggerNo(){
        //do something
    }
    
    static class Command{
        final static String PREFIX = "/";
        final static String CHOICES = PREFIX + "choices";
        static boolean isCommand(String command, String input){
            return input.startsWith(command);
        }
        static void runChoices(String string, TextView textview){
            string = string.substring((CHOICES + '=').length());
            
            String noString = string.substring(0, string.indexOf(':')).trim();
            String yesString = string.substring(string.indexOf(':') + 1).trim();
            textview.append("\n");
            textview.append(noString + "\t\t\t" + yesString);
        }
    }
}
