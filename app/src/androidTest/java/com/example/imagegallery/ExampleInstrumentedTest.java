package com.example.imagegallery;

import android.content.Context;

import com.example.imagegallery.adapters.ViewAdapter;
import com.example.imagegallery.models.FlickrImage;

import androidx.test.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.example.imagegallery", appContext.getPackageName());
    }

    @Test
    public void testOnImageClick(){
        //initialize the adapter
        FlickrImage img1 = new FlickrImage("id", "title1");
        List<FlickrImage> list = new ArrayList<FlickrImage>();
        list.add(img1);
        ViewAdapter loginPresenter = new ViewAdapter(activityTestRule.getActivity(), list);

        //

    }
}
