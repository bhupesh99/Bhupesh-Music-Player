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
import android.widget.ListView;

import java.util.ArrayList;

import hugo.weaving.DebugLog;
import in.novopay.bhupeshmusicplayer.model.Music;
import in.novopay.bhupeshmusicplayer.provider.MusicSQLiteOpenHelper;
import in.novopay.bhupeshmusicplayer.services.MusicService;

/**
 * Created by bhupeshkumar on 8/4/15.
 */
public class FirstFragment extends Fragment {

    private final static String TAG = "FirstFragment";
    ArrayList<Music> musicList = new ArrayList<>();
    private MusicAdapter musicAdapter;
    private ListView listView;
    public MusicSQLiteOpenHelper musicSQLiteOpenHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
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

        musicSQLiteOpenHelper = new MusicSQLiteOpenHelper(getActivity());
        SQLiteDatabase sqLiteDatabase = musicSQLiteOpenHelper.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + MusicSQLiteOpenHelper.Tables.MUSIC + " WHERE 1=1 ;");

        String INSERT_INTO_TABLE = "INSERT INTO " + MusicSQLiteOpenHelper.Tables.MUSIC
                + "(" + MusicSQLiteOpenHelper.TableMusic.ARTIST_IMAGE_URL + ","
                + MusicSQLiteOpenHelper.TableMusic.SONG + ","
                + MusicSQLiteOpenHelper.TableMusic.ALBUM + ")"
                + " VALUES (\'" + "Rammstein" + "\',"
                + "\'" + "Du Hast" + "\',"
                + "\'" + "Hybrid Theory" + "\');";
        sqLiteDatabase.execSQL(INSERT_INTO_TABLE);

        for (int i = 0; i < musicList.size(); i++) {
            INSERT_INTO_TABLE = "INSERT INTO " + MusicSQLiteOpenHelper.Tables.MUSIC
                    + "(" + MusicSQLiteOpenHelper.TableMusic.ARTIST_IMAGE_URL + ","
                    + MusicSQLiteOpenHelper.TableMusic.SONG + ","
                    + MusicSQLiteOpenHelper.TableMusic.ALBUM + ")"
                    + " VALUES (\'" + musicList.get(i).getMusicArtist() + "\',"
                    + "\'" + musicList.get(i).getMusicName() + "\',"
                    + "\'" + musicList.get(i).getMusicAlbum() + "\');";
            sqLiteDatabase.execSQL(INSERT_INTO_TABLE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        SQLiteDatabase sqLiteDatabase = musicSQLiteOpenHelper.getReadableDatabase();
        final Cursor cursor = sqLiteDatabase.query(MusicSQLiteOpenHelper.Tables.MUSIC, null, null, null, null, null, null);

        listView = (ListView) view.findViewById(R.id.fragment_first_listview);
        musicAdapter = new MusicAdapter(getActivity(), musicList, musicSQLiteOpenHelper);
        listView.setAdapter(musicAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            @DebugLog
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                String artistUrl = cursor.getString(cursor.getColumnIndex(MusicSQLiteOpenHelper.TableMusic.ARTIST_IMAGE_URL));
                String album = cursor.getString(cursor.getColumnIndex(MusicSQLiteOpenHelper.TableMusic.ALBUM));
                String song = cursor.getString(cursor.getColumnIndex(MusicSQLiteOpenHelper.TableMusic.SONG));

                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("songName", artistUrl);
                intent.putExtra("artistName", album);
                intent.putExtra("albumName", song);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Log.d(TAG, "oncCreateAnimation");
        return super.onCreateAnimation(transit, enter, nextAnim);
    }


    @Override
    public void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }


}
