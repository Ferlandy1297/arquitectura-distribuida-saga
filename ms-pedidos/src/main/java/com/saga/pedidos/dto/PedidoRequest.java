package com.saga.pedidos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PedidoRequest {
    @NotBlank(message = "El producto es obligatorio")
    private String producto;

    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private int cantidad;
}

