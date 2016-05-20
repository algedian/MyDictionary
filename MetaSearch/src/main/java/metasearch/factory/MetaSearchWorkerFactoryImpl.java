/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metasearch.factory;

import java.util.concurrent.CountDownLatch;
import javax.ejb.Stateless;
import metasearch.workers.MetaSearchWorker;
import metasearch.enums.Vendor;

/**
 *
 * @author Yewon Kim - Administrator
 */
@Stateless
public class MetaSearchWorkerFactoryImpl implements MetaSearchWorkerFactory {

    @Override
    public MetaSearchWorker getMetaSearchWorker(Vendor vendor, CountDownLatch latch) {

        if (vendor == Vendor.DAUM) {
            return null;
        } else if (vendor == Vendor.NAVER) {
            return null;
        } else if (vendor == Vendor.YOUTUBE) {
            return null;
        }

        return null;
    }
}
