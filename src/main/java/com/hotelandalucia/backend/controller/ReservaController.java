package com.hotelandalucia.backend.controller;

import com.hotelandalucia.backend.dto.ReservaRequest;
import com.hotelandalucia.backend.dto.ReservaResponse;
import com.hotelandalucia.backend.model.Habitacion;
import com.hotelandalucia.backend.model.Reserva;
import com.hotelandalucia.backend.model.Usuario;
import com.hotelandalucia.backend.repository.HabitacionRepository;
import com.hotelandalucia.backend.repository.ReservaRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaRepository reservaRepository;
    private final HabitacionRepository habitacionRepository;

    public ReservaController(ReservaRepository reservaRepository, HabitacionRepository habitacionRepository) {
        this.reservaRepository = reservaRepository;
        this.habitacionRepository = habitacionRepository;
    }

    @GetMapping
    public List<ReservaResponse> listar() {
        return reservaRepository.findAll()
                .stream()
                .map(ReservaResponse::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ReservaResponse obtener(@PathVariable Long id) {
        return new ReservaResponse(buscarOFallar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservaResponse crear(@Valid @RequestBody ReservaRequest request, @AuthenticationPrincipal Usuario usuarioActual) {
        Habitacion habitacion = buscarHabitacionOFallar(request.getHabitacionId());

        Reserva reserva = new Reserva();
        aplicarDatos(reserva, request, habitacion);
        reserva.setUsuario(usuarioActual);

        return new ReservaResponse(reservaRepository.save(reserva));
    }

    @PutMapping("/{id}")
    public ReservaResponse actualizar(@PathVariable Long id, @Valid @RequestBody ReservaRequest request) {
        Reserva reserva = buscarOFallar(id);
        Habitacion habitacion = buscarHabitacionOFallar(request.getHabitacionId());
        aplicarDatos(reserva, request, habitacion);
        return new ReservaResponse(reservaRepository.save(reserva));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        Reserva reserva = buscarOFallar(id);
        reservaRepository.delete(reserva);
    }

    private void aplicarDatos(Reserva reserva, ReservaRequest request, Habitacion habitacion) {
        reserva.setHabitacion(habitacion);
        reserva.setHuespedNombre(request.getHuespedNombre());
        reserva.setHuespedDocumento(request.getHuespedDocumento());
        reserva.setHuespedTelefono(request.getHuespedTelefono());
        reserva.setCheckin(request.getCheckin());
        reserva.setCheckout(request.getCheckout());
        reserva.setNumHuespedes(request.getNumHuespedes());
        reserva.setMetodoPago(request.getMetodoPago());
        reserva.setEstado(request.getEstado());
        reserva.setNotas(request.getNotas());
    }

    private Reserva buscarOFallar(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva no encontrada"));
    }

    private Habitacion buscarHabitacionOFallar(Long habitacionId) {
        return habitacionRepository.findById(habitacionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "La habitacion indicada no existe"));
    }
}
