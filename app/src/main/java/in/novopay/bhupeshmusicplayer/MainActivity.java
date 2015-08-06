package in.novopay.bhupeshmusicplayer;

import android.content.Intent;
import android.content.res.Resources;
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


import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;

import butterknife.Bind;
import de.greenrobot.event.EventBus;
import hugo.weaving.DebugLog;
import in.novopay.bhupeshmusicplayer.event.MusicCompletionEvent;
import in.novopay.bhupeshmusicplayer.event.PausePlayToggleEvent;
import in.novopay.bhupeshmusicplayer.event.SeekBarEvent;
import in.novopay.bhupeshmusicplayer.services.MusicService;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    Button mPlayButton;
    private Button fastForwardButton;
    private Button rewindButton;
    private Button songList;
    private SeekBar mSeekBar;
    private ImageView albumCover;
    TextView nameTextView;
    @Bind(R.id.activity_song_artist)
    TextView artistTextView;


    private MusicHandler musicHandler = new MusicHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("MainActivity", "Started Activity");
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlayButton = (Button) findViewById(R.id.activity_main_play);
        fastForwardButton = (Button) findViewById(R.id.activity_main_ff);
        rewindButton = (Button) findViewById(R.id.activity_main_rewind);
        mSeekBar = (SeekBar) findViewById(R.id.activity_main_seekbar);
        albumCover = (ImageView) findViewById(R.id.album_cover);
        mPlayButton.setBackgroundResource(R.drawable.play_button);
        fastForwardButton.setBackgroundResource(R.drawable.fastforward_button);
        rewindButton.setBackgroundResource(R.drawable.rewind_button);
        songList = (Button) findViewById(R.id.android_song_list);
        nameTextView = (TextView) findViewById(R.id.activity_song_name);
        artistTextView = (TextView) findViewById(R.id.activity_song_artist);

        Resources r = getResources();


        String songName = getIntent().getExtras().getString("songName");
        int drawableId = r.getIdentifier(songName, "raw", "in.novopay.bhupeshmusicplayer");
        Log.d(TAG, songName);
        Log.d(TAG, String.valueOf(Uri.parse(songName)));
        Log.d(TAG, String.valueOf(drawableId));
        Log.d(TAG, String.valueOf(R.raw.song));

        //albumCover.setImageResource(getIntent().getExtras().getString("artistName"));
        Picasso
        .with(getApplicationContext())
                .load(getIntent().getExtras().getString("artistName"))
                .error(R.drawable.ny)
                .into(albumCover);
        nameTextView.setText(songName);
        artistTextView.setText(getIntent().getExtras().getString("albumName"));


        songList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListOfMusicActivity.class);
                startActivity(intent);
            }
        });
        //musicHandler.sendEmptyMessage(MESSAGE_WAKE_UP_AND_SEEK);
        musicHandler.sendEmptyMessage(MESSAGE_WAKE_UP_AND_SEEK) ;


        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Play is Clicked", Toast.LENGTH_SHORT).show();
                MusicService.startPlaying(MainActivity.this);
                //mSeekBar.setMax(MusicService.mediaPlayer.getDuration());
            }
        });

        fastForwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicService.fastForward(MainActivity.this);
            }
        });

        rewindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Rewind is Clicked", Toast.LENGTH_SHORT).show();
                MusicService.rewind(MainActivity.this);
            }
        });


        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //MusicService.mediaPlayer.seekTo((int) (((float) progress / 100) * MusicService.mediaPlayer.getDuration()));
                if (fromUser) {
                    //MusicService.mediaPlayer.seekTo(progress);
                    EventBus.getDefault().post(new SeekBarEvent(progress));
                }
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
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.wtf(TAG, "onRestart");
    }

    @DebugLog
    public void onEvent(PausePlayToggleEvent pausePlayToggleEvent) {
        Log.d(TAG, "PAUSE_PLAY_ON#RF:ENFRN:#ERFNENR" + String.valueOf(MusicService.mediaPlayer.isPlaying()));

        if(MusicService.mediaPlayer.isPlaying() == false) {
            mPlayButton.setBackgroundResource(R.drawable.pause_button);
            MusicService.mediaPlayer.start();
        }
        else {
            mPlayButton.setBackgroundResource(R.drawable.play_button);
            MusicService.mediaPlayer.pause();
        }
    }

    public void onEvent(MusicCompletionEvent musicCompletionEvent) {
        Log.d(TAG, "On Event Completion Event");
    }

    public void onEvent(SeekBarEvent seekBarEvent) {
        Log.d(TAG, "SeekBar position changed" + String.valueOf(seekBarEvent.position));
        mSeekBar.setProgress(seekBarEvent.position);
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
                if (MusicService.mediaPlayer != null && MusicService.mediaPlayer.isPlaying() == true) {
                    mSeekBar.setMax(MusicService.mediaPlayer.getDuration());
                    mSeekBar.setProgress(MusicService.getPostion()) ;
                    sendEmptyMessageDelayed(MESSAGE_WAKE_UP_AND_SEEK, 200);
                }
            }
        }
    }
}
