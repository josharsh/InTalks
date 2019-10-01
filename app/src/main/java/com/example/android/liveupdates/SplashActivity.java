package com.example.android.liveupdates;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

// Since this is a splash screen and the style set for in the AndroidManifest
// is the splash screen itself, this doesn't need a layout (so, there's no setContentView here)
// This makes the app run faster and open with the splash screen already open
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // check if we are not recreating... (avoid multiple splash's to spawn)
        if (savedInstanceState == null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 1000);
        }
    }
}
