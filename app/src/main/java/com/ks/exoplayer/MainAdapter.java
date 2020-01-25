package com.ks.exoplayer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ks.exoplayer.ata.network.model.ApiVideo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.SchedulerSupport;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.VideoViewHolder> {
    private long CLICK_THROTTLE_WINDOW_MILLIS = 300L;
    private Subject<ApiVideo> onVideoClickSubject = BehaviorSubject.create();
    private List<ApiVideo> videos=new ArrayList<>();
    @Override
   public  VideoViewHolder   onCreateViewHolder(ViewGroup parent, int viewType) {
       View  itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_item_view, parent, false);
        return new VideoViewHolder(itemView, onVideoClickSubject);
    }
    @Override
  public  void onBindViewHolder(VideoViewHolder holder,int position) {
        holder.setVideo(videos.get(position));
    }
    @Override
    public int getItemCount() {
        return videos.size();
    }

   public  void onVideosUpdate( List<ApiVideo> videos) {
        this.videos = videos;
        notifyDataSetChanged();
    }


    public final Observable<ApiVideo> onItemClick(){
       return onVideoClickSubject.throttleFirst(CLICK_THROTTLE_WINDOW_MILLIS, TimeUnit.MILLISECONDS);
    }


    class VideoViewHolder extends RecyclerView.ViewHolder {
        Subject<ApiVideo> clickSubject;
        ApiVideo video;
        TextView videoTitleTextView;
        VideoViewHolder(View view,Subject<ApiVideo> clickSubject){
            super(view);
            this.clickSubject=clickSubject;
        }
        public void  setVideo( ApiVideo video) {
            this.video = video;

            videoTitleTextView= (TextView) itemView.findViewById(R.id.tv_main_video_title);
            itemView.findViewById(R.id.main_video_item_container).setOnClickListener(
                    new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickSubject.onNext(video);
                }
            });

        }


    }

}





