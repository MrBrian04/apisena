package com.sena.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {
    private Integer id;
    private String email;
    private String password;
    private boolean activo;
    private List<RolDTO> roles;
}
