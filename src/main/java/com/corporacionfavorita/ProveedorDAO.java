
package com.corporacionfavorita;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class ProveedorDAO {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("ProveedoresPU");

    // CREATE: registrar un proveedor
    public void crear(Proveedor proveedor) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(proveedor);
            tx.commit();
            System.out.println("Proveedor registrado correctamente.");

        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;

        } finally {
            em.close();
        }
    }

    // READ: listar todos los proveedores
    public List<Proveedor> listar() {
        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery(
                    "SELECT p FROM Proveedor p ORDER BY p.id",
                    Proveedor.class
            ).getResultList();

        } finally {
            em.close();
        }
    }

    // READ: buscar un proveedor por su ID
    public Proveedor buscar(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            return em.find(Proveedor.class, id);

        } finally {
            em.close();
        }
    }

    // UPDATE: actualizar los datos de un proveedor
    public boolean actualizar(Proveedor proveedor) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Proveedor existente =
                    em.find(Proveedor.class, proveedor.getId());

            if (existente == null) {
                tx.rollback();
                return false;
            }

            existente.setNombre(proveedor.getNombre());
            existente.setRuc(proveedor.getRuc());
            existente.setTelefono(proveedor.getTelefono());
            existente.setCorreo(proveedor.getCorreo());

            tx.commit();
            return true;

        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;

        } finally {
            em.close();
        }
    }

    // DELETE: eliminar un proveedor por su ID
    public boolean eliminar(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Proveedor proveedor = em.find(Proveedor.class, id);

            if (proveedor == null) {
                tx.rollback();
                return false;
            }

            em.remove(proveedor);
            tx.commit();
            return true;

        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;

        } finally {
            em.close();
        }
    }

    // Cerrar la fábrica de conexiones al terminar el programa
    public static void cerrar() {
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
