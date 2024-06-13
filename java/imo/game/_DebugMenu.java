package imo.game;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class _DebugMenu {
    
    static void showDebug(Context mContext, View anchor){
        final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;

        LinearLayout layout = new LinearLayout(mContext);
        layout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams childParams = new LinearLayout.LayoutParams(MATCH_PARENT, 0, 1.0f);
        Button button1 = new Button(mContext);
        button1.setLayoutParams(childParams);
        layout.addView(button1);

        Button button2 = new Button(mContext);
        button2.setLayoutParams(childParams);
        layout.addView(button2);

        final PopupWindow popupWindow = new PopupWindow(layout, 200, 200, true);
        popupWindow.showAsDropDown(anchor, 0, 0);

        button1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    popupWindow.dismiss();
                }
            });
        button2.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    popupWindow.dismiss();
                }
            });
    }
    
}