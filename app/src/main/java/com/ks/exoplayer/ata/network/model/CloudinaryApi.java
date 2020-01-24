package com.ks.exoplayer.ata.network.model;

import retrofit2.http.GET;

public interface CloudinaryApi {
    @GET("demo/video/list/samples.json")
    public Single<ApiResponse>  fetchVideos();
}
