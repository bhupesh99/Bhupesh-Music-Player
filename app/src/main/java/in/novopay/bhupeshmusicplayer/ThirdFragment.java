package in.novopay.bhupeshmusicplayer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import hugo.weaving.DebugLog;

/**
 * Created by bupeshkumar on 8/4/15.
 */
public class ThirdFragment extends Fragment {
    private static final String TAG="ThirdFragment" ;

    @Nullable
    @Override
    @DebugLog
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView") ;
        return inflater.inflate(R.layout.fragment_first, container, false) ;
    }

    @Override
    @DebugLog
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Log.d(TAG, "oncCreateAnimation") ;
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    @DebugLog
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate") ;
        super.onCreate(savedInstanceState);
    }

    @Override
    @DebugLog
    public void onPause() {
        Log.d(TAG, "onPause") ;
        super.onPause();
    }


}
