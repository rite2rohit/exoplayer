package com.ks.exoplayer;

import android.app.Activity;
import android.content.Intent;

import androidx.core.util.Consumer;

import com.ks.exoplayer.ata.network.model.ApiResponse;
import com.ks.exoplayer.ata.network.model.VideosService;
import com.ks.exoplayer.video.VideoViewActivity;

import java.lang.ref.WeakReference;
import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainPresenter implements MainContract.Presenter {

    private WeakReference<MainContract.View> view;
    private CompositeDisposable disposables = new CompositeDisposable();

    MainPresenter(MainContract.View view){
        this.view=new WeakReference<MainContract.View>(view);
    }

    @Override
    public void fetchSampleVideos() {
      disposables.add(
                VideosService.fetchVideos()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe( apiResponse ->  onVideosFetchedSuccessfully(apiResponse),
                                throwable -> onVideosFetchError(throwable)
                                ));
    }

    @Override
    public void deactivate() {
        disposables.clear();

    }

    @Override
    public void showVideoScreen(String videoUrl) {
       Intent intent = new Intent((Activity)view.get() ,VideoViewActivity.class);
        intent.putExtra(VideoViewActivity.VIDEO_URL_EXTRA, videoUrl);
        ((Activity) view.get()).startActivity(intent);

    }

    private  void    onVideosFetchedSuccessfully(ApiResponse videoData) {
       view.get().renderVideos(videoData.getResources());
    }

    private void  onVideosFetchError( Throwable throwable) {
        view.get().showErrorMessage();
    }
}
