package com.mayderc.hotel.adapters.services;


import com.mayderc.hotel.application.dtos.AddBookingRequest;
import com.mayderc.hotel.application.entities.Bookings;
import com.mayderc.hotel.application.repositories.BookingRepository;
import com.mayderc.hotel.infrastructure.dtos.BookingResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingsService {

    private final ModelMapper modelMapper;

    private final BookingRepository bookingRepository;

    public List<BookingResponse> getBookings(){

        List<Bookings> bookings= this.bookingRepository.findAll();
        return modelMapper.map(bookings, new TypeToken<List<BookingResponse>>(){}.getType());
    }

    public BookingResponse getBooking(Integer id){
        return modelMapper.map(bookingRepository.findById(id), BookingResponse.class);
    }

    public BookingResponse save(AddBookingRequest request){
        var response = bookingRepository.save(modelMapper.map(request, Bookings.class));
        return modelMapper.map(response, BookingResponse.class);
    }

}
