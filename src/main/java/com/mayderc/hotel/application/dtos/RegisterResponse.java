package com.mayderc.hotel.application.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {

    public String token;

    public RegisterResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public boolean isAuthenticated;

    public RegisterResponse setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
        return this;
    }
}
