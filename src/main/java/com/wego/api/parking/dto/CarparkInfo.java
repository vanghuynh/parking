package com.wego.api.parking.dto;

public class CarparkInfo {
    private String total_lots;
    private String lot_type;
    private String lots_available;

    public CarparkInfo() {
    }

    public String getTotal_lots() {
        return total_lots;
    }

    public void setTotal_lots(String total_lots) {
        this.total_lots = total_lots;
    }

    public String getLot_type() {
        return lot_type;
    }

    public void setLot_type(String lot_type) {
        this.lot_type = lot_type;
    }

    public String getLots_available() {
        return lots_available;
    }

    public void setLots_available(String lots_available) {
        this.lots_available = lots_available;
    }
}
