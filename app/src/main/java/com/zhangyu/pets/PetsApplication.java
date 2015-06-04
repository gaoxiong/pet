package com.zhangyu.pets;

import android.app.Application;
import android.content.Context;

import com.zhangyu.pets.view.PetWebView;

/**
 * Created by gaoxiong on 2015/6/5.
 */
public class PetsApplication extends Application {

    private static Context context;
    private PetWebView mWebView;

    @Override
    public void onCreate() {
        super.onCreate();
        doInit();
    }


    private void doInit() {
        context = this;
    }

    public static Context getAppContext() {
        return context;
    }

    public static PetsApplication getInstance() {
        return (PetsApplication) context;
    }

    public void setWebView(PetWebView webView) {
        this.mWebView = webView;
    }

    public PetWebView getWebView() {
        return this.mWebView;
    }
}
