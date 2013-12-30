package com.matthewlogan.uitricks;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;

public class MainActivity extends ActionBarActivity {

    private MainFragment mMainFragment;
//    private ShadeMenuFragment mShadeMenuFragment;

//    private static final int mActionBarColor = Color.parseColor("#80C0C0C0");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
//        setupActionBar();

        setContentView(R.layout.activity_main);

        mMainFragment = new MainFragment();
//        mShadeMenuFragment = new ShadeMenuFragment();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, mMainFragment)
//                    .add(R.id.container, mShadeMenuFragment)
                    .commit();
        }
    }

//    private void setupActionBar() {
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayShowHomeEnabled(false);
//            actionBar.setDisplayShowTitleEnabled(false);
//            actionBar.setDisplayShowCustomEnabled(true);
//            actionBar.setBackgroundDrawable(new ColorDrawable(mActionBarColor));
//
//            actionBar.setCustomView(R.layout.actionbar_view);
//            View hamburgerView = actionBar.getCustomView().findViewById(R.id.hamburger_view);
//            hamburgerView.setBackgroundResource(R.drawable.hamburger);
//            hamburgerView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d("MattTest", "on click, shade menu null? " + String.valueOf(mShadeMenuFragment == null));
//                    mShadeMenuFragment.toggleDropDown();
//                }
//            });
//        }
//    }
}
