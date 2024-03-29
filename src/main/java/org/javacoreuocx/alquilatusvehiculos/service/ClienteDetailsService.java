package org.javacoreuocx.alquilatusvehiculos.service;

import org.javacoreuocx.alquilatusvehiculos.model.Cliente;
import org.javacoreuocx.alquilatusvehiculos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ClienteDetailsService implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No se encontró el usuario: " + email));

        return new User(cliente.getEmail(), passwordEncoder.encode(cliente.getPassword()),
                Collections.singletonList(new SimpleGrantedAuthority(cliente.getRol())));
    }
}
