package com.wego.api.parking.controller;

import com.wego.api.parking.dto.ParkingItemResponse;
import com.wego.api.parking.service.ParkingService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { ParkingController.class })
public class ParkingControllerTest {

	@Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private ParkingService parkingService;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Test
    public void givenFindAvailableParking_whenFindNearestParking_thenReturnJsonArray()
      throws Exception {

        List<ParkingItemResponse> parkingItemResponseList = new ArrayList<>();

        ParkingItemResponse parking1 = new ParkingItemResponse();
        parking1.setLatitude(1.37326);
        parking1.setLongitude(103.897);
        parking1.setAddress("BLK 101 JALAN DUSUN");
        parking1.setAvailable_lots(100L);
        parking1.setTotal_lots(200L);

        ParkingItemResponse parking2 = new ParkingItemResponse();
        parking1.setLatitude(1.3662370557741195);
        parking1.setLongitude(103.84304639719981);
        parking1.setAddress("BLK 206/207 ANG MO KIO STREET 22");
        parking1.setAvailable_lots(10L);
        parking1.setTotal_lots(20L);

        parkingItemResponseList.add(parking1);
        parkingItemResponseList.add(parking2);

    	when(parkingService.findNearestParkingList(1, 10, 1.3, 103.8)).thenReturn(parkingItemResponseList);
     
        mockMvc.perform(MockMvcRequestBuilders.get("/carparks/nearest?latitude=1.3&longitude=103.8&page=1&per_page=10")
          .contentType(contentType)
          )
          .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
          .andExpect(MockMvcResultMatchers.jsonPath("$.[0].address", Matchers.is("BLK 206/207 ANG MO KIO STREET 22")));
    }

    @Test
    public void givenFindAvailableParking_whenFindNearestParkingWithoutLatitudeLongitude_thenReturn400JsonArray()
            throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/carparks/nearest?longitude=103.8&page=1&per_page=10")
                .contentType(contentType))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}