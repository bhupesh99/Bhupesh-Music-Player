package in.novopay.bhupeshmusicplayer.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import hugo.weaving.DebugLog;

/**
 * Created by bupeshkumar on 8/6/15.
 */
public class MusicSQLiteOpenHelper extends SQLiteOpenHelper {
    public static int VERSION=1;
    public static String DATABASE_NAME = "musicdb" ;

    public interface Tables {
        String MUSIC = "music" ;
    }

    public interface TableMusic {
        String ARTIST_IMAGE_URL = "artisturl";
        String ALBUM = "album";
        String SONG = "song";
    }

    public MusicSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    final String CREATE_TABLE_MUSIC = "CREATE TABLE " + Tables.MUSIC + "("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TableMusic.ALBUM + " TEXT NOT NULL, "
            + TableMusic.SONG + " TEXT NOT NULL, "
            + TableMusic.ARTIST_IMAGE_URL + " TEXT NOT NULL);" ;

    @Override
    @DebugLog
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MUSIC);

    }

    public void insertMusic(String query) {

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
