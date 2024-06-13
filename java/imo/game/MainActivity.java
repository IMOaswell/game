package imo.game;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.PopupMenu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        View root = findViewById(R.id.root);
        root.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showDropdown(v);
            }
        });
    }
    
    void showDropdown(View anchor){
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, anchor);
        popupMenu.getMenu().add(0, 1, 0, "hi world:D");
        popupMenu.getMenu().add(0, 2, 1, "hi world:D");
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item){
                switch(item.getItemId()){
                    case 1:
                        Toast.makeText(MainActivity.this, "hallo:D", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });
        popupMenu.show();
    }
}
