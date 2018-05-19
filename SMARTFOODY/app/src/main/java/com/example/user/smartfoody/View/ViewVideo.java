package com.example.user.smartfoody.View;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.user.smartfoody.Adapter.VideoAdapter;
import com.example.user.smartfoody.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by User on 12/3/2017.
 */

public class ViewVideo extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    public static final String API_KEY = "AIzaSyAcyYC020K0MmcBvuqq5nqFNIR1yvgKRzs";
    public static String VIDEO_ID = "AJ9Jg1PwRJo";
    String ID_PLAYLIST = "PLyO5XJbG6BRnH2hFGbXt8-wncbDfHgpbY";
    String urlplaylist = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=PLyO5XJbG6BRnH2hFGbXt8-wncbDfHgpbY&key=AIzaSyAcyYC020K0MmcBvuqq5nqFNIR1yvgKRzs";
    private YouTubePlayerView youtubeView;
    private Button Click;
    private LinearLayout layout;
    private boolean up = false;
    ListView viewlistvideo;
    ArrayList<Video> listarray;
    VideoAdapter adapter;
    YouTubePlayer player;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewvideo);
        GetJsonYoutube(urlplaylist);

        //init
        youtubeView = (YouTubePlayerView) findViewById(R.id.youtubevideo);
        youtubeView.initialize(API_KEY, this);

        //
        final Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_down);
        final Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_up);
        //
        layout = (LinearLayout)findViewById(R.id.frameup);
        Click = (Button)findViewById(R.id.click);
        Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (up == false) {
                    layout.setVisibility(View.VISIBLE);
                    layout.startAnimation(slide_down);
                    Click.setBackgroundResource(R.drawable.ic_up);
                    up = true;
                }
                else
                {
                    layout.setVisibility(View.GONE);
                    layout.startAnimation(slide_up);
                    Click.setBackgroundResource(R.drawable.ic_down);
                    up = false;
                }
            }
        });


        viewlistvideo = (ListView)findViewById(R.id.listvideo);
        listarray = new ArrayList<>();
        adapter = new VideoAdapter(this, R.layout.videoadapter, listarray);
        viewlistvideo.setAdapter(adapter);
        viewlistvideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                VIDEO_ID = listarray.get(i).getVideoid().toString();
                player.loadVideo(VIDEO_ID);
                player.play();
            }
        });

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasrestore) {
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);

        if (! wasrestore)
        {
            youTubePlayer.cueVideo(VIDEO_ID);
            player = youTubePlayer;
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        Toast.makeText(ViewVideo.this, "Failed to Initialize!", Toast.LENGTH_SHORT).show();
    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {

        }

        @Override
        public void onPaused() {

        }

        @Override
        public void onStopped() {

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {

        }

        @Override
        public void onVideoStarted() {

        }

        @Override
        public void onVideoEnded() {

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };




    // Json youtube playlist
    private void GetJsonYoutube(String url)
    {
        RequestQueue request = Volley.newRequestQueue(this);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonitem = response.getJSONArray("items");
                            String title = ""; String url= ""; String idvideo = "";
                            for (int i=0; i<jsonitem.length(); i++)
                            {
                                //get item from list in json
                                JSONObject jsonitemopject = jsonitem.getJSONObject(i);
                                // get snippet
                                JSONObject jsonSnippet = jsonitemopject.getJSONObject("snippet");
                                title = jsonSnippet.getString("title");
                                //get thumbnail
                                JSONObject jsonthumnail = jsonSnippet.getJSONObject("thumbnails");
                                // setup size for thumbnail
                                JSONObject jsonsize = jsonthumnail.getJSONObject("medium");
                                url = jsonsize.getString("url");
                                //get resource
                                JSONObject jsonresource = jsonSnippet.getJSONObject("resourceId");
                                //get videoid
                                idvideo = jsonresource.getString("videoId");
                                listarray.add(new Video(title, url, idvideo));
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        request.add(jsonRequest);
    }
}
