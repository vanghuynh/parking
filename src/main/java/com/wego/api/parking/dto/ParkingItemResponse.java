package com.wego.api.parking.dto;

public class ParkingItemResponse {
    private String address;
    private Double latitude;
    private Double longitude;
    private Long total_lots;
    private Long available_lots;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Long getTotal_lots() {
        return total_lots;
    }

    public void setTotal_lots(Long total_lots) {
        this.total_lots = total_lots;
    }

    public Long getAvailable_lots() {
        return available_lots;
    }

    public void setAvailable_lots(Long available_lots) {
        this.available_lots = available_lots;
    }
}
