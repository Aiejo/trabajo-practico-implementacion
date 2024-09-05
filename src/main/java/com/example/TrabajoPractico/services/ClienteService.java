package com.example.TrabajoPractico.services;
import com.example.TrabajoPractico.repository.IClienteRepository;
import com.example.TrabajoPractico.modelentity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService implements IClienteService {
    @Autowired
    private IClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    @Override
    public Cliente save(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsById(Long id){
        return clienteRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }
}
