package in.novopay.bhupeshmusicplayer.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import hugo.weaving.DebugLog;

/**
 * Created by bhupeshkumar on 8/6/15.
 */
public class MusicSQLiteOpenHelper extends SQLiteOpenHelper {
    public static int VERSION = 1;
    public static String DATABASE_NAME = "musicdb";

    public interface Tables {
        String MUSIC = "music";
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
            + TableMusic.ALBUM + " TEXT NOT NULL UNIQUE, "
            + TableMusic.SONG + " TEXT NOT NULL UNIQUE, "
            + TableMusic.ARTIST_IMAGE_URL + " TEXT NOT NULL UNIQUE);";

    @Override
    @DebugLog
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE " + Tables.MUSIC + " IF EXISTS;");
        db.execSQL(CREATE_TABLE_MUSIC);
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableMusic.ARTIST_IMAGE_URL, "Du Hast");
        contentValues.put(TableMusic.ALBUM, "Fuck it!!!");
        contentValues.put(TableMusic.SONG, "http://lorempixel.com/400/200");

        db.insert(Tables.MUSIC, null, contentValues) ;

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
