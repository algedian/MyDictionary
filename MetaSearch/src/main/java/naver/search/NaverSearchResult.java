package naver.search;

import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Yewon Kim
 * 본 클래스는 네이버 검색결과를 감싸는 추상클래스입니당.
 * 네이버 블로그, 뉴스, 백과사전, 웹문서, 이미지 검색결과는 이 클래스를 상속받는다.
 */
@Stateless
@LocalBean
public class NaverSearchResult {
    private Date lastBuildDate;//검색 결과를 생성한 시간이다.
    private int total;//검색 결과 문서의 총 개수를 의미한다.
    private int start;//검색 결과 문서 중, 문서의 시작점을 의미한다.
    private int display;//검색된 검색 결과의 개수이다.
    private ArrayList<NaverSearchItem> items;//item 하나는 개별 검색 결과이며, title, link 및 각 검색에 따른 property를 포함한다.

    public Date getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(Date lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public ArrayList<NaverSearchItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<NaverSearchItem> items) {
        this.items = items;
    }
    
}
