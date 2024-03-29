package org.javacoreuocx.alquilatusvehiculos.service;

import org.javacoreuocx.alquilatusvehiculos.model.Cliente;
import org.javacoreuocx.alquilatusvehiculos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Cliente registrarCliente(Cliente cliente) {
        cliente.setPassword(passwordEncoder.encode(cliente.getPassword())); // Codifica la contraseña
        return clienteRepository.save(cliente); // Guarda el cliente en la BD
    }
}
