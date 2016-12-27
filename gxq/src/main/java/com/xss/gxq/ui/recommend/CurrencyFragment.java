package com.xss.gxq.ui.recommend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xss.gxq.R;
import com.xss.gxq.ui.base.BaseFragment;

/**
 * @类描述
 * @创建人：xss
 * @创建时间：2015/9/21 20:03
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class CurrencyFragment extends BaseFragment {
    private View view;

    private LinearLayout ll_new_only_share;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recommend, container, false);
        initView();
        return view;
    }

    protected void initView() {
        ll_new_only_share = (LinearLayout)view.findViewById(R.id.ll_new_only_share);
        ll_new_only_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    protected void initData() {

    }
}
