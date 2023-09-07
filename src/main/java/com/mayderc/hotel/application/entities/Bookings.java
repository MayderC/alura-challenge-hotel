package com.mayderc.hotel.application.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Bookings {

    @Id
    private Integer id;
    public Date checkIn;
    public Date checkOut;

    @ManyToOne
    public Customers customer;
    @ManyToOne
    public User collaborator;

}
