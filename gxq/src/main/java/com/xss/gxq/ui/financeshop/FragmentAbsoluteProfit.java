package com.xss.gxq.ui.financeshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.xss.gxq.R;
import com.xss.gxq.db.AbsoluteProfitDB;
import com.xss.gxq.model.AbsoluteProfitModel;
import com.xss.gxq.ui.adapter.AbsoluteProfitAdapter;
import com.xss.gxq.ui.base.BaseFragment;

import java.util.ArrayList;

/**
 * @类描述
 * @创建人：xss
 * @创建时间：2015/9/15 17:28
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class FragmentAbsoluteProfit extends BaseFragment {
    private View view;

    private ListView lv_absolute_profit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_absolute_profit, container, false);

        initView();

        return view;
    }

    public void initView() {
        lv_absolute_profit = (ListView)view.findViewById(R.id.lv_absolute_profit);
        ArrayList<AbsoluteProfitModel> list = AbsoluteProfitDB.getProfitModelList();
        AbsoluteProfitAdapter adapter = new AbsoluteProfitAdapter(getActivity(), list);
        lv_absolute_profit.setAdapter(adapter);

    }

    @Override
    public void initData() {

    }


    protected void initProgressBar() {

    }
}
