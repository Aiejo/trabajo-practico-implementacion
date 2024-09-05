package com.example.TrabajoPractico.modelrequest;

import lombok.Data;

@Data
public class CompraRequest {
    private Long clienteId;
    private Long productoId;
    private Integer cantidad;
}
