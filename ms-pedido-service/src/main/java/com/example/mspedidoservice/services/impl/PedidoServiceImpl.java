package com.example.mspedidoservice.services.impl;

import com.example.mspedidoservice.dtos.ClienteDTO;
import com.example.mspedidoservice.dtos.PedidoDTO;
import com.example.mspedidoservice.feign.ClienteFeign;
import com.example.mspedidoservice.models.Pedido;
import com.example.mspedidoservice.repositorys.PedidoRepository;
import com.example.mspedidoservice.services.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository repository;
    private final ClienteFeign clienteFeign;

    @Override
    public PedidoDTO guardar(Long clienteId, PedidoDTO dto) {
        Pedido pedido = Pedido.builder()
                .producto(dto.getProducto())
                .cantidad(dto.getCantidad())
                .total(dto.getTotal())
                .fecha(LocalDateTime.now())
                .clienteId(clienteId)
                .build();
        Pedido saved = repository.save(pedido);
        ClienteDTO cliente = clienteFeign.buscarPorId(clienteId);
        return mapToDTO(saved, cliente);
    }

    @Override
    public PedidoDTO buscarPorId(Long id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        ClienteDTO cliente = clienteFeign.buscarPorId(pedido.getClienteId());
        return mapToDTO(pedido, cliente);
    }

    @Override
    public List<PedidoDTO> listar() {
        return repository.findAll()
                .stream()
                .map(pedido -> mapToDTO(pedido, clienteFeign.buscarPorId(pedido.getClienteId())))
                .collect(Collectors.toList());
    }

    @Override
    public List<PedidoDTO> listarPorCliente(Long clienteId) {
        return repository.findByClienteId(clienteId)
                .stream()
                .map(pedido -> mapToDTO(pedido, clienteFeign.buscarPorId(clienteId)))
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    private PedidoDTO mapToDTO(Pedido pedido, ClienteDTO cliente) {
        return PedidoDTO.builder()
                .id(pedido.getId())
                .producto(pedido.getProducto())
                .cantidad(pedido.getCantidad())
                .total(pedido.getTotal())
                .fecha(pedido.getFecha())
                .clienteDTO(cliente)
                .build();
    }
}