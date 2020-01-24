package com.ks.exoplayer;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainPresenter implements MainContract.Presenter {

    private WeakReference<MainContract.View> view;
    private Disposable disposables = new CompositeDisposable();

    MainPresenter(MainContract.View view){
        this.view=new WeakReference<MainContract.View>(view);
    }

    @Override
    public void fetchSampleVideos() {

    }

    @Override
    public void deactivate() {

    }

    @Override
    public void showVideoScreen(String videoUrl) {

    }
}
