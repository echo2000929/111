package com.example.zyfypt_229.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.zyfypt_229.R;
import com.example.zyfypt_229.common.Common;

public class ViewVideoActivity extends AppCompatActivity {
    private int resid;
    private WebView webView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_video);
        System.out.println("----查看文章详情");

        resid  = getIntent().getIntExtra("resid",1);

        webView3 = findViewById(R.id.webview3);
        webView3.loadUrl(Common.VIDEOURL+resid);
    }
}
