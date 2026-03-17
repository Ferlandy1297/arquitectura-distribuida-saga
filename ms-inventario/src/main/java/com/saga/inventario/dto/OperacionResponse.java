package com.saga.inventario.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OperacionResponse {
    private boolean success;
    private String message;
    private String producto;
    private Integer cantidad;
    private Integer stockActual;
}

