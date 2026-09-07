package com.hotelandalucia.backend.dto;

import com.hotelandalucia.backend.model.EstadoReserva;
import com.hotelandalucia.backend.model.MetodoPago;
import com.hotelandalucia.backend.model.Reserva;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservaResponse {

    private Long id;

    // Datos de la habitacion enlazada (para no obligar al frontend a
    // hacer una segunda peticion solo para mostrar el numero/tipo)
    private Long habitacionId;
    private String habitacionNumero;
    private String habitacionTipo;

    private String huespedNombre;
    private String huespedDocumento;
    private String huespedTelefono;
    private LocalDate checkin;
    private LocalDate checkout;
    private Integer numHuespedes;
    private MetodoPago metodoPago;
    private EstadoReserva estado;
    private String notas;
    private String registradoPor;
    private LocalDateTime createdAt;

    public ReservaResponse(Reserva r) {
        this.id = r.getId();
        this.habitacionId = r.getHabitacion().getId();
        this.habitacionNumero = r.getHabitacion().getNumero();
        this.habitacionTipo = r.getHabitacion().getTipo().name();
        this.huespedNombre = r.getHuespedNombre();
        this.huespedDocumento = r.getHuespedDocumento();
        this.huespedTelefono = r.getHuespedTelefono();
        this.checkin = r.getCheckin();
        this.checkout = r.getCheckout();
        this.numHuespedes = r.getNumHuespedes();
        this.metodoPago = r.getMetodoPago();
        this.estado = r.getEstado();
        this.notas = r.getNotas();
        this.registradoPor = r.getUsuario() != null ? r.getUsuario().getNombreCompleto() : null;
        this.createdAt = r.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public Long getHabitacionId() {
        return habitacionId;
    }

    public String getHabitacionNumero() {
        return habitacionNumero;
    }

    public String getHabitacionTipo() {
        return habitacionTipo;
    }

    public String getHuespedNombre() {
        return huespedNombre;
    }

    public String getHuespedDocumento() {
        return huespedDocumento;
    }

    public String getHuespedTelefono() {
        return huespedTelefono;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public Integer getNumHuespedes() {
        return numHuespedes;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public String getNotas() {
        return notas;
    }

    public String getRegistradoPor() {
        return registradoPor;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
