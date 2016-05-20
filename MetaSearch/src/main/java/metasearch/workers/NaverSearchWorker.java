/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metasearch.workers;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import metasearch.enums.SearchCategory;
import metasearch.enums.Vendor;

/**
 *
 * @author Yewon Kim - Administrator
 */
public class NaverSearchWorker extends MetaSearchWorker {

    public NaverSearchWorker(CountDownLatch latch, String keyword) {
        super(latch, keyword);
        this.setVendor(Vendor.NAVER);
    }

    @Override
    public void doSearch(String keyword) {
        
        HashMap<SearchCategory, HashMap> map = new HashMap<SearchCategory, HashMap>();
        
        //각각의 검색결과를 받아온 뒤에 그 결과값을 넣어줌
        map.put(SearchCategory.BLOG, new HashMap());
        
        super.setSearchResult(map);
    }
}
