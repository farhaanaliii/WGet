package com.github.farhanaliofficial.wget.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.View;
import android.widget.LinearLayout;
import android.view.Gravity;
import android.widget.ImageView;
import com.github.farhanaliofficial.wget.R;
import android.os.Handler;
import android.content.Intent;
import android.graphics.Color;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        
        LinearLayout layout = new LinearLayout(SplashActivity.this);
        layout.setGravity(Gravity.CENTER);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(Color.parseColor("#4789F1"));
        ImageView iv = new ImageView(SplashActivity.this);
        iv.setImageResource(R.drawable.icon2);
        
        layout.addView(iv);
        
        setContentView(layout);
        
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        },2000);
    }
}
