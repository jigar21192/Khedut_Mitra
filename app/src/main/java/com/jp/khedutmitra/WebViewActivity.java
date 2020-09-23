package com.jp.khedutmitra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jp.khedutmitra.databinding.ActivityWebViewBinding;
import com.jp.khedutmitra.utils.Utility;
import com.jp.khedutmitra.utils.UtilsStatusbar;

public class WebViewActivity extends AppCompatActivity {

    private ActivityWebViewBinding binding;
    private final String BASE_URL="https://anyror.gujarat.gov.in/";
    private final String RURAL=BASE_URL+"LandRecordRural.aspx";
    private final String URBAN=BASE_URL+"emilkat/GeneralReport_IDB.aspx";
    private final String PROPERTY_SEARCH=BASE_URL+"egarvipropertyror/DefaultPagePropertySearchror.aspx";
    private String web_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web_view);
        ButterKnife.bind(this);
        UtilsStatusbar.setStatusBarWhite(binding.getRoot());
        UtilsStatusbar.setStatusBarColor(this,getResources().getColor(R.color.c_7f8d02));

        web_value = getIntent().getStringExtra("data");
        initViews();

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initViews() {
        WebSettings webSettings = binding.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        if (web_value.equals("rural")) {
        //    binding.tvAboutUsActivityTitle.setText(R.string.about_us);
            binding.webView.setWebViewClient(new myWebClient());
            binding.webView.loadUrl(RURAL);
        }else if (web_value.equals("urban")) {
        //    binding.tvAboutUsActivityTitle.setText(R.string.about_us);
            binding.webView.setWebViewClient(new myWebClient());
            binding.webView.loadUrl(URBAN);
        }else if (web_value.equals("property")) {
        //    binding.tvAboutUsActivityTitle.setText(R.string.about_us);
            binding.webView.setWebViewClient(new myWebClient());
            binding.webView.loadUrl(PROPERTY_SEARCH);
        }else if (web_value.equals("anyror")) {
        //    binding.tvAboutUsActivityTitle.setText(R.string.about_us);
            binding.webView.setWebViewClient(new myWebClient());
            binding.webView.loadUrl(BASE_URL);
        }
    }

    @OnClick(R.id.iv_back)
    public void back(View v) {
        Utility.preventTwoClick(v);
        onBackPressed();
    }

    public class myWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            binding.pb.setVisibility(View.VISIBLE);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            progress_AboutAppActivity.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            binding.pb.setVisibility(View.GONE);
            super.onPageFinished(view, url);
//            progress_AboutAppActivity.setVisibility(View.GONE);
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (binding.webView.canGoBack()) {
                        binding.webView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

}