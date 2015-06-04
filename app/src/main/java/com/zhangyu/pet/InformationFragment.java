package com.zhangyu.pet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by gaoxiong on 2015/6/4.
 */
public class InformationFragment extends PlaceholderFragment {
    private LayoutInflater layoutInflater;

    public static InformationFragment newInstance(int sectionNumber) {
        InformationFragment fragment = new InformationFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int resourceId = R.layout.fragment_pet_info;
        layoutInflater = inflater;
        View rootView = inflater.inflate(resourceId, container, false);
        return rootView;
    }
}
