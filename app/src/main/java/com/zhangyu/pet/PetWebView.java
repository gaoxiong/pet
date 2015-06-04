package com.zhangyu.pet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by gaoxiong on 2015/6/4.
 */
public class PetWebView extends WebView {

    private BaseWebChromeClient baseWebChromeClient;
    private boolean addedJavascriptInterface;

    public PetWebView(Context context) {
        super(context);
        addedJavascriptInterface = false;
    }

    public PetWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        addedJavascriptInterface = false;
    }

    public PetWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        addedJavascriptInterface = false;
    }

    /**
     * Pass only a VideoEnabledWebChromeClient instance.
     */
    @Override
    @SuppressLint("SetJavaScriptEnabled")
    public void setWebChromeClient(WebChromeClient client) {
        getSettings().setJavaScriptEnabled(true);

        if (client instanceof BaseWebChromeClient) {
            this.baseWebChromeClient = (BaseWebChromeClient) client;
        }

        super.setWebChromeClient(client);
    }

    @Override
    public void loadUrl(String url) {
        addJavascriptInterface();
        super.loadUrl(url);
    }

    private void addJavascriptInterface() {
        if (!addedJavascriptInterface) {
            // Add javascript interface to notify the video end
            addJavascriptInterface(new PetPluginInterface(), "_PetWebView");

            addedJavascriptInterface = true;
        }
    }

    /**
     * java interface for js.
     */
    public class PetPluginInterface {
        /*
         * called after video end
         */
        @android.webkit.JavascriptInterface
        public void notifyPetEnd() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    if (baseWebChromeClient != null) {
                        baseWebChromeClient.onHideCustomView();
                    }
                }
            });
        }
    }
}
