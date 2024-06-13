package imo.game;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class _DebugMenu{
    static PopupWindow popupWindow;
    private static LinearLayout layout;

    static void show(Context mContext,View anchor){
        if(layout != null){
            popupWindow.showAsDropDown(anchor, 0, 0);
            return;
        }
        final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;

        layout = new LinearLayout(mContext);
        layout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams childParams = new LinearLayout.LayoutParams(MATCH_PARENT, 0, 1.0f);
        Button button1 = new Button(mContext);
        button1.setLayoutParams(childParams);
        layout.addView(button1);

        Button button2 = new Button(mContext);
        button2.setLayoutParams(childParams);
        layout.addView(button2);

        if(popupWindow == null)
            popupWindow = new PopupWindow(layout, 200, 200, true);

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

        popupWindow.showAsDropDown(anchor, 0, 0);
    }

}
