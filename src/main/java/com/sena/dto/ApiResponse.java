package com.sena.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@Builder
public class ApiResponse<T> {
    private T data;
    private boolean ok;
    private String message;

    public ApiResponse(T data, boolean ok, String message) {
        this.data = data;
        this.ok = ok;
        this.message = message;
    }
    public ApiResponse(T data, boolean ok) {
        this.data = data;
        this.ok = ok;
    }

    public ResponseEntity<ApiResponse<T>> createResponse(){
       return new ResponseEntity<>(this, HttpStatus.OK);
    }
    public ResponseEntity<ApiResponse<T>> createResponse(HttpStatus status){
        return new ResponseEntity<>(this, status);
    }
}
