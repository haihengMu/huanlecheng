package activity.huanlecheng;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/10/31.
 */

public class ViewPagerFragment extends FragmentPagerAdapter {
    private List<Fragment> list;
    private List<String> mList;
    public ViewPagerFragment(FragmentManager fm, List<Fragment> list, List<String> mlist) {
        super(fm);
        this.list=list;
        this.mList=mlist;

    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position);
    }
}
