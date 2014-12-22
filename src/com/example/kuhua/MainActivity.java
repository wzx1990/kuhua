package com.example.kuhua;

import com.example.kuhua.fragment.ByHistory;
import com.example.kuhua.fragment.KuHuaShop;
import com.example.kuhua.fragment.MainPaperFragment;
import com.example.kuhua.fragment.MakeMoneyList;
import com.example.kuhua.fragment.Setting;
import com.example.scrollview.MyHorizontalScrollView;

import android.R.anim;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity {

	private LayoutInflater mInflater;
	private RadioGroup scv_RadioGroup;
	private String[] tabTitle = { "首页", "赚钱套餐", "商城", "历史", "设置" };
	private int viewidth;// 显示器高度
	private ImageView view_index;// 选项卡下标
	private ViewPager mViewPager;// 滑动下标
	private MyFragmentPagerAdapter mAdapter;// viewpager的适配器
	private int currentIndicatorLeft = 0;// view_index动画的左侧位置坐标
	private MyHorizontalScrollView myscv_case;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		this.setContentView(R.layout.main);
		this.findId();
		this.inintData();
		this.initScrolView();
	}

	private void findId() {
		this.scv_RadioGroup = (RadioGroup) findViewById(R.id.scview_radiogroup);
		this.view_index = (ImageView) findViewById(R.id.scview_item_line);
		this.mViewPager = (ViewPager) findViewById(R.id.viewpaper);
		this.myscv_case = (MyHorizontalScrollView) findViewById(R.id.scrollview);
	}

	private void inintData() {
		// 初始化单个横向滑动模块
		mInflater = (LayoutInflater) this
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		this.viewidth = dm.widthPixels / 4;
		// 初始化滑块下标
		LayoutParams view_index_params = this.view_index.getLayoutParams();
		view_index_params.width = this.viewidth;
		this.view_index.setLayoutParams(view_index_params);
		// 初始化viewpaper
		this.mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
		this.mViewPager.setAdapter(mAdapter);
		// 对viewpage和scrollView进行监听
		this.setScrollViewPageListener();
	}

	private void initScrolView() {
		this.scv_RadioGroup.removeAllViews();
		for (int i = 0; i < this.tabTitle.length; i++) {
			RadioButton Rg = (RadioButton) this.mInflater.inflate(
					R.layout.radiogroup_item, null);
			Rg.setId(i);
			Rg.setText(this.tabTitle[i]);
			Rg.setLayoutParams(new LayoutParams(this.viewidth,
					LayoutParams.MATCH_PARENT));
			this.scv_RadioGroup.addView(Rg);
		}
	}

	private void setScrollViewPageListener()
	{
		this.mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				if(MainActivity.this.scv_RadioGroup != null && scv_RadioGroup.getChildCount() > position)
				{
					scv_RadioGroup.getChildAt(position).performClick();
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		this.scv_RadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(MainActivity.this.scv_RadioGroup.getChildAt(checkedId) != null)
				{
					TranslateAnimation animation = new TranslateAnimation(MainActivity.this.currentIndicatorLeft, MainActivity.this.scv_RadioGroup.getChildAt(checkedId).getLeft(),0f,0f);
					animation.setInterpolator(new LinearInterpolator());
					animation.setDuration(80);
					animation.setFillAfter(true);
					
					//执行动画
					MainActivity.this.view_index.startAnimation(animation);
					//viewpager同时切换
					MainActivity.this.mViewPager.setCurrentItem(checkedId);
					//调整左侧view_index的动画坐标
					MainActivity.this.currentIndicatorLeft = MainActivity.this.scv_RadioGroup.getChildAt(checkedId).getLeft();
					//调整滑动之后视图的切换
					MainActivity.this.myscv_case.smoothScrollTo((checkedId > 1 ? MainActivity.this.scv_RadioGroup.getChildAt(checkedId).getLeft() : 0) - MainActivity.this.scv_RadioGroup.getChildAt(2).getLeft(),0);
				}
			}
		});
	}

	class MyFragmentPagerAdapter extends FragmentPagerAdapter {

		public MyFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			Fragment ft = null;
			switch (arg0) {
			case 0:
				ft = new MainPaperFragment();
				break;
			case 1:
				ft = new MakeMoneyList();
				break;
			case 2:
				ft = new KuHuaShop();
				break;
			case 3:
				ft = new ByHistory();
				break;
			case 4:
				ft = new Setting();
				break;
			default:
				break;
			}
			return ft;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return MainActivity.this.tabTitle.length;
		}
	}

}
