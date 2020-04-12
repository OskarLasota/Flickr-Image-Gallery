package com.example.imagegallery.api;

import com.example.imagegallery.BuildConfig;
import com.example.imagegallery.MainActivity;
import com.example.imagegallery.adapters.ViewAdapter;
import com.example.imagegallery.models.FlickrImage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

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

public class APIUnitTest {

    private final String key = BuildConfig.ApiKey;
    private final String keyword = "kitten";


    @Test
    public void checking_api_availability() throws Exception{
        InputStream response = createConnection(
                "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key="+key+"&tags="+keyword+"&page=1&format=json&nojsoncallback=1");

        assert convertResponse(response).length() > 0;
    }

    @Test
    public void checking_apikey_availability() throws Exception{
        String randomstring = "sfsgasdf%";
        InputStream response = createConnection(
                "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key="+key+randomstring+"&tags="+keyword+"&page=1&format=json&nojsoncallback=1");

        assertTrue(convertResponse(response).contains("Invalid API Key (Key has invalid format)"));
    }


    private InputStream createConnection(String url) throws Exception{
        URLConnection connection = new URL(url).openConnection();
        return connection.getInputStream();
    }

    private String convertResponse(InputStream stream) throws Exception{
        StringBuffer buffer = new StringBuffer();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, Charset.defaultCharset()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                buffer.append(line);
            }
        }
        return buffer.toString();
    }


}