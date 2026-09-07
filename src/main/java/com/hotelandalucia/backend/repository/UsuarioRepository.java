package com.hotelandalucia.backend.repository;

import com.hotelandalucia.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Usado en el login para buscar al usuario por su email
    Optional<Usuario> findByEmail(String email);
}
