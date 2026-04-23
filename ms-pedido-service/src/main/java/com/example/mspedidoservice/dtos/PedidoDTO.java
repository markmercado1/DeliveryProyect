package com.example.mspedidoservice.dtos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private Long id;
    private String producto;
    private Integer cantidad;
    private Double total;
    private LocalDateTime fecha;
    private ClienteDTO clienteDTO; // respuesta enriquecida
}