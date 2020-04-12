package com.example.imagegallery;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.imagegallery.adapters.ViewAdapter;
import com.example.imagegallery.databinding.ActivityMainBinding;
import com.example.imagegallery.models.FlickrImage;
import com.example.imagegallery.viewmodels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import maes.tech.intentanim.CustomIntent;


public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private ViewAdapter mAdapter;

    private List<FlickrImage> listOfImages;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.progressBar.setVisibility(View.GONE);

        //improvising user input
        String keyword = "kitten";

        mainActivityViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(MainActivityViewModel.class);
        if(isNetworkAvailable()){
            mainActivityViewModel.apiGetImages(keyword);
        }
        listOfImages = new ArrayList<FlickrImage>();

        setObservers();
        initializeView(listOfImages);
    }

    private void setObservers(){
        //initialize the view
        mainActivityViewModel.getImages().observe(this, new Observer<List<FlickrImage>>() {
            @Override
            public void onChanged(List<FlickrImage> flickrImages) {
                initializeView(mainActivityViewModel.getImages().getValue());
                mAdapter.notifyDataSetChanged();
            }
        });


        //update loading animation
        mainActivityViewModel.getIsApiProcessing().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    binding.progressBar.setVisibility(View.VISIBLE);
                }else{
                    binding.progressBar.setVisibility(View.GONE);
                    mainActivityViewModel.dbStoreImages();
                }
            }
        });
    }

    private void initializeView(List<FlickrImage> values){
        mAdapter = new ViewAdapter(this, values);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        binding.recyclerView.setAdapter(mAdapter);
        

        mAdapter.setOnItemClickListener(new ViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(FlickrImage note) {
                if(note.getLargeImageURL().length() < 10){
                    Toast.makeText(MainActivity.this, "Cannot open big image", Toast.LENGTH_SHORT).show();
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("key", note);
                Intent inte = new Intent(MainActivity.this, FullScreenImageActivity.class);
                inte.putExtras(bundle);
                startActivity(inte);
                CustomIntent.customType(MainActivity.this, "fadein-to-fadeout");
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
