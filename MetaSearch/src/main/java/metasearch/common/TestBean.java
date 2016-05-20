/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metasearch.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import metasearch.enums.SearchCategory;
import metasearch.enums.Vendor;

/**
 *
 * @author Yewon Kim - Administrator
 */
@XmlRootElement
public class TestBean {

    List<List<SearchResultBean>> result = new ArrayList<List<SearchResultBean>>();

    public TestBean() {
        List<SearchResultBean> list1 = new ArrayList<SearchResultBean>();
        SearchResultBean bean1 = new SearchResultBean();
        bean1.setVendor(Vendor.NAVER);
        bean1.setCategory(SearchCategory.BLOG);
        bean1.setTitle("bean1");
        bean1.setLink("link1");
        
        SearchResultBean bean2 = new SearchResultBean();
        bean2.setVendor(Vendor.NAVER);
        bean2.setCategory(SearchCategory.BLOG);
        bean2.setTitle("bean2");
        bean2.setLink("link2");
        
        list1.add(bean2);
        list1.add(bean1);
        
        List<SearchResultBean> list2 = new ArrayList<SearchResultBean>();
        list2.add(bean1);
        list2.add(bean2);
        
        result.add(list1);
        result.add(list2);
    }

    public List<List<SearchResultBean>> getResult() {
        return result;
    }

    public void setResult(List<List<SearchResultBean>> result) {
        this.result = result;
    }

}
