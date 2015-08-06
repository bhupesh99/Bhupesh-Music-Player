package in.novopay.bhupeshmusicplayer;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import in.novopay.bhupeshmusicplayer.model.MusicAPIResponse;
import in.novopay.bhupeshmusicplayer.network.MusicAPI;
import in.novopay.bhupeshmusicplayer.provider.MusicSQLiteOpenHelper;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by bhupeshkumar on 8/4/15.
 */
public class SecondFragment extends Fragment {
    private final static String TAG = "SecondFragment" ;
    //ArrayList<Music> musicList = new ArrayList<>() ;
    //private MusicAdapter musicAdapter ;
    private GridView gridView ;

    MusicCursorAdapter musicCursorAdapter ;
    MusicSQLiteOpenHelper musicSQLiteOpenHelper ;
    SQLiteDatabase sqLiteDatabase ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate") ;
        super.onCreate(savedInstanceState);
        /*musicList.add(new Music("song", "album1", "artist1"));
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
        musicList.add(new Music("song1", "album2", "artist2"));*/
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView") ;
        View view = inflater.inflate(R.layout.fragment_second, container, false) ;
        gridView = (GridView) view.findViewById(R.id.fragment_second_gridview) ;

        musicSQLiteOpenHelper = new MusicSQLiteOpenHelper(getActivity()) ;
        sqLiteDatabase = musicSQLiteOpenHelper.getReadableDatabase() ;
        final Cursor cursor = sqLiteDatabase.query(MusicSQLiteOpenHelper.Tables.MUSIC, null, null, null, null, null, null) ;
        musicCursorAdapter = new MusicCursorAdapter(getActivity(), cursor) ;
        gridView.setAdapter(musicCursorAdapter);

        //musicAdapter = new MusicAdapter(getActivity(), MusicAPI.getApi().) ;
        //gridView.setAdapter(musicAdapter);

        /*MusicAPI.getApi().getMusicList(new Callback<MusicAPIResponse>() {
            @Override
            public void success(MusicAPIResponse musicAPIResponse, Response response) {
                musicAdapter = new MusicAdapter(getActivity(), musicAPIResponse.getResults().getCollection1());
                gridView.setAdapter(musicAdapter);
                Toast.makeText(getActivity(), "No. of Entries" + musicAPIResponse.getResults().getCollection1().size(), Toast.LENGTH_SHORT);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), "Something didn't went the way it was expected to", Toast.LENGTH_SHORT);
            }
        }) ;*/
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position) ;
                String musicName = cursor.getString(cursor.getColumnIndex(MusicSQLiteOpenHelper.TableMusic.SONG));
                String musicArtist = cursor.getString(cursor.getColumnIndex(MusicSQLiteOpenHelper.TableMusic.ARTIST_IMAGE_URL));
                String musicAlbum = cursor.getString(cursor.getColumnIndex(MusicSQLiteOpenHelper.TableMusic.ALBUM)) ;

                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("songName", musicName);
                intent.putExtra("artistName", musicArtist);
                intent.putExtra("albumName", musicAlbum);

                startActivity(intent);

                //EventBus.getDefault().post(new UpdateMusicBar(musicName, musicArtist, musicAlbum)) ;
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
