package com.mayderc.hotel.application.services;

import com.mayderc.hotel.application.dtos.LoginRequest;
import com.mayderc.hotel.application.dtos.LoginResponse;
import com.mayderc.hotel.application.dtos.RegisterRequest;
import com.mayderc.hotel.application.dtos.RegisterResponse;


public interface AuthService {

    LoginResponse login(LoginRequest request);


    RegisterResponse register (RegisterRequest request);

}
