package com.example.imagegallery;

import android.os.Bundle;

import com.example.imagegallery.adapters.ViewAdapter;
import com.example.imagegallery.models.FlickrImage;
import com.example.imagegallery.viewmodels.MainActivityViewModel;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

        String url = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key="+key+"&tags=kitten&page=1&format=json&nojsoncallback=1";
        connectToApi(url);

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
        mainActivityViewModel.addNewValue(new FlickrImage("test id", "title1", R.drawable.ic_launcher_background));
        initializeView();
    }

    private void initializeView(){
        mAdapter = new ViewAdapter(this, mainActivityViewModel.getImages().getValue());
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(mAdapter);
    }







    private void connectToApi(String url) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call,Response response) throws IOException {
                try {
                    if (response.isSuccessful()) {
                        String data = response.body().string();
                        //System.out.println(data);
                        JSONObject jsonObject = new JSONObject(data);
                        JSONArray photos = jsonObject.getJSONObject("photos").getJSONArray("photo");

                        for(int i=0; i<photos.length() ; i++){
                            JSONObject obj1 = photos.getJSONObject(i);
                            String id = obj1.getString("id");
                            System.out.println(id);
                        }


                    }
                }catch(JSONException e){
                    System.out.println("error");
                }
            }
        });
    }
}
