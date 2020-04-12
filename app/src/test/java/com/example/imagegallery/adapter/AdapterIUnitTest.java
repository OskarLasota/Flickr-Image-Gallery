package com.example.imagegallery.adapter;

import com.example.imagegallery.BuildConfig;
import com.example.imagegallery.MainActivity;
import com.example.imagegallery.adapters.ViewAdapter;
import com.example.imagegallery.models.FlickrImage;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class AdapterIUnitTest {

    @Test
    public void initializing_recycler_adapter(){
        FlickrImage img1 = new FlickrImage("id", "title1");
        List<FlickrImage> list = new ArrayList<FlickrImage>();
        list.add(img1);
        MainActivity activity = mock(MainActivity.class);
        ViewAdapter loginPresenter = new ViewAdapter(activity, list);
        Assert.assertEquals(1, loginPresenter.getItemCount());
    }

}