package com.example.TrabajoPractico.services;
import com.example.TrabajoPractico.modelentity.Producto;
import java.util.List;

public interface IProductoService {
    public List<Producto> findAll();
    public Producto save(Producto producto);
    public Producto findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
}
