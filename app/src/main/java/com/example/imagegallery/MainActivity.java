package com.example.imagegallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

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

    private MainActivityViewModel mainActivityViewModel;
    private ViewAdapter mAdapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        mainActivityViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(MainActivityViewModel.class);

        //obtain data from the api
        mainActivityViewModel.init();

        //initialize the view
        mainActivityViewModel.getImages().observe(this, new Observer<List<FlickrImage>>() {
            @Override
            public void onChanged(List<FlickrImage> flickrImages) {
                mAdapter.notifyDataSetChanged();
                initializeView();
            }
        });

        //update loading animation
        mainActivityViewModel.getIsUpdating().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    progressBar.setVisibility(View.VISIBLE);
                }else{
                    progressBar.setVisibility(View.GONE);
                }
            }
        });



        initializeView();
    }

    private void initializeView(){
        mAdapter = new ViewAdapter(this, mainActivityViewModel.getImages().getValue());
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(FlickrImage note) {
                if(note.getLargeImageURL().length() < 10){
                    Toast.makeText(MainActivity.this, "Cannot open big image", Toast.LENGTH_SHORT).show();
                    return;
                }
                System.out.println("name : " + note.getImageTitle());
                Bundle bundle = new Bundle();
                bundle.putSerializable("key", note);
                Intent inte = new Intent(MainActivity.this, FullScreenImageActivity.class);
                inte.putExtras(bundle);
                startActivity(inte);
            }
        });
    }




}
