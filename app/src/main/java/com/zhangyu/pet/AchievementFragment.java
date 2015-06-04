package com.zhangyu.pet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by gaoxiong on 2015/6/4.
 */
public class AchievementFragment extends PlaceholderFragment {
    private LayoutInflater layoutInflater;

    public static AchievementFragment newInstance(int sectionNumber) {
        AchievementFragment fragment = new AchievementFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int resourceId = R.layout.fragment_pet_achievement;
        layoutInflater = inflater;
        View rootView = inflater.inflate(resourceId, container, false);
        return rootView;
    }
}
