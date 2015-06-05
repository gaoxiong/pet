package com.zhangyu.pets;

import android.app.Application;
import android.content.Context;

import com.zhangyu.pets.view.PetWebView;

import java.util.HashMap;

/**
 * Created by gaoxiong on 2015/6/5.
 */
public class PetsApplication extends Application {

    private static Context context;
    private HashMap<Integer, PetWebView> mWebViewList = new HashMap<>();

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

    public void addWebView(int sectionNuber, PetWebView webView) {
        if (mWebViewList.get(sectionNuber) == null) {
            this.mWebViewList.put(sectionNuber, webView);
        }
    }

    public PetWebView getWebView(int sectionNuber) {
        return mWebViewList.get(sectionNuber);
    }
}
