package com.sena.validator;

import com.sena.dto.ClienteDTO;

public class ClienteValidator {

    public static void save(ClienteDTO registro){
        if(registro.getNombre() == null || registro.getNombre().trim().isEmpty()){
            throw new IllegalArgumentException("El nombre del cliente es requerido");
        }
        if(registro.getNumeroDocumento() == null || registro.getNumeroDocumento().trim().isEmpty()){
            throw new IllegalArgumentException("El numero de documento es requerido");
        }
        if (registro.getTipoDocumento()==null){
            throw new IllegalArgumentException("El tipo de docuemnto es requerido.");
        }

    }
}
