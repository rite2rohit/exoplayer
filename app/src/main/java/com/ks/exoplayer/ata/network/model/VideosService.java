package com.ks.exoplayer.ata.network.model;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class VideosService {

    private  String CLOUDINARY_BASE_URL = "https://res.cloudinary.com/";

    fun fetchVideos() = createCloudinaryVideoService().fetchVideos()

    private void  createRetrofitInstance() {
        Retrofit.Builder(
            .baseUrl(CLOUDINARY_BASE_URL)
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create()}

    private void createCloudinaryVideoService() {createRetrofitInstance().}
}
