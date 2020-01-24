package com.ks.exoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.ks.exoplayer.ata.network.model.ApiVideo;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void renderVideos(List<ApiVideo> videos){}

    public void showErrorMessage(){}
}
