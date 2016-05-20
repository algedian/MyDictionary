/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naver.blog;
import naver._general.NaverSearchItem;

/**
 *
 * @author Yewon Kim - Administrator
 */
public class NaverBlogSearchItem extends NaverSearchItem {
    private String description;//검색 결과 문서의 내용을 요약한 패시지 정보이다. 문서 전체의 내용은 link를 따라가면, 읽을 수 있다. 
    private String bloggerName;//검색 결과 블로그 포스트를 작성한 블로거의 이름이다.
    private String bloggerLink;//검색 결과 블로그 포스트를 작성한 블로거의 하이퍼텍스트 link이다.

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBloggerName() {
        return bloggerName;
    }

    public void setBloggerName(String bloggerName) {
        this.bloggerName = bloggerName;
    }

    public String getBloggerLink() {
        return bloggerLink;
    }

    public void setBloggerLink(String bloggerLink) {
        this.bloggerLink = bloggerLink;
    }
    
}
