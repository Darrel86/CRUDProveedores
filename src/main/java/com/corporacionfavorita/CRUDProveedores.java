
package com.corporacionfavorita;

import java.util.List;
import java.util.Scanner;

public class CRUDProveedores {

    private static final Scanner sc = new Scanner(System.in);
    private static final ProveedorDAO dao = new ProveedorDAO();

    public static void main(String[] args) {

        int opcion = -1;

        try {
            do {
                System.out.println("\n===== CRUD DE PROVEEDORES =====");
                System.out.println("1. Registrar proveedor");
                System.out.println("2. Listar proveedores");
                System.out.println("3. Buscar proveedor por ID");
                System.out.println("4. Actualizar proveedor");
                System.out.println("5. Eliminar proveedor");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");

                try {
                    opcion = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    opcion = -1;
                }

                try {
                    switch (opcion) {
                        case 1 -> registrar();
                        case 2 -> listar();
                        case 3 -> buscar();
                        case 4 -> actualizar();
                        case 5 -> eliminar();
                        case 0 -> System.out.println("Programa finalizado.");
                        default -> System.out.println(
                                "Opción no válida. Intente nuevamente.");
                    }
                } catch (Exception e) {
                    System.out.println(
                            "No se pudo completar la operación: "
                            + e.getMessage());
                }

            } while (opcion != 0);

        } finally {
            ProveedorDAO.cerrar();
            sc.close();
        }
    }

    private static void registrar() {
        System.out.println("\n--- Registrar proveedor ---");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();

        System.out.print("RUC: ");
        String ruc = sc.nextLine().trim();

        System.out.print("Teléfono: ");
        String telefono = sc.nextLine().trim();

        System.out.print("Correo: ");
        String correo = sc.nextLine().trim();

        if (nombre.isEmpty() || ruc.isEmpty()) {
            System.out.println("El nombre y el RUC son obligatorios.");
            return;
        }

        Proveedor proveedor =
                new Proveedor(nombre, ruc, telefono, correo);

        dao.crear(proveedor);
    }

    private static void listar() {
        System.out.println("\n--- Lista de proveedores ---");

        List<Proveedor> proveedores = dao.listar();

        if (proveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
            return;
        }

        for (Proveedor proveedor : proveedores) {
            System.out.println(proveedor);
        }
    }

    private static void buscar() {
        System.out.println("\n--- Buscar proveedor ---");

        System.out.print("Ingrese el ID: ");
        int id = leerId();

        Proveedor proveedor = dao.buscar(id);

        if (proveedor == null) {
            System.out.println("No se encontró ese proveedor.");
        } else {
            System.out.println(proveedor);
        }
    }

    private static void actualizar() {
        System.out.println("\n--- Actualizar proveedor ---");

        System.out.print("Ingrese el ID del proveedor: ");
        int id = leerId();

        Proveedor existente = dao.buscar(id);

        if (existente == null) {
            System.out.println("No se encontró ese proveedor.");
            return;
        }

        System.out.println("Datos actuales: " + existente);

        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine().trim();

        System.out.print("Nuevo RUC: ");
        String ruc = sc.nextLine().trim();

        System.out.print("Nuevo teléfono: ");
        String telefono = sc.nextLine().trim();

        System.out.print("Nuevo correo: ");
        String correo = sc.nextLine().trim();

        if (nombre.isEmpty() || ruc.isEmpty()) {
            System.out.println("El nombre y el RUC son obligatorios.");
            return;
        }

        existente.setNombre(nombre);
        existente.setRuc(ruc);
        existente.setTelefono(telefono);
        existente.setCorreo(correo);

        if (dao.actualizar(existente)) {
            System.out.println("Proveedor actualizado correctamente.");
        } else {
            System.out.println("No se pudo actualizar el proveedor.");
        }
    }

    private static void eliminar() {
        System.out.println("\n--- Eliminar proveedor ---");

        System.out.print("Ingrese el ID del proveedor: ");
        int id = leerId();

        Proveedor proveedor = dao.buscar(id);

        if (proveedor == null) {
            System.out.println("No se encontró ese proveedor.");
            return;
        }

        System.out.println("Proveedor seleccionado: " + proveedor);
        System.out.print("¿Seguro que desea eliminarlo? (S/N): ");

        String confirmacion = sc.nextLine().trim();

        if (confirmacion.equalsIgnoreCase("S")) {
            if (dao.eliminar(id)) {
                System.out.println("Proveedor eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar el proveedor.");
            }
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }

    private static int leerId() {
        while (true) {
            try {
                int id = Integer.parseInt(sc.nextLine().trim());

                if (id > 0) {
                    return id;
                }

                System.out.print("El ID debe ser mayor que cero: ");

            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número válido: ");
            }
        }
    }
}
