package com.ks.exoplayer.ata.network.model;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideosService {

    private static  String CLOUDINARY_BASE_URL = "https://res.cloudinary.com/";
    private static Retrofit retrofit = null;
    public static Single<ApiResponse> fetchVideos() {

        return VideosService.getClient().create(CloudinaryApi.class).fetchVideos();
    }
    public static Retrofit getClient() {
        if(retrofit == null){
            synchronized (VideosService.class) {
                if(retrofit == null){
                    retrofit =  new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .baseUrl(CLOUDINARY_BASE_URL)
                            .build();
                }
            }
        }
        return retrofit;
    }
}
