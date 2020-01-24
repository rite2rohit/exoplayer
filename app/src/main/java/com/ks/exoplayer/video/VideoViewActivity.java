package com.ks.exoplayer.video;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import com.google.android.exoplayer2.ui.PlayerView;
import com.ks.exoplayer.R;

public class VideoViewActivity extends AppCompatActivity implements VideoViewContract.View {
    public static  final String  VIDEO_URL_EXTRA = "video_url_extra";
    private PlayerView videoView;
    private VideoViewContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view1);
        init();
    }





    private void init() {
        presenter = new VideoViewPresenter(this);
        String videoUrl = getIntent().getStringExtra(VIDEO_URL_EXTRA);
        videoView = findViewById(R.id.ep_video_view);
        videoView.setPlayer(presenter.getPlayer().getPlayerImpl(this));
        presenter.play(videoUrl);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            presenter.releasePlayer();
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            presenter.releasePlayer();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deactivate();
        presenter.setMediaSessionState(false);
    }


}
