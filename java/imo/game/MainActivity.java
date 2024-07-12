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
    View root;
    TextView textview;
    List<String> storyScript;
    int scriptIndex = 0;
    OnTouchListener shuffleScriptMode;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        textview = findViewById(R.id.textview);
        root = findViewById(R.id.root);
        String story_script = getString(R.string.story_script);
        story_script = story_script.trim();
        storyScript = Arrays.asList(story_script.split("\n"));
        
        shuffleScriptMode = new OnTouchListener(){
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
                    root.setOnTouchListener(choiceSelectMode());
                }
                return true;
            }
        };
        root.setOnTouchListener(shuffleScriptMode);
        root.setBackgroundResource(R.drawable.card_transparent);
    }
    
    void triggerYes(){
        textview.setText("Yes");
        root.setOnTouchListener(shuffleScriptMode);
    }
    
    void triggerNo(){
        textview.setText("No");
        root.setOnTouchListener(shuffleScriptMode);
    }
    
    OnTouchListener choiceSelectMode(){
        return new OnTouchListener(){
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
        };
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
