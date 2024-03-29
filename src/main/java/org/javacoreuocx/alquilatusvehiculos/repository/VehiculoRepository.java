package org.javacoreuocx.alquilatusvehiculos.repository;

import org.javacoreuocx.alquilatusvehiculos.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {
    Optional<Vehiculo> findByMatricula(String matricula);

    Optional<Vehiculo> findByMarca(String marca);

    Optional<Vehiculo> findByModelo(String modelo);

    Optional<Vehiculo> findByMarcaAndModelo(String marca, String modelo);
}
