package com.ks.exoplayer.video;

import com.ks.exoplayer.MainContract;
import com.ks.exoplayer.device.player.MediaPlayer;
import com.ks.exoplayer.device.player.MediaPlayerImpl;

import java.lang.ref.WeakReference;

public class VideoViewPresenter implements VideoViewContract.Presenter {

    private WeakReference<VideoViewContract.View> view;
    private MediaPlayer mediaPlayer = new MediaPlayerImpl();


    VideoViewPresenter(VideoViewContract.View view){
      this.view=    new WeakReference<VideoViewContract.View>(view);

    }


    @Override
    public MediaPlayer getPlayer() {
        return mediaPlayer;
    }

    @Override
    public void play(String url) {
        mediaPlayer.play(url);
    }

    @Override
    public void releasePlayer() {
        mediaPlayer.releasePlayer();

    }

    @Override
    public void setMediaSessionState(Boolean isActive) {
        mediaPlayer.setMediaSessionState(isActive);
    }

    @Override
    public void deactivate() {
    }
}
