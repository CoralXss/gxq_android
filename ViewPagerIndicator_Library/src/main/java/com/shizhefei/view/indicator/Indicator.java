/*
Copyright 2014 shizhefei��LuckyJayce��

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.shizhefei.view.indicator;

import java.util.LinkedHashSet;
import java.util.Set;

import android.view.View;
import android.view.ViewGroup;

import com.shizhefei.view.indicator.slidebar.ScrollBar;

/**
 * @author���ŷ�
 * @date 2014��11��1��
 * @version 1.0 ָʾ��
 */
public interface Indicator {
	/**
	 * ����������
	 */
	public void setAdapter(IndicatorAdapter adapter);

	/**
	 * ����ѡ�м���
	 * 
	 * @param onItemSelectedListener
	 */
	public void setOnItemSelectListener(OnItemSelectedListener onItemSelectedListener);

	/**
	 * ��ȡѡ�м���
	 * 
	 * @return
	 */
	public OnItemSelectedListener getOnItemSelectListener();

	/**
	 * ���û����仯��ת������tab���л�����л���ô˼���<br>
	 * ����������Զ���ʵ���ڻ�������У�tab�������仯����ɫ�仯�ȵ�Ч��<br>
	 * Ŀǰ�ṩ������
	 * {@link com.shizhefei.view.indicator.transition.OnTransitionTextListener}
	 * 
	 * @param onTransitionListener
	 * 
	 */
	public void setOnTransitionListener(OnTransitionListener onTransitionListener);

	public OnTransitionListener getOnTransitionListener();

	/**
	 * ���û�����<br>
	 * ����������Զ��廬�������ʽ��<br>
	 * Ŀǰ�ṩ������ {@link com.shizhefei.view.indicator.slidebar.ColorBar}
	 * {@link com.shizhefei.view.indicator.slidebar.DrawableBar}
	 * {@link com.shizhefei.view.indicator.slidebar.LayoutBar}
	 * 
	 * @param scrollBar
	 */
	public void setScrollBar(ScrollBar scrollBar);

	public IndicatorAdapter getAdapter();

	/**
	 * ���õ�ǰ��.<br>
	 * ���ʹ��IndicatorViewPager��ʹ��IndicatorViewPager.setCurrentItem�����ڵ��ø÷���
	 * 
	 * @param item
	 */
	public void setCurrentItem(int item);

	public void setCurrentItem(int item, boolean anim);

	/**
	 * ��ȡÿһ���tab
	 * 
	 * @param item
	 *            ����
	 * @return
	 */
	public View getItemView(int item);

	/**
	 * ��ȡ��ǰѡ����
	 * 
	 * @return
	 */
	public int getCurrentItem();

	/**
	 * ��ȡ֮ǰѡ�е���,���ܷ���-1����ʾ֮ǰû��ѡ��
	 * 
	 * @return
	 */
	public int getPreSelectItem();

	/**
	 * ViewPager�л��仯�ĺ���
	 * 
	 * @param position
	 * @param positionOffset
	 * @param positionOffsetPixels
	 */
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

	/**
	 * ������
	 * 
	 */
	public static abstract class IndicatorAdapter {
		private Set<DataSetObserver> observers = new LinkedHashSet<DataSetObserver>(2);

		public abstract int getCount();

		public abstract View getView(int position, View convertView, ViewGroup parent);

		public void notifyDataSetChanged() {
			for (DataSetObserver dataSetObserver : observers) {
				dataSetObserver.onChange();
			}
		}

		public void registDataSetObserver(DataSetObserver observer) {
			observers.add(observer);
		}

		public void unRegistDataSetObserver(DataSetObserver observer) {
			observers.remove(observer);
		}

	}

	/**
	 * 
	 * 
	 * @author���ŷ�
	 * @date 2014��11��1��
	 * @version 1.0 ���Դ�۲���
	 */
	static interface DataSetObserver {
		public void onChange();
	}

	/**
	 * tab��ѡ�м���
	 */
	public static interface OnItemSelectedListener {
		/**
		 * ע�� preItem ����Ϊ -1����ʾ֮ǰû��ѡ�й�,ÿ��adapter.notifyDataSetChangedҲ�ὫpreItem
		 * ����Ϊ-1��
		 * 
		 * @param selectItemView
		 *            ��ǰѡ�е�view
		 * @param select
		 *            ��ǰѡ���������
		 * @param preSelect
		 *            ֮ǰѡ���������
		 */
		public void onItemSelected(View selectItemView, int select, int preSelect);
	}

	/**
	 * tab�����仯��ת������tab���л�����л���ô˼���<br>
	 * ͨ��������Զ���ʵ���ڻ�������У�tab�������仯����ɫ�仯�ȵ�Ч��<br>
	 * Ŀǰ�ṩ������
	 * {@link com.shizhefei.view.indicator.transition.OnTransitionTextListener}
	 */
	public static interface OnTransitionListener {
		public void onTransition(View view, int position, float selectPercent);
	}
}
