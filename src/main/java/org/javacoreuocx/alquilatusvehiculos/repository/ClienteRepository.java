package org.javacoreuocx.alquilatusvehiculos.repository;

import org.javacoreuocx.alquilatusvehiculos.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByEmail(String email);
}