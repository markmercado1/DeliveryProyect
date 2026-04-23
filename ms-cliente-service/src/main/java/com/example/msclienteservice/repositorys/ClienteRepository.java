package com.example.msclienteservice.repositorys;

import com.example.msclienteservice.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {}