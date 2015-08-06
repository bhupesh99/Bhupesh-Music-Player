package in.novopay.bhupeshmusicplayer.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import de.greenrobot.event.EventBus;
import hugo.weaving.DebugLog;
import in.novopay.bhupeshmusicplayer.R;
import in.novopay.bhupeshmusicplayer.event.MusicCompletionEvent;
import in.novopay.bhupeshmusicplayer.event.PausePlayToggleEvent;

/**
 * Created by bhupeshkumar on 8/5/15.
 */
public class MusicService extends Service {

    private final IBinder mBinder = new MusicBinder();

    public static MediaPlayer mediaPlayer;

    public static final String KEY = "METHOD";
    public static final String KEY_PLAY_PAUSE = "METHOD_PLAY_PAUSE";
    public static final String KEY_PAUSE = "METHOD_PAUSE";
    public static final String KEY_STOP = "METHOD_STOP";
    public static final String KEY_FF = "METHOD_FF";
    public static final String KEY_REWIND = "METHOD_REWIND";

    public static final String EVENT_MUSIC_COMPLETED = "EVENT_MUSIC_COMPLETED" ;

    public static int getPostion() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            return mediaPlayer.getCurrentPosition();
        }
        return -1;
    }

    public class MusicBinder extends Binder {
        MusicService getService() {
            return MusicService.this ;
        }
    }


    @Override
    @DebugLog
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    @DebugLog
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    @DebugLog
    public int onStartCommand(Intent intent, int flags, int startId) {
        String method = intent.getStringExtra(KEY);
        Log.d("eiuhrgihewrg", method) ;

        if (method.equals(KEY_PLAY_PAUSE)) {
            EventBus.getDefault().post(new PausePlayToggleEvent());
        }


        else if(method.equals(KEY_FF)) {
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 30000);
        }

        else if(method.equals(KEY_REWIND)) {
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 15000);
        }

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                EventBus.getDefault().post(new MusicCompletionEvent(EVENT_MUSIC_COMPLETED));
            }
        });


        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    @DebugLog
    public void onCreate() {
        mediaPlayer = MediaPlayer.create(this, R.raw.duhast);
        super.onCreate();
    }


    public static void startPlaying(Context context) {
        Intent intent = new Intent(context, MusicService.class) ;
        intent.putExtra(MusicService.KEY, MusicService.KEY_PLAY_PAUSE) ;
        context.startService(intent) ;
    }

    public static void stopPlaying(Context context) {
        Intent intent = new Intent(context, MusicService.class) ;
        intent.putExtra(MusicService.KEY, MusicService.KEY_PAUSE) ;
        context.startService(intent) ;
    }

    public static void fastForward(Context context) {
        Intent intent = new Intent(context, MusicService.class) ;
        intent.putExtra(MusicService.KEY, MusicService.KEY_FF) ;
        context.startService(intent) ;
    }

    public static void rewind(Context context) {
        Intent intent = new Intent(context, MusicService.class) ;
        intent.putExtra(MusicService.KEY, MusicService.KEY_REWIND) ;
        context.startService(intent);
    }
}
