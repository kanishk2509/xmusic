/*
 * This is the source code of DMPLayer for Android v. 1.0.0.
 * You should have received a copy of the license in this archive (see LICENSE).
 * Copyright @Dibakar_Mistry, 2015.
 */
package com.xmusic.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xmusic.R;
import com.xmusic.tablayout.SlidingTabLayout;
import com.xmusic.childfragment.ChildFragmentAlbum;
import com.xmusic.childfragment.ChildFragmentArtists;
import com.xmusic.childfragment.ChildFragmentGenres;
import com.xmusic.childfragment.ChildFragmentMostPlay;

public class FragmentLibrary extends Fragment {

    private final String[] TITLES = {"ALBUMS", "ARTISTS", "GENRES", "MOSTPLAY"};
    private TypedValue typedValueToolbarHeight = new TypedValue();
    private ChildFragmentGenres childFragmentGenres;
    private ChildFragmentArtists childFragmentArtists;
    private ChildFragmentAlbum childFragmentAlbum;
    private ChildFragmentMostPlay childFragmentMostplay;
    private OnFragmentInteractionListener mListener;

    private MyPagerAdapter pagerAdapter;
    private ViewPager pager;
    private SlidingTabLayout tabs;
    private int tabsPaddingTop;

    public static FragmentLibrary newInstance() {
        return new FragmentLibrary();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_library, null);
        setupView(v);
        return v;
    }


    private void setupView(View view) {
        pager = (ViewPager) view.findViewById(R.id.pager);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getFragmentManager());
        pager.setAdapter(pagerAdapter);
        tabs = (SlidingTabLayout) view.findViewById(R.id.tabs);
        tabs.setDistributeEvenly(false);
        // Tab indicator color
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.md_white_1000);
            }
        });
        tabs.setViewPager(pager);
    }

    public class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    childFragmentAlbum = ChildFragmentAlbum.newInstance(position, getActivity());
                    return childFragmentAlbum;
                case 1:
                    childFragmentArtists = ChildFragmentArtists.newInstance(position, getActivity());
                    return childFragmentArtists;
                case 2:
                    childFragmentGenres = ChildFragmentGenres.newInstance(position, getActivity());
                    return childFragmentGenres;
                case 3:
                    childFragmentMostplay = ChildFragmentMostPlay.newInstance(position, getActivity());
                    return childFragmentMostplay;
            }
            return null;
        }
    }

    public int convertToPx(int dp) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (dp * scale + 0.5f);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentAllSongs.OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
