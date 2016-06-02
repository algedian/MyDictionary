/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * 본 클래스는 다음 검색결과를 감싸는 추상클래스입니당. 다음 블로그, 이미지, 
 * 동영상, 웹문서 검색결과는 이 클래스를 상속받는다.
 * 
 * @author user
 */
@Stateless
@LocalBean
public class DaumSearchResult {
    //private Date lastBuildDate;//검색 결과를 생성한 시간이다. 안쓸래!
    private String category;
    private String result; //한 페이지에 출력될 결과수
    private String totalCount;//검색 결과 문서의 총 개수(추정치)
    private String pageCount; //보여줄 수 있는 문서의 수 (추정치)
    //private String start;//검색 결과 문서 중, 문서의 시작점을 의미한다.
    //private String display;//검색된 검색 결과의 개수이다.
    private ArrayList<HashMap<String, String>> searchItems;//item 하나는 개별 검색 결과이며, title, link 및 각 검색에 따른 property를 포함한다.
    
    
    public DaumSearchResult() {
        searchItems = new ArrayList<>();
    }

    //-
    public DaumSearchResult(String category) {
        this.category = category;
        searchItems = new ArrayList<>();
    }
    
    /**
     * - parse xml string and set values from extracted data.
     * 
     * @param jsonString 
     */
    public void parseJson(String jsonString){
        System.out.println("DaumSearchResult.constructor with jsonString");
        
        searchItems.clear();
        
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, Object>>(){}.getType();
        HashMap<String, Object> queryResult = gson.fromJson(jsonString, type);
                
        LinkedTreeMap<String, Object> channel = (LinkedTreeMap<String,Object>) queryResult.get("channel");
        
        for(Map.Entry<String, Object> entry:  channel.entrySet()) {
            //System.out.println(entry.getKey() + ": " + entry.getValue());
            if(entry.getKey().equalsIgnoreCase("result")){
                result = (String)(entry.getValue());
            }
            else if(entry.getKey().equalsIgnoreCase("totalCount")){
                totalCount = (String)(entry.getValue());
            }
            else if(entry.getKey().equalsIgnoreCase("pageCount")){
                pageCount = (String)(entry.getValue());
            }
            
            else if(entry.getKey().equalsIgnoreCase("item")){
                //System.out.println(entry.getValue());
                ArrayList<LinkedTreeMap<String, String>> itemlist = (ArrayList<LinkedTreeMap<String, String>>)entry.getValue();
                for(LinkedTreeMap<String, String> item: itemlist){
                    //LinkedTreeMap<String, String> item = itemlist.get(0);
                    System.out.println("itemlist size: " + itemlist.size());
                    HashMap<String,String> temp = new HashMap<>();
                    for(Map.Entry<String, String> entry2:  item.entrySet()) {
                        System.out.println(entry2.getKey() + ":: " + entry2.getValue());                    
                        temp.put(entry2.getKey(), entry2.getValue());                    
                    }             
                    searchItems.add(temp);
                    //System.out.println(temp);
                }
            }
        }
    }

    /**
     *
     * 최종 검색 결과를 담은 hashmap을 만들어 return하는 함수.
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
