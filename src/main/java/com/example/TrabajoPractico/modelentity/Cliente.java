package com.example.TrabajoPractico.modelentity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name="Cliente")
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotNull
    private String nombre;
    @Column
    private String direccion;
    @Column
    @Email(message = "El email debe ser válido")
    private String email;
    @Column
    private String telefono;
    @OneToMany(mappedBy = "cliente")
    @JsonManagedReference
    @JsonIgnore
    private List<Compra> compras;
}
