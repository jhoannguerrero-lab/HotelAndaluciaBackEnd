package com.hotelandalucia.backend.service;
import com.hotelandalucia.backend.dto.LoginRequest;
import com.hotelandalucia.backend.dto.LoginResponse;
import com.hotelandalucia.backend.model.Usuario;
import com.hotelandalucia.backend.repository.UsuarioRepository;
import com.hotelandalucia.backend.security.JwtService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Email o contrasena incorrectos"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPasswordHash())) {
            throw new BadCredentialsException("Email o contrasena incorrectos");
        }

        String token = jwtService.generarToken(usuario);
        return new LoginResponse(token, usuario.getNombreCompleto(), usuario.getRol().name());
    }
}
