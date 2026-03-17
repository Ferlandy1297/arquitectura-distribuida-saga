package com.saga.inventario.controller;

import com.saga.inventario.dto.InventarioItemResponse;
import com.saga.inventario.dto.OperacionResponse;
import com.saga.inventario.dto.StockRequest;
import com.saga.inventario.service.InventarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventario")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService inventarioService;

    @GetMapping
    public ResponseEntity<List<InventarioItemResponse>> listar() {
        return ResponseEntity.ok(inventarioService.listarTodo());
    }

    @PostMapping("/reservar")
    public ResponseEntity<OperacionResponse> reservar(@Valid @RequestBody StockRequest req) {
        return ResponseEntity.ok(inventarioService.reservar(req));
    }

    @PostMapping("/liberar")
    public ResponseEntity<OperacionResponse> liberar(@Valid @RequestBody StockRequest req) {
        return ResponseEntity.ok(inventarioService.liberar(req));
    }
}

