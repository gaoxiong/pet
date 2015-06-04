package com.zhangyu.pet;

import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by gaoxiong on 2015/6/4.
 */
public class utils {

    public static void initWebView(WebView webView) {
        webView.getSettings().setJavaScriptEnabled(false);
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setDefaultFontSize(18);
    }
}
