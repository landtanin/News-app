package com.landtanin.news.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.landtanin.news.R;
import com.landtanin.news.databinding.FragmentMainBinding;
import com.landtanin.news.utils.SmartFragmentStatePagerAdapter;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class MainFragment extends Fragment {

    FragmentMainBinding b;
    private static final String TAG = "MainFragment";

    public MainFragment() {
        super();
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        View rootView = b.getRoot();
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here


    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        // Note: State of variable initialized here could not be saved
        //       in onSavedInstanceState

        FragmentMainPagerAdapter fragmentMainPagerAdapter = new FragmentMainPagerAdapter(getChildFragmentManager());

        b.fragmentMainViewPager.setAdapter(fragmentMainPagerAdapter);
        b.fragMainTabLayout.setupWithViewPager(b.fragmentMainViewPager);
        for(int i = 0; i < b.fragMainTabLayout.getTabCount(); i++) {

            TabLayout.Tab tab = b.fragMainTabLayout.getTabAt(i);
            tab.setText(fragmentMainPagerAdapter.tabString[i] + "   ");

        }

        b.fragMainTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance (Fragment level's variables) State here
    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance (Fragment level's variables) State here
    }

    public class FragmentMainPagerAdapter extends SmartFragmentStatePagerAdapter {

        private SmartFragmentStatePagerAdapter adapterViewPager;
        public String[] tabString = getResources().getStringArray(R.array.news);

        public FragmentMainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Log.d(TAG, "getItem: position = " + position + " tabString[0] = " + tabString[0]);
                    return NewsFragment.newInstance(tabString[0]);

                case 1:
                    Log.d(TAG, "getItem: position = " + position + " tabString[1] = " + tabString[1]);
                    return NewsFragment.newInstance(tabString[1]);

                case 2:
                    Log.d(TAG, "getItem: position = " + position + " tabString[2] = " + tabString[2]);
                    return NewsFragment.newInstance(tabString[2]);

                case 3:
                    Log.d(TAG, "getItem: position = " + position + " tabString[3] = " + tabString[3]);
                    return NewsFragment.newInstance(tabString[3]);

                case 4:
                    Log.d(TAG, "getItem: position = " + position + " tabString[4] = " + tabString[4]);
                    return NewsFragment.newInstance(tabString[4]);

                case 5:
                    Log.d(TAG, "getItem: position = " + position + " tabString[5] = " + tabString[5]);
                    return NewsFragment.newInstance(tabString[5]);

                case 6:
                    Log.d(TAG, "getItem: position = " + position + " tabString[6] = " + tabString[6]);
                    return NewsFragment.newInstance(tabString[6]);

                default:
                    Log.d(TAG, "getItem: default position = " + position + " tabString[0] = " + tabString[0]);
                    return NewsFragment.newInstance(tabString[0]);

            }
        }

        @Override
        public int getCount() {

            return tabString.length;

        }
    }


}
