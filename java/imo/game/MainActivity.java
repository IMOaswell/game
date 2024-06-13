package imo.game;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        View root = findViewById(R.id.root);
        root.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                _DebugMenu.show(MainActivity.this, v);
            }
        });
    }
}
