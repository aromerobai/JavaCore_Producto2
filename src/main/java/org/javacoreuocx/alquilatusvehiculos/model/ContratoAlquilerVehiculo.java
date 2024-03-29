package org.javacoreuocx.alquilatusvehiculos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "contrato_alquiler_vehiculo")
public class ContratoAlquilerVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contrato_alquiler_id")
    private ContratoAlquiler contratoAlquiler;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    // Constructor vacío
    public ContratoAlquilerVehiculo() {
    }

    // Constructor con parámetros
    public ContratoAlquilerVehiculo(ContratoAlquiler contratoAlquiler, Vehiculo vehiculo) {
        this.contratoAlquiler = contratoAlquiler;
        this.vehiculo = vehiculo;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ContratoAlquiler getContratoAlquiler() {
        return contratoAlquiler;
    }

    public void setContratoAlquiler(ContratoAlquiler contratoAlquiler) {
        this.contratoAlquiler = contratoAlquiler;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    // Implementar hashCode y equals si necesitas comparar instancias de ContratoVehiculo o usar colecciones como Sets
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContratoAlquilerVehiculo)) return false;
        ContratoAlquilerVehiculo that = (ContratoAlquilerVehiculo) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
