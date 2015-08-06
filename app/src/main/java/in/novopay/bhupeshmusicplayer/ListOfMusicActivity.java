package in.novopay.bhupeshmusicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.app.ActionBar ;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import de.greenrobot.event.EventBus;
import hugo.weaving.DebugLog;
import in.novopay.bhupeshmusicplayer.event.PausePlayToggleEvent;

/**
 * Created by bhupeshkumar on 8/4/15.
 */
public class ListOfMusicActivity extends AppCompatActivity{
    private ViewPager viewPager;

    private final int NUMBER_OF_PAGES = 3;

    private MusicListFragmentStatePagerAdapter mlfsa = new MusicListFragmentStatePagerAdapter(getSupportFragmentManager());

    /*ActionBar actionBar = getActionBar() ;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.music_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_play:
                EventBus.getDefault().post(new PausePlayToggleEvent());
                return true;
            case R.id.activity_music:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_viewpager);

        getSupportActionBar().setIcon(R.drawable.play_button) ;

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
