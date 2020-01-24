package com.ks.exoplayer.ata.network.model;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideosService {

    private final static VideosService videosService=new VideosService();

    private VideosService(){

    }


    public static VideosService getInstance(){
        return videosService;
    }


    private String CLOUDINARY_BASE_URL = "https://res.cloudinary.com/";

    public void fetchVideos() {

        new Retrofit.Builder()
                .baseUrl(CLOUDINARY_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(CloudinaryApi.class).fetchVideos();
    }



}
