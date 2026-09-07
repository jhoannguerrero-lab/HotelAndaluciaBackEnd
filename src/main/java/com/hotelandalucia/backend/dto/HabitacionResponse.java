package com.hotelandalucia.backend.dto;

import com.hotelandalucia.backend.model.EstadoHabitacion;
import com.hotelandalucia.backend.model.Habitacion;
import com.hotelandalucia.backend.model.TipoHabitacion;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class HabitacionResponse {

    private Long id;
    private String numero;
    private TipoHabitacion tipo;
    private Integer piso;
    private Integer capacidad;
    private BigDecimal precioNoche;
    private EstadoHabitacion estado;
    private LocalDateTime createdAt;

    public HabitacionResponse(Habitacion h) {
        this.id = h.getId();
        this.numero = h.getNumero();
        this.tipo = h.getTipo();
        this.piso = h.getPiso();
        this.capacidad = h.getCapacidad();
        this.precioNoche = h.getPrecioNoche();
        this.estado = h.getEstado();
        this.createdAt = h.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public TipoHabitacion getTipo() {
        return tipo;
    }

    public Integer getPiso() {
        return piso;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public BigDecimal getPrecioNoche() {
        return precioNoche;
    }

    public EstadoHabitacion getEstado() {
        return estado;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
