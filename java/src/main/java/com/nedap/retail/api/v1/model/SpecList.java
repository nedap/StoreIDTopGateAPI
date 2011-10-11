package com.nedap.retail.api.v1.model;

import java.util.List;

public class SpecList {

    private List<Spec> specs;

    public List<Spec> getSpecs() {
        return specs;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(specs.size());
        result.append(" spec(s):\n");
        for (Spec s : specs) {
            result.append(s.toString());
            result.append("\n");
        }
        return result.toString();
    }

}
