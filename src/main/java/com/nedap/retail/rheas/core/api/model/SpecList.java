package com.nedap.retail.rheas.core.api.model;

import java.util.List;

/**
 * Class that contains list of {@link Spec}.
 * 
 * @author Dusko Vesin
 * 
 */
public class SpecList {

    private List<Spec> specs;

    public List<Spec> getSpecs() {
        return specs;
    }

    public void setSpecs(final List<Spec> specs) {
        this.specs = specs;
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
