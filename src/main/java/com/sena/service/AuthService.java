package com.sena.service;

import com.sena.dto.AuthResponseDTO;
import com.sena.dto.LoginRequestDTO;
import com.sena.entity.Usuario;
import com.sena.exception.NoDataFoundException;
import com.sena.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import security.JwtService;

@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NoDataFoundException("Usuario no encontrado"));

        String jwtToken = jwtService.generateToken(usuario);

        return AuthResponseDTO.builder()
                .id(usuario.getId())
                .email(usuario.getEmail())
                .token(jwtToken)
                .build();
    }
}
