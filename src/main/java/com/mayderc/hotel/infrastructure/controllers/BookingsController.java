package com.mayderc.hotel.infrastructure.controllers;

import com.mayderc.hotel.adapters.services.BookingsService;
import com.mayderc.hotel.application.dtos.AddBookingRequest;
import com.mayderc.hotel.infrastructure.dtos.BookingResponse;
import com.mayderc.hotel.infrastructure.dtos.SaveBooking;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
@AllArgsConstructor
public class BookingsController {

    private final BookingsService bookingsService;
    private final ModelMapper modelMapper;
    @PostMapping
    public BookingResponse saveBooking(SaveBooking request){
        return bookingsService.save(modelMapper.map(request, AddBookingRequest.class));
    }

    @GetMapping("/")
    public ResponseEntity<List<BookingResponse>> get(){
        var response = bookingsService.getBookings();
        return ResponseEntity.ok(response);
    }


}
