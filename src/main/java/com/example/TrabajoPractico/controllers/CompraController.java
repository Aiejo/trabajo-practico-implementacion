package com.example.TrabajoPractico.controllers;
import com.example.TrabajoPractico.modeldto.CompraByClienteDTO;
import com.example.TrabajoPractico.modelentity.Cliente;
import com.example.TrabajoPractico.services.ICompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.TrabajoPractico.modelentity.Compra;
import com.example.TrabajoPractico.modelrequest.CompraRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/compras")
public class CompraController {
    @Autowired
    private ICompraService compraService;

    @GetMapping("/")
    public ResponseEntity<?> getCompras(){
        try{
            List<Compra> c = compraService.findAll();
            return ResponseEntity.status(200).body(c);
        }catch (Exception e){
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error al obtener las compras: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCompraById(@PathVariable Long id){
        Compra c = compraService.findById(id);
        Map<String,String> response = new HashMap<>();
        if(c==null){
            response.put("message", "Compra no encontrada");
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.status(200).body(c);
    }

    @GetMapping("/cliente/{clientId}")
    public ResponseEntity<?> getComprasByCliente(@PathVariable Long clientId){
        try{
            List<CompraByClienteDTO> c = compraService.findByCliente(clientId);
            return ResponseEntity.status(200).body(c);
        }catch (Exception e){
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error al obtener las compras por cliente: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<?> registrarCompra(
            @PathVariable Long productoId,
            @RequestParam Long clienteId,
            @RequestParam Integer cantidad) {
        Map<String, String> response = new HashMap<>();
        try {
            Compra compra = compraService.registrarCompra(productoId, clienteId, cantidad);
            return ResponseEntity.status(200).body(compra);

        } catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(400).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompra(@PathVariable Long id) {
        Map<String,String> response = new HashMap<>();
        try {
            if (compraService.existsById(id)) {
                compraService.deleteById(id);
                response.put("message", "Compra eliminada exitosamente");
                return ResponseEntity.status(201).body(response);
            } else {
                response.put("message", "Compra no encontrado");
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            response.put("message", "Error al eliminar la compra: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}
