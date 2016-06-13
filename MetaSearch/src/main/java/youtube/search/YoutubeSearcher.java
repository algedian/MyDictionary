package youtube.search;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;

import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Aziz
 */
public class YoutubeSearcher {

    //My own API KEY
    private String API_KEY = "AIzaSyApIPCzmpNCyUE029uMK9tFt_d4D9hIWlE";

    private final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private final JsonFactory JSON_FACTORY = new JacksonFactory();

    //upper limit per page = 50
    private final long MAX_RETURNED_SIZE = 20;  //should be long type

    // Global instance of Youtube object to make all API requests
    private YouTube youtube;

    YoutubeSearchResult result = null;

    public YoutubeSearcher() {

    }

    public HashMap search(String keyword) {
        System.out.println("YoutubeSearcher.search");

        result = new YoutubeSearchResult();

        result.ParseJson(requestYoutube(keyword));

        HashMap map = result.getResultHashMap();

        System.out.println("YoutubeSearcher returns");

        return map;

    }

    public String requestYoutube(String keyword) { //currently we get the result as list

        String resultJsonString = "";

        try {
            /*
             * The YouTube object is used to make all API requests. The last argument is required, but
             * because we don't need anything initialized when the HttpRequest is initialized, we override
             * the interface and provide a no-op function.
             */
            youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("MetaSearch_YoutubeSearch").build();

            YouTube.Search.List search = youtube.search().list("id,snippet");

            search.setKey(API_KEY);
            search.setQ(keyword);
            search.setType("video"); //this is our category.  Could be playlist,channel,...

            ////select necessary fields to make calls more efficient
            //search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)"); //in that case:  kind, videoId, title, thumbnails(default)
            search.setMaxResults(MAX_RETURNED_SIZE);

            SearchListResponse searchResponse = search.execute();

            resultJsonString = searchResponse.toString();
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }

        return resultJsonString;

    }
}
