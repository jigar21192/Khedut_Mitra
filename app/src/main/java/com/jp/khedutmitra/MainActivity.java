package com.jp.khedutmitra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.jp.khedutmitra.databinding.ActivityMainBinding;
import com.jp.khedutmitra.databinding.ActivityWebViewBinding;
import com.jp.khedutmitra.utils.Utility;
import com.jp.khedutmitra.utils.UtilsStatusbar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ButterKnife.bind(this);
        UtilsStatusbar.setStatusBarWhite(binding.getRoot());
        UtilsStatusbar.setStatusBarColor(this,getResources().getColor(R.color.c_7f8d02));

    }

    @OnClick(R.id.tv_rural)
    public void rural(View v) {
        Utility.preventTwoClick(v);
        startActivity(new Intent(MainActivity.this,WebViewActivity.class).putExtra("data","rural"));
    }
    @OnClick(R.id.tv_urban)
    public void urban(View v) {
        Utility.preventTwoClick(v);
        startActivity(new Intent(MainActivity.this,WebViewActivity.class).putExtra("data","urban"));
    }
    @OnClick(R.id.tv_property)
    public void property(View v) {
        Utility.preventTwoClick(v);
        startActivity(new Intent(MainActivity.this,WebViewActivity.class).putExtra("data","property"));
    }
    @OnClick(R.id.tv_anyror)
    public void anyror(View v) {
        Utility.preventTwoClick(v);
        startActivity(new Intent(MainActivity.this,WebViewActivity.class).putExtra("data","anyror"));
    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}