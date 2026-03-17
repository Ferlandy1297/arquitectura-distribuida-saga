package com.saga.inventario.config;

import com.saga.inventario.entity.Inventario;
import com.saga.inventario.repository.InventarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

    private final InventarioRepository inventarioRepository;

    @Bean
    CommandLineRunner initData() {
        return args -> {
            if (inventarioRepository.count() == 0) {
                inventarioRepository.save(Inventario.builder().producto("Laptop").stock(10).build());
                inventarioRepository.save(Inventario.builder().producto("Mouse").stock(20).build());
                inventarioRepository.save(Inventario.builder().producto("Teclado").stock(15).build());
            }
        };
    }
}

