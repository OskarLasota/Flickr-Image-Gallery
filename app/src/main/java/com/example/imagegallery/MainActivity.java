package com.example.imagegallery;

import android.os.Bundle;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    //in a real project, gradle.properties would not be comitted to github
    private String key = BuildConfig.ApiKey;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key="+key+"&tags=kitten&page=1&format=json&nojsoncallback=1";
        connectToApi(url);


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
