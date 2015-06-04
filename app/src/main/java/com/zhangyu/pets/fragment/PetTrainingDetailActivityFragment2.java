package com.zhangyu.pets.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.zhangyu.pets.R;
import com.zhangyu.pets.utils.Consts;
import com.zhangyu.pets.utils.utils;
import com.zhangyu.pets.view.PetWebView;
import com.zhangyu.pets.view.PetWebViewHelper;


/**
 * A placeholder fragment containing a simple view.
 */
public class PetTrainingDetailActivityFragment2 extends BaseFragment
        implements PetWebViewHelper.Delegate, View.OnClickListener {
    Context context;
    String courseId;
    private LayoutInflater layoutInflater;
    PetWebView mWebView;
    private View webviewLayout;
    private ViewGroup fullScreenLayout;
    private PetWebViewHelper petWebViewHelper;
    private String defaultUrl = "http://100.64.20.167/detail/detail.htm?courseId=";

    public PetTrainingDetailActivityFragment2() {
    }

    public static PetTrainingDetailActivityFragment2 newInstance(Context context, String courseId) {
        PetTrainingDetailActivityFragment2 fragment = new PetTrainingDetailActivityFragment2();
        fragment.context = context;
        fragment.courseId = courseId;
        fragment.defaultUrl += courseId;

        Bundle args = new Bundle();
        args.putString(Consts.ARG_SECTION_NUMBER, courseId);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int resourceId = R.layout.fragment_pet_training_detail;
        layoutInflater = inflater;
        View rootView = inflater.inflate(resourceId, container, false);
        mWebView = (PetWebView) rootView.findViewById(R.id.detail_training_video_list);
        webviewLayout = rootView.findViewById(R.id.detail_webviewLayout);
        utils.initWebView(mWebView);

        petWebViewHelper = new PetWebViewHelper(this, mWebView, webviewLayout, fullScreenLayout);
        if (!TextUtils.isEmpty(defaultUrl)) {
            petWebViewHelper.setDefaultUrl(defaultUrl);
        }
        petWebViewHelper.startLoading(mWebView);
        return rootView;
    }


    @Override
    public void onLoadFinished(WebView view, String url) {
//        TipsViewUtil.hideTipsView(webView, TipsType.LOADING);
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onPageChanged(WebView view, String url) {
    }

    @Override
    public void onLoadFailed(final WebView view, int errorCode, String description,
                             final String failingUrl) {
    }

    public void onStartActivity(Intent intent) {
        startActivity(intent);
    }
}
