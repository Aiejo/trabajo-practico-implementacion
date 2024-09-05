package com.example.TrabajoPractico.repository;
import com.example.TrabajoPractico.modelentity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ICompraRepository extends JpaRepository<Compra, Long> {
    List<Compra> findByClienteId(Long clienteId);
}
