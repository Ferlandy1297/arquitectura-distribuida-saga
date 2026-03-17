package com.saga.inventario.service;

import com.saga.inventario.dto.InventarioItemResponse;
import com.saga.inventario.dto.OperacionResponse;
import com.saga.inventario.dto.StockRequest;
import com.saga.inventario.entity.Inventario;
import com.saga.inventario.exception.InsufficientStockException;
import com.saga.inventario.exception.NotFoundException;
import com.saga.inventario.repository.InventarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    public List<InventarioItemResponse> listarTodo() {
        return inventarioRepository.findAll().stream()
                .map(inv -> InventarioItemResponse.builder()
                        .id(inv.getId())
                        .producto(inv.getProducto())
                        .stock(inv.getStock())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public OperacionResponse reservar(StockRequest req) {
        Inventario inv = inventarioRepository.findByProducto(req.getProducto())
                .orElseThrow(() -> new NotFoundException("Producto no encontrado: " + req.getProducto()));

        if (inv.getStock() < req.getCantidad()) {
            throw new InsufficientStockException("Stock insuficiente para " + req.getProducto());
        }

        inv.setStock(inv.getStock() - req.getCantidad());
        inventarioRepository.save(inv);

        return OperacionResponse.builder()
                .success(true)
                .message("Stock reservado")
                .producto(inv.getProducto())
                .cantidad(req.getCantidad())
                .stockActual(inv.getStock())
                .build();
    }

    @Transactional
    public OperacionResponse liberar(StockRequest req) {
        Inventario inv = inventarioRepository.findByProducto(req.getProducto())
                .orElseThrow(() -> new NotFoundException("Producto no encontrado: " + req.getProducto()));

        inv.setStock(inv.getStock() + req.getCantidad());
        inventarioRepository.save(inv);

        return OperacionResponse.builder()
                .success(true)
                .message("Stock liberado")
                .producto(inv.getProducto())
                .cantidad(req.getCantidad())
                .stockActual(inv.getStock())
                .build();
    }
}

