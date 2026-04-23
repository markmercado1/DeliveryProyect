package com.example.mspedidoservice.services;


import com.example.mspedidoservice.dtos.PedidoDTO;

import java.util.List;

public interface PedidoService {
    PedidoDTO guardar(Long clienteId, PedidoDTO dto);
    PedidoDTO buscarPorId(Long id);
    List<PedidoDTO> listar();
    List<PedidoDTO> listarPorCliente(Long clienteId);
    void eliminar(Long id);
}