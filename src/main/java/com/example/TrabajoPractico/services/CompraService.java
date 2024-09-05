package com.example.TrabajoPractico.services;
import com.example.TrabajoPractico.modeldto.CompraByClienteDTO;
import com.example.TrabajoPractico.modelrequest.CompraRequest;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.TrabajoPractico.repository.*;
import com.example.TrabajoPractico.modelentity.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CompraService implements ICompraService {
    @Autowired
    private ICompraRepository compraRepository;

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    public List<Compra> findAll(){
        return compraRepository.findAll();
    }

    @Override
    public Compra findById(Long id) {
        return compraRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsById(Long id){
        return compraRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        compraRepository.deleteById(id);
    }

    public Compra registrarCompra(Long productoId, Long clienteId,Integer cantidad) throws Exception {
        Optional<Cliente> clienteOpt = clienteRepository.findById(clienteId);
        Optional<Producto> productoOpt = productoRepository.findById(productoId);

        if (clienteOpt.isEmpty()) {
            throw new Exception("Cliente no encontrado");
        }
        if (productoOpt.isEmpty()) {
            throw new Exception("Producto no encontrado");
        }

        Producto producto = productoOpt.get();
        if (producto.getCantidadStock() < cantidad) {
            throw new Exception("Stock insuficiente para la compra");
        }

        // Reducir la cantidad de stock del producto
        producto.setCantidadStock(producto.getCantidadStock() - cantidad);
        productoRepository.save(producto);

        Compra nuevaCompra = new Compra();
        nuevaCompra.setCliente(clienteOpt.get());
        nuevaCompra.setProducto(producto);
        nuevaCompra.setCantidadComprada(cantidad);
        nuevaCompra.setFechaCompra(LocalDate.now());

        return compraRepository.save(nuevaCompra);
    }
    public List<CompraByClienteDTO> findByCliente(Long clienteId){
        List<Compra> compras = compraRepository.findByClienteId(clienteId);
        return compras.stream().map(compra -> {
            CompraByClienteDTO dto = new CompraByClienteDTO();
            dto.setId(compra.getId());
            dto.setFechaCompra(compra.getFechaCompra());
            dto.setNombreProducto(compra.getProducto().getNombre());
            dto.setIdProducto(compra.getProducto().getId());
            dto.setCantidadComprada(compra.getCantidadComprada());
            return dto;
        }).toList();
    }
}
