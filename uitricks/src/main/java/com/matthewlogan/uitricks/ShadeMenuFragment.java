package com.matthewlogan.uitricks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

public class ShadeMenuFragment extends Fragment implements Animation.AnimationListener, View.OnClickListener {

    private static final String LOG_TAG = "ShadeMenuFragment";

    private View mInflatedView;
    private View mRowZero;
    private RelativeLayout mRowOne;
    private RelativeLayout mRowTwo;
    private RelativeLayout mRowThree;

    private Animation mDropDownAnimationShow;
    private Animation mDropDownAnimationHide;

    private boolean mIsShowingDropDown = true;
    private boolean mIsAnimatingDropDown = false;

    private static final int ANIMATE_DURATION = 500;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mInflatedView = inflater.inflate(R.layout.fragment_shade_menu, container, false);

        if (mInflatedView != null) {

            mRowZero = mInflatedView.findViewById(R.id.row_zero);
            mRowOne = (RelativeLayout) mInflatedView.findViewById(R.id.row_one);
            mRowTwo = (RelativeLayout) mInflatedView.findViewById(R.id.row_two);
            mRowThree = (RelativeLayout) mInflatedView.findViewById(R.id.row_three);

            mRowOne.setOnClickListener(this);
            mRowTwo.setOnClickListener(this);
            mRowThree.setOnClickListener(this);

            loadAnimations();
        }

        return mInflatedView;
    }

    private void loadAnimations() {
        mDropDownAnimationShow = AnimationUtils.loadAnimation(getActivity(), R.anim.drop_down_anim_show);
        mDropDownAnimationHide = AnimationUtils.loadAnimation(getActivity(), R.anim.drop_down_anim_hide);

        configureAnimation(mDropDownAnimationShow);
        configureAnimation(mDropDownAnimationHide);
    }

    private void configureAnimation(Animation animation) {
        if (animation != null) {
            animation.setDuration(ANIMATE_DURATION);
            animation.setFillEnabled(true);
            animation.setFillAfter(true);
            animation.setAnimationListener(this);
        }
    }

    public void toggleDropDown() {
        if (!mIsAnimatingDropDown) {
            Animation animation = mIsShowingDropDown ? mDropDownAnimationHide : mDropDownAnimationShow;
            if (mInflatedView != null) {
                mInflatedView.startAnimation(animation);
            }
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {
        mIsAnimatingDropDown = true;
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        mIsAnimatingDropDown = false;
        mIsShowingDropDown = !mIsShowingDropDown;

        // if views are animated off screen, set click listeners to null
        setClickListenersForAllRows(mIsShowingDropDown ? this : null);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }

    public interface onRowSelectedListener {
        public void onRowSelected(int rowNum);
    }

    @Override
    public void onClick(View v) {
        Log.d(LOG_TAG, "clicked");

        int rowNum = -1;
        if (v.equals(mRowOne)) {
            rowNum = 1;
        } else if (v.equals(mRowTwo)) {
            rowNum = 2;
        } else if (v.equals(mRowThree)) {
            rowNum = 3;
        }

        ((MainFragment)getParentFragment()).onRowSelected(rowNum);
    }

    private void setClickListenersForAllRows(View.OnClickListener listener) {
        mRowOne.setOnClickListener(listener);
        mRowTwo.setOnClickListener(listener);
        mRowThree.setOnClickListener(listener);
    }

    private void setBackgroundColors(int color) {
        mRowZero.setBackgroundColor(color);
        mRowOne.setBackgroundColor(color);
        mRowTwo.setBackgroundColor(color);
        mRowThree.setBackgroundColor(color);
    }
}
