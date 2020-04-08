package com.example.imagegallery;

import android.os.Bundle;

import com.example.imagegallery.adapters.ViewAdapter;
import com.example.imagegallery.models.FlickrImage;
import com.example.imagegallery.viewmodels.MainActivityViewModel;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {

    //in a real project, gradle.properties would not be comitted to github
    private String key = BuildConfig.ApiKey;
    private MainActivityViewModel mainActivityViewModel;
    private ViewAdapter mAdapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);


        mainActivityViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(MainActivityViewModel.class);
        mainActivityViewModel.init();
        mainActivityViewModel.getImages().observe(this, new Observer<List<FlickrImage>>() {
            @Override
            public void onChanged(List<FlickrImage> flickrImages) {
               mAdapter.notifyDataSetChanged();
            }
        });

        //animation when list is loading
        mainActivityViewModel.getIsUpdating().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    System.out.println("loading");
                }else{
                    System.out.println("finished");
                }
            }
        });
        initializeView();
    }

    private void initializeView(){
        mAdapter = new ViewAdapter(this, mainActivityViewModel.getImages().getValue());
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(mAdapter);
    }




}
