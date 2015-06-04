package com.zhangyu.pets.view;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;

/**
 * Parse the download url of videos on Youtube.
 */
public class PetWebViewHelper {

  private String defaultUrl;
  private Delegate delegate;
  private boolean isLoading;
  private BaseWebChromeClient webChromeClient;
  private BaseWebChromeClient baseWebChromeClient;
  private PetWebView webView;
  private View view;
  private ViewGroup fullScreenViewGroup;

  public interface Delegate {
    void onPageChanged(WebView view, String url);

    void onLoadFinished(WebView view, String url);

    void onLoadFailed(WebView view, int errorCode, String description, String failingUrl);

    void onStartActivity(Intent intent);
  }

  public PetWebViewHelper(Delegate delegate, PetWebView webView, View view,
                          ViewGroup fullScreenViewGroup) {
    this.delegate = delegate;
    this.webView = webView;
    this.view = view;
    this.fullScreenViewGroup = fullScreenViewGroup;
    setupWebView(webView);
  }

  public void setDefaultUrl(String defaultUrl) {
    this.defaultUrl = defaultUrl;
  }

  public void setupWebView(PetWebView webView) {
    webView.getSettings().setJavaScriptEnabled(true);
    webView.setWebViewClient(new BaseWebViewClient(webView.getContext(), delegate));
    baseWebChromeClient = new BaseWebChromeClient(webView.getContext(), delegate);
    webView.setWebChromeClient(baseWebChromeClient);
  }

  /**
   * @return true if the webview can go back
   */
  public boolean canGoBack() {
    if (webView.canGoBack()) {
      return true;
    }
    return false;
  }

  public void startLoading(WebView webView) {
    isLoading = true;
    webView.loadUrl(defaultUrl);
  }

  public void pauseWebView() {
  }

  public void resumeWebView() {
  }

  public void reloadWebView() {
    if (webChromeClient != null) {
//      webChromeClient.onBackPressed();
    }
    webView.reload();
  }

  public void destroyWebView() {
    if (webChromeClient != null) {
//      webChromeClient.onBackPressed();
    }
    if (webView != null) {
      ViewParent videoLayout = webView.getParent();
      if (videoLayout != null && videoLayout instanceof ViewGroup) {
        ((ViewGroup) videoLayout).removeView(webView);
      }
      webView.removeAllViews();
      webView.destroy();
    }
  }

  public void goBack() {
    webView.goBack();
  }
}
