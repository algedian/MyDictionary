/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daum.searchImpl;

import daum.search.DaumSearchResult;
import daum.search.DaumSearcher;
import java.util.HashMap;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import metasearch.common.SearchCategory;

/**
 *
 * @author user
 */
@Stateless
@LocalBean
public class DaumVideoSearcher extends DaumSearcher{
    private String sort = "accuracy"; //accuracy(정확도순) | recency(최신글순)
    private String output = "json";
    //private String result; //한 페이지에 출력될 결과수
    //private String pageno; //검색 결과 페이지 번호
    
    public DaumVideoSearcher(){
        
    }
        
    @Override
    public HashMap search(String keyword) {
        System.out.println("DaumVideoSearcher.search");
        
        url += "vclip?apikey=" + apiKey + "" + "&output=" + output + "";
        
        daumSearchResult = new DaumSearchResult(SearchCategory.VIDEO.getName());
        
        daumSearchResult.parseJson(requestToDaum(keyword));
                
        HashMap map = daumSearchResult.getResultHashMap();
        
        System.out.println("DaumVideoSearcher returns");
        return map; 
    }
    
}
