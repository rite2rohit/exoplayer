package com.ks.exoplayer.ata.network.model;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CloudinaryApi {
    @GET("demo/video/list/samples.json")
    public Single<ApiResponse> fetchVideos();
}
