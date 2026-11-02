
package com.corporacionfavorita;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "contactos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 20)
    private String telefono;

    @Column(length = 120)
    private String correo;

    @Column(length = 80)
    private String cargo;

    // Muchos contactos pueden pertenecer a un proveedor
    @ManyToOne
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedor proveedor;
}
