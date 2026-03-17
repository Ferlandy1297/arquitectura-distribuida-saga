package com.saga.pedidos.dto;

import com.saga.pedidos.entity.EstadoPedido;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PedidoResponse {
    private Long id;
    private String producto;
    private Integer cantidad;
    private EstadoPedido estado;
}

