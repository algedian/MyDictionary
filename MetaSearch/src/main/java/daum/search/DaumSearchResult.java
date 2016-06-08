package daum.search;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * This abstract class wraps Daum search result. blog, image, video, web
 * documentation results extend this class
 *
 * @author kyeonghee
 */
@Stateless
@LocalBean
public class DaumSearchResult {

    //private Date lastBuildDate; //created time about search result. Do not use.
    private String category; //four category: blog, image, video, web
    private String result; //number of displayed results in one page
    private String totalCount; //whole count of result(estimation)
    private String pageCount; //number of available results (estimation)
    //private String start; //starting point in results //maybe not in daum api
    //private String display; //the number of searched result //maybe not in daum api
    private ArrayList<HashMap<String, String>> searchItems; //each item is one search result including title, link, property according to category.

    public DaumSearchResult() {
        searchItems = new ArrayList<>();
    }

    public DaumSearchResult(String category) {
        this.category = category;
        searchItems = new ArrayList<>();
    }

    /**
     * parse xml string and set values from extracted data.
     *
     * @param jsonString
     */
    public void parseJson(String jsonString) {
        System.out.println("DaumSearchResult.constructor with jsonString");

        searchItems.clear();

        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, Object>>() {
        }.getType();
        HashMap<String, Object> queryResult = gson.fromJson(jsonString, type);

        LinkedTreeMap<String, Object> channel = (LinkedTreeMap<String, Object>) queryResult.get("channel");
        //wrap over 'channel'

        for (Map.Entry<String, Object> entry : channel.entrySet()) {
            //System.out.println(entry.getKey() + ": " + entry.getValue());
            if (entry.getKey().equalsIgnoreCase("result")) {
                result = (String) (entry.getValue());
            } else if (entry.getKey().equalsIgnoreCase("totalCount")) {
                totalCount = (String) (entry.getValue());
            } else if (entry.getKey().equalsIgnoreCase("pageCount")) {
                pageCount = (String) (entry.getValue());
            } else if (entry.getKey().equalsIgnoreCase("item")) {
                //System.out.println(entry.getValue());
                ArrayList<LinkedTreeMap<String, String>> itemlist = (ArrayList<LinkedTreeMap<String, String>>) entry.getValue();
                for (LinkedTreeMap<String, String> item : itemlist) {
                    //LinkedTreeMap<String, String> item = itemlist.get(0);
                    //System.out.println("itemlist size: " + itemlist.size());
                    HashMap<String, String> temp = new HashMap<>();
                    for (Map.Entry<String, String> entry2 : item.entrySet()) {
                        //System.out.println(entry2.getKey() + ":: " + entry2.getValue());                    
                        temp.put(entry2.getKey(), entry2.getValue());
                    }
                    searchItems.add(temp);
                    //System.out.println(temp);
                }
            }
        }
    }

    /**
     * makes hashmap about final search results and returns.
     *
     * @return HashMap: total search result hashmap
     */
    //-
    public HashMap getResultHashMap() {
        System.out.println("DaumSearchResult.getResultHashMap");
        HashMap map = new HashMap();
        map.put("totalCount", totalCount);
        map.put("pageCount", pageCount);
        map.put("result", result);
        map.put("items", searchItems);
        return map;
    }
}
