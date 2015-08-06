package in.novopay.bhupeshmusicplayer.event;

import de.greenrobot.event.EventBus;

/**
 * Created by bupeshkumar on 8/5/15.
 */
public class PlaySongEvent extends EventBus {
    public boolean isFilePresent ;

    public PlaySongEvent(boolean isFilePresent) {
        this.isFilePresent = isFilePresent;
    }
}
