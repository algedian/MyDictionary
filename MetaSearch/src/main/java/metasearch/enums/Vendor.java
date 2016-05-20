package metasearch.enums;

/**
 *
 * @author Yewon Kim - Administrator 검색 api 에 대한 enum 클래스
 */
public enum Vendor {

    NAVER("naver"), DAUM("daum"), YOUTUBE("youtube");
    
    public static final int size = Vendor.values().length;
    
    private String name;

    Vendor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
