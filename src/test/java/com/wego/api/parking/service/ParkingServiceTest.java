package com.wego.api.parking.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import com.wego.api.parking.model.Parking;
import com.wego.api.parking.repositories.ParkingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;



/**
 * @author huynhvang
 *
 * Unit tesing for parking service
 * */
@RunWith(MockitoJUnitRunner.class)
public class ParkingServiceTest {

    @Mock
    private ParkingRepository parkingRepository;

    @InjectMocks
    ParkingServiceImpl parkingServiceImpl;

    @Test
    public void testFindNearestParkingListWithAvailableData() {
        List<Parking> parkingList = new ArrayList<>();

        Parking parking1 = new Parking();
        parking1.setId(1L);
        parking1.setxCoord(1.37326);
        parking1.setyCoord(103.897);
        parking1.setAddress("BLK 101 JALAN DUSUN");
        parking1.setLotsAvailable(100L);
        parking1.setTotalLots(200L);

        Parking parking2 = new Parking();
        parking1.setId(2L);
        parking1.setxCoord(1.3662370557741195);
        parking1.setyCoord(103.84304639719981);
        parking1.setAddress("BLK 206/207 ANG MO KIO STREET 22");
        parking1.setLotsAvailable(10L);
        parking1.setTotalLots(20L);

        parkingList.addAll(Arrays.asList(parking1, parking2));

        when(parkingRepository.findNearestParkingList(1, 10, 1.3, 103.8)).thenReturn(parkingList);
        assertNotNull("Not null value of parking repository", parkingRepository.findNearestParkingList(1, 10, 1.3, 103.8));
        assertEquals("Parking repository return only 2 items", 2, parkingRepository.findNearestParkingList(1, 10, 1.3, 103.8).size());

        // check for parking service implement
        assertNotNull("parking service not return null value", parkingServiceImpl.findNearestParkingList(1, 10, 1.3, 103.8));
        assertNotNull("parking service return 2 items", parkingServiceImpl.findNearestParkingList(1, 10, 1.3, 103.8).size());
    }

    @Test
    public void testFindNearestParkingListWithNullValueReturnFromRepository() {
        when(parkingRepository.findNearestParkingList(1, 10, 1.3, 103.8)).thenReturn(null);
        assertNull("parking repository return null value", parkingRepository.findNearestParkingList(1, 10, 1.3, 103.8));

        // check for parking service implement
        assertNotNull("parking service not return null value", parkingServiceImpl.findNearestParkingList(1, 10, 1.3, 103.8));
        assertEquals("parking service return 2 items", 0, parkingServiceImpl.findNearestParkingList(1, 10, 1.3, 103.8).size());
    }
}
