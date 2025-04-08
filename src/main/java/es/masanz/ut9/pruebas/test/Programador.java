package es.masanz.ut9.pruebas.test;

public class Programador extends Trabajador {

    public int edad;

    public Programador(int edad) {
        super(edad);
        this.edad = this.edad + 10;
    }
}