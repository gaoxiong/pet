package com.zhangyu.pets.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.zhangyu.pets.PetTrainingDetailActivity;
import com.zhangyu.pets.utils.Consts;

/**
 * Created by gaoxiong on 2015/6/4.
 */
public class BaseWebChromeClient extends WebChromeClient {
    protected PetWebViewHelper.Delegate delegate;
    private Context context;

    public BaseWebChromeClient(Context context, PetWebViewHelper.Delegate delegate) {
        this.context = context;
        this.delegate = delegate;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        if (delegate == null) {
            return;
        }

        String url = view.getUrl();
        if (url == null) {
            return;
        }

        delegate.onPageChanged(view, url);
    }

    @Override
    public View getVideoLoadingProgressView() {
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        return frameLayout;
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        if (message.startsWith(Consts.PETS_PREFIX)) {
            if (message.contains(Consts.PETS_DOMAIN_TRAININGDETAILS)) {
                Intent intent = new Intent(context, PetTrainingDetailActivity.class);
                intent.putExtra(Consts.INTENT_URL, message);
                delegate.onStartActivity(intent);
            }
            return true;
        } else {
            return super.onJsAlert(view, url, message, result);
        }
    }
}
