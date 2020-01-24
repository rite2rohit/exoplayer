package com.ks.exoplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ks.exoplayer.ata.network.model.ApiVideo;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private ProgressBar progressBar;
    private RecyclerView videosList;
    private TextView emptyText;
    private   MainContract.Presenter presenter;
    private  MainAdapter videosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public void renderVideos(List<ApiVideo> videos){

    }

    @Override
    public void showErrorMessage(){

    }
}
