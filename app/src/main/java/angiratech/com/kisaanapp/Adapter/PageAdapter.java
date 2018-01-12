package angiratech.com.kisaanapp.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by NZT-59 on 5/5/2017.
 */

public class PageAdapter extends FragmentPagerAdapter {
    CharSequence Titles[] = {"फसल जानकारी", "खेती की तैयारी", "बिजाई", "सिचाई खाद व् कीटनाशक की जानकारी","कटाई"};
    ArrayList<Fragment> pagerItem;


    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return pagerItem.get(position);
    }

    @Override
    public int getCount() {
        return pagerItem.size();
    }

    public Object getUnreadCount(int i) {
        // TODO Auto-generated method stub
        return null;
    }

    public void setPagerItem(ArrayList<Fragment> pagerItem) {
        this.pagerItem = pagerItem;
    }
}
