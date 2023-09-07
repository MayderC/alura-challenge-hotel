package com.mayderc.hotel.infrastructure.controllers;

import com.mayderc.hotel.adapters.services.BookingsService;
import com.mayderc.hotel.infrastructure.dtos.BookingResponse;
import com.mayderc.hotel.infrastructure.dtos.SaveBooking;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@AllArgsConstructor
public class BookingsController {

    private final BookingsService bookingsService;

    @PostMapping
    public BookingResponse saveBooking(SaveBooking request){

        return new BookingResponse();
    }

    @GetMapping
    public ResponseEntity<List<BookingResponse>> get(){
        var response = bookingsService.getBookings();
        return ResponseEntity.ok(response);
    }


}
