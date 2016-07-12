package org.Bookup.Android.BookUpApp.Views;
/**
 * Created by Patrick Flathers on 6/16/16.
 */

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import org.Bookup.Android.BookUpApp.Globals;

import java.util.ArrayList;


//Class to handle the tabs that will be used by mainActivity
public class ViewPageAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;
    //int val for each of the tabs
    //creates Apadter
    public ViewPageAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    //returns item
    public Fragment getItem(int pos) {
        return fragments.get(pos);
    }

    // gets the number of tabs
    public int getCount() {
        return fragments.size();
    }

    //gets the page when requested
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case Globals.CLASSES:
                return Globals.UI_TAB_MESSAGES;
            case Globals.MESSAGES:
                return Globals.UI_TAB_CLASSES;
            case Globals.BUDDIES:
                return Globals.UI_TAB_BUDDIES;
            case Globals.WHAT:
                return Globals.UI_TAB_WHAT;
            default:
                break;
        }
        return null;
    }
}
