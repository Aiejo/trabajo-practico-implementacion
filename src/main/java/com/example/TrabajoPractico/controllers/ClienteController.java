package com.example.TrabajoPractico.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.example.TrabajoPractico.modelentity.Cliente;
import com.example.TrabajoPractico.services.IClienteService;
import java.util.*;

@RestController()
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private IClienteService clienteService;

    @GetMapping("/")
    public ResponseEntity<?> getCliente(){
        try{
            List<Cliente> c = clienteService.findAll();
            return ResponseEntity.status(200).body(c);
        }catch (Exception e){
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error al obtener los clientes: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClienteById
            (@PathVariable Long id) {
        Cliente c = clienteService.findById(id);
        Map<String,String> response = new HashMap<>();
        if(c==null){
            response.put("message", "Cliente no encontrado");
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.status(200).body(c);
    }

    @PostMapping("/")
    public ResponseEntity<?> postCliente(@RequestBody Cliente cliente){
        Map<String, String> response = new HashMap<>();
        try{
            clienteService.save(cliente);
            response.put("message", "Cliente guardado con éxito");
            return ResponseEntity.status(201).body(response);
        }catch(Exception e){
            response.put("message", "Error al agregar el cliente: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
        Map<String,String> response = new HashMap<>();
        try {
            if (clienteService.existsById(id)) {
                clienteService.deleteById(id);
                response.put("message", "Cliente eliminado exitosamente");
                return ResponseEntity.status(201).body(response);
            } else {
                response.put("message", "Cliente no encontrado");
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            response.put("message", "Error al eliminar el cliente: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putCliente(@RequestBody Cliente cliente, @PathVariable Long id) {
        Map<String,String> response = new HashMap<>();
        try{
            Cliente c = clienteService.findById(id);
            if(c==null){
                response.put("message", "Cliente no encontrado");
                return ResponseEntity.status(404).body(response);
            }
            if(cliente.getNombre()!=null){
                c.setNombre(cliente.getNombre());
            }
            if(cliente.getDireccion()!=null){
                c.setDireccion(cliente.getDireccion());
            }
            if(cliente.getEmail()!=null){
                c.setEmail(cliente.getEmail());
            }
            if(cliente.getTelefono()!=null){
                c.setTelefono(cliente.getTelefono());
            }
            clienteService.save(c);
            return ResponseEntity.status(201).body(c);
        }catch (Exception e){
            response.put("message", "Error al actualizar el cliente: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}
