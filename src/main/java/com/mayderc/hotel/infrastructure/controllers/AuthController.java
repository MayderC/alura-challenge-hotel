package com.mayderc.hotel.infrastructure.controllers;




import com.mayderc.hotel.adapters.services.AuthServiceImpl;
import com.mayderc.hotel.infrastructure.dtos.LoginRequest;
import com.mayderc.hotel.infrastructure.dtos.LoginResponse;
import com.mayderc.hotel.infrastructure.dtos.RegisterRequest;
import com.mayderc.hotel.infrastructure.dtos.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;
    private final ModelMapper modelMapper;


    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request){
        System.out.printf(request.toString());
        var myRequest = modelMapper.map(request, com.mayderc.hotel.application.dtos.RegisterRequest.class);
        RegisterResponse controllerResponse = modelMapper
                .map(this.authService.register(myRequest), RegisterResponse.class);
        return ResponseEntity.ok(controllerResponse);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponse> register(@RequestBody LoginRequest request){
        com.mayderc.hotel.application.dtos.LoginResponse response = this.authService
                .login(modelMapper.map(
                    request,
                    com.mayderc.hotel.application.dtos.LoginRequest.class
                ));
        LoginResponse controllerResponse = modelMapper.map(response, LoginResponse.class);
        return ResponseEntity.ok(controllerResponse);
    }


}
