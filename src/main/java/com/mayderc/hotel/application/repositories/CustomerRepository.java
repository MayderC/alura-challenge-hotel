package com.mayderc.hotel.application.repositories;

import com.mayderc.hotel.application.entities.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CustomerRepository extends JpaRepository<Customers, Integer> {


}
