
package com.corporacionfavorita;

import jakarta.persistence.*;
import java.util.List;

public class ConsultasJPQL {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("ProveedoresPU");

        EntityManager em = emf.createEntityManager();

        try {

            // 1. Listar todos los proveedores
            TypedQuery<Proveedor> consulta = em.createQuery(
                    "SELECT p FROM Proveedor p ORDER BY p.nombre",
                    Proveedor.class
            );

            List<Proveedor> proveedores = consulta.getResultList();

            System.out.println("=== LISTA DE PROVEEDORES ===");

            for (Proveedor p : proveedores) {
                System.out.println(p);
            }

            // 2. Buscar proveedores por nombre
            buscarProveedorPorNombre(em, "comercial");

            // 3. Listar productos con proveedor y categoría
            listarProductos(em);

            // 4. Buscar contactos por proveedor
            buscarContactosPorProveedor(em, 1);

            // 5. Consultar contratos por estado
            buscarContratosPorEstado(em, "Activo");

        } finally {
            em.close();
            emf.close();
        }
    }

    // 2. Buscar proveedores por nombre
    public static void buscarProveedorPorNombre(
            EntityManager em, String nombre) {

        TypedQuery<Proveedor> consulta = em.createQuery(
                "SELECT p FROM Proveedor p "
                + "WHERE LOWER(p.nombre) LIKE LOWER(:nombre)",
                Proveedor.class
        );

        consulta.setParameter("nombre", "%" + nombre + "%");

        List<Proveedor> resultados = consulta.getResultList();

        System.out.println("\n=== RESULTADOS DE BUSQUEDA ===");

        for (Proveedor p : resultados) {
            System.out.println(p);
        }
    }

    // 3. Listar productos con proveedor y categoría
    public static void listarProductos(EntityManager em) {

        TypedQuery<Producto> consulta = em.createQuery(
                "SELECT p FROM Producto p "
                + "JOIN FETCH p.proveedor "
                + "JOIN FETCH p.categoria "
                + "ORDER BY p.nombre",
                Producto.class
        );

        List<Producto> productos = consulta.getResultList();

        System.out.println("\n=== LISTA DE PRODUCTOS ===");

        for (Producto p : productos) {
            System.out.println("Producto: " + p.getNombre());
            System.out.println("Precio: " + p.getPrecio());
            System.out.println("Stock: " + p.getStock());
            System.out.println("Proveedor: " + p.getProveedor().getNombre());
            System.out.println("Categoría: " + p.getCategoria().getNombre());
            System.out.println("----------------------------");
        }
    }

    // 4. Buscar contactos por proveedor
    public static void buscarContactosPorProveedor(
            EntityManager em, Integer proveedorId) {

        TypedQuery<Contacto> consulta = em.createQuery(
                "SELECT c FROM Contacto c "
                + "JOIN FETCH c.proveedor "
                + "WHERE c.proveedor.id = :id",
                Contacto.class
        );

        consulta.setParameter("id", proveedorId);

        List<Contacto> contactos = consulta.getResultList();

        System.out.println("\n=== CONTACTOS DEL PROVEEDOR "
                + proveedorId + " ===");

        for (Contacto c : contactos) {
            System.out.println("Nombre: " + c.getNombre());
            System.out.println("Teléfono: " + c.getTelefono());
            System.out.println("Correo: " + c.getCorreo());
            System.out.println("Cargo: " + c.getCargo());
            System.out.println("Proveedor: " + c.getProveedor().getNombre());
            System.out.println("----------------------------");
        }
    }

    // 5. Buscar contratos por estado
    public static void buscarContratosPorEstado(
            EntityManager em, String estado) {

        TypedQuery<Contrato> consulta = em.createQuery(
                "SELECT c FROM Contrato c "
                + "JOIN FETCH c.proveedor "
                + "WHERE LOWER(c.estado) = LOWER(:estado)",
                Contrato.class
        );

        consulta.setParameter("estado", estado);

        List<Contrato> contratos = consulta.getResultList();

        System.out.println("\n=== CONTRATOS CON ESTADO: "
                + estado + " ===");

        for (Contrato c : contratos) {
            System.out.println("Número: " + c.getNumeroContrato());
            System.out.println("Fecha de inicio: " + c.getFechaInicio());
            System.out.println("Fecha de fin: " + c.getFechaFin());
            System.out.println("Estado: " + c.getEstado());
            System.out.println("Proveedor: " + c.getProveedor().getNombre());
            System.out.println("----------------------------");
        }
    }
}

