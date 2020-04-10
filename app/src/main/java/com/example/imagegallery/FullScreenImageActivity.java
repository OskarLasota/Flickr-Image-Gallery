package com.example.imagegallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.imagegallery.models.FlickrImage;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;

import androidx.appcompat.app.AppCompatActivity;
import maes.tech.intentanim.CustomIntent;

public class FullScreenImageActivity extends AppCompatActivity {

    private FlickrImage image;
    private ImageView ivImage;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CustomIntent.customType(FullScreenImageActivity.this, "fadein-to-fadeout");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullimage);
        ivImage = findViewById(R.id.ivImage);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        image = (FlickrImage) bundle.getSerializable("key");

        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(image.getLargeImageByte());
        Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
        ivImage.setImageBitmap(bitmap);

    }
}
