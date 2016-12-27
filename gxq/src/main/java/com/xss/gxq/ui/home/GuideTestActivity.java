package com.xss.gxq.ui.home;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
public class GuideTestActivity extends BaseActivity {
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
        viewPager = (ViewPager)findViewById(R.id.guide_viewPager);
        fixedIndicatorView = (Indicator)findViewById(R.id.guide_indicator);

        indicatorViewPager = new IndicatorViewPager(fixedIndicatorView, viewPager);
        indicatorViewPager.setAdapter(adapter);

    }

    @Override
    public void initData() {

    }

    private IndicatorViewPager.IndicatorPagerAdapter adapter = new IndicatorViewPager.IndicatorViewPagerAdapter() {
        private int[] images = {R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4};
        private ViewHolder holder = null;

        private String[][] tv_txt_arr = {{"1", "2", "3", "4", "5", "6", "7"}, {"1", "2", "3", "4", "5", "6", "7"}};

        @Override
        public int getCount() {
            return tv_txt_arr.length;
        }

        //初始化小圆点
        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.indicator_tab_guide, container, false);
                }
                return convertView;
            //return null;
        }

        //初始化指引图片
        @Override
        public View getViewForPage(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
//                convertView = new View(getApplicationContext());
//                convertView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.viewpager_guide_test, container, false);
               convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.tv_1 = (TextView) convertView.findViewById(R.id.tv_1);
            holder.tv_2 = (TextView) convertView.findViewById(R.id.tv_2);
            holder.tv_3 = (TextView) convertView.findViewById(R.id.tv_3);
            holder.tv_4 = (TextView) convertView.findViewById(R.id.tv_4);
            holder.tv_5 = (TextView) convertView.findViewById(R.id.tv_5);
            holder.tv_6 = (TextView) convertView.findViewById(R.id.tv_6);
            holder.tv_7 = (TextView) convertView.findViewById(R.id.tv_7);

            String[] arr = tv_txt_arr[position];

                holder.tv_1.setText(arr[0]);
                holder.tv_2.setText(arr[1]);
                holder.tv_3.setText(arr[2]);
                holder.tv_4.setText(arr[3]);
                holder.tv_5.setText(arr[4]);
                holder.tv_6.setText(arr[5]);
                holder.tv_7.setText(arr[6]);


            return convertView;
        }

        class ViewHolder {
            TextView tv_1;
            TextView tv_2;
            TextView tv_3;
            TextView tv_4;
            TextView tv_5;
            TextView tv_6;
            TextView tv_7;
        }
    };
}
