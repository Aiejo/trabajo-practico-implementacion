package com.example.TrabajoPractico.modeldto;
import java.time.LocalDate;
import lombok.Data;

@Data
public class CompraByClienteDTO {
    private Long id;
    private LocalDate fechaCompra;
    private String nombreProducto;
    private Long idProducto;
    private Integer cantidadComprada;
}
