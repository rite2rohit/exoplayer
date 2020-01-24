package com.ks.exoplayer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.ks.exoplayer.ata.network.model.ApiVideo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.VideoViewHolder> {


    private long CLICK_THROTTLE_WINDOW_MILLIS = 300L;


    private Subject<ApiVideo> onVideoClickSubject = BehaviorSubject.create();

    private List<ApiVideo> videos;

   public  VideoViewHolder   onCreateViewHolder(ViewGroup parent, int viewType) {
       View  itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_item_view, parent, false);
        return new VideoViewHolder(itemView, onVideoClickSubject);
    }
@Override
  public  void onBindViewHolder(VideoViewHolder holder,int position) {holder.setVideo(videos.get(position));}
@Override
    public int getItemCount() { return videos.size();}

   public  void onVideosUpdate( List<ApiVideo> videos) {
        this.videos = videos;
        notifyDataSetChanged();
    }

    public void  onItemClick(){ onVideoClickSubject.throttleFirst(CLICK_THROTTLE_WINDOW_MILLIS, TimeUnit.MILLISECONDS);}


    class VideoViewHolder extends RecyclerView.ViewHolder {


        Subject<ApiVideo> clickSubject;
        ApiVideo video;

        VideoViewHolder(View view,Subject<ApiVideo> clickSubject){
            //this.view=view;
            super(view);
            this.clickSubject=clickSubject;
        }
        public void  setVideo( ApiVideo video) {
            this.video = video;
            itemView.tv_main_video_title.text = video.getPublicId();
            itemView.main_video_item_container.setOnClickListener { onMovieClick(); }
        }

        private void onMovieClick() { clickSubject.onNext(video);}
    }

}





