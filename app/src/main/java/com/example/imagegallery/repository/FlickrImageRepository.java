package com.example.imagegallery.repository;

import com.example.imagegallery.BuildConfig;
import com.example.imagegallery.models.FlickrImage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import androidx.lifecycle.MutableLiveData;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FlickrImageRepository {

    private static FlickrImageRepository instance;
    private List<FlickrImage> dataSet = new ArrayList<>();
    private String key = BuildConfig.ApiKey;// in a real project properties wouldnt be committed
    private boolean requestCompleted = false;

    public static FlickrImageRepository getInstance(){
        if(instance == null){
            instance = new FlickrImageRepository();
        }
        return instance;
    }


    public MutableLiveData<List<FlickrImage>> getImages(){
        String url = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key="+key+"&tags=kitten&page=1&format=json&nojsoncallback=1";
        setImages(url);
        while(!requestCompleted) {
            try {
                Thread.sleep(500); // find a fix for this asap
            } catch (InterruptedException e) {
                requestCompleted = true;
            }
        }

        MutableLiveData<List<FlickrImage>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setImages(String url){
        connectToApi(url);
    }

    private void apiGetImage(String id, final int index, final int total){
        String singleImageURL = "https://api.flickr.com/services/rest/?method=flickr.photos.getSizes&api_key="+key+"&photo_id="+id+"&format=json&nojsoncallback=1";
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(singleImageURL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                requestCompleted = true;
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (response.isSuccessful()) {
                        String data = response.body().string();
                        JSONObject jsonObject = new JSONObject(data);
                        JSONArray photos = jsonObject.getJSONObject("sizes").getJSONArray("size");
                        JSONObject obj1 = photos.getJSONObject(1);
                        String url = obj1.getString("source");
                        dataSet.get(index).setImageURL(url);
                        if(index == total-1){
                            requestCompleted = true;
                        }
                    }
                }catch(JSONException e){
                    System.out.println("error");
                }

            }
        });

    }

    private void connectToApi(String url) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                requestCompleted = true;
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (response.isSuccessful()) {
                        String data = response.body().string();
                        JSONObject jsonObject = new JSONObject(data);
                        JSONArray photos = jsonObject.getJSONObject("photos").getJSONArray("photo");
                        for(int i=0; i<photos.length() ; i++){
                            JSONObject obj1 = photos.getJSONObject(i);
                            String id = obj1.getString("id");
                            String title = obj1.getString("title");
                            FlickrImage img = new FlickrImage(id, title);
                            dataSet.add(img);
                            apiGetImage(id, i, photos.length());
                        }
                    }
                }catch(JSONException e){
                    System.out.println("error");
                }
            }
        });
    }

}
