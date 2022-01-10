package com.wego.api.parking.service;

import com.wego.api.parking.dto.CarparkResponse;
import com.wego.api.parking.dto.CoordinateConversionResponse;
import com.wego.api.parking.dto.ParkingItemResponse;
import com.wego.api.parking.model.Parking;
import com.wego.api.parking.repositories.ParkingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service("parkingService")
public class ParkingServiceImpl implements ParkingService {

	public static final Logger logger = LoggerFactory.getLogger(ParkingServiceImpl.class);

	@Autowired
	private ParkingRepository parkingRepository;


	@Override
	public Parking findById(Long id) {
		return parkingRepository.getById(id);
	}

	@Override
	public void saveParking(Parking parking) {
		parkingRepository.save(parking);
	}

	@Override
	public void updateParking(Parking parking) {
		parkingRepository.save(parking);
	}

	@Override
	public void deleteParkingById(Long id) {
		parkingRepository.deleteById(id);
	}

	@Override
	public List<Parking> findAllParkings() {
		return parkingRepository.findAll();
	}

	@Override
	public Object updateCarAvailability(String dateString) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			if(dateString == null || dateString.length() == 0){
				Date now = Date.from(Instant.now().minus(Duration.ofDays(10)));
				dateString = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(now);
			}
			HttpEntity<Object> request = new HttpEntity<>(headers);
			ResponseEntity<CarparkResponse> response = restTemplate
					.exchange("https://api.data.gov.sg/v1/transport/carpark-availability?date_time="+ dateString, HttpMethod.GET, request, CarparkResponse.class);
			CarparkResponse res = response.getBody();
			logger.info("Response Body Data: ", res);
			logger.info("First Item: ", res.getItems().get(0));
			res.getItems().get(0).getCarpark_data().forEach(carparkData -> {
				if(carparkData.getCarpark_info() != null && carparkData.getCarpark_info().size() > 0){
					String parkingNumber = carparkData.getCarpark_number();
					Parking parking = parkingRepository.findByCarParkNo(parkingNumber);
					if(parking != null){
						Long lotsAvailable = Long.valueOf(carparkData.getCarpark_info().get(0).getLots_available());
						Long totalLots = Long.valueOf(carparkData.getCarpark_info().get(0).getTotal_lots());
						parking.setLotsAvailable(lotsAvailable);
						parking.setTotalLots(totalLots);
						this.updateParking(parking);
					}
				}
			});
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error getting token " + e);
		}
		return null;
	}

	@Override
	public Object updateCoordinateFormat() {
		List<Parking> parkings = parkingRepository.findAll();
		if(parkings != null){
			parkings.forEach(parking -> {
				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<Object> request = new HttpEntity<>(headers);
				ResponseEntity<CoordinateConversionResponse> response = restTemplate
						.exchange("https://developers.onemap.sg/commonapi/convert/3414to4326?X="+ parking.getxCoord() +"&Y=" + parking.getyCoord(), HttpMethod.GET, request, CoordinateConversionResponse.class);
				CoordinateConversionResponse res = response.getBody();
				logger.info("Response Body Data: ", res);
				logger.info("First Item: ", res.getLatitude(), res.getLongitude());
				parking.setxCoord(res.getLatitude());
				parking.setyCoord(res.getLongitude());
				updateParking(parking);
				logger.info("Parking: ", parking);
			});
		}
		return parkingRepository.findAll();
	}

	@Override
	public List<ParkingItemResponse> findNearestParkingList(Integer page, Integer perPage, Double latitude, Double longitude) {
		List<Parking> parkingList = parkingRepository.findNearestParkingList(page, perPage, latitude, longitude);
		if(parkingList != null){
			return parkingList.stream().map(item -> {
				ParkingItemResponse resItem = new ParkingItemResponse();
				resItem.setAddress(item.getAddress());
				resItem.setLatitude(item.getxCoord());
				resItem.setLongitude(item.getyCoord());
				resItem.setAvailable_lots(item.getLotsAvailable());
				resItem.setTotal_lots(item.getTotalLots());
				return resItem;
			}).collect(Collectors.toList());
		}
		return new ArrayList<ParkingItemResponse>();
	}


}
