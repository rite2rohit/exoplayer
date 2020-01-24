package com.ks.exoplayer.video;

import com.ks.exoplayer.ata.network.model.ApiVideo;
import com.ks.exoplayer.device.player.MediaPlayer;

import java.util.List;

public interface VideoViewContract {

    interface Presenter {
        public void  deactivate();
        public MediaPlayer getPlayer();
        public void  play( String url);
        public void releasePlayer();
        public void  setMediaSessionState( Boolean isActive);
    }

    interface View {
    }



}
