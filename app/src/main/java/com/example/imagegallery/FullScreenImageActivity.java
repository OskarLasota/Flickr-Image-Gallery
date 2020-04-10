package com.example.imagegallery;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.imagegallery.models.FlickrImage;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

public class FullScreenImageActivity extends AppCompatActivity {

    private FlickrImage image;
    private ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullimage);
        ivImage = findViewById(R.id.ivImage);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        image = (FlickrImage) bundle.getSerializable("key");

        Picasso.with(this.getApplicationContext()).load(image.getLargeImageURL()).into(ivImage);
    }
}
