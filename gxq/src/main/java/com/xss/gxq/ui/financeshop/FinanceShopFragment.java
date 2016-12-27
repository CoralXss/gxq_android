package com.xss.gxq.ui.financeshop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shizhefei.fragment.LazyFragment;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.xss.gxq.R;
import com.xss.gxq.ui.base.BaseFragment;

/**
 * @类描述
 * @创建人：xss
 * @创建时间：2015/9/15 14:05
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class FinanceShopFragment extends LazyFragment {
    private IndicatorViewPager indicatorViewPager;
    private ViewPager viewPager;
    private Indicator indicator;

    private int index = 0;  //设置选中的tab

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_finance_shop);

        initView();

    }

    protected void initView() {
        indicator = (Indicator)findViewById(R.id.tab_indicator);
        viewPager = (ViewPager)findViewById(R.id.tab_viewpager);
        //设置标题tab滑动栏的颜色
        int barColor = getResources().getColor(R.color.color_50D2CC);
        indicator.setScrollBar(new ColorBar(getActivity(), barColor, 5));
        indicator.setScrollBar(new ColorBar(getActivity(), barColor, 5, 200));

        //设置滑动时字体颜色的变化 (选中的颜色，未选中的颜色)
        int selectColor = getResources().getColor(R.color.color_50D2CC);
        int unselectColor = getResources().getColor(R.color.color_000000);
        indicator.setOnTransitionListener(new OnTransitionTextListener().setColor(selectColor, unselectColor));

        // 设置viewpager保留界面不重新加载的页面数量
        viewPager.setOffscreenPageLimit(2);
        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);

        //在Fragment是通过getChildFragmentManager(),在Activity中是通过getSupportFragmentManager()获取
        indicatorViewPager.setAdapter(new MyIndicatorFragmentPagerAdapter(getChildFragmentManager()));

    }

    class MyIndicatorFragmentPagerAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
        private String[] tabTitles = {"固定收益", "基金保险"};

        public MyIndicatorFragmentPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.fragment_shop_tab_title, container, false);
            }
            TextView textView = (TextView)convertView;
            textView.setText(tabTitles[position]);
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            BaseFragment bf = null;
            if (position == 0) {
                bf = new FragmentAbsoluteProfit();
            } else if (position == 1) {
                bf = new FragmentAssurance();
            }
            return bf;
        }
    }

}
