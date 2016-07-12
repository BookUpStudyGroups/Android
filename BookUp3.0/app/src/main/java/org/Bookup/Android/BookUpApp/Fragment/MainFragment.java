package org.Bookup.Android.BookUpApp.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;




import org.Bookup.Android.BookUpApp.Globals;
import org.Bookup.Android.BookUpApp.Objects.ClassEntry;
import org.Bookup.Android.BookUpApp.R;
import org.Bookup.Android.BookUpApp.Views.SlidingTabLayout;
import org.Bookup.Android.BookUpApp.Views.ViewPageAdapter;
import java.util.ArrayList;

/**
 * Created by Patrick J. Flathers on 7/7/16.
 */


//Main Fragment for the landingPage, Creates the tabs and inflates all the Framgents in the tabs
public class MainFragment extends Fragment {

    private ViewPageAdapter myViewPageAdapter;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragments;
    private View view;
    private ArrayList<ClassEntry> ce;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_main, container, false);

        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(Globals.TERM);

        ce = this.getArguments().getParcelableArrayList("ce");

        return view;
    }

    public void onStart(){
        super.onStart();
        //createTabs();
    }

    public void onResume(){
        super.onResume();
        createTabs();
    }


    // creates the tabs
    private void createTabs(){
        slidingTabLayout = (SlidingTabLayout) getView().findViewById(R.id.tab);

        viewPager = (ViewPager) getView().findViewById(R.id.viewpager);

        fragments = new ArrayList<Fragment>();

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("ce", ce);
        Fragment hf = new HomeFragment();
        Fragment hf1 = new HomeFragment();
        Fragment hf2 = new HomeFragment();
        Fragment hf3 = new HomeFragment();

        hf.setArguments(bundle);
        hf1.setArguments(bundle);
        hf2.setArguments(bundle);
        hf3.setArguments(bundle);

        fragments.add(hf);
        fragments.add(hf1);
        fragments.add(hf2);
        fragments.add(hf3);


        // makes an adapter of fragments and set it to viewPager
        myViewPageAdapter = new ViewPageAdapter(getChildFragmentManager(),
                fragments);


        viewPager.setAdapter(myViewPageAdapter);
        viewPager.setAdapter(myViewPageAdapter);

        // make sure the tabs are equally spaced.
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(viewPager);

        // Used in order to update fragments upon swiping to them
        slidingTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            public void onPageScrolled(final int position, final float v, final int i2) {

            }

            public void onPageSelected(final int position) {

            }

            public void onPageScrollStateChanged(final int position) {

            }
        });


    }


}
