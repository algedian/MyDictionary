package metasearch.common;

/**
 * common attribute about search result
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
