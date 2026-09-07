package com.hotelandalucia.backend.repository;
import com.hotelandalucia.backend.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByHabitacionId(Long habitacionId);
}
