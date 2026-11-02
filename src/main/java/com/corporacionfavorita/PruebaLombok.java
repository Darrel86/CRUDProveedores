package com.corporacionfavorita;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PruebaLombok {

    private String nombre;
    private int edad;

    public static void main(String[] args) {

        PruebaLombok prueba = new PruebaLombok();

        prueba.setNombre("Darrel");
        prueba.setEdad(25);

        System.out.println(prueba.getNombre());
        System.out.println(prueba.getEdad());
    }
}