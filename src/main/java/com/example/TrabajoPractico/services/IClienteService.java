package com.example.TrabajoPractico.services;
import com.example.TrabajoPractico.modelentity.Cliente;

import java.util.List;

public interface IClienteService  {
    public List<Cliente> findAll();
    public Cliente save(Cliente cliente);
    public Cliente findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
}
