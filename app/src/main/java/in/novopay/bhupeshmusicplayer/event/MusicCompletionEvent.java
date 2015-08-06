package in.novopay.bhupeshmusicplayer.event;

import de.greenrobot.event.EventBus;

/**
 * Created by bupeshkumar on 8/5/15.
 */
public class MusicCompletionEvent extends EventBus {
    public static String message;

    //public static String

    public MusicCompletionEvent(String message) {
        this.message = message ;
    }
}
