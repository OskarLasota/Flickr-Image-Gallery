package com.example.imagegallery.models;

import com.example.imagegallery.adapters.ViewAdapter;
import com.example.imagegallery.models.FlickrImage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class FlickrImageUnitTest {

    private final String imageId = "id";
    private final String imageTitle = "kitty";
    private final String imageUrl = "https://live.staticflickr.com/5800/31456463045_5a0af4ddc8_q.jpg";
    private final String largeImageUrl = "https://live.staticflickr.com/5800/31456463045_5a0af4ddc8_b.jpg";
    private final byte[] imageBytes = "Any String you".getBytes();
    private final byte[] largeImageBytes = "Any String you want".getBytes();


    @Mock
    FlickrImage flickrImage;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Mockito.when(flickrImage.getImageId()).thenReturn(imageId);
        Mockito.when(flickrImage.getImageTitle()).thenReturn(imageTitle);
        Mockito.when(flickrImage.getImageUrl()).thenReturn(imageUrl);
        Mockito.when(flickrImage.getLargeImageURL()).thenReturn(largeImageUrl);
        Mockito.when(flickrImage.getImageByte()).thenReturn(imageBytes);
        Mockito.when(flickrImage.getLargeImageByte()).thenReturn(largeImageBytes);
    }


    @Test
    public void testImageId(){
        Mockito.when(flickrImage.getImageId()).thenReturn(imageId);
        Assert.assertEquals("id", flickrImage.getImageId());
    }

    @Test
    public void testImageTitle(){
        Mockito.when(flickrImage.getImageTitle()).thenReturn(imageTitle);
        Assert.assertEquals("kitty", flickrImage.getImageTitle());
    }

    @Test
    public void testImageUrl(){
        Mockito.when(flickrImage.getImageUrl()).thenReturn(imageUrl);
        Assert.assertEquals("https://live.staticflickr.com/5800/31456463045_5a0af4ddc8_q.jpg", flickrImage.getImageUrl());
    }

    @Test
    public void testLargeImageUrl(){
        Mockito.when(flickrImage.getLargeImageURL()).thenReturn(largeImageUrl);
        Assert.assertEquals("https://live.staticflickr.com/5800/31456463045_5a0af4ddc8_b.jpg", flickrImage.getLargeImageURL());
    }

    @Test
    public void testImageBytes(){
        Mockito.when(flickrImage.getImageByte()).thenReturn(imageBytes);
        boolean result = Arrays.equals("Any String you".getBytes(), flickrImage.getImageByte());
        Assert.assertEquals(true, result);
    }

    @Test
    public void testLargeImageBytes(){
        Mockito.when(flickrImage.getLargeImageByte()).thenReturn(largeImageBytes);
        boolean result = Arrays.equals("Any String you want".getBytes(), flickrImage.getLargeImageByte());
        Assert.assertEquals(true, result);
    }
}