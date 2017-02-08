package com.compunetconnections.iyyamthavir;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Edwin on 15/02/2015.
 */
public class ViewPagerAdapterBanking extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapterBanking(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {
         //Fragment selectedTab=null;
        if(position == 0) // if the position is 0 we are returning the First tab
        {
            Fragment selectedTab = new Fragment4();
            return selectedTab;
        }

        else if(position == 1){
            Fragment  selectedTab2 = new Fragment3();
            return selectedTab2;
        }
        else if(position == 2){
            Fragment selectedTab4 = new Fragment6();
            return selectedTab4;
        }
        else if(position == 3){
            Fragment   selectedTab3 = new Fragment1();
            return selectedTab3;
        }
        else if(position == 4){
            Fragment selectedTab6 = new Fragment2();
            return selectedTab6;
        }
        else {
            Fragment selectedTab5 = new Fragment5();
            return selectedTab5;
        }
    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}