package in.novopay.bhupeshmusicplayer.event;

import de.greenrobot.event.EventBus;

/**
 * Created by bupeshkumar on 8/5/15.
 */
public class SeekBarEvent extends EventBus {
    public int position ;

    public SeekBarEvent(int position) {
        this.position = position;
    }
}
