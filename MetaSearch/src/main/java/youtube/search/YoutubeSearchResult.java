/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youtube.search;

import java.util.ArrayList;
import java.util.HashMap;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Aziz
 */
@Stateless
@LocalBean
public class YoutubeSearchResult {

    private String totalResults;
    private String resultsPerPage;
    private String regionCode;
    private ArrayList<HashMap<String, String>> searchItems;
    
    //// Statistics part
    // long viewCount, likeCount, dislikeCount, favoriteCount, commentCount

    //// Status part - I think these we will not need
    // string uploadStatus, failureReason, rejectionReason, privacyStatus


    public YoutubeSearchResult() {   
             searchItems = new ArrayList<>();
    }
    
    
    public void ParseJson (String jsonString) {
        
        searchItems.clear();

        // JSON.simple parser
        JSONParser parser = new JSONParser();
        JSONObject jsonObjWhole = new JSONObject();

        JSONArray jsonArrItems = new JSONArray();
        JSONObject jsonObjPageInfo = new JSONObject();

        try {
            if (jsonString != null) {
                    jsonObjWhole = (JSONObject) parser.parse(jsonString);

                    regionCode = (String) jsonObjWhole.get("regionCode");
                    System.out.println("Region Code " + regionCode); // test

                    jsonObjPageInfo = (JSONObject) jsonObjWhole.get("pageInfo");
                    totalResults = String.valueOf(jsonObjPageInfo.get("totalResults"));
                    resultsPerPage = String.valueOf(jsonObjPageInfo.get("resultsPerPage"));
                    System.out.println("Total results: " + totalResults + ", result per page: " + resultsPerPage); // test
                    System.out.println("----------------------------------------------------------------------------------------------------------------");
                    jsonArrItems = (JSONArray) jsonObjWhole.get("items");
                    // System.out.println(item);

                    for (int i = 0; i < jsonArrItems.size(); i++) {
                            searchItems.add(parseSearchedItemList(jsonArrItems.get(i)));
                        }
                }
        }
        catch (ParseException e){
                e.printStackTrace();
            }
    }
    
    private HashMap<String, String> parseSearchedItemList(Object objectSearchedItem){

            JSONObject jsonObjItem = (JSONObject)objectSearchedItem;				

            JSONObject jsonObjId = (JSONObject)jsonObjItem.get("id");				
            String videoId = (String)jsonObjId.get("videoId");

            JSONObject jsonObjSnippet = (JSONObject)jsonObjItem.get("snippet");	

            String videoTitle = (String)jsonObjSnippet.get("title");
            String channelId = (String)jsonObjSnippet.get("channelId");
            String channelTitle = (String)jsonObjSnippet.get("channelTitle");
            String publishedAt = (String)jsonObjSnippet.get("publishedAt");
            String description = (String)jsonObjSnippet.get("description");

            JSONArray jsonObjThumbnails = (JSONArray)jsonObjItem.get("thumbnails");	
//	    JSONObject jsonObjThumbDefault = (JSONObject)jsonObjThumbnails.get(0); 	 //Need to fix NullPointerException
//	    String thumbnailUrl = (String)jsonObjThumbDefault.get("url");

            HashMap<String, String> map = new HashMap<>();

            map.put("videoId", videoId);
            map.put("videoTitle", videoTitle);
            map.put("channelId", channelId);
            map.put("channelTitle", channelTitle);
            map.put("publishedAt", publishedAt);				
            map.put("description", description);
            //map.put("thumbnailUrl", thumbnailUrl);

            System.out.println("videoId: " + videoId + ", videoTitle: " + videoTitle + ",\n channelId: " + channelId + ", channelTitle: " +
                            channelTitle + ",\n publishedAt: " + publishedAt +  ",\n description: " + description ); //", thumbnailUrl: " + thumbnailUrl				
            System.out.println("----------------------------------------------------------------------------------------------------------------");

            return map;
	}
    
    
    public HashMap getResultHashMap() {
        System.out.println("YoutubeSearchResult.getResultHashMap");
        HashMap map = new HashMap();
        map.put("totalResults", totalResults);
        map.put("resultsPerPage", resultsPerPage);
        map.put("resultsPerPage", resultsPerPage);
        map.put("items", searchItems);
        return map;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
