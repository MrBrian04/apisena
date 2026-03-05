package com.sena.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoDocumento {
    CC("CC", 11),
    RUT("RUT", 11),
    PPT("Permiso de permanencia temporal", 7),
    PASAPORTE("Pasaporte", 20);

    private final String descripcion;
    private final int maxLength;
}
