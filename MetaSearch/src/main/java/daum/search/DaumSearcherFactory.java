/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daum.search;

/**
 *  - Factory interface for DaumSearchers
 * @author user
 */
public interface DaumSearcherFactory {
    public DaumSearcher getDaumSearcher(String category);
}
