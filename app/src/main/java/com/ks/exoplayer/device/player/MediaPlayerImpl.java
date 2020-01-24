package com.ks.exoplayer.device.player;


import android.content.Context;
import android.net.Uri;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import com.ks.exoplayer.R;

public class MediaPlayerImpl implements MediaPlayer {

    private Context context;
    private MediaSessionCompat mediaSession;
    private final String  TAG = "MediaPlayerTag";
    private ExoPlayer exoPlayer;
    private PlaybackStateCompat.Builder stateBuilder;


   public void play(String url) {

       // Create a data source factory.
       DataSource.Factory dataSourceFactory =
               new DefaultHttpDataSourceFactory(Util.getUserAgent(context, context.getString(R.string.app_name)));
//          //Create a progressive media source pointing to a stream uri.
       MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
               .createMediaSource(Uri.parse(url));
        exoPlayer.setPlayWhenReady(true);
    }

    public ExoPlayer getPlayerImpl( Context context){
        this.context = context;
        exoPlayer = new SimpleExoPlayer.Builder(context).build();
        initializeMediaSession();
        return exoPlayer;
    }


    private void initializePlayer(){


        mediaSession = new  MediaSessionCompat(context, TAG);
        mediaSession.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS
        );
        mediaSession.setMediaButtonReceiver(null);

        stateBuilder = new PlaybackStateCompat.Builder()
                .setActions(
                        PlaybackStateCompat.ACTION_PLAY |
                        PlaybackStateCompat.ACTION_PAUSE |
                        PlaybackStateCompat.ACTION_PLAY_PAUSE |
                        PlaybackStateCompat.ACTION_FAST_FORWARD |
                        PlaybackStateCompat.ACTION_REWIND
                );

        mediaSession.setPlaybackState(stateBuilder.build());

        mediaSession.setCallback(new SessionCallback());

        mediaSession.setActive(true);
    }

    private  class SessionCallback extends MediaSessionCompat.Callback {

        private long SEEK_WINDOW_MILLIS = 10000;

        public void onPlay() {
            exoPlayer.setPlayWhenReady(true);
        }

        public void  onPause() {
            exoPlayer.setPlayWhenReady(false);
        }

        public void  onRewind() {
            exoPlayer.seekTo(exoPlayer.getCurrentPosition() - SEEK_WINDOW_MILLIS);
        }

        public void  onFastForward() {
            exoPlayer.seekTo(exoPlayer.getCurrentPosition() + SEEK_WINDOW_MILLIS);
        }
    }


    private void initializeMediaSession(){}

    public void releasePlayer(){
        exoPlayer.stop();
        exoPlayer.release();
    }

    public void setMediaSessionState(Boolean isActive){
        mediaSession.setActive(isActive);}
}
