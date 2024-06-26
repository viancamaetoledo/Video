package com.example.image;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    Button selectImage, selectVideo;

    ImageView previewImage;

    VideoView video;
    int LOAD_VIDEO = 200;
    int select_image = 200;
    MediaController mediaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        selectImage = findViewById(R.id.btnSelect);
        previewImage = findViewById(R.id.previewimage);
        selectVideo = findViewById(R.id.btnSelect1);
        video = findViewById(R.id.videoView);

        mediaController = new MediaController(MainActivity.this);
        video.setMediaController(mediaController);
        mediaController.setAnchorView(video);

        selectVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), LOAD_VIDEO);

            }
        });

        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), select_image);

            }
        });



    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == select_image && resultCode == RESULT_OK
                && null != data){

                Uri selectImageUri = data.getData();
                video.setVisibility(View.INVISIBLE);
                 previewImage.setVisibility(View.VISIBLE);
                 previewImage.setImageURI(selectImageUri);



        } if (requestCode == LOAD_VIDEO && resultCode == RESULT_OK && data != null) {

            Uri videoCaptureUri = data.getData();
            previewImage.setVisibility(View.INVISIBLE);
            video.setVisibility(View.VISIBLE);
            video.setVideoURI(videoCaptureUri);
            video.start();
        }
    }

}