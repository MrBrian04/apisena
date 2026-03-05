package com.sena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApisenaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApisenaApplication.class, args);
	}

}
/*
* 1 Spring boot started Security
* 2 io.jsonwebtoken
* 3 Editar la entidad Usuario
* 4 Editar UsuarioServiceImplementation
* 5 Editar aplication.properties - key para firmar los token en el servidor
* 6 Crear paquete security.jwt
* 7 Crear una clase llamada JwtService
* 8 En el mismo paquete security.jwt crear una clase JwtAuthenticationFilter
* 9 En el paquete service crear una clase CustomUserDetailsService
* 10 crear el paquete config
* 11 dentro de config crear la clase SecurityConfig
* 12 en el paquete dto creamos dos clases
* 13 primera: LoginRequestDTO
* 14 segunda: AuthResponseDTO
* 15 en la carpeta service creamos la clase AuthService
* 16 ahora en controller creamos un controlador AuthController
* 17 en el paquete exception editamos ErroHandlerConfig y agregamos nuevos metodos
* 18 luego en el paquete de servicio editamos CustomUserDetailsService
* 19 en nuestros controladores damos accesos con @PreAuthorizacion
* 20
*
* */
