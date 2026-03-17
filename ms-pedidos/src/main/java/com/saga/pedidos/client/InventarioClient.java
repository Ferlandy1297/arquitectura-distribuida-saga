package com.saga.pedidos.client;

import com.saga.pedidos.dto.OperacionResponse;
import com.saga.pedidos.dto.StockRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventarioClient", url = "${inventory.service.url}")
public interface InventarioClient {

    @PostMapping("/inventario/reservar")
    OperacionResponse reservar(@RequestBody StockRequest request);

    @PostMapping("/inventario/liberar")
    OperacionResponse liberar(@RequestBody StockRequest request);
}

