package com.saga.pedidos.service;

import com.saga.pedidos.client.InventarioClient;
import com.saga.pedidos.dto.PedidoRequest;
import com.saga.pedidos.dto.PedidoResponse;
import com.saga.pedidos.dto.StockRequest;
import com.saga.pedidos.entity.EstadoPedido;
import com.saga.pedidos.entity.Pedido;
import com.saga.pedidos.exception.ResourceNotFoundException;
import com.saga.pedidos.repository.PedidoRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final InventarioClient inventarioClient;

    @Transactional
    public PedidoResponse crearPedido(PedidoRequest request) {
        Pedido pedido = Pedido.builder()
                .producto(request.getProducto())
                .cantidad(request.getCantidad())
                .estado(EstadoPedido.PENDIENTE)
                .build();
        pedido = pedidoRepository.save(pedido);

        try {
            StockRequest stockReq = new StockRequest();
            stockReq.setProducto(request.getProducto());
            stockReq.setCantidad(request.getCantidad());
            inventarioClient.reservar(stockReq);
            pedido.setEstado(EstadoPedido.COMPLETADO);
        } catch (FeignException e) {
            pedido.setEstado(EstadoPedido.CANCELADO);
        }

        pedido = pedidoRepository.save(pedido);
        return mapToResponse(pedido);
    }

    public List<PedidoResponse> listar() {
        return pedidoRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public PedidoResponse obtener(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado: " + id));
        return mapToResponse(pedido);
    }

    private PedidoResponse mapToResponse(Pedido pedido) {
        return PedidoResponse.builder()
                .id(pedido.getId())
                .producto(pedido.getProducto())
                .cantidad(pedido.getCantidad())
                .estado(pedido.getEstado())
                .build();
    }
}

