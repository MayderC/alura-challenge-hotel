package com.mayderc.hotel.application.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddCustomerRequest {

    public String firstname;
    public String lastname;
    public String email;
    public String telephone;
}
