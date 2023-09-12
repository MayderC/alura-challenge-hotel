package com.mayderc.hotel.adapters.services;


import com.mayderc.hotel.application.dtos.AddCustomerRequest;
import com.mayderc.hotel.application.dtos.CustomerResponse;
import com.mayderc.hotel.application.entities.Bookings;
import com.mayderc.hotel.application.entities.Customers;
import com.mayderc.hotel.application.repositories.CustomerRepository;
import com.mayderc.hotel.infrastructure.dtos.BookingResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerResponse save(AddCustomerRequest request){
        var customer = this.customerRepository.save(modelMapper.map(request, Customers.class));
        return modelMapper.map(customer, CustomerResponse.class);
    }

    public List<CustomerResponse> getCustomers(){
        List<Customers>  customers = this.customerRepository.findAll();
        return modelMapper.map(customers, new TypeToken<List<CustomerResponse>>(){}.getType());
    }

    public CustomerResponse getCustomer(Integer id){
        return modelMapper.map(this.customerRepository.getReferenceById(id), CustomerResponse.class);
    }
    public void delete(Integer id){
        this.customerRepository.deleteById(id);
    }

    public CustomerResponse update(Integer id, AddCustomerRequest request){

        var customer = this.customerRepository.findById(id);

        if(!customer.get().getEmail().equals(request.email))customer.get().setEmail(request.email);
        if(!customer.get().getFirstname().equals(request.firstname))customer.get().setFirstname(request.firstname);
        if(!customer.get().getLastname().equals(request.lastname))customer.get().setLastname(request.lastname);
        if(!customer.get().getTelephone().equals(request.telephone))customer.get().setTelephone(request.telephone);

        var response = this.customerRepository.save(customer.get());
        return modelMapper.map(response, CustomerResponse.class);
    }
    
}
