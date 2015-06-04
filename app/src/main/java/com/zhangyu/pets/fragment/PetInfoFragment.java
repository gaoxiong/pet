package com.zhangyu.pets.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.zhangyu.pets.PetsApplication;
import com.zhangyu.pets.R;
import com.zhangyu.pets.utils.Consts;
import com.zhangyu.pets.utils.utils;
import com.zhangyu.pets.view.PetWebView;
import com.zhangyu.pets.view.PetWebViewHelper;

/**
 * Created by gaoxiong on 2015/6/4.
 */
public class PetInfoFragment extends BaseFragment
        implements PetWebViewHelper.Delegate, View.OnClickListener {
    private LayoutInflater layoutInflater;
    PetWebView mWebView;
    private View webviewLayout;
    private ViewGroup fullScreenLayout;
    private PetWebViewHelper petWebViewHelper;
    private String defaultUrl = "http://100.64.20.167/info/info.htm";

    public static PetInfoFragment newInstance(int sectionNumber) {
        PetInfoFragment fragment = new PetInfoFragment();
        Bundle args = new Bundle();
        args.putInt(Consts.ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int resourceId = R.layout.fragment_pet_info;
        layoutInflater = inflater;
        View rootView = inflater.inflate(resourceId, container, false);
        mWebView = (PetWebView) rootView.findViewById(R.id.wv_info_list);
        webviewLayout = rootView.findViewById(R.id.info_webviewLayout);
        utils.initWebView(mWebView);
        PetsApplication.getInstance().setWebView(mWebView);

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
