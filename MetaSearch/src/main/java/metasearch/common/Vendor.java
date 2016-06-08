package metasearch.common;

/**
 * Enum class about search API
 *
 * @author Yewon Kim
 */
public enum Vendor {

    NAVER("naver"),
    DAUM("daum"),
    YOUTUBE("youtube");

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
