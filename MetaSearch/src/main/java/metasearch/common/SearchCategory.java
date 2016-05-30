package metasearch.common;

/**
 * 검색 카테고리에 대한 enum 클래스
 * 
 * @author Yewon Kim
 */
public enum SearchCategory {

    BLOG("blog"),
    ENCYCLOPEDIA("encyclopedia"),
    IMAGE("image"),
    NEWS("news"),
    VIDEO("video"),
    WEB("web");

    public static final int size = SearchCategory.values().length;

    private String name;

    SearchCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
