package com.kbrosapp.whatstracktest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button buttonStart;
    private Button buttonStop;

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webview);

        //webView =  new WebView(getApplicationContext());

        webView.loadUrl("https://web.whatsapp.com/send?phone=918655803448");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView webView, String url) {
                Toast.makeText(MainActivity.this, "done", Toast.LENGTH_SHORT).show();
                //super.onPageFinished(webView, url);
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        webView.loadUrl("javascript:function x(){document.querySelector(\"._1WKYb\").textContent=\"sad\";var canvas = document.querySelector(\"._1yHR2\").children[2];\n" +
//                                "var img    = canvas.toDataURL(\"image/png\");\n" +
//                                "document.write('<img src=\"'+img+'\"/><a href=\"'+img+'\" download=\"AwesomeImage.png\">sadsa</a>');}; x();");

                    }
                }, 20000);

            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Win64; x64; rv:46.0) Gecko/20100101 Firefox/68.0");
        webView.getSettings().setGeolocationEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setNeedInitialFocus(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //webView.getSettings().setBlockNetworkLoads(true);
        webView.getSettings().setBlockNetworkImage(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setInitialScale(100);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);



        buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStop = (Button) findViewById(R.id.buttonStop);

        Intent intent=new Intent(this,MyService.class);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                    startForegroundService(intent);
                }else{
                    startService(intent);
                }

//                webView.evaluateJavascript("javascript:function x(){var isOnline=document.querySelector(\"._3Id9P\").textContent;return isOnline;}; x();", new ValueCallback<String>() {
//                    @Override
//                    public void onReceiveValue(String isOnline) {
//                        Toast.makeText(MainActivity.this, isOnline, Toast.LENGTH_SHORT).show();
//                    }
//                });

//                Handler handler = new Handler();
//                final int delay = 2000; // 1000 milliseconds == 1 second
//
//                handler.postDelayed(new Runnable() {
//                    public void run() {
//
//                        webView.evaluateJavascript("javascript:function x(){var isOnline=document.querySelector(\"._3Id9P\").textContent;return isOnline;}; x();", new ValueCallback<String>() {
//                            @Override
//                            public void onReceiveValue(String isOnline) {
//                                if(isOnline!=null && !isOnline.equals("null")){
//                                    Toast.makeText(MainActivity.this, isOnline, Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//
//                        handler.postDelayed(this, delay);
//                    }
//                }, delay);

            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(intent);
            }
        });

    }
}