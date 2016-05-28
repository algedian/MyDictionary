/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metasearch.common;

/**
 *
 * @author Yewon Kim - Administrator
 */
public class SearchResultBean {

    private Vendor vendor;
    private SearchCategory category;
    private String title;
    private String link;

    public SearchResultBean() {

    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public SearchCategory getCategory() {
        return category;
    }

    public void setCategory(SearchCategory category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


}
