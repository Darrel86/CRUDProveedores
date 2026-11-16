
package com.corporacionfavorita.dao;

import com.corporacionfavorita.model.Proveedor;
import com.corporacionfavorita.model.Producto;
import com.corporacionfavorita.model.Contrato;
import com.corporacionfavorita.model.Contacto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class ConsultasJPQL {

    private static final int TAMANIO_PAGINA = 5;

    public static void main(String[] args) {

        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("ProveedoresPU");
            em = emf.createEntityManager();

            System.out.println("\n====================================");
            System.out.println("     CONSULTAS JPQL - PROVEEDORES");
            System.out.println("====================================");

            // 1. Proveedores cuyo nombre contiene "comercial"
            buscarProveedorPorNombre(em, "comercial", 1);
            buscarProveedorPorNombre(em, "comercial", 2);

            // 2. Productos pertenecientes a la categoría Alimentos (ID 1)
            buscarProductosPorCategoria(em, 1, 1);
            buscarProductosPorCategoria(em, 1, 2);

            // 3. Contratos con estado Activo
            buscarContratosPorEstado(em, "Activo", 1);
            buscarContratosPorEstado(em, "Activo", 2);

            // 4. Listar todos los productos
            listarProductos(em);

            // 5. Contactos del proveedor Comercial Andina (ID 2)
            buscarContactosPorProveedor(em, 2);

        } catch (Exception e) {
            System.err.println("Error al ejecutar las consultas:");
            e.printStackTrace();

        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }

            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }

    // =========================================
    // 1. BUSCAR PROVEEDORES POR NOMBRE
    // =========================================

    public static void buscarProveedorPorNombre(
            EntityManager em, String nombre, int pagina) {

        System.out.println("\n====================================");
        System.out.println("PROVEEDORES POR NOMBRE");
        System.out.println("Página: " + pagina);
        System.out.println("====================================");

        String jpql =
                "SELECT p FROM Proveedor p " +
                "WHERE LOWER(p.nombre) LIKE LOWER(:nombre) " +
                "ORDER BY p.nombre ASC";

        List<Proveedor> proveedores = em.createQuery(
                jpql, Proveedor.class)
                .setParameter("nombre", "%" + nombre + "%")
                .setFirstResult((pagina - 1) * TAMANIO_PAGINA)
                .setMaxResults(TAMANIO_PAGINA)
                .getResultList();

        if (proveedores.isEmpty()) {
            System.out.println("No se encontraron proveedores en esta página.");
        } else {
            for (Proveedor p : proveedores) {
                System.out.println(
                        "ID: " + p.getId() +
                        " | Nombre: " + p.getNombre() +
                        " | RUC: " + p.getRuc() +
                        " | Teléfono: " + p.getTelefono()
                );
            }
        }

        System.out.println("Registros mostrados: " + proveedores.size());
    }

    // =========================================
    // 2. BUSCAR PRODUCTOS POR CATEGORÍA
    // =========================================

    public static void buscarProductosPorCategoria(
            EntityManager em, int categoriaId, int pagina) {

        System.out.println("\n====================================");
        System.out.println("PRODUCTOS POR CATEGORÍA");
        System.out.println("Página: " + pagina);
        System.out.println("====================================");

        String jpql =
                "SELECT p FROM Producto p " +
                "JOIN FETCH p.proveedor " +
                "JOIN FETCH p.categoria " +
                "WHERE p.categoria.id = :categoriaId " +
                "ORDER BY p.nombre ASC";

        List<Producto> productos = em.createQuery(
                jpql, Producto.class)
                .setParameter("categoriaId", categoriaId)
                .setFirstResult((pagina - 1) * TAMANIO_PAGINA)
                .setMaxResults(TAMANIO_PAGINA)
                .getResultList();

        if (productos.isEmpty()) {
            System.out.println("No se encontraron productos en esta página.");
        } else {
            for (Producto p : productos) {
                System.out.println(
                        "ID: " + p.getId() +
                        " | Producto: " + p.getNombre() +
                        " | Precio: $" + p.getPrecio() +
                        " | Stock: " + p.getStock() +
                        " | Proveedor: " + p.getProveedor().getNombre() +
                        " | Categoría: " + p.getCategoria().getNombre()
                );
            }
        }

        System.out.println("Registros mostrados: " + productos.size());
    }

    // =========================================
    // 3. BUSCAR CONTRATOS POR ESTADO
    // =========================================

    public static void buscarContratosPorEstado(
            EntityManager em, String estado, int pagina) {

        System.out.println("\n====================================");
        System.out.println("CONTRATOS POR ESTADO");
        System.out.println("Página: " + pagina);
        System.out.println("====================================");

        String jpql =
                "SELECT c FROM Contrato c " +
                "JOIN FETCH c.proveedor " +
                "WHERE LOWER(c.estado) = LOWER(:estado) " +
                "ORDER BY c.fechaInicio DESC";

        List<Contrato> contratos = em.createQuery(
                jpql, Contrato.class)
                .setParameter("estado", estado)
                .setFirstResult((pagina - 1) * TAMANIO_PAGINA)
                .setMaxResults(TAMANIO_PAGINA)
                .getResultList();

        if (contratos.isEmpty()) {
            System.out.println("No se encontraron contratos en esta página.");
        } else {
            for (Contrato c : contratos) {
                System.out.println(
                        "ID: " + c.getId() +
                        " | Número: " + c.getNumeroContrato() +
                        " | Estado: " + c.getEstado() +
                        " | Inicio: " + c.getFechaInicio() +
                        " | Fin: " + c.getFechaFin() +
                        " | Proveedor: " + c.getProveedor().getNombre()
                );
            }
        }

        System.out.println("Registros mostrados: " + contratos.size());
    }

    // =========================================
    // 4. LISTAR TODOS LOS PRODUCTOS
    // =========================================

    public static void listarProductos(EntityManager em) {

        System.out.println("\n====================================");
        System.out.println("LISTA COMPLETA DE PRODUCTOS");
        System.out.println("====================================");

        String jpql =
                "SELECT p FROM Producto p " +
                "JOIN FETCH p.proveedor " +
                "JOIN FETCH p.categoria " +
                "ORDER BY p.nombre ASC";

        List<Producto> productos = em.createQuery(
                jpql, Producto.class)
                .getResultList();

        for (Producto p : productos) {
            System.out.println(
                    "Producto: " + p.getNombre() +
                    " | Precio: $" + p.getPrecio() +
                    " | Proveedor: " + p.getProveedor().getNombre() +
                    " | Categoría: " + p.getCategoria().getNombre()
            );
        }

        System.out.println("Total de productos: " + productos.size());
    }

    // =========================================
    // 5. BUSCAR CONTACTOS POR PROVEEDOR
    // =========================================

    public static void buscarContactosPorProveedor(
            EntityManager em, int proveedorId) {

        System.out.println("\n====================================");
        System.out.println("CONTACTOS DEL PROVEEDOR");
        System.out.println("====================================");

        String jpql =
                "SELECT c FROM Contacto c " +
                "JOIN FETCH c.proveedor " +
                "WHERE c.proveedor.id = :proveedorId " +
                "ORDER BY c.nombre ASC";

        List<Contacto> contactos = em.createQuery(
                jpql, Contacto.class)
                .setParameter("proveedorId", proveedorId)
                .getResultList();

        if (contactos.isEmpty()) {
            System.out.println("No se encontraron contactos.");
        } else {
            for (Contacto c : contactos) {
                System.out.println(
                        "Nombre: " + c.getNombre() +
                        " | Teléfono: " + c.getTelefono() +
                        " | Correo: " + c.getCorreo() +
                        " | Cargo: " + c.getCargo() +
                        " | Proveedor: " + c.getProveedor().getNombre()
                );
            }
        }

        System.out.println("Total de contactos: " + contactos.size());
    }
}
