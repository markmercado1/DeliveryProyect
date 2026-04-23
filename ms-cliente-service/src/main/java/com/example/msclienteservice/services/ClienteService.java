package com.example.msclienteservice.services;

import com.example.msclienteservice.dtos.ClienteDTO;

import java.util.List;

public interface ClienteService {
    ClienteDTO guardar(ClienteDTO dto);
    ClienteDTO buscarPorId(Long id);
    List<ClienteDTO> listar();
    void eliminar(Long id);
}