package com.example.imagegallery;

import android.view.LayoutInflater;
import android.view.View;

import com.example.imagegallery.adapters.ViewAdapter;
import com.example.imagegallery.models.FlickrImage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.mock;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

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