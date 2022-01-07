package com.wego.api.parking.dto;

import java.util.ArrayList;
import java.util.List;

public class CarparkResponse {
    private List<CarparkItem> items = new ArrayList<>();

    public CarparkResponse() {
    }

    public List<CarparkItem> getItems() {
        return items;
    }

    public void setItems(List<CarparkItem> items) {
        this.items = items;
    }
}
