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
 * This abstract class wraps Naver search result. naver blog, news,
 * encyclopedia, web, image searcher extend this class.
 *
 * @author Yewon Kim
 */
@Stateless
@LocalBean
public class NaverSearchResult {

    //private Date lastBuildDate; //created time about search result. Do not use.
    private String category;
    private String total; //whole count of result(estimation)
    private String start; //starting point in results
    private String display; //the number of searched result
    private ArrayList<HashMap<String, String>> searchItems; //each item is one search result including title, link, property according to category.

    public NaverSearchResult() {
        searchItems = new ArrayList<>();
    }

    public NaverSearchResult(String category) {
        this.category = category;
        searchItems = new ArrayList<>();
    }

    /**
     * parse xml string and set values from extracted data
     *
     * @param xmlString
     */
    public void parseXml(String xmlString) {

        System.out.println("NaverSearchResult.constructor with xmlString");

        searchItems.clear();

        // make input source from xmlString
        InputSource is = new InputSource(new StringReader(xmlString));

        Document document;

        try {
            // create XML Document object
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);

            // create xpath 
            XPath xpath = XPathFactory.newInstance().newXPath();

            // get total, start, display 
            total = (String) xpath.evaluate("/rss/channel/total", document, XPathConstants.STRING);
            start = (String) xpath.evaluate("/rss/channel/start", document, XPathConstants.STRING);
            display = (String) xpath.evaluate("/rss/channel/display", document, XPathConstants.STRING);

            // get item list 
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
     * call different parser functions and get results by category.
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
     * Function that parses blog search item and saves to hashmap
     *
     * @param items
     * @return HashMap: one blog item map
     */
    private HashMap<String, String> parseBlogSearchItems(Element element) {

        String title = element.getElementsByTagName("title").item(0).getTextContent(); //title of search result document 
        String link = element.getElementsByTagName("link").item(0).getTextContent(); //hyperpext link of search result document 
        String description = element.getElementsByTagName("description").item(0).getTextContent(); //description of search result document 
        String bloggername = element.getElementsByTagName("bloggername").item(0).getTextContent(); //blogger name of search result document 
        String bloggerlink = element.getElementsByTagName("bloggerlink").item(0).getTextContent(); //blogger's hypertext link of search result document 

        HashMap<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("link", link);
        map.put("description", description);
        map.put("bloggername", bloggername);
        map.put("bloggerlink", bloggerlink);

        return map;
    }

    /**
     * Function that parses news search item and saves to hashmap
     *
     * @param items
     * @return HashMap: one news item map
     */
    private HashMap<String, String> parseNewsSearchItems(Element element) {

        String title = element.getElementsByTagName("title").item(0).getTextContent(); //title of search result document 
        String originallink = element.getElementsByTagName("originallink").item(0).getTextContent(); //provider's hyperpext link of search result document 
        String link = element.getElementsByTagName("link").item(0).getTextContent(); //hyperpext link of search result document 
        String description = element.getElementsByTagName("description").item(0).getTextContent(); //description of search result document 
        String pubDate = element.getElementsByTagName("pubDate").item(0).getTextContent(); //provided time of search result document

        HashMap<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("originallink", originallink);
        map.put("link", link);
        map.put("description", description);
        map.put("pubDate", pubDate);

        return map;
    }

    /**
     * Function that parses encyclopedia search item and saves to hashmap
     *
     * @param items
     * @return HashMap: one encyclopedia item map
     */
    private HashMap<String, String> parseEncyclopediaSearchItems(Element element) {

        String title = element.getElementsByTagName("title").item(0).getTextContent(); //title of search result document (dictionary definition) 
        String link = element.getElementsByTagName("link").item(0).getTextContent(); //link of search result document that provide definition's other info 
        String description = element.getElementsByTagName("description").item(0).getTextContent(); //description of search result document 
        String thumbnail = element.getElementsByTagName("thumbnail").item(0).getTextContent(); //thumbnail link url of search result document, if there is image in result

        HashMap<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("link", link);
        map.put("description", description);
        map.put("thumbnail", thumbnail);

        return map;
    }

    /**
     * Function that parses web search item and saves to hashmap
     *
     * @param items
     * @return HashMap: one web item map
     */
    private HashMap<String, String> parseWebSearchItems(Element element) {

        String title = element.getElementsByTagName("title").item(0).getTextContent(); //title of search result document 
        String link = element.getElementsByTagName("link").item(0).getTextContent(); //hyperpext link of search result document 
        String description = element.getElementsByTagName("description").item(0).getTextContent(); //description of search result document 

        HashMap<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("link", link);
        map.put("description", description);

        return map;
    }

    /**
     * Function that parses image search item and saves to hashmap
     *
     * @param items
     * @return HashMap: one image item map
     */
    private HashMap<String, String> parseImageSearchItems(Element element) {

        String title = element.getElementsByTagName("title").item(0).getTextContent(); //title of search result image
        String link = element.getElementsByTagName("link").item(0).getTextContent(); //hypertext link of search result image 
        String thumbnail = element.getElementsByTagName("thumbnail").item(0).getTextContent(); //thumbnail link of search result image 
        String sizeheight = element.getElementsByTagName("sizeheight").item(0).getTextContent(); //height of search result image (pixel)
        String sizewidth = element.getElementsByTagName("sizewidth").item(0).getTextContent(); //width of search result image (pixel)

        HashMap<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("link", link);
        map.put("thumbnail", thumbnail);
        map.put("sizeheight", sizeheight);
        map.put("sizewidth", sizewidth);

        return map;
    }

    /**
     * makes hashmap about final search results and returns.
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
