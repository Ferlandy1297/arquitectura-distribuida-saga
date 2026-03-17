package com.saga.pedidos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StockRequest {
    @NotBlank
    private String producto;
    @Min(1)
    private int cantidad;
}

