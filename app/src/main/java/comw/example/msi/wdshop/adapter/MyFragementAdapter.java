package comw.example.msi.wdshop.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * <p>文件描述：<p>
 * <p>作者：${Ln}<p>
 * <p>创建时间：2019/1/4  11:55<p>
 * <p>更改时间：2019/1/4  11:55<p>
 * <p>版本号：1<p>
 */
public class MyFragementAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment>mFragment;

    public MyFragementAdapter(FragmentManager fm, ArrayList<Fragment> mFragment) {
        super(fm);
        this.mFragment = mFragment;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragment.get(i);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }



}
