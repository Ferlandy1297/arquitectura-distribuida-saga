package com.saga.inventario.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventarioItemResponse {
    private Long id;
    private String producto;
    private Integer stock;
}

