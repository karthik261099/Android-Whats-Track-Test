package com.kbrosapp.whatstracktest;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {

    @Override
    public void onPageFinished(WebView webView, String url) {
        super.onPageFinished(webView, url);

        String script = "document.getElementsByClassName(\"_1WKYb\")[0].innerHTML=\"Karthisadk\"";

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            webView.evaluateJavascript(script, null);
        } else {
            webView.loadUrl("javascript:"+script);
        }

    }
}
