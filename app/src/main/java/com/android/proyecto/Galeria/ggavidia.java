package com.android.proyecto.Galeria;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.proyecto.R;

public class ggavidia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ggavidia);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        WebView view = (WebView) findViewById(R.id.web);
        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setBuiltInZoomControls(false);
        view.loadUrl("http://www.sda.heavywebdesign.com/android/galeria/gavidia.php");
        view.setWebViewClient(new WebViewClient(){
            public boolean shouldOverriceUrlLoading(WebView view, String url){
                return false;
            }
        });
    }
}
