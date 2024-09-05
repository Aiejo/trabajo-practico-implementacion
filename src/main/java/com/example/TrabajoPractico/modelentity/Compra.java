package com.example.TrabajoPractico.modelentity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;

@Entity
@Table(name="Compra")
@Data
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaCompra;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonManagedReference
    private Cliente cliente;

    @ManyToOne()
    @JoinColumn(name = "producto_id")
    @JsonManagedReference
    private Producto producto;

    private Integer cantidadComprada;
}
