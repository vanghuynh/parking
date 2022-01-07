package com.wego.api.parking.dto;

import java.util.ArrayList;
import java.util.List;

public class CarparkItem {
    private String timestamp;
    private List<CarparkData> carpark_data = new ArrayList<>();

    public CarparkItem() {
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<CarparkData> getCarpark_data() {
        return carpark_data;
    }

    public void setCarpark_data(List<CarparkData> carpark_data) {
        this.carpark_data = carpark_data;
    }
}
