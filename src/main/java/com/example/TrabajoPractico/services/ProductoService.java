package com.example.TrabajoPractico.services;
import com.example.TrabajoPractico.modelentity.Producto;
import com.example.TrabajoPractico.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProductoService implements IProductoService {
    @Autowired
    private IProductoRepository productoRepository;

    @Override
    public List<Producto> findAll(){
        return productoRepository.findAll();
    }

    @Override
    public Producto save(Producto producto){
        return productoRepository.save(producto);
    }

    @Override
    public Producto findById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsById(Long id){
        return productoRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }
}
