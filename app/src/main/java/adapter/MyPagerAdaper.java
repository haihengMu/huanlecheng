package adapter;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import bean.CaipiaoTopBean;

/**
 * Created by Administrator on 2016/10/31.
 */

public class MyPagerAdaper extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private List<CaipiaoTopBean> mList;
    private Application application;
    public MyPagerAdaper(FragmentManager fm, List<Fragment> fragmentlist, List<CaipiaoTopBean> mList, Application application) {
        super(fm);
        this.fragmentList=fragmentlist;
       this.mList=mList;
        this.application=application;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public long getItemId(int position) {
//        Toast.makeText(application,mList.get(position).getG_P_Id()+"------"+mList.get(position).getG_P_NameId(),Toast.LENGTH_SHORT).show();
        return super.getItemId(position);
    }
}
