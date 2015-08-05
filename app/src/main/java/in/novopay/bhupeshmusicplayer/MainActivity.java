package in.novopay.bhupeshmusicplayer;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import java.lang.reflect.Field;

import butterknife.Bind;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActivity" ;

    @Bind(R.id.activity_main_play_pause)
    Button mPlayButton;
    private Button fastForwardButton;
    private Button rewindButton;
    private Button songList;
    private SeekBar mSeekBar;
    private ImageView albumCover ;
    TextView nameTextView;
    @Bind(R.id.activity_song_artist)
    TextView artistTextView;

    private MediaPlayer mediaPlayer;

    private MusicHandler musicHandler = new MusicHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("MainActivity", "Started Activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlayButton = (Button) findViewById(R.id.activity_main_play_pause) ;
        fastForwardButton = (Button) findViewById(R.id.activity_main_ff);
        rewindButton = (Button) findViewById(R.id.activity_main_rewind);
        mSeekBar = (SeekBar) findViewById(R.id.activity_main_seekbar);
        albumCover = (ImageView) findViewById(R.id.album_cover) ;
        mPlayButton.setBackgroundResource(R.drawable.pause_button);
        fastForwardButton.setBackgroundResource(R.drawable.fastforward_button);
        rewindButton.setBackgroundResource(R.drawable.rewind_button);
        songList = (Button) findViewById(R.id.android_song_list) ;
        nameTextView = (TextView) findViewById(R.id.activity_song_name) ;
        artistTextView = (TextView) findViewById(R.id.activity_song_artist) ;

        Resources r = getResources() ;


        String songName = getIntent().getExtras().getString("songName") ;
        int drawableId = r.getIdentifier(songName, "raw", "in.novopay.bhupeshmusicplayer") ;
        Log.d(TAG, songName);
        Log.d(TAG, String.valueOf(Uri.parse(songName)));
        Log.d(TAG, String.valueOf(drawableId)) ;
        Log.d(TAG, String.valueOf(R.raw.song));
        mediaPlayer = MediaPlayer.create(this, drawableId);
        mSeekBar.setMax(mediaPlayer.getDuration());

        albumCover.setImageResource(R.drawable.ny) ;
        nameTextView.setText(songName);
        artistTextView.setText(getIntent().getExtras().getString("artistName"));


        songList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListOfMusicActivity.class);
                startActivity(intent);
            }
        });
        mediaPlayer.start();
        musicHandler.sendEmptyMessage(MESSAGE_WAKE_UP_AND_SEEK);


        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Play is Clicked", Toast.LENGTH_SHORT).show();
                if (mPlayButton != null && mediaPlayer.isPlaying() == true) {
                    mediaPlayer.pause();
                    mPlayButton.setBackgroundResource(R.drawable.play_button);
                }
                else {
                    mediaPlayer.start();
                    mPlayButton.setBackgroundResource(R.drawable.pause_button);
                }
                Log.d("MainActivity", "Starting Position/Total Duration is " + mediaPlayer.getCurrentPosition() + "," + mediaPlayer.getDuration());
            }
        });


        fastForwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Fast Foeward is Clicked", Toast.LENGTH_SHORT).show();
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 30000);
            }
        });

        rewindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Rewind is Clicked", Toast.LENGTH_SHORT).show();
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 15000);
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(MainActivity.this, "Music Completed", Toast.LENGTH_LONG).show();
            }
        });


        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //mediaPlayer.seekTo((int) (((float)progress/100)*mediaPlayer.getDuration()));
                if (fromUser)
                    mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart") ;
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause") ;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume") ;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy") ;
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop") ;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.wtf(TAG, "onRestart") ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int MESSAGE_WAKE_UP_AND_SEEK = 10;

    class MusicHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MESSAGE_WAKE_UP_AND_SEEK) {
                if (mediaPlayer != null && mediaPlayer.isPlaying() == true) {
                    mSeekBar.setProgress(mediaPlayer.getCurrentPosition());
                    sendEmptyMessageDelayed(MESSAGE_WAKE_UP_AND_SEEK, 200);
                }
            }
        }
    }
}
