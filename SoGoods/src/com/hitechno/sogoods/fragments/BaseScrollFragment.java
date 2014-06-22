package com.hitechno.sogoods.fragments;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.HorizontalScrollView;

import com.hitechno.sogoods.R;
import com.hitechno.sogoods.models.Base;

public class BaseScrollFragment extends Fragment implements GestureDetector.OnGestureListener, OnScrollChangedListener {

	private static int MINIMUM_SWIPE_DISTANCE = 400;
	
	private HorizontalScrollView scrollView;
	protected GestureDetector gestureDetector;
	Boolean isTouchingPage;
	Boolean willScrollPage;
	
	public BaseScrollFragment() {
		gestureDetector = new GestureDetector(getActivity(), this);
		willScrollPage = true;
	}
	
	public void setModels(ArrayList<? extends Base> advertisements) {}

	private HorizontalScrollView getHorizontalScrollView() {
		if (scrollView != null)
			return scrollView;
		
		HorizontalScrollView scrollView = (HorizontalScrollView) getActivity().findViewById(R.id.horizontal_scroll_view);
		return scrollView;
	}
	
	@Override
	public boolean onDown(MotionEvent e) {
		isTouchingPage = true;
//		Log.d(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + ": onDown");
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
//		Log.d(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + ": onFling");
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
//		Log.d(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + ": onLongPress");
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		HorizontalScrollView scrollView = getHorizontalScrollView();
//		Log.d(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + ": onScroll");
		float deltaX = e1.getX() - e2.getX();
		if (isTouchingPage && willScrollPage) {
			if (deltaX > MINIMUM_SWIPE_DISTANCE) {
				scrollView.pageScroll(HorizontalScrollView.FOCUS_RIGHT);
				willScrollPage = false;
				isTouchingPage = false;
			} else if (deltaX < -MINIMUM_SWIPE_DISTANCE) {
				scrollView.pageScroll(HorizontalScrollView.FOCUS_LEFT);
				willScrollPage = false;
				isTouchingPage = false;
			}
			return true;
		}
		willScrollPage = true;
		return true;
	}

	@Override
	public void onShowPress(MotionEvent e) {
//		Log.d(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + ": onShowPress");
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
//		Log.d(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + ": onSingleTapUp");
		return false;
	}

	@Override
	public void onScrollChanged() {
//		Log.d(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + ": onScrollChanged");
	}
}
