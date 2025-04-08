package es.masanz.ut9.pruebas;

import io.javalin.http.Context;

import java.util.HashMap;

public class Persona {

    private String nombre;
    private int edad;

    public Persona(String nombre, int edad, HashMap<String, Object> model) {
        this.nombre = nombre;
        this.edad = edad;
        model.put("nombre", edad);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
