package in.novopay.bhupeshmusicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import hugo.weaving.DebugLog;
import in.novopay.bhupeshmusicplayer.model.Music;

/**
 * Created by bhupeshkumar on 8/4/15.
 */
public class FirstFragment extends Fragment {

    private final static String TAG = "FirstFragment" ;
    ArrayList<Music> musicList = new ArrayList<>() ;
    private MusicAdapter musicAdapter ;
    private ListView listView ;

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
        musicList.add(new Music("song1", "album1", "artist1"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView") ;
        View view = inflater.inflate(R.layout.fragment_first, container, false) ;
        listView = (ListView) view.findViewById(R.id.fragment_first_listview) ;
        musicAdapter = new MusicAdapter(getActivity(), musicList) ;
        listView.setAdapter(musicAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            @DebugLog
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("songName", musicList.get(position).getMusicName()) ;
                intent.putExtra("artistName", musicList.get(position).getMusicArtist());
                intent.putExtra("albumName", musicList.get(position).getMusicAlbum()) ;
                startActivity(intent);
            }
        });
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
