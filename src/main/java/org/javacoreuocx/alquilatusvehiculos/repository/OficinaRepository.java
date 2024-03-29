package org.javacoreuocx.alquilatusvehiculos.repository;

import org.javacoreuocx.alquilatusvehiculos.model.Oficina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OficinaRepository extends JpaRepository<Oficina, Integer> {
    Optional<Oficina> findByCiudad(String ciudad);

    Optional<Oficina> findByCodigoPostal(String codigoPostal);

}
