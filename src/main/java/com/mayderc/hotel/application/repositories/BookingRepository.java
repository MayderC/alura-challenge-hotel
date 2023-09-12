package com.mayderc.hotel.application.repositories;

import com.mayderc.hotel.application.entities.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



public interface BookingRepository extends JpaRepository<Bookings, Integer> {
    Optional<Bookings> findBookingsByCustomer_Id(Integer id);

}
