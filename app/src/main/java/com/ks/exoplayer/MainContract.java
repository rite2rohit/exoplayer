package com.ks.exoplayer;


import com.ks.exoplayer.ata.network.model.ApiVideo;

import java.util.List;

public interface MainContract {

    interface Presenter {

        public void  fetchSampleVideos();

        public void  deactivate();

        public void  showVideoScreen(String videoUrl );
    }

    interface View {

        public void renderVideos(List<ApiVideo> videos);

        public void showErrorMessage();
    }
}
