package com.zhilin.evaluationapp.common;

import java.util.ArrayList;
import java.util.List;

import com.zhilin.evaluationapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;

/**
 * 水平引导功能
 * 需要拷贝 view_guide_page layout文件到工程
 */
public abstract class GuideActivityHorizontal extends Activity {
	private ViewPager vp;
	private List<View> views = new ArrayList<View>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jar_view_guide_page);
		views = getPageView();
		initView();
	}
	
	public abstract List<View> getPageView();
	
	public OnPageChangeListener setOnPageChangeListener() {
		// 绑定回调
		OnPageChangeListener listener = new OnPageChangeListener() {
			// 当新的页面被选中时调用
			@Override
			public void onPageSelected(int position) {
			}
			// 当当前页面被滑动时调用
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			}
			// 当滑动状态改变时调用
			@Override
			public void onPageScrollStateChanged(int state) {
			}
		};
		return listener;
	}
	
	private void initView() {
		vp = (ViewPager) findViewById(R.id.viewpager);
		ViewPagerAdapter vpAdapter = new ViewPagerAdapter();
		vp.setAdapter(vpAdapter);
		vp.setOnPageChangeListener(setOnPageChangeListener());
	}

	public class ViewPagerAdapter extends PagerAdapter {
		// 销毁position位置的界面
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(views.get(position));
		}

		// 获得当前界面数
		@Override
		public int getCount() {
			if (views != null) return views.size();
			else return 0;
		}

		// 初始化position位置的界面
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(views.get(position), 0);
			return views.get(position);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return (arg0 == arg1);
		}
	}

	public ViewPager getViewPager() {
		return vp;
	}
}
