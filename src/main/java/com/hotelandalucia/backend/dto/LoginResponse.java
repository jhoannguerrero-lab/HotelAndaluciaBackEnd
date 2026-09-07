package com.hotelandalucia.backend.dto;

public class LoginResponse {

    private String token;
    private String nombreCompleto;
    private String rol;

    public LoginResponse(String token, String nombreCompleto, String rol) {
        this.token = token;
        this.nombreCompleto = nombreCompleto;
        this.rol = rol;
    }

    public String getToken() {
        return token;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getRol() {
        return rol;
    }
}

