package com.matthewlogan.uitricks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class SpinUpFragment extends Fragment implements Animation.AnimationListener, View.OnClickListener {

    private View mSpinUpButton;
    private View mSpinUpView;
    private Animation mSpinUpAnimationShow;
    private Animation mSpinUpAnimationHide;
    private Animation mSpinCwAnimation;
    private Animation mSpinCcwAnimation;
    private boolean mIsShowingSpinUp = false;
    private boolean mIsAnimating = false;

    private static final int ANIMATE_DURATION = 500;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_spin_up, container, false);

        if (rootView != null) {

            mSpinUpButton = rootView.findViewById(R.id.spin_up_button);
            mSpinUpButton.setOnClickListener(this);

            mSpinUpView = rootView.findViewById(R.id.spin_up);
            mSpinUpView.setOnClickListener(this);

            loadAnimations();
        }

        return rootView;
    }

    private void loadAnimations() {
        mSpinUpAnimationShow = AnimationUtils.loadAnimation(getActivity(), R.anim.spin_up_show);
        mSpinUpAnimationHide = AnimationUtils.loadAnimation(getActivity(), R.anim.spin_up_hide);
        mSpinCwAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.spin_cw);
        mSpinCcwAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.spin_ccw);

        configureAnimation(mSpinUpAnimationShow);
        configureAnimation(mSpinUpAnimationHide);
        configureAnimation(mSpinCwAnimation);
        configureAnimation(mSpinCcwAnimation);
    }

    private void configureAnimation(Animation animation) {
        if (animation != null) {
            animation.setDuration(ANIMATE_DURATION);
            animation.setFillEnabled(true);
            animation.setFillAfter(true);
            animation.setAnimationListener(this);
        }
    }

    private void toggleSpinUp() {
        if (!mIsAnimating) {
            Animation animationSpin = mIsShowingSpinUp ? mSpinCcwAnimation : mSpinCwAnimation;
            Animation animationUp = mIsShowingSpinUp ? mSpinUpAnimationHide : mSpinUpAnimationShow;

            mSpinUpButton.startAnimation(animationSpin);
            mSpinUpView.startAnimation(animationUp);
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {
        if (!mIsAnimating) {
            mIsAnimating = true;
        }
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (mIsAnimating) {
            mIsAnimating = false;
            mIsShowingSpinUp = !mIsShowingSpinUp;
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }

    @Override
    public void onClick(View v) {
        toggleSpinUp();
    }
}
