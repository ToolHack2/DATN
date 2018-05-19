package com.example.user.smartfoody.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.smartfoody.R;
import com.example.user.smartfoody.View.Video;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by User on 12/11/2017.
 */

public class VideoAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Video> videoList;

    public VideoAdapter(Context context, int layout, List<Video> videoList) {
        this.context = context;
        this.layout = layout;
        this.videoList = videoList;
    }

    @Override
    public int getCount() {
        return videoList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder
    {
        ImageView videoimage;
        TextView videotitle;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        if(view == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder.videotitle = (TextView) view.findViewById(R.id.txttitle);
            viewHolder.videoimage = (ImageView)view.findViewById(R.id.imgvideo);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Video video = videoList.get(i);
        viewHolder.videotitle.setText(video.getTitle());
        Picasso.with(context).load(video.getThumnail()).into(viewHolder.videoimage);
        return view;
    }
}
