/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daum.search;

import daum.searchImpl.DaumBlogSearcher;
import daum.searchImpl.DaumImageSearcher;
import daum.searchImpl.DaumVideoSearcher;
import daum.searchImpl.DaumWebSearcher;
import metasearch.common.SearchCategory;

/**
 * - implementation of DaumSearcherFactory
 * 
 * @author user
 */
public class DaumSearcherFactoryImpl implements DaumSearcherFactory{

    /**
     * - returns specific DaumSearcher class by category.
     *
     * @param category
     * @return NaverSearcher: specific searcher object
     */
    @Override
    public DaumSearcher getDaumSearcher(String category) {
        System.out.println("DaumSearcherFactory.getDaumSearcher");

        if (category.equals(SearchCategory.BLOG.getName())) {
            return new DaumBlogSearcher();
        } else if (category.equals(SearchCategory.IMAGE.getName())) {
            return new DaumImageSearcher();
        } else if (category.equals(SearchCategory.WEB.getName())) {
            return new DaumWebSearcher();
        } else if (category.equals(SearchCategory.VIDEO.getName())) {
            return new DaumVideoSearcher();
        } else {
            System.out.println("No such search category in DAUM:" + category);
            return null;
        }
    }
    
}
