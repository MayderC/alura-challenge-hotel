package com.mayderc.hotel.application.dtos;

import com.mayderc.hotel.application.entities.Customers;
import com.mayderc.hotel.application.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


import java.sql.Date;



@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddBookingRequest {

    private Integer id;
    public Date checkIn;
    public Date checkOut;
    public Customers customer;
    public User collaborator;
}
