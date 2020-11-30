package com.coremedia.blueprint.contenthub.adapters.typeform.service.model;

import java.util.List;

public class Forms {

    private int totalItems;
    private List<Form> items;

    public int getTotalItems() {
        return totalItems;
    }

    public List<Form> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Forms{" +
                "totalItems=" + totalItems +
                ", items=" + items +
                '}';
    }
}
