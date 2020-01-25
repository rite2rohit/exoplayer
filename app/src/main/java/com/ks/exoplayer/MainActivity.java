package com.ks.exoplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ks.exoplayer.ata.network.model.ApiVideo;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private ProgressBar progressBar;
    private RecyclerView videosList;
    private TextView emptyText;
    private MainContract.Presenter presenter;
    private MainAdapter videosAdapter;

    private  final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        init();
    }
    @Override
    public void renderVideos(List<ApiVideo> videos){
        hideLoadingIndicator();
        hideEmptyView();
        videosAdapter.onVideosUpdate(videos);

    }

    @Override
    public void showErrorMessage(){
        hideLoadingIndicator();
        showEmptyView();

    }
@Override
    protected void   onDestroy() {
        super.onDestroy();
        presenter.deactivate();
    }
    private void init() {
        progressBar = findViewById(R.id.pb_main);
        videosList = findViewById(R.id.rv_videos);
        emptyText = findViewById(R.id.tv_empty);

        initializeRecyclerView();

        presenter= new MainPresenter(this);

        presenter.fetchSampleVideos();
        showLoadingIndicator();
        hideEmptyView();
    }
    private void initializeRecyclerView() {
        videosList.setLayoutManager(new  LinearLayoutManager(this));
        videosList.setHasFixedSize(true);
        videosAdapter =new  MainAdapter();
        videosAdapter.onItemClick().subscribe(this::onVideoItemClick);
        videosList.setAdapter(videosAdapter);
    }

    private void onVideoItemClick(ApiVideo video) {
        presenter.showVideoScreen(createVideoUrl(video));
    }

    private String createVideoUrl(ApiVideo video) {
        StringBuilder url=new StringBuilder("https://res.cloudinary.com/demo/video/");
            url.append(video.getType())
                    .append("/v").append(video.getVersion())
                    .append("/").append(video.getPublicId())
                    .append(".").append(video.getFormat());

        Log.d(TAG, "createVideoUrl: "+url.toString());
        return url.toString();

    }


    private void showLoadingIndicator() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoadingIndicator() {
        progressBar.setVisibility(View.GONE);

    }

    private void hideEmptyView() {
        emptyText.setVisibility(View.VISIBLE);
    }

    private void showEmptyView() {
        emptyText.setVisibility(View.VISIBLE);
    }
}
