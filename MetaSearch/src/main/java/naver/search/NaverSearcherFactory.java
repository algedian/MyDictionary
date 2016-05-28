/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naver.search;

/**
 *
 * @author Yewon Kim - Administrator
 */
public interface NaverSearcherFactory {
    public NaverSearcher getNaverSearcher(String category);
}
