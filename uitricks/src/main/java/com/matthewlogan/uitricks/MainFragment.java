package com.matthewlogan.uitricks;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class MainFragment extends Fragment {

    private SpinUpFragment mSpinUpFragment;
    private ShadeMenuFragment mShadeMenuFragment;

    private static final int mActionBarColor = Color.parseColor("#80C0C0C0");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout rootView = (RelativeLayout)inflater.inflate(R.layout.fragment_main, container, false);

        mSpinUpFragment = new SpinUpFragment();
        mShadeMenuFragment = new ShadeMenuFragment();
        if (rootView != null) {
            getChildFragmentManager().beginTransaction()
                    .add(R.id.mainfragment, mSpinUpFragment)
                    .add(R.id.mainfragment, mShadeMenuFragment)
                    .commit();
        }

        setupActionBar();

        return rootView;
    }

    private void setupActionBar() {
        ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setBackgroundDrawable(new ColorDrawable(mActionBarColor));

            actionBar.setCustomView(R.layout.actionbar_view);
            View hamburgerView = actionBar.getCustomView().findViewById(R.id.hamburger_view);
            hamburgerView.setBackgroundResource(R.drawable.hamburger);
            hamburgerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("MattTest", "on click, shade menu null? " + String.valueOf(mShadeMenuFragment == null));
                    mShadeMenuFragment.toggleDropDown();
                }
            });
        }
    }
}
