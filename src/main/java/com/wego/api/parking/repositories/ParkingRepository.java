package com.wego.api.parking.repositories;

import com.wego.api.parking.model.Parking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author huynhvang
 *
 */
@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {

    Parking findByCarParkNo(String carParkNo);

}
