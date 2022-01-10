package com.wego.api.parking.controller;

import com.wego.api.parking.model.Parking;
import com.wego.api.parking.service.ParkingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carparks")
public class ParkingController {

	public static final Logger logger = LoggerFactory.getLogger(ParkingController.class);

	@Autowired
	ParkingService parkingService;

	@RequestMapping(value = "/update-car-availability", method = RequestMethod.GET)
	public ResponseEntity<Object> updateCarAvailability(@RequestParam String dateString) {
		Object res = parkingService.updateCarAvailability(dateString);
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}

	@RequestMapping(value = "/update-coordinate-format", method = RequestMethod.GET)
	public ResponseEntity<Object> updateCoordinateFormat() {
		Object res = parkingService.updateCoordinateFormat();
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}

	@RequestMapping(value = "/nearest", method = RequestMethod.GET)
	public ResponseEntity<Object> findNearestPath(@RequestParam int page, @RequestParam("per_page") int per_page, @RequestParam String latitude, @RequestParam String longitude) {
		Object res = parkingService.findNearestParkingList(page, per_page, Double.valueOf(latitude), Double.valueOf(longitude));
		if(latitude == null || longitude == null){
			return new ResponseEntity<Object>("latitude and longitude must be available", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}

}