package com.example.TrabajoPractico.modelentity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Entity
@Table(name="Producto")
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotNull
    private String nombre;
    @Column
    private String descripcion;
    @Column
    private Double precio;
    @Column
    private Integer cantidadStock;
    @OneToMany(mappedBy = "producto")
    @JsonManagedReference
    @JsonIgnore
    private List<Compra> compras;
}
