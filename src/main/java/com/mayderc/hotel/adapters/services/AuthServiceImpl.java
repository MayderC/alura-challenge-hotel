package com.mayderc.hotel.adapters.services;


import com.mayderc.hotel.application.dtos.LoginResponse;
import com.mayderc.hotel.application.dtos.RegisterRequest;
import com.mayderc.hotel.application.dtos.RegisterResponse;
import com.mayderc.hotel.application.entities.Role;
import com.mayderc.hotel.application.entities.User;
import com.mayderc.hotel.application.repositories.UserRepository;
import com.mayderc.hotel.application.services.AuthService;

import com.mayderc.hotel.infrastructure.dtos.LoginRequest;
import com.mayderc.hotel.infrastructure.helper.JwtService;
import com.mayderc.hotel.infrastructure.helper.ModelMapperConfig;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    @Override
    public LoginResponse login(com.mayderc.hotel.application.dtos.LoginRequest request) {
        System.out.printf(request.toString());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        System.out.println("ALOGIN ");
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        System.out.println("REGISTER");
        System.out.printf(user.toString());
        System.out.println("REGISTER");
        var jwtToken = jwtService.generateToken(user);

        return LoginResponse.builder()
                .token(jwtToken)
                .isAuthenticated(true)
                .build();
    }


    @Override
    public RegisterResponse register(RegisterRequest request) {
        var entity = new User();
        System.out.println("REQUEST ABAJO");
        System.out.println(request);
        entity.setEmail(request.email);
        entity.setFirstname(request.firstname);
        entity.setPassword(passwordEncoder.encode(request.password));
        entity.setLastname(request.lastname);
        entity.setRole(Role.ROLE_USER);
        System.out.println(entity);
        var user = userRepository.save(entity);
        var jwtToken = jwtService.generateToken(user);
        System.out.printf(jwtToken);
        var response = new RegisterResponse();

        return response.setToken(jwtToken).setAuthenticated(true);
    }
}
