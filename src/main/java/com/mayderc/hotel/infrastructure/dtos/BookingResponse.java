package com.mayderc.hotel.infrastructure.dtos;

import com.mayderc.hotel.application.entities.Customers;
import com.mayderc.hotel.application.entities.User;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.sql.Date;

public class BookingResponse {


    private Integer id;
    public Date checkIn;
    public Date checkOut;
    public Customers customer;
    public User collaborator;


}
