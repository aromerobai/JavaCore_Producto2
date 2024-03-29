package org.javacoreuocx.alquilatusvehiculos.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomSecurityService {

    private AuthenticationManager authenticationManager;

    // Constructor para inyectar AuthenticationManager
    public CustomSecurityService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    public void autoLogin(String username, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        // Autenticación del usuario
        Authentication authentication = authenticationManager.authenticate(token);

        // Establecer la autenticación en el contexto de seguridad
        if (authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
}
