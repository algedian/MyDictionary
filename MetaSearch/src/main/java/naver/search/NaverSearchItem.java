package naver.search;

/**
 *
 * @author Yewon Kim
 * 본 클래스는 검색 결과들 중 개별 검색 결과 하나를 나타내는 추상 클래스임.
 */
public abstract class NaverSearchItem {
    private String title;
    private String link;

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
