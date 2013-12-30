package com.matthewlogan.uitricks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class ShadeMenuFragment extends Fragment implements Animation.AnimationListener {

    private View mInflatedView;

    private Animation mDropDownAnimationShow;
    private Animation mDropDownAnimationHide;

    private boolean mIsShowingDropDown = false;
    private boolean mIsAnimatingDropDown = false;

    private static final int ANIMATE_DURATION = 500;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mInflatedView = inflater.inflate(R.layout.fragment_shade_menu, container, false);
        Log.d("MattTest", "onCreateView, rootView null? " + String.valueOf(mInflatedView == null));
        if (mInflatedView != null) {
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
            Log.d("MattTest", "toggleDropDown, view null? " + String.valueOf(mInflatedView == null));
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
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }
}
