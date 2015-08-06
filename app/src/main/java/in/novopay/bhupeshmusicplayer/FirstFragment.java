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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import in.novopay.bhupeshmusicplayer.model.Collection1;
import in.novopay.bhupeshmusicplayer.model.MusicAPIResponse;
import in.novopay.bhupeshmusicplayer.network.MusicAPI;
import in.novopay.bhupeshmusicplayer.provider.MusicSQLiteOpenHelper;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by bhupeshkumar on 8/4/15.
 */
public class FirstFragment extends Fragment {

    private final static String TAG = "FirstFragment";
    //ArrayList<Music> musicList = new ArrayList<>();
    //private MusicAdapter musicAdapter;
    private ListView listView;
    MusicCursorAdapter musicCursorAdapter;
    MusicSQLiteOpenHelper musicSQLiteOpenHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
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

        /*musicSQLiteOpenHelper = new MusicSQLiteOpenHelper(getActivity());
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
        }*/

        musicSQLiteOpenHelper = new MusicSQLiteOpenHelper(getActivity());
        sqLiteDatabase = musicSQLiteOpenHelper.getWritableDatabase();
        //sqLiteDatabase.execSQL("DELETE FROM " + MusicSQLiteOpenHelper.Tables.MUSIC + " WHERE 1=1");

        MusicAPI.getApi().getMusicList(new Callback<MusicAPIResponse>() {
            @Override
            public void success(MusicAPIResponse musicAPIResponse, Response response) {
                /*musicCursorAdapter = new MusicCursorAdapter(getActivity(), musicAPIResponse.getResults().getCollection1()) ;
                listView.setAdapter(musicAdapter);
                Toast.makeText(getActivity(), "No. of Entries" + musicAPIResponse.getResults().getCollection1().size(), Toast.LENGTH_SHORT);*//*
                *//*String songName = musicAPIResponse.getResults().getCollection1().get

                String INSERT_INTO_TABLE = "INSERT INTO " + MusicSQLiteOpenHelper.Tables.MUSIC
                        + "(" + MusicSQLiteOpenHelper.TableMusic.ARTIST_IMAGE_URL + ","
                        + MusicSQLiteOpenHelper.TableMusic.SONG + ","
                        + MusicSQLiteOpenHelper.TableMusic.ALBUM + ")"
                        + " VALUES (\'" + musicList.get(i).getMusicArtist() + "\',"
                        + "\'" + musicList.get(i).getMusicName() + "\',"
                        + "\'" + musicList.get(i).getMusicAlbum() + "\');";*/
                List<Collection1> musicList = musicAPIResponse.getResults().getCollection1();
                Log.d(TAG, String.valueOf(musicList.size()) + "in total" + musicList.get(0).getArtistimage().getSrc());
                for (int i = 0; i < musicList.size(); i++) {
                    String INSERT_INTO_TABLE = "INSERT INTO " + MusicSQLiteOpenHelper.Tables.MUSIC
                            + "(" + MusicSQLiteOpenHelper.TableMusic.ARTIST_IMAGE_URL + ","
                            + MusicSQLiteOpenHelper.TableMusic.SONG + ","
                            + MusicSQLiteOpenHelper.TableMusic.ALBUM + ")"
                            + " VALUES (\'" + musicList.get(i).getArtistimage().getSrc() + "\',"
                            + "\'" + musicList.get(i).getSongname().getText() + "\',"
                            + "\'" + musicList.get(i).getArtistname().getText() + "\');";
                    sqLiteDatabase.execSQL(INSERT_INTO_TABLE);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), "Something didn't went the way it was expected to", Toast.LENGTH_SHORT);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        listView = (ListView) view.findViewById(R.id.fragment_first_listview);

        musicSQLiteOpenHelper = new MusicSQLiteOpenHelper(getActivity());
        sqLiteDatabase = musicSQLiteOpenHelper.getReadableDatabase();
        final Cursor cursor = sqLiteDatabase.query(MusicSQLiteOpenHelper.Tables.MUSIC, null, null, null, null, null, null);
        musicCursorAdapter = new MusicCursorAdapter(getActivity(), cursor);
        listView.setAdapter(musicCursorAdapter);

        /*SQLiteDatabase sqLiteDatabase = musicSQLiteOpenHelper.getReadableDatabase();
        final Cursor cursor = sqLiteDatabase.query(MusicSQLiteOpenHelper.Tables.MUSIC, null, null, null, null, null, null);

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
        });*/

        /*MusicAPI.getApi().getMusicList(new Callback<MusicAPIResponse>() {
            @Override
            public void success(MusicAPIResponse musicAPIResponse, Response response) {
                musicAdapter = new MusicAdapter(getActivity(), musicAPIResponse.getResults().getCollection1()) ;
                listView.setAdapter(musicAdapter);
                Toast.makeText(getActivity(), "No. of Entries" + musicAPIResponse.getResults().getCollection1().size(), Toast.LENGTH_SHORT);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), "Something didn't went the way it was expected to", Toast.LENGTH_SHORT) ;
            }
        }) ;*/

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                String musicName = cursor.getString(cursor.getColumnIndex(MusicSQLiteOpenHelper.TableMusic.SONG));
                String musicArtist = cursor.getString(cursor.getColumnIndex(MusicSQLiteOpenHelper.TableMusic.ARTIST_IMAGE_URL));
                String musicAlbum = cursor.getString(cursor.getColumnIndex(MusicSQLiteOpenHelper.TableMusic.ALBUM));

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
        Log.d(TAG, "oncCreateAnimation");
        return super.onCreateAnimation(transit, enter, nextAnim);
    }


    @Override
    public void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }


}
