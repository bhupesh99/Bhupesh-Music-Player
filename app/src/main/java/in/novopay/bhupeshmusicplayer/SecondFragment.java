package in.novopay.bhupeshmusicplayer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.GridView;

import java.util.ArrayList;

import in.novopay.bhupeshmusicplayer.model.Music;

/**
 * Created by bhupeshkumar on 8/4/15.
 */
public class SecondFragment extends Fragment {
    private final static String TAG = "SecondFragment" ;
    ArrayList<Music> musicList = new ArrayList<>() ;
    private MusicAdapter musicAdapter ;
    private GridView gridView ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate") ;
        super.onCreate(savedInstanceState);
        musicList.add(new Music("song", "album1", "artist1"));
        musicList.add(new Music("duhast", "album1", "artist1"));
        musicList.add(new Music("carnivalofrust", "album1", "artist1"));
        musicList.add(new Music("song2", "album1", "artist1"));
        musicList.add(new Music("song3", "album1", "artist1"));
        musicList.add(new Music("song4", "album1", "artist1"));
        musicList.add(new Music("song5", "album1", "artist1"));
        musicList.add(new Music("song6", "album1", "artist1"));
        musicList.add(new Music("song7", "album1", "artist1"));
        musicList.add(new Music("song1", "album1", "artist1"));
        musicList.add(new Music("song1", "album1", "artist1"));
        musicList.add(new Music("song1", "album1", "artist1"));
        musicList.add(new Music("song1", "album2", "artist2"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView") ;
        View view = inflater.inflate(R.layout.fragment_second, container, false) ;
        gridView = (GridView) view.findViewById(R.id.fragment_second_gridview) ;
        musicAdapter = new MusicAdapter(getActivity(), musicList) ;
        gridView.setAdapter(musicAdapter);

        return view;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Log.d(TAG, "oncCreateAnimation") ;
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause") ;
        super.onPause();
    }
}
