package com.nedap.retail.api.v1.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SpecList {

    private final List<Spec> specs;

    public SpecList(final Spec ... specs) {
        this.specs = Arrays.asList(specs);
    }

    public List<Spec> getSpecs() {
        return specs;
    }

    @Override
    public String toString() {
        return String.format(
                "%d spec(s):%n%s", specs.size(), specs.stream().map(Spec::toString).collect(Collectors.joining("\n"))
        );
    }

}
