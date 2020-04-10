package com.example.imagegallery.repository;

import com.example.imagegallery.BuildConfig;
import com.example.imagegallery.api.ApiResult;
import com.example.imagegallery.api.JsonPlaceHolderApi;
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
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FlickrImageRepository {

    private static FlickrImageRepository instance;
    private List<FlickrImage> dataSet = new ArrayList<>();
    private String key = BuildConfig.ApiKey;// in a real project properties wouldnt be committed
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private MutableLiveData<List<FlickrImage>> data;
    private MutableLiveData<Boolean> process;

    public static FlickrImageRepository getInstance(){
        if(instance == null){
            instance = new FlickrImageRepository();
        }
        return instance;
    }


    public MutableLiveData<Boolean> getProcess(){
        process = new MutableLiveData<>();
        process.setValue(true);
        return process;
    }

    public MutableLiveData<List<FlickrImage>> getEntries(){
        String url = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key="+key+"&tags=kitten&page=1&format=json&nojsoncallback=1/";
        apiGetData(url);
        data = new MutableLiveData<>();
        data.setValue(dataSet);
        System.out.println("returned");
        return data;
    }



    private void apiGetData(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        retrofit2.Call<ApiResult> call = jsonPlaceHolderApi.getApiImages("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=f9cc014fa76b098f9e82f1c288379ea1&tags=kitten&page=1&format=json&nojsoncallback=1/");
        call.enqueue(new retrofit2.Callback<ApiResult>() {
            @Override
            public void onResponse(retrofit2.Call<ApiResult> call, retrofit2.Response<ApiResult> response) {
                if (!response.isSuccessful()) {
                    System.out.println("here " + response.code());
                    return;
                }
                //success here
                ApiResult result = response.body();
                dataSet = result.getPhotos().getPhotos();
                //could speed this up using one request
                for(int i=0; i<dataSet.size() ; i++) {
                    getImages(dataSet.get(i).getImageId(), i, dataSet.size());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ApiResult> call, Throwable t) {
                System.out.println("here failure");
            }
        });
    }

        public void getImages(String id, final int index, final int maxindex){
        String singleImageURL = "https://api.flickr.com/services/rest/?method=flickr.photos.getSizes&api_key="+key+"&photo_id="+id+"&format=json&nojsoncallback=1";
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(singleImageURL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (response.isSuccessful()) {
                        String res = response.body().string();
                        JSONObject jsonObject = new JSONObject(res);
                        JSONArray photos = jsonObject.getJSONObject("sizes").getJSONArray("size");
                        JSONObject obj1 = photos.getJSONObject(1);
                        String url = obj1.getString("source");
                        //if largeImageExists then initialize it
                        if(photos.length() > 9) {
                            JSONObject obj2 = photos.getJSONObject(9);
                            String url2 = obj2.getString("source");
                            dataSet.get(index).setLargeImageURL(url2);
                        }

                        dataSet.get(index).setImageURL(url);
                        //post results
                        data.postValue(dataSet);
                        process.postValue(false);
                    }
                }catch(JSONException e){
                    System.out.println("error");
                }

            }
        });

    }


}
