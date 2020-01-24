package com.ks.exoplayer;

import android.content.Intent;

import com.ks.exoplayer.ata.network.model.ApiResponse;
import com.ks.exoplayer.ata.network.model.VideosService;

import java.lang.ref.WeakReference;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
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
                VideosService.getInstance().fetchVideos()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { apiResponse -> onVideosFetchedSuccessfully(apiResponse) },
                                { throwable -> onVideosFetchError(throwable) }
                        ));
    }

    @Override
    public void deactivate() {
        disposables.clear();

    }

    @Override
    public void showVideoScreen(String videoUrl) {
        Intent intent = Intent((view.get() as Activity), VideoViewActivity.class);
        intent.putExtra(VideoViewActivity.VIDEO_URL_EXTRA, videoUrl);
        (view.get() as Activity).startActivity(intent);

    }

    private void   onVideosFetchedSuccessfully( ApiResponse videoData) {


        view.get().renderVideos(videoData.getResources());
    }

    private void  onVideosFetchError( Throwable throwable) {
        view.get().showErrorMessage();
    }
}
