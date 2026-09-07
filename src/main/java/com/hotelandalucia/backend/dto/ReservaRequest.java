package com.hotelandalucia.backend.dto;


import com.hotelandalucia.backend.model.EstadoReserva;
import com.hotelandalucia.backend.model.MetodoPago;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ReservaRequest {

    @NotNull(message = "La habitacion es obligatoria")
    private Long habitacionId;

    @NotBlank(message = "El nombre del huesped es obligatorio")
    private String huespedNombre;

    @NotBlank(message = "El documento del huesped es obligatorio")
    private String huespedDocumento;

    private String huespedTelefono;

    @NotNull(message = "La fecha de check-in es obligatoria")
    private LocalDate checkin;

    @NotNull(message = "La fecha de check-out es obligatoria")
    private LocalDate checkout;

    @NotNull(message = "El numero de huespedes es obligatorio")
    @Min(value = 1, message = "Debe haber al menos 1 huesped")
    private Integer numHuespedes;

    @NotNull(message = "El metodo de pago es obligatorio")
    private MetodoPago metodoPago;

    @NotNull(message = "El estado de la reserva es obligatorio")
    private EstadoReserva estado;

    private String notas;

    public Long getHabitacionId() {
        return habitacionId;
    }

    public void setHabitacionId(Long habitacionId) {
        this.habitacionId = habitacionId;
    }

    public String getHuespedNombre() {
        return huespedNombre;
    }

    public void setHuespedNombre(String huespedNombre) {
        this.huespedNombre = huespedNombre;
    }

    public String getHuespedDocumento() {
        return huespedDocumento;
    }

    public void setHuespedDocumento(String huespedDocumento) {
        this.huespedDocumento = huespedDocumento;
    }

    public String getHuespedTelefono() {
        return huespedTelefono;
    }

    public void setHuespedTelefono(String huespedTelefono) {
        this.huespedTelefono = huespedTelefono;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDate checkin) {
        this.checkin = checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
    }

    public Integer getNumHuespedes() {
        return numHuespedes;
    }

    public void setNumHuespedes(Integer numHuespedes) {
        this.numHuespedes = numHuespedes;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}