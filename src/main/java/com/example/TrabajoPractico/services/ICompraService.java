package com.example.TrabajoPractico.services;
import com.example.TrabajoPractico.modeldto.CompraByClienteDTO;
import com.example.TrabajoPractico.modelentity.Cliente;
import com.example.TrabajoPractico.modelentity.Compra;
import com.example.TrabajoPractico.modelrequest.CompraRequest;

import java.util.List;

public interface ICompraService {
    public List<Compra> findAll();
    public Compra findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
    public Compra registrarCompra(Long productoId, Long clienteId,Integer cantidad) throws Exception;
    public List<CompraByClienteDTO> findByCliente(Long clienteId);
}
