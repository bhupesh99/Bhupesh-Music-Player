package in.novopay.bhupeshmusicplayer;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


import in.novopay.bhupeshmusicplayer.model.Collection1;
import in.novopay.bhupeshmusicplayer.provider.MusicSQLiteOpenHelper;

/**
 * Created by bhupeshkumar on 8/4/15.
 */
public class MusicAdapter extends BaseAdapter {

    List<Collection1> musicList;
    WeakReference<Context> contextWeakReference;
    MusicSQLiteOpenHelper musicSQLiteOpenHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;

    /*public MusicAdapter(Context context, ArrayList<Collection1> musicArrayList, MusicSQLiteOpenHelper musicSQLiteOpenHelper) {
        this.musicList = musicArrayList;
        this.contextWeakReference = new WeakReference<Context>(context);
        sqLiteDatabase = musicSQLiteOpenHelper.getReadableDatabase();
        cursor = sqLiteDatabase.query(MusicSQLiteOpenHelper.Tables.MUSIC, null, null, null, null, null, null);
    }*/

    public MusicAdapter(Context context, List<Collection1> musicArrayList) {
        this.musicList =  musicArrayList;
        this.contextWeakReference = new WeakReference<Context>(context);
    }

    @Override
    public int getCount() {
        return musicList.size()+1;
    }

    @Override
    public Collection1 getItem(int position) {
        //cursor = sqLiteDatabase.query(MusicSQLiteOpenHelper.Tables.MUSIC, null, null, null, null, null, null);
        //cursor.moveToPosition(position);
        Log.d("Collection1", "Collection Call") ;
        return musicList.get(2);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        ImageView songNameView;
        TextView songAlbumView;
        TextView songArtistView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;

        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(contextWeakReference.get());
            view = layoutInflater.inflate(R.layout.item_music, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.songAlbumView = (TextView) view.findViewById(R.id.fragment_song_album);
            viewHolder.songArtistView = (TextView) view.findViewById(R.id.fragment_song_artist);
            viewHolder.songNameView = (ImageView) view.findViewById(R.id.fragment_song_name);

            view.setTag(viewHolder);
        }

        if (viewHolder == null) {
            viewHolder = (ViewHolder) view.getTag();
        }

        Collection1 collection1 = getItem(position);


        /*viewHolder.songAlbumView.setText(cursor.getString(cursor.getColumnIndex(MusicSQLiteOpenHelper.TableMusic.ALBUM)));
        Picasso
                .with(contextWeakReference.get())
                .load("http://lorempixel.com/400/200")
                .error(R.drawable.ny)
                .into(viewHolder.songNameView);
        viewHolder.songArtistView.setText(cursor.getString(cursor.getColumnIndex(MusicSQLiteOpenHelper.TableMusic.ARTIST_IMAGE_URL)));
*/

        viewHolder.songAlbumView.setText(collection1.getArtistname().getText());
        Picasso
                .with(contextWeakReference.get())
                .load(collection1.getArtistimage().getSrc())
                .error(R.drawable.ny)
                .into(viewHolder.songNameView);
        viewHolder.songArtistView.setText(collection1.getSongname().getText());
        return view;
    }
}
