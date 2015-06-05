package com.zhangyu.pets;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.zhangyu.pets.fragment.BaseFragment;
import com.zhangyu.pets.fragment.ExplorerFragment;
import com.zhangyu.pets.fragment.PetInfoFragment;
import com.zhangyu.pets.fragment.TrainingVideosFragment;
import com.zhangyu.pets.utils.Consts;
import com.zhangyu.pets.view.PetWebView;
import com.zhangyu.pets.view.indicator.FragmentListPageAdapter;
import com.zhangyu.pets.view.indicator.IndicatorViewPager;
import com.zhangyu.pets.view.indicator.ScrollIndicatorView;
import com.zhangyu.pets.view.indicator.slidebar.ColorBar;
import com.zhangyu.pets.view.indicator.transition.OnTransitionTextListener;


public class MainActivity extends FragmentActivity {
    private IndicatorViewPager indicatorViewPager;
    private LayoutInflater inflate;
    private long clickTime = 0; //记录第一次点击的时间
    private int[] namesID = {
            R.string.tab1,
            R.string.tab2,
            R.string.tab3
    };
    private ScrollIndicatorView indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton1);
        ViewPager viewPager = (ViewPager) findViewById(R.id.moretab_viewPager);
        indicator = (ScrollIndicatorView) findViewById(R.id.moretab_indicator);
        indicator.setScrollBar(new ColorBar(this, Color.RED, 5));

        // 设置滚动监听
        int selectColorId = R.color.tab_top_text_2;
        int unSelectColorId = R.color.tab_top_text_1;
        indicator.setOnTransitionListener(new OnTransitionTextListener().setColorId(this, selectColorId, unSelectColorId));

        viewPager.setOffscreenPageLimit(2);
        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        inflate = LayoutInflater.from(getApplicationContext());
        indicatorViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));

        // 默认true ，自动布局
//        toggleButton.setChecked(indicator.isSplitAuto());
//        toggleButton.setOnCheckedChangeListener(onCheckedChangeListener);

        indicator.setSplitAuto(true);

    }

    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // 设置是否自动布局
            indicator.setSplitAuto(isChecked);
        }
    };

    private int size = 3;

    public void on3(View view) {
        size = 3;
        indicatorViewPager.getAdapter().notifyDataSetChanged();
    }

    public void on4(View view) {
        size = 4;
        indicatorViewPager.getAdapter().notifyDataSetChanged();
    }

    public void on5(View view) {
        size = 5;
        indicatorViewPager.getAdapter().notifyDataSetChanged();
    }

    public void on12(View view) {
        size = 12;
        indicatorViewPager.getAdapter().notifyDataSetChanged();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            int sectionNumber = indicatorViewPager.getCurrentItem();
            PetWebView webView = PetsApplication.getInstance().getWebView(sectionNumber);
            if (webView != null && sectionNumber > 0) {
                webView.goBack();
                return true;
            } else {
                exit();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    private void exit() {
        if ((System.currentTimeMillis() - clickTime) > 2000) {
            Toast.makeText(getApplicationContext(), "Press again to exit...",
                    Toast.LENGTH_SHORT).show();
            clickTime = System.currentTimeMillis();
        } else {
            this.finish();
        }
    }

    private class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return size;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflate.inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(getString(namesID[position % namesID.length]));
            textView.setPadding(20, 0, 20, 0);
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            BaseFragment fragment;
            if (position == 0) {
                fragment = ExplorerFragment.newInstance(position);
            } else if (position == 1) {
                fragment = TrainingVideosFragment.newInstance(position);
            } else {
                fragment = PetInfoFragment.newInstance(position);
            }
            Bundle bundle = new Bundle();
            bundle.putInt(Consts.ARG_SECTION_NUMBER, position);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getItemPosition(Object object) {
            return FragmentListPageAdapter.POSITION_NONE;
        }
    }
}
