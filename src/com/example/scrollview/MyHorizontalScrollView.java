/**
 * 
 */
package com.example.scrollview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;


public class MyHorizontalScrollView extends HorizontalScrollView {

	public MyHorizontalScrollView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyHorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyHorizontalScrollView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
//	private View view;
//	private int windowWitdh = 0; //фад╩©М╤х
//	private Activity mActivity;
//	
//	public void showRightView(View view, int windowWitdh, Activity mActivity)
//	{
//		this.view = view;
//		this.mActivity = mActivity;
//		this.windowWitdh = windowWitdh;
//	}

//	@Override
//	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
//		// TODO Auto-generated method stub
//		super.onScrollChanged(l, t, oldl, oldt);
//		if(!mActivity.isFinishing() && this.view != null)
//		{
//			this.measure(0, 0);
//		}
//	}
	

}
