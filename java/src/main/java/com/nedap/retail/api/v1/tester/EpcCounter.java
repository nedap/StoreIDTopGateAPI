package com.nedap.retail.api.v1.tester;

import java.util.HashMap;

/**
 * List of unique EPCs
 */
public class EpcCounter {

    private static HashMap epcList = new HashMap();
    private static HashMap<String, HashMap<String, Boolean>> directionList = new HashMap<String, HashMap<String, Boolean>>();

    public static void addEpc(String epc) {
        epcList.put(epc, true);
    }

    public static void addEpcWithDirection(String epc, String direction) {
        HashMap<String, Boolean> map;
        if (directionList.containsKey(direction)) {
            map = directionList.get(direction);
        } else {
            map = new HashMap<String, Boolean>();
            directionList.put(direction, map);
        }
        map.put(epc, true);
    }

    /**
     * @return Number of unique EPCs
     */
    public static int count() {
        return epcList.size();
    }

    public static int countForDirection(String direction) {

        if (directionList.containsKey(direction)) {
            HashMap<String, Boolean> map = directionList.get(direction);
            return map.size();
        } else {
            return 0;
        }
    }

    /**
     * Returns entire list of EPCs
     */
    public static HashMap getEpcs() {
        return epcList;
    }

    public static HashMap<String, HashMap<String, Boolean>> getEpcsPerDirection() {
        return directionList;
    }
}
