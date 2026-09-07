package com.hotelandalucia.backend.controller;

import com.hotelandalucia.backend.dto.HabitacionRequest;
import com.hotelandalucia.backend.dto.HabitacionResponse;
import com.hotelandalucia.backend.model.Habitacion;
import com.hotelandalucia.backend.repository.HabitacionRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionController {

    private final HabitacionRepository habitacionRepository;

    public HabitacionController(HabitacionRepository habitacionRepository) {
        this.habitacionRepository = habitacionRepository;
    }

    @GetMapping
    public List<HabitacionResponse> listar() {
        return habitacionRepository.findAll()
                .stream()
                .map(HabitacionResponse::new)
                .toList();
    }

    @GetMapping("/{id}")
    public HabitacionResponse obtener(@PathVariable Long id) {
        return new HabitacionResponse(buscarOFallar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HabitacionResponse crear(@Valid @RequestBody HabitacionRequest request) {
        Habitacion habitacion = new Habitacion(
                request.getNumero(),
                request.getTipo(),
                request.getPiso(),
                request.getCapacidad(),
                request.getPrecioNoche(),
                request.getEstado()
        );
        return new HabitacionResponse(habitacionRepository.save(habitacion));
    }

    @PutMapping("/{id}")
    public HabitacionResponse actualizar(@PathVariable Long id, @Valid @RequestBody HabitacionRequest request) {
        Habitacion habitacion = buscarOFallar(id);
        habitacion.setNumero(request.getNumero());
        habitacion.setTipo(request.getTipo());
        habitacion.setPiso(request.getPiso());
        habitacion.setCapacidad(request.getCapacidad());
        habitacion.setPrecioNoche(request.getPrecioNoche());
        habitacion.setEstado(request.getEstado());
        return new HabitacionResponse(habitacionRepository.save(habitacion));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        Habitacion habitacion = buscarOFallar(id);
        habitacionRepository.delete(habitacion);
    }

    private Habitacion buscarOFallar(Long id) {
        return habitacionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Habitacion no encontrada"));
    }
}
