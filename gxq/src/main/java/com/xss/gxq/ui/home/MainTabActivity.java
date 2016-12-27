package com.xss.gxq.ui.home;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xss.gxq.R;
import com.xss.gxq.ui.financeshop.FinanceShopFragment;
import com.xss.gxq.ui.more.MoreFragment;
import com.xss.gxq.ui.myfinance.MyFinanceFragment;
import com.xss.gxq.ui.recommend.RecommendFragment;
import com.xss.gxq.utils.Constants;

/**
 * @类描述
 * @创建人：xss
 * @创建时间：2015/9/15 10:45
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class MainTabActivity extends FragmentActivity implements View.OnClickListener{
    private TextView tv_recommend;
    private TextView tv_shop;
    private TextView tv_finance;
    private TextView tv_more;

    private int index = 0;
    private RecommendFragment recommendFragment = null;
    private FinanceShopFragment financeShopFragment = null;
    private MyFinanceFragment myFinanceFragment = null;
    private MoreFragment moreFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        getWidthAndHeight();

        initView();
        initData();
    }

    protected void getWidthAndHeight() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;    //得到宽度
        Constants.SCREEN_HEIGHT = dm.heightPixels;  //得到高度

        Log.e("jj", Constants.SCREEN_WIDTH + ", " + Constants.SCREEN_HEIGHT);
    }

    public void initView() {
        tv_recommend = (TextView)findViewById(R.id.tv_recommend);
        tv_shop = (TextView)findViewById(R.id.tv_shop);
        tv_finance = (TextView)findViewById(R.id.tv_finance);
        tv_more = (TextView)findViewById(R.id.tv_more);

        initMainTab();

        tv_recommend.setOnClickListener(this);
        tv_shop.setOnClickListener(this);
        tv_finance.setOnClickListener(this);
        tv_more.setOnClickListener(this);
    }

    protected void initMainTab() {
        index = 1;
        tv_recommend.setSelected(true);
        if (recommendFragment == null) {
            recommendFragment = new RecommendFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.ll_main_content, recommendFragment, null).addToBackStack(null).commitAllowingStateLoss();
        selectMenu(1);
    }


    public void initData() {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_recommend) {
            changeTab(1);
        } else if (view.getId() == R.id.tv_shop) {
            changeTab(2);
        } else if (view.getId() == R.id.tv_finance) {
            changeTab(3);
        } else if (view.getId() == R.id.tv_more) {
            changeTab(4);
        }
    }

    protected void changeTab(int i) {
        if (index != i) {  //点击的不是当前页 index为原来的tab， i为将要点击的tab
            selectMenu(i);
            switch (i) {
                case 1:
                    if (recommendFragment == null) {
                        recommendFragment = new RecommendFragment();
                    }
                    //如果点击的不是当前所在的页面，就hide当前页面 index，show点击的页面 i
                    hideFragment(index);
                    //如果之前的Fragment已经添加到栈中了，就show，否则就add
                    if (!recommendFragment.isAdded()) {
                        getSupportFragmentManager().beginTransaction().add(R.id.ll_main_content,recommendFragment).commitAllowingStateLoss();
                    } else {
                        getSupportFragmentManager().beginTransaction().show(recommendFragment).commitAllowingStateLoss();
                    }
                    break;
                case 2:
                    if (financeShopFragment == null) {
                        financeShopFragment = new FinanceShopFragment();
                    }
                    //如果点击的不是当前所在的页面，就hide当前页面 index，show点击的页面 i
                    hideFragment(index);
                    //如果之前的Fragment已经添加到栈中了，就show，否则就add
                    if (!financeShopFragment.isAdded()) {
                        getSupportFragmentManager().beginTransaction().add(R.id.ll_main_content,financeShopFragment).commitAllowingStateLoss();
                    } else {
                        getSupportFragmentManager().beginTransaction().show(financeShopFragment).commitAllowingStateLoss();
                    }
                    break;
                case 3:
                    if (myFinanceFragment == null) {
                        myFinanceFragment = new MyFinanceFragment();
                    }
                    //如果点击的不是当前所在的页面，就hide当前页面 index，show点击的页面 i
                    hideFragment(index);
                    //如果之前的Fragment已经添加到栈中了，就show，否则就add
                    if (!myFinanceFragment.isAdded()) {
                        getSupportFragmentManager().beginTransaction().add(R.id.ll_main_content,myFinanceFragment).commitAllowingStateLoss();
                    } else {
                        getSupportFragmentManager().beginTransaction().show(myFinanceFragment).commitAllowingStateLoss();
                    }
                    break;
                case 4:
                    if (moreFragment == null) {
                        moreFragment = new MoreFragment();
                    }
                    //如果点击的不是当前所在的页面，就hide当前页面 index，show点击的页面 i
                    hideFragment(index);
                    //如果之前的Fragment已经添加到栈中了，就show，否则就add
                    if (!moreFragment.isAdded()) {
                        getSupportFragmentManager().beginTransaction().add(R.id.ll_main_content,moreFragment).commitAllowingStateLoss();
                    } else {
                        getSupportFragmentManager().beginTransaction().show(moreFragment).commitAllowingStateLoss();
                    }
                    break;
            }
            index = i;
        }
    }

    protected void hideFragment(int index) {
        //如果点击的不是当前所在的页面，就hide当前页面 index，show点击的页面 i
        if (index == 1) {
            getSupportFragmentManager().beginTransaction().hide(recommendFragment).commitAllowingStateLoss();
        } else if (index == 2) {
            getSupportFragmentManager().beginTransaction().hide(financeShopFragment).commitAllowingStateLoss();
        } else if (index == 3) {
            getSupportFragmentManager().beginTransaction().hide(myFinanceFragment).commitAllowingStateLoss();
        } else if (index == 4) {
            getSupportFragmentManager().beginTransaction().hide(moreFragment).commitAllowingStateLoss();
        }
    }

    protected void selectMenu(int index) {
        switch (index) {
            case 1:
                tv_recommend.setSelected(true);
                tv_shop.setSelected(false);
                tv_finance.setSelected(false);
                tv_more.setSelected(false);
                break;
            case 2:
                tv_shop.setSelected(true);
                tv_recommend.setSelected(false);
                tv_finance.setSelected(false);
                tv_more.setSelected(false);
                break;
            case 3:
                tv_finance.setSelected(true);
                tv_recommend.setSelected(false);
                tv_shop.setSelected(false);
                tv_more.setSelected(false);
                break;
            case 4:
                tv_more.setSelected(true);
                tv_recommend.setSelected(false);
                tv_shop.setSelected(false);
                tv_finance.setSelected(false);
                break;
        }
    }
}

