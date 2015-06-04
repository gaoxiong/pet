package com.zhangyu.pet;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;

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
}
