package com.saga.pedidos.dto;

import lombok.Data;

@Data
public class OperacionResponse {
    private boolean success;
    private String message;
    private String producto;
    private Integer cantidad;
    private Integer stockActual;
}

