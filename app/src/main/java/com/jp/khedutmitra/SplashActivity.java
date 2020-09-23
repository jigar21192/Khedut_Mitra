package com.jp.khedutmitra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;

import com.jp.khedutmitra.databinding.ActivitySplashBinding;
import com.jp.khedutmitra.utils.UtilsStatusbar;

import java.lang.reflect.Method;

public class SplashActivity extends AppCompatActivity {

    private int SPLASH_TIME = 2000;
    private ActivitySplashBinding binding;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        ButterKnife.bind(this);
        UtilsStatusbar.setStatusBarWhite(binding.getRoot());
        UtilsStatusbar.setStatusBarColor(this,getResources().getColor(R.color.c_7f8d02));

        handler = new Handler();
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, SPLASH_TIME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class).
                        setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));

        }
    };
}
