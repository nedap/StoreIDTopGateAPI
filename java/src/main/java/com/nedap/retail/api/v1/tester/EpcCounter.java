package com.nedap.retail.api.v1.tester;

import java.util.HashMap;

/**
 * List of unique EPCs
 */
public class EpcCounter {
    private static HashMap epcList = new HashMap();
    
    public static void addEpc(String epc) {
        epcList.put(epc, true);
    }
    
    /**
     * @return Number of unique EPCs
     */
    public static int count() {
        return epcList.size();
    }
    
    /**
     * Returns entire list of EPCs
     */
    public static HashMap getEpcs() {
        return epcList;
    }
}
