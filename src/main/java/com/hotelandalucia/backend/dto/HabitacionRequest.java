package com.hotelandalucia.backend.dto;

import com.hotelandalucia.backend.model.EstadoHabitacion;
import com.hotelandalucia.backend.model.TipoHabitacion;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class HabitacionRequest {

    @NotBlank(message = "El numero de habitacion es obligatorio")
    private String numero;

    @NotNull(message = "El tipo de habitacion es obligatorio")
    private TipoHabitacion tipo;

    @NotNull(message = "El piso es obligatorio")
    @Min(value = 1, message = "El piso debe ser mayor a 0")
    private Integer piso;

    @NotNull(message = "La capacidad es obligatoria")
    @Min(value = 1, message = "La capacidad debe ser al menos 1 persona")
    private Integer capacidad;

    @NotNull(message = "El precio por noche es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    private BigDecimal precioNoche;

    @NotNull(message = "El estado es obligatorio")
    private EstadoHabitacion estado;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoHabitacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoHabitacion tipo) {
        this.tipo = tipo;
    }

    public Integer getPiso() {
        return piso;
    }

    public void setPiso(Integer piso) {
        this.piso = piso;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public BigDecimal getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(BigDecimal precioNoche) {
        this.precioNoche = precioNoche;
    }

    public EstadoHabitacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoHabitacion estado) {
        this.estado = estado;
    }
}
