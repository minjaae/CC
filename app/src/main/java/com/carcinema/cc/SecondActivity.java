package com.carcinema.cc;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterButton;

import com.carcinema.cc.R;

public class SecondActivity extends AppCompatActivity {
    ImageButton map_btn;
    ImageView login_btn, updown, play;
    VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        video = (VideoView)findViewById(R.id.video);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/uses");
        video.setVideoURI(uri);

        updown = (ImageView)findViewById(R.id.updown);
        play = (ImageView)findViewById(R.id.play);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(video.isPlaying() == false){
                    play.setImageResource(R.drawable.pause);
                    video.start();
                }
                else if(video.isPlaying() == true){
                    play.setImageResource(R.drawable.play);
                    video.pause();
                }
            }
        });

        updown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(video.getVisibility() == View.GONE){
                    updown.setImageResource(R.drawable.up);
                    video.setVisibility(View.VISIBLE);
                    play.setVisibility(View.VISIBLE);
                }else if(video.getVisibility() == View.VISIBLE){
                    updown.setImageResource(R.drawable.down);
                    video.setVisibility(View.GONE);
                    play.setVisibility(View.GONE);
                }
            }
        });
        login_btn = (ImageView)findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        map_btn = (ImageButton)findViewById(R.id.map_btn);

        map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }
}