package com.example.carl.ui_clicklike;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private LoveLayout mLoveLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoveLayout = findViewById(R.id.love_layout);
    }

    public void addLike(View view) {
        mLoveLayout.addLove();
    }
}
