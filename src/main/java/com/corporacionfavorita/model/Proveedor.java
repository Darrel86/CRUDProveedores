
package com.corporacionfavorita.model;

import com.corporacionfavorita.model.Producto;
import com.corporacionfavorita.model.Contrato;
import com.corporacionfavorita.model.Contacto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Table(name = "proveedores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 120)
    private String nombre;

    @Column(nullable = false, unique = true, length = 13)
    private String ruc;

    private String telefono;

    private String correo;

    // Relación: un proveedor puede ofrecer muchos productos
    @OneToMany(mappedBy = "proveedor")
    private List<Producto> productos;

    // Relación: un proveedor puede tener varios contactos
    @OneToMany(mappedBy = "proveedor")
    private List<Contacto> contactos;

    // Relación: un proveedor puede tener varios contratos
    @OneToMany(mappedBy = "proveedor")
    private List<Contrato> contratos;

    // Constructor para registrar proveedores desde el CRUD anterior
    public Proveedor(String nombre, String ruc,
                     String telefono, String correo) {
        this.nombre = nombre;
        this.ruc = ruc;
        this.telefono = telefono;
        this.correo = correo;
    }
}
