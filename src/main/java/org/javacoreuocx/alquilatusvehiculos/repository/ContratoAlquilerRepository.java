package org.javacoreuocx.alquilatusvehiculos.repository;

import org.javacoreuocx.alquilatusvehiculos.model.ContratoAlquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratoAlquilerRepository extends JpaRepository<ContratoAlquiler, Long> {

    // Encuentra contratos por cliente
    List<ContratoAlquiler> findByClienteId(Long clienteId);

    // Encuentra contratos por oficina
    List<ContratoAlquiler> findByOficinaId(Long oficinaId);

    // Encuentra contratos entre fechas de inicio y fin
    List<ContratoAlquiler> findByFechaInicioGreaterThanEqualAndFechaFinLessThanEqual(String fechaInicio, String fechaFin);

    // Encuentra contratos que incluyen un vehículo específico
    @Query("select ca from ContratoAlquiler ca join ca.contratoVehiculos cv where cv.vehiculo.id = :vehiculoId")
    List<ContratoAlquiler> findContratosByVehiculoId(@Param("vehiculoId") Long vehiculoId);
}
