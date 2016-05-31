package naver.search;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import metasearch.common.SearchCategory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * 본 클래스는 네이버 검색결과를 감싸는 추상클래스입니당. 네이버 블로그, 뉴스, 백과사전, 웹문서, 이미지 검색결과는 이 클래스를
 * 상속받는다.
 *
 * @author Yewon Kim
 */
@Stateless
@LocalBean
public class NaverSearchResult {

    //private Date lastBuildDate;//검색 결과를 생성한 시간이다. 안쓸래!
    private String category;
    private String total;//검색 결과 문서의 총 개수를 의미한다.
    private String start;//검색 결과 문서 중, 문서의 시작점을 의미한다.
    private String display;//검색된 검색 결과의 개수이다.
    private ArrayList<HashMap<String, String>> searchItems;//item 하나는 개별 검색 결과이며, title, link 및 각 검색에 따른 property를 포함한다.

    public NaverSearchResult() {
        searchItems = new ArrayList<>();
    }

    public NaverSearchResult(String category) {
        this.category = category;
        searchItems = new ArrayList<>();
    }

    /**
     *
     * - parse xml string and set values from extracted data
     *
     * @param xmlString
     */
    public void parseXml(String xmlString) {

        System.out.println("NaverSearchResult.constructor with xmlString");
        
        searchItems.clear();
        
        // xmlString으로 부터 input source 생성
        InputSource is = new InputSource(new StringReader(xmlString));

        Document document;

        try {
            // XML Document 객체 생성
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);

            // xpath 생성
            XPath xpath = XPathFactory.newInstance().newXPath();

            // total, start, display 가져오기
            total = (String) xpath.evaluate("/rss/channel/total", document, XPathConstants.STRING);
            start = (String) xpath.evaluate("/rss/channel/start", document, XPathConstants.STRING);
            display = (String) xpath.evaluate("/rss/channel/display", document, XPathConstants.STRING);

            // item list 가져오기
            NodeList items = (NodeList) xpath.evaluate("/rss/channel/item", document, XPathConstants.NODESET);

            System.out.println(items.getLength());

            for (int i = 0; i < items.getLength(); ++i) {
                Node node = items.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    searchItems.add(parseSearchItemList(element));
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException ex) {
            Logger.getLogger(NaverSearcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * - category에 따라 각기 다른 parse 함수를 호출해주는 함수 - call different parser functions
     * and get results by category.
     *
     * @param items
     * @return HashMap: one item hashmap
     */
    private HashMap<String, String> parseSearchItemList(Element element) {
        if (category.equals(SearchCategory.BLOG.getName())) {
            return parseBlogSearchItems(element);
        } else if (category.equals(SearchCategory.IMAGE.getName())) {
            return parseImageSearchItems(element);
        } else if (category.equals(SearchCategory.WEB.getName())) {
            return parseWebSearchItems(element);
        } else if (category.equals(SearchCategory.NEWS.getName())) {
            return parseNewsSearchItems(element);
        } else if (category.equals(SearchCategory.ENCYCLOPEDIA.getName())) {
            return parseEncyclopediaSearchItems(element);
        } else {
            System.out.println("[XML Parse Error] No such search category in NAVER:" + category);
            return null;
        }
    }

    /**
     *
     * blog search item으로 parse 하여 hashmap에 저장해주는 함수
     *
     * @param items
     * @return HashMap: one blog item map
     */
    private HashMap<String, String> parseBlogSearchItems(Element element) {

        String title = element.getElementsByTagName("title").item(0).getTextContent();//검색 결과 문서의 제목
        String link = element.getElementsByTagName("link").item(0).getTextContent();//검색 결과 문서의 하이퍼텍스트 link
        String description = element.getElementsByTagName("description").item(0).getTextContent();//검색 결과 문서의 내용을 요약한 패시지 정보
        String bloggername = element.getElementsByTagName("bloggername").item(0).getTextContent();//검색 결과 블로그 포스트를 작성한 블로거의 이름
        String bloggerlink = element.getElementsByTagName("bloggerlink").item(0).getTextContent();//검색 결과 블로그 포스트를 작성한 블로거의 하이퍼텍스트 link

        HashMap<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("link", link);
        map.put("description", description);
        map.put("bloggername", bloggername);
        map.put("bloggerlink", bloggerlink);

        return map;
    }

    /**
     *
     * news search item으로 parse 하여 hashmap에 저장해주는 함수
     *
     * @param items
     * @return HashMap: one news item map
     */
    private HashMap<String, String> parseNewsSearchItems(Element element) {

        String title = element.getElementsByTagName("title").item(0).getTextContent();//검색 결과 문서의 제목
        String originallink = element.getElementsByTagName("originallink").item(0).getTextContent();//검색 결과 문서의 제공 언론사 하이퍼텍스트 link
        String link = element.getElementsByTagName("link").item(0).getTextContent();//검색 결과 문서의 하이퍼텍스트 link
        String description = element.getElementsByTagName("description").item(0).getTextContent();//검색 결과 문서의 내용을 요약한 패시지 정보
        String pubDate = element.getElementsByTagName("pubDate").item(0).getTextContent();//검색 결과 문서가 네이버에 제공된 시간

        HashMap<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("originallink", originallink);
        map.put("link", link);
        map.put("description", description);
        map.put("pubDate", pubDate);

        return map;
    }

    /**
     *
     * encyclopedia search item으로 parse 하여 hashmap에 저장해주는 함수
     *
     * @param items
     * @return HashMap: one encyclopedia item map
     */
    private HashMap<String, String> parseEncyclopediaSearchItems(Element element) {

        String title = element.getElementsByTagName("title").item(0).getTextContent();//검색 결과 사전 정의의 제목
        String link = element.getElementsByTagName("link").item(0).getTextContent();//사전 정의 정보 및 추가 정보를 볼 수 있는 link
        String description = element.getElementsByTagName("description").item(0).getTextContent();//검색 결과 문서의 내용을 요약한 패시지 정보
        String thumbnail = element.getElementsByTagName("thumbnail").item(0).getTextContent();//검색 결과에 이미지가 포함된 경우, 해당 이미지의 썸네일 link url

        HashMap<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("link", link);
        map.put("description", description);
        map.put("thumbnail", thumbnail);

        return map;
    }

    /**
     *
     * web search item으로 parse 하여 hashmap에 저장해주는 함수
     *
     * @param items
     * @return HashMap: one web item map
     */
    private HashMap<String, String> parseWebSearchItems(Element element) {

        String title = element.getElementsByTagName("title").item(0).getTextContent();//검색 결과 문서의 제목
        String link = element.getElementsByTagName("link").item(0).getTextContent();//검색 결과 문서의 하이퍼텍스트 link
        String description = element.getElementsByTagName("description").item(0).getTextContent();//검색 결과 문서의 내용을 요약한 패시지 정보

        HashMap<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("link", link);
        map.put("description", description);

        return map;
    }

    /**
     *
     * image search item으로 parse 하여 hashmap에 저장해주는 함수
     *
     * @param items
     * @return HashMap: one image item map
     */
    private HashMap<String, String> parseImageSearchItems(Element element) {

        String title = element.getElementsByTagName("title").item(0).getTextContent();//검색 결과 이미지의 제목
        String link = element.getElementsByTagName("link").item(0).getTextContent();//검색 결과 이미지의 하이퍼텍스트 link
        String thumbnail = element.getElementsByTagName("thumbnail").item(0).getTextContent();//검색 결과 이미지의 썸네일 link
        String sizeheight = element.getElementsByTagName("sizeheight").item(0).getTextContent();//검색 결과 이미지의 썸네일 높이(pixel)
        String sizewidth = element.getElementsByTagName("sizewidth").item(0).getTextContent();//검색 결과 이미지의 썸네일 너비(pixel)

        HashMap<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("link", link);
        map.put("thumbnail", thumbnail);
        map.put("sizeheight", sizeheight);
        map.put("sizewidth", sizewidth);

        return map;
    }

    /**
     *
     * 최종 검색 결과를 담은 hashmap을 만들어 return하는 함수.
     *
     * @return HashMap: total search result hashmap
     */
    public HashMap getResultHashMap() {
        System.out.println("NaverSearchResult.getResultHashMap");
        HashMap map = new HashMap();
        map.put("total", total);
        map.put("start", start);
        map.put("display", display);
        map.put("items", searchItems);
        return map;
    }
}
