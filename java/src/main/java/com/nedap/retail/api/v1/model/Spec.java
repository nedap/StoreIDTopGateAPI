package com.nedap.retail.api.v1.model;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Spec {

    private final Integer id;
    private final String name;
    private final String[] eventTypes;

    public Spec(final Integer id, final String name, final String ... eventTypes) {
        this.id = id;
        this.name = name;
        this.eventTypes = eventTypes;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String[] getEventTypes() {
        return eventTypes;
    }

    @Override
    public String toString() {
        return String.format(
                "id = %d%nname = %s%nevent types = %s", this.id, this.name,
                Optional.ofNullable(eventTypes)
                        .map(eventTypes -> String.join("", eventTypes) + " ")
                        .orElse("")
        );
    }
}
