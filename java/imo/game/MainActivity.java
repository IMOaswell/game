package imo.game;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity{
    List<String> storyScript;
    int scriptIndex = 0;
    static boolean pauseScript = false;
    static boolean unpauseScriptAfterInput = true;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        View root = findViewById(R.id.root);
        final TextView textview = findViewById(R.id.textview);
        final ImageView card = findViewById(R.id.imageview);
        String story_script = getString(R.string.story_script);
        story_script = story_script.trim();
        storyScript = Arrays.asList(story_script.split("\n"));
        
        root.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v){
                    if(pauseScript) return;
                    if(scriptIndex > storyScript.size() - 1) return;

                    String string = storyScript.get(scriptIndex);
                    scriptIndex++;

                    boolean isCommand = string.startsWith(Command.PREFIX);
                    if(!isCommand)
                        textview.setText(string);

                    if(Command.isCommand(Command.DISPLAY_CHOICES, string)){
                        Command.runDisplayChoices(string, textview, card);
                    }
                }
        });
        
        deactivateCardInputs(card);
        
        card.setImageResource(R.drawable.card_transparent);
        card.setOnTouchListener(new OnTouchListener(){
                final int CENTER_NO_TOUCH_AREA = 75;
                int output = -1;
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
                            output = 0;
                        } 
                        if(x > CENTER_NO_TOUCH_AREA){
                            resId = R.drawable.card_right;
                            output = 1;
                        }
                        if(resId == -1) resId = R.drawable.card_solid;
                        v.setImageResource(resId);
                    }
                    if(MotionEvent.ACTION_UP == motion.getAction()){
                        v.setImageResource(R.drawable.card_transparent);
                        if(output == 0) cardInputNo(v);
                        if(output == 1) cardInputYes(v);
                    }
                    return true;
                }
            });
    }

    static class Command{
        final static String PREFIX = "/";
        final static String DISPLAY_CHOICES = PREFIX + "choices";
        
        static boolean isCommand(String command,String input){
            return input.startsWith(command);
        }

        static void runDisplayChoices(String string,TextView textview, View card){
            string = string.substring((DISPLAY_CHOICES + '=').length());

            String noString = string.substring(0, string.indexOf("::")).trim();
            String yesString = string.substring(string.indexOf("::") + 2).trim();
            textview.append("\n");
            textview.append(noString + "\t\t\t" + yesString);
            activateCardInputs(true, card);
        }
    }
    
    static void activateCardInputs(boolean unpauseScriptAfter, View card){
        pauseScript = true;
        card.setEnabled(true);
        unpauseScriptAfterInput = unpauseScriptAfter;
    }
    
    static void deactivateCardInputs(View card){
        if(unpauseScriptAfterInput) pauseScript = false;
        card.setEnabled(false);
    }
    
    void cardInputNo(View card){
        cardInput(card);
    }
    
    void cardInputYes(View card){
        cardInput(card);
    }
    
    void cardInput(View card){
        deactivateCardInputs(card);
    }
}
