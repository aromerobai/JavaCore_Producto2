package org.javacoreuocx.alquilatusvehiculos.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(name = "contrato_alquiler")
public class ContratoAlquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fechaInicio;
    private String fechaFin;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oficina_id")
    private Oficina oficina;

    @OneToMany(mappedBy = "contratoAlquiler", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ContratoAlquilerVehiculo> contratoVehiculos = new HashSet<>();

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<Vehiculo> getVehiculos() {
        Set<Vehiculo> vehiculos = new HashSet<>();
        for (ContratoAlquilerVehiculo contratoVehiculo : contratoVehiculos) {
            vehiculos.add(contratoVehiculo.getVehiculo());
        }
        return vehiculos;
    }

    public void setVehiculos(Set<Vehiculo> vehiculos) {
        for (Vehiculo vehiculo : vehiculos) {
            addVehiculo(vehiculo);
        }
    }

    public void addVehiculo(Vehiculo vehiculo) {
        ContratoAlquilerVehiculo contratoVehiculo = new ContratoAlquilerVehiculo(this, vehiculo);
        contratoVehiculos.add(contratoVehiculo);
        vehiculo.getContratoVehiculos().add(contratoVehiculo);
    }

    public void removeVehiculo(Vehiculo vehiculo) {
        for (Iterator<ContratoAlquilerVehiculo> iterator = contratoVehiculos.iterator();
             iterator.hasNext(); ) {
            ContratoAlquilerVehiculo contratoVehiculo = iterator.next();

            if (contratoVehiculo.getContratoAlquiler().equals(this) &&
                    contratoVehiculo.getVehiculo().equals(vehiculo)) {
                iterator.remove();
                contratoVehiculo.getVehiculo().getContratoVehiculos().remove(contratoVehiculo);
                contratoVehiculo.setContratoAlquiler(null);
                contratoVehiculo.setVehiculo(null);
            }
        }
    }

    public Set<ContratoAlquilerVehiculo> getContratoVehiculos() {
        return contratoVehiculos;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

}
