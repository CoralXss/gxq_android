package com.xss.gxq.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.xss.gxq.R;

/**
 * @类描述
 * @创建人：xss
 * @创建时间：2015/9/14 17:48
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected abstract void initView();

    protected abstract void initData();


    //进入Activity的动画
    public void startActivityAnimation(Intent intent) {
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    //结束Activity的动画
    public void finishAnimation() {
        getActivity().finish();
        getActivity().overridePendingTransition(R.anim.push_left_out, R.anim.push_right_in);
    }
}
