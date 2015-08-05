package in.novopay.bhupeshmusicplayer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import hugo.weaving.DebugLog;

/**
 * Created by bhupeshkumar on 8/4/15.
 */
public class ListOfMusicActivity extends FragmentActivity {
    private ViewPager viewPager;

    private final int NUMBER_OF_PAGES = 3;

    private MusicListFragmentStatePagerAdapter mlfsa = new MusicListFragmentStatePagerAdapter(getSupportFragmentManager());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_viewpager);

        viewPager = (ViewPager) findViewById(R.id.activity_viewpager_viewpager);
        viewPager.setAdapter(mlfsa);
    }

    private class MusicListFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

        public MusicListFragmentStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        @DebugLog
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FirstFragment();
                case 1:
                    return new SecondFragment();
                case 2:
                    return new ThirdFragment();
            }
            return null;
        }

        @Override
        @DebugLog
        public int getCount() {
            return NUMBER_OF_PAGES;
        }
    }
}
