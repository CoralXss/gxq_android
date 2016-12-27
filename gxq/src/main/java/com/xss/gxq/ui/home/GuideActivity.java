package com.xss.gxq.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.xss.gxq.R;
import com.xss.gxq.ui.base.BaseActivity;

/**
 * @类描述
 * @创建人：xss
 * @创建时间：2015/9/14 17:51
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class GuideActivity extends BaseActivity {
    private IndicatorViewPager indicatorViewPager;
    private ViewPager viewPager;
    private Indicator fixedIndicatorView;

    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initView();
        initData();
    }

    @Override
    public void initView() {
        inflater = LayoutInflater.from(getApplicationContext());

        fixedIndicatorView = (Indicator) findViewById(R.id.guide_indicator);
        viewPager = (ViewPager)findViewById(R.id.guide_viewPager);
        indicatorViewPager = new IndicatorViewPager(fixedIndicatorView, viewPager);
        indicatorViewPager.setAdapter(adapter);

    }

    @Override
    public void initData() {

    }

    private IndicatorViewPager.IndicatorPagerAdapter adapter = new IndicatorViewPager.IndicatorViewPagerAdapter() {
        private int[] images = {R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4};
        private ViewHolder holder = null;

        @Override
        public int getCount() {
            return images.length;
        }

        //初始化小圆点
        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.indicator_tab_guide, container, false);
            }
            return convertView;
        }

        //初始化指引图片
        @Override
        public View getViewForPage(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
//                convertView = new View(getApplicationContext());
//                convertView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.indicator_view_page, container, false);
               convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.ll_page = (RelativeLayout) convertView.findViewById(R.id.ll_page);
            holder.btn_start = (Button)convertView.findViewById(R.id.btn_start);

            if (position == images.length - 1) {
                holder.btn_start.setVisibility(View.VISIBLE);
                holder.btn_start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(GuideActivity.this, SplashActivity.class);
                        startActivityAnimation(intent);
                        finish();
                    }
                });
            }
            holder.ll_page.setBackgroundResource(images[position]);

            return convertView;
        }

        class ViewHolder {
            RelativeLayout ll_page;
            Button btn_start;
        }
    };
}
