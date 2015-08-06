package in.novopay.bhupeshmusicplayer.network;


import in.novopay.bhupeshmusicplayer.model.MusicAPIResponse;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;

/**
 * Created by bupeshkumar on 8/6/15.
 */
public class MusicAPI {

    private static final String URL = "https://www.kimonolabs.com/api" ;
    private static MusicInterface musicInterface = null ;

    public static MusicInterface getApi() {
        if(musicInterface == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint(URL)
                    .build() ;
            musicInterface = restAdapter.create(MusicInterface.class) ;
        }
        return musicInterface ;
    }

    public interface MusicInterface {
        @GET("/6eiod0by?apikey=ao0yorwAaas8VQ5abSz3sPnoQVwea2Wy")
        MusicAPIResponse getMusicList();

        @GET("/6eiod0by?apikey=ao0yorwAaas8VQ5abSz3sPnoQVwea2Wy")
        void getMusicList(Callback<MusicAPIResponse> musicAPIResponseCallback) ;
    }
}
