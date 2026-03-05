package com.sena.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@Getter
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String nombre;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento", length = 10, nullable = false)
    private TipoDocumento tipoDocumento;
    @Column(name = "numero_documento", length = 15, nullable = false)
    private String numeroDocumento;
    @Column(length = 100)
    private String direccion;
    @Column(length = 15)
    private String telefono;
    @Column(length = 50)
    private String email;


}
