package com.wego.api.parking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
@Table(name="parking")
public class Parking implements Serializable {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="car_park_no")
	private String carParkNo;

	@Column(name="address")
	private String address;

	@Column(name="x_coord")
	private Double xCoord;
	
	@Column(name="y_coord")
	private Double yCoord;
	
	@Column(name="car_park_type")
	private String carParkType;
	
	@Column(name="type_of_parking_system")
	private String typeOfParkingSystem;
	
	@Column(name="short_term_parking")
	private String shortTermParking;
	
	@Column(name="free_parking")
	private String freeParking;
	
	@Column(name="night_parking")
	private String nightParking;
	
	@Column(name="car_park_decks")
	private String carParkDecks;

	@Column(name="gantry_height")
	private String gantryHeight;

	@Column(name="car_park_basement")
	private String carParkBasement;

	@Column(name="total_lots")
	private Long totalLots;

	@Column(name="lots_available")
	private Long lotsAvailable;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCarParkNo() {
		return carParkNo;
	}

	public void setCarParkNo(String carParkNo) {
		this.carParkNo = carParkNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getxCoord() {
		return xCoord;
	}

	public void setxCoord(Double xCoord) {
		this.xCoord = xCoord;
	}

	public Double getyCoord() {
		return yCoord;
	}

	public void setyCoord(Double yCoord) {
		this.yCoord = yCoord;
	}

	public String getCarParkType() {
		return carParkType;
	}

	public void setCarParkType(String carParkType) {
		this.carParkType = carParkType;
	}

	public String getTypeOfParkingSystem() {
		return typeOfParkingSystem;
	}

	public void setTypeOfParkingSystem(String typeOfParkingSystem) {
		this.typeOfParkingSystem = typeOfParkingSystem;
	}

	public String getShortTermParking() {
		return shortTermParking;
	}

	public void setShortTermParking(String shortTermParking) {
		this.shortTermParking = shortTermParking;
	}

	public String getFreeParking() {
		return freeParking;
	}

	public void setFreeParking(String freeParking) {
		this.freeParking = freeParking;
	}

	public String getNightParking() {
		return nightParking;
	}

	public void setNightParking(String nightParking) {
		this.nightParking = nightParking;
	}

	public String getCarParkDecks() {
		return carParkDecks;
	}

	public void setCarParkDecks(String carParkDecks) {
		this.carParkDecks = carParkDecks;
	}

	public String getGantryHeight() {
		return gantryHeight;
	}

	public void setGantryHeight(String gantryHeight) {
		this.gantryHeight = gantryHeight;
	}

	public String getCarParkBasement() {
		return carParkBasement;
	}

	public void setCarParkBasement(String carParkBasement) {
		this.carParkBasement = carParkBasement;
	}

	public Long getTotalLots() {
		return totalLots;
	}

	public void setTotalLots(Long totalLots) {
		this.totalLots = totalLots;
	}

	public Long getLotsAvailable() {
		return lotsAvailable;
	}

	public void setLotsAvailable(Long lotsAvailable) {
		this.lotsAvailable = lotsAvailable;
	}
}