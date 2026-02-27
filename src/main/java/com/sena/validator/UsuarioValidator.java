package com.sena.validator;

import com.sena.dto.UsuarioDTO;
import com.sena.exception.ValidateException;

public class UsuarioValidator {
    public static void save(UsuarioDTO registro) {
        if (registro.getEmail() == null || registro.getEmail().trim().isEmpty()) {
            throw new ValidateException("El email del usuario es requerido");
        }
        if (registro.getPassword() == null || registro.getPassword().trim().isEmpty()) {
            throw new ValidateException("El password es requerido");
        }
        if (registro.getRoles() == null) {
            throw new ValidateException("Es requerido un ROL");
        }
    }
}
