package com.example.mspedidoservice.feign;

import com.example.mspedidoservice.dtos.ClienteDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-cliente-service", path = "/clientes")
public interface ClienteFeign {

    @GetMapping("/{id}")
    @CircuitBreaker(name = "clienteBuscarPorIdCB")
    ClienteDTO buscarPorId(@PathVariable Long id);
}