package com.wego.api.parking.repositories;

import com.wego.api.parking.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author huynhvang
 *
 */
@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {

    Parking findByCarParkNo(String carParkNo);

    @Query(value = "SELECT * FROM (\n" +
            "    SELECT *, \n" +
            "        (\n" +
            "            (\n" +
            "                (\n" +
            "                    acos(\n" +
            "                        sin(( :latitude * pi() / 180))\n" +
            "                        *\n" +
            "                        sin(( p.x_coord * pi() / 180)) + cos(( :latitude * pi() /180 ))\n" +
            "                        *\n" +
            "                        cos(( p.x_coord * pi() / 180)) * cos((( :longitude - p.y_coord) * pi()/180)))\n" +
            "                ) * 180/pi()\n" +
            "            ) * 60 * 1.1515 * 1.609344\n" +
            "        )\n" +
            "    as distance FROM parking p\n" +
            ") parkingWithDistance\n" +
            "WHERE distance > 0 AND lots_available > 0 \n" +
            "ORDER BY distance \n" +
            "limit :perPage offset :page ", nativeQuery = true)
    List<Parking> findNearestParkingList(@Param("page") Integer page, @Param("perPage") Integer perPage, @Param("latitude")  Double latitude, @Param("longitude")  Double longitude);

}
