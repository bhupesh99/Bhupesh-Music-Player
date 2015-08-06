package in.novopay.bhupeshmusicplayer;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import in.novopay.bhupeshmusicplayer.provider.MusicSQLiteOpenHelper;

/**
 * Created by bhupeshkumar on 8/6/15.
 */
public class MusicCursorAdapter extends CursorAdapter {
    private final static String TAG = "MusicCursorAdapter";

    public MusicCursorAdapter(Context context, Cursor c) {
        super(context, c, false);
    }

    private class ViewHolder {
        TextView songArtistView;
        TextView songAlbumView;
        ImageView songNameView;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        Log.d(TAG, "Inside newView");
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_music, parent, false);

        ViewHolder viewHolder = new ViewHolder();

        viewHolder.songAlbumView = (TextView) view.findViewById(R.id.fragment_song_album);
        viewHolder.songNameView = (ImageView) view.findViewById(R.id.fragment_song_name);
        viewHolder.songArtistView = (TextView) view.findViewById(R.id.fragment_song_artist);

        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        Log.d(TAG, "Inside bindview");
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        String imageUrl = cursor.getString(cursor.getColumnIndex(MusicSQLiteOpenHelper.TableMusic.ARTIST_IMAGE_URL));
        String albumName = cursor.getString(cursor.getColumnIndex(MusicSQLiteOpenHelper.TableMusic.ALBUM));
        String artistName = cursor.getString(cursor.getColumnIndex(MusicSQLiteOpenHelper.TableMusic.SONG));
        Log.d(TAG, imageUrl + " " + albumName + " " + artistName);

        if (imageUrl.length() == 0)
            imageUrl = "http://lorempixel.com/400/200";
/*        String albumName = "Seriously!";
        String artistName = "Good One!!!";
*/

        viewHolder.songArtistView.setText(artistName);
        Picasso
                .with(context)
                .load(imageUrl)
                .error(R.drawable.ny)
                .into(viewHolder.songNameView);
        viewHolder.songAlbumView.setText(albumName);

    }
}
