package com.wego.api.parking.service;

import com.wego.api.parking.model.Parking;

import java.util.List;

public interface ParkingService {
	
	Parking findById(Long id);

	void saveParking(Parking parking);

	void updateParking(Parking parking);

	void deleteParkingById(Long id);

	List<Parking> findAllParkings();

	Object updateCarAvailability(String dateString);

	Object updateCoordinateFormat();
	
}