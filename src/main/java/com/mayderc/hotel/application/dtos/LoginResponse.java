package com.mayderc.hotel.application.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    public String token;

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public LoginResponse setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
        return this;
    }

    boolean isAuthenticated;

}
