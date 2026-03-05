package com.sena.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {
    private Integer id;
    private String email;
    private String password;
    private boolean activo;
    private List<RolDTO> roles;

    public boolean isActivo(){
        return activo;
    }
}
