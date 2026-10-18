
package com.corporacionfavorita;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PruebaConexion {

    public static void main(String[] args) {
        EntityManagerFactory emf = null;

        try {
            emf = Persistence.createEntityManagerFactory("ProveedoresPU");

            System.out.println("¡Conexión con PostgreSQL exitosa!");
            System.out.println("Hibernate reconoce la unidad de persistencia.");

        } catch (Exception e) {
            System.err.println("Error al conectar con PostgreSQL:");
            e.printStackTrace();

        } finally {
            if (emf != null) {
                emf.close();
            }
        }
    }
}
