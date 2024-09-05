package com.example.TrabajoPractico.repository;
import com.example.TrabajoPractico.modelentity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {
}
