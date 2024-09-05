package com.example.TrabajoPractico.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.example.TrabajoPractico.modelentity.Producto;
import com.example.TrabajoPractico.services.IProductoService;
import java.util.*;

@RestController()
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private IProductoService productoService;

    @GetMapping("/")
    public ResponseEntity<?> getProducto(){
        try{
            List<Producto> p = productoService.findAll();
            return ResponseEntity.status(200).body(p);
        }catch (Exception e){
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error al obtener los productos: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductoById(@PathVariable Long id) {
        Producto p = productoService.findById(id);
        Map<String,String> response = new HashMap<>();
        if(p==null){
            response.put("message", "Producto no encontrado");
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.status(200).body(p);
    }

    @PostMapping("/")
    public ResponseEntity<?> postProducto(@RequestBody Producto producto){
        Map<String, String> response = new HashMap<>();
        try{
            productoService.save(producto);
            response.put("message", "Producto guardado con éxito");
            return ResponseEntity.status(201).body(response);
        }catch(Exception e){
            response.put("message", "Error al agregar el producto: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id) {
        Map<String,String> response = new HashMap<>();
        try {
            if (productoService.existsById(id)) {
                productoService.deleteById(id);
                response.put("message", "Producto eliminado exitosamente");
                return ResponseEntity.status(201).body(response);
            } else {
                response.put("message", "Producto no encontrado");
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            response.put("message", "Error al eliminar el producto: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
    // Puedo hacerlo con patch
    @PatchMapping("/{id}")
    public ResponseEntity<?> patchProducto(@RequestBody Producto producto, @PathVariable Long id) {
        Map<String,String> response = new HashMap<>();
        try{
            Producto p = productoService.findById(id);
            if(p==null){
                response.put("message", "Producto no encontrado");
                return ResponseEntity.status(404).body(response);
            }
            if(producto.getNombre()!=null){
                p.setNombre(producto.getNombre());
            }
            if(producto.getDescripcion()!=null){
                p.setDescripcion(producto.getDescripcion());
            }
            if(producto.getPrecio()!=null){
                p.setPrecio(producto.getPrecio());
            }
            if(producto.getCantidadStock()!=null){
                p.setCantidadStock(producto.getCantidadStock());
            }
            productoService.save(p);
            return ResponseEntity.status(200).body(p);
        }catch (Exception e){
            response.put("message", "Error al actualizar el producto: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> putProducto(@RequestBody Producto producto, @PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            Producto p = productoService.findById(id);
            if (p == null) {
                response.put("message", "Producto no encontrado");
                return ResponseEntity.status(404).body(response);
            }

            // Reemplazar completamente los campos del producto existente
            p.setNombre(producto.getNombre());
            p.setDescripcion(producto.getDescripcion());
            p.setPrecio(producto.getPrecio());
            p.setCantidadStock(producto.getCantidadStock());

            productoService.save(p);
            return ResponseEntity.status(200).body(p);
        } catch (Exception e) {
            response.put("message", "Error al actualizar el producto: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}
