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
@RequestMapping("/parking")
public class ParkingController {

	public static final Logger logger = LoggerFactory.getLogger(ParkingController.class);

	@Autowired
	ParkingService parkingService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Parking>> listAllParkings() {
		List<Parking> parkings = parkingService.findAllParkings();
		if (parkings.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Parking>>(parkings, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getParking(@PathVariable("id") Long id) {
		logger.info("Fetching Parking with id {}", id);
		Parking parking = parkingService.findById(id);
		if (parking == null) {
			logger.error("Parking with id {} not found.", id);
			return new ResponseEntity("Parking with id " + id
					+ " not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Parking>(parking, HttpStatus.OK);
	}

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

}