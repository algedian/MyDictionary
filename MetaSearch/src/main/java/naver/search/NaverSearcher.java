package naver.search;


/**
 * 네이버 검색에 대한 추상클래스. 각 카테고리에 대한 Search 클래스들이 본 클래스를 상속받아 구현함.
 * 
 * @author Yewon Kim
 */
public abstract class NaverSearcher {
    
    protected String clientId = "LMjMuDEq1wjSCvRgP9KC";
    protected String clientSecret = "hhMIIS1Ci5";
    
    protected String query;//필수,검색을 원하는 문자열로서 UTF-8로 인코딩한다.
    protected int display = 10;//선택,10(기본값),100(최대)    검색 결과 출력 건수 지정
    protected int start = 1;//선택,1(기본값), 1000(최대)   검색 시작 위치로 최대 1000까지 가능.
    protected String url = "https://openapi.naver.com/v1/search/blog.xml?";
    
    //Search Result object를 리턴하도록 수정해야함.
    public abstract NaverSearchResult search(String keyword);
    
}
