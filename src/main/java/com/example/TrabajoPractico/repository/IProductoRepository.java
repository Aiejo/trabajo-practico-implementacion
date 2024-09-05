package com.example.TrabajoPractico.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.TrabajoPractico.modelentity.Producto;

public interface IProductoRepository extends JpaRepository<Producto, Long> {
}
