package in.novopay.bhupeshmusicplayer;

import android.content.Context;
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


import in.novopay.bhupeshmusicplayer.model.Music;

/**
 * Created by bhupeshkumar on 8/4/15.
 */
public class MusicAdapter extends BaseAdapter {

    ArrayList<Music> musicList;
    WeakReference<Context> contextWeakReference ;

    public MusicAdapter(Context context, ArrayList<Music> musicArrayList) {
        this.musicList = musicArrayList ;
        this.contextWeakReference = new WeakReference<Context>(context) ;
    }

    @Override
    public int getCount() {
        return musicList.size();
    }

    @Override
    public Object getItem(int position) {
        return musicList.get(position);
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
        ViewHolder viewHolder = null ;

        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(contextWeakReference.get()) ;
            view = layoutInflater.inflate(R.layout.item_music, parent, false) ;

            viewHolder = new ViewHolder();
            viewHolder.songAlbumView = (TextView) view.findViewById(R.id.fragment_song_album) ;
            viewHolder.songArtistView = (TextView) view.findViewById(R.id.fragment_song_artist) ;
            viewHolder.songNameView = (ImageView) view.findViewById(R.id.fragment_song_name) ;

            view.setTag(viewHolder);
        }

        if(viewHolder == null) {
            viewHolder = (ViewHolder) view.getTag() ;
        }

        Music music = (Music) getItem(position);


        viewHolder.songAlbumView.setText(music.getMusicAlbum());
        Picasso
                .with(contextWeakReference.get())
                .load("http://lorempixel.com/400/200")
                .error(R.drawable.ny)
                .into(viewHolder.songNameView);
        viewHolder.songArtistView.setText(music.getMusicName());

        return view;
    }
}
