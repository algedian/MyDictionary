package naver._general;

import java.util.ArrayList;

/**
 *
 * @author Yewon Kim
 */
public abstract class NaverSearch {
    private String query;//필수,검색을 원하는 문자열로서 UTF-8로 인코딩한다.
    private int display;//선택,10(기본값),100(최대)    검색 결과 출력 건수 지정
    private int start;//선택,1(기본값), 1000(최대)   검색 시작 위치로 최대 1000까지 가능.
    
    //Search Result object를 리턴하도록 수정해야함.
    public abstract NaverIngretedSearchResult search(String keyword);
}
