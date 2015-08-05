package in.novopay.bhupeshmusicplayer.model;

/**
 * Created by bupeshkumar on 8/4/15.
 */
public class Music {
    private String musicName;
    private String musicAlbum;
    private String musicArtist;

    public Music(String musicName, String musicAlbum, String musicArtist) {
        this.musicName = musicName;
        this.musicAlbum = musicAlbum;
        this.musicArtist = musicArtist;
    }

    public String getMusicName() {
        return musicName;
    }

    public String getMusicAlbum() {
        return musicAlbum;
    }

    public String getMusicArtist() {
        return musicArtist ;
    }

}
